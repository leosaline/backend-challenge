package com.invillia.acme.serviceimp;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.invillia.acme.client.PaymentClient;
import com.invillia.acme.entity.OrderItem;
import com.invillia.acme.entity.OrderPurchase;
import com.invillia.acme.entity.Payment;
import com.invillia.acme.enumerator.Status;
import com.invillia.acme.repository.OrderRepository;
import com.invillia.acme.service.OrderService;

@Service
public class OrderServiceImp implements OrderService {
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	PaymentClient paymentClient;

	@Override
	public OrderPurchase save(OrderPurchase order) {
		return orderRepository.save(order);
	}

	@Override
	public Collection<OrderPurchase> findByParameter(OrderPurchase order) {
		Set<OrderPurchase> listOrder = new HashSet<OrderPurchase>();

		if (order.getAddress() != null) {
			listOrder.addAll(orderRepository.findOrderByAddress(order.getAddress()));
		}
		if (order.getConfirmationDate() != null) {
			listOrder.addAll(orderRepository.findOrderByConfirmationDate(order.getConfirmationDate()));
		}
		if (order.getStatus() != null) {
			listOrder.addAll(orderRepository.findOrderByStatus(order.getStatus()));
		}

		return listOrder;
	}

	@Override
	public OrderPurchase refundOrderPurchase(Integer id) {
		Optional<OrderPurchase> orderPurchase = orderRepository.findById(id);
		Payment payment = null;

		if (orderPurchase.isPresent()) {
			try {
				CompletableFuture<Payment> cft = paymentClient.getPayment(orderPurchase.get().getId());
				CompletableFuture.allOf(cft).join();
				payment = cft.get();
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Problem during execution!");
			}

			if (payment == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found!");
			}

			LocalDate now = LocalDate.now();
			LocalDate tenDaysBehind = new java.sql.Date(orderPurchase.get().getConfirmationDate().getTime())
					.toLocalDate();
			Period period = Period.between(now, tenDaysBehind);

			if (Status.CONCLUDED.equals(payment.getStatus()) && (period.getDays() < 10)) {
				paymentClient.deletePayment(payment.getId());
				return orderPurchase.get();
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Refund not permited!");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!");
		}
	}

	@Override
	public OrderItem refundOrderItem(OrderItem orderItem) {
		OrderPurchase orderPurchase = orderRepository.findOrderPurchaseByOrderItemId(orderItem.getId());
		this.refundOrderPurchase(orderPurchase.getId());
		return orderItem;
	}

	@Override
	public OrderPurchase getOrderPurchaseById(Integer id) {
		return orderRepository.getOne(id);
	}

	@Override
	public OrderPurchase updateOrderPurchase(OrderPurchase order) {
		return orderRepository.save(order);
	}

}
