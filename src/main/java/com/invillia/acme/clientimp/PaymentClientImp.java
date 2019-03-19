package com.invillia.acme.clientimp;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.invillia.acme.client.PaymentClient;
import com.invillia.acme.entity.Payment;

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

	@Override
	public Payment getPayment(Integer id) {
		return restTemplate.getForObject(URL_CALL_PAYMENT + PAYMENT + "/" + ORDER + "/" + id, Payment.class);
	}

	@Override
	public void deletePayment(Integer id) {
		restTemplate.delete(URL_CALL_PAYMENT + PAYMENT + "/" + id);
	}

}
