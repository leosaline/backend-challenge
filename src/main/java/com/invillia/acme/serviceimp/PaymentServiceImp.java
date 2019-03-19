package com.invillia.acme.serviceimp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.invillia.acme.entity.OrderPurchase;
import com.invillia.acme.entity.Payment;
import com.invillia.acme.enumerator.Status;
import com.invillia.acme.repository.PaymentRepository;
import com.invillia.acme.service.PaymentService;

@Service
public class PaymentServiceImp implements PaymentService {
	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Payment save(Payment payment) {
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(requestFactory);

		OrderPurchase orderPurchase = restTemplate
				.getForObject("http://localhost:8080/order/" + payment.getOrderPurchase(), OrderPurchase.class);

		orderPurchase.setConfirmationDate(new Date());
		orderPurchase.setStatus(Status.CONCLUDED);

		restTemplate.patchForObject("http://localhost:8080/order/update", orderPurchase, OrderPurchase.class);

		return paymentRepository.save(payment);
	}

}
