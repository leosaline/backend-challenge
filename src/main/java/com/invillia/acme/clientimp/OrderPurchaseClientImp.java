package com.invillia.acme.clientimp;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.invillia.acme.client.OrderPurchaseClient;
import com.invillia.acme.entity.OrderPurchase;

@Component
public class OrderPurchaseClientImp implements OrderPurchaseClient {

	private static final String URL_CALL_ORDER_PURCHASE = "http://localhost:8080/";
	private static final String ORDER = "order";
	private static final String UPDATE = "update";
	private RestTemplate restTemplate;

	public OrderPurchaseClientImp() {
		restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(requestFactory);
	}

	public OrderPurchase getOrderPurchase(Integer id) {
		return restTemplate.getForObject(URL_CALL_ORDER_PURCHASE + ORDER + "/" + id, OrderPurchase.class);
	}

	public OrderPurchase updateOrderPurchase(OrderPurchase orderPurchase) {
		return restTemplate.patchForObject(URL_CALL_ORDER_PURCHASE + ORDER + "/" + UPDATE, orderPurchase,
				OrderPurchase.class);
	}
}
