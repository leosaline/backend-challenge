package com.invillia.acme.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.client.OrderPurchaseClient;
import com.invillia.acme.entity.OrderPurchase;
import com.invillia.acme.entity.Payment;
import com.invillia.acme.enumerator.Status;
import com.invillia.acme.repository.PaymentRepository;
import com.invillia.acme.service.PaymentService;

@Service
public class PaymentServiceImp implements PaymentService {
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	OrderPurchaseClient orderPurchaseClient;

	@Override
	public Payment save(Payment payment) {
		OrderPurchase orderPurchase = orderPurchaseClient.getOrderPurchase(payment.getOrderPurchase());
		orderPurchase.setStatus(Status.CONCLUDED);

		orderPurchaseClient.updateOrderPurchase(orderPurchase);

		return paymentRepository.save(payment);
	}

	@Override
	public Payment getPaymentByOrderPurchaseId(Integer id) {
		return paymentRepository.findPaymentByOrderPurchaseId(id);
	}

	@Override
	public void delete(Payment payment) {
		paymentRepository.delete(payment);
	}

}
