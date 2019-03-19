package com.invillia.acme.service;

import java.util.Collection;

import com.invillia.acme.entity.OrderItem;
import com.invillia.acme.entity.OrderPurchase;

public interface OrderService {

	public OrderPurchase save(OrderPurchase order);

	public Collection<OrderPurchase> findByParameter(OrderPurchase order);

	public OrderPurchase refundOrderPurchase(OrderPurchase orderPurchase);

	public OrderItem refundOrderItem(OrderItem orderItem);

	public OrderPurchase getOrderPurchaseById(Integer id);

	public OrderPurchase updateOrderPurchase(OrderPurchase order);
}
