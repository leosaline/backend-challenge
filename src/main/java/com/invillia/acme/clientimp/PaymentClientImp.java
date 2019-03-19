package com.invillia.acme.clientimp;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.invillia.acme.client.PaymentClient;
import com.invillia.acme.entity.Payment;

@Component
public class PaymentClientImp implements PaymentClient {
	private static final String URL_CALL_PAYMENT = "http://localhost:8080/";
	private static final String PAYMENT = "payment";
	private static final String ORDER = "order";
	private RestTemplate restTemplate;

	public PaymentClientImp() {
		restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(requestFactory);
	}

	@Async
	@Override
	public CompletableFuture<Payment> getPayment(Integer id) {
		Payment payment = restTemplate.getForObject(URL_CALL_PAYMENT + PAYMENT + "/" + ORDER + "/" + id, Payment.class);
		return CompletableFuture.completedFuture(payment);
	}

	@Override
	public void deletePayment(Integer id) {
		restTemplate.delete(URL_CALL_PAYMENT + PAYMENT + "/" + id);
	}

}
