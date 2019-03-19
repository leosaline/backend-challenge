package com.invillia.acme.serviceimp;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.entity.OrderItem;
import com.invillia.acme.entity.OrderPurchase;
import com.invillia.acme.repository.OrderRepository;
import com.invillia.acme.service.OrderService;

@Service
public class OrderServiceImp implements OrderService {
	@Autowired
	OrderRepository orderRepository;

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
	public OrderPurchase refundOrderPurchase(OrderPurchase orderPurchase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItem refundOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return null;
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
