package com.invillia.acme.client;

import com.invillia.acme.entity.OrderPurchase;

public interface OrderPurchaseClient {
	public OrderPurchase getOrderPurchase(Integer id);

	public OrderPurchase updateOrderPurchase(OrderPurchase orderPurchase);
}
