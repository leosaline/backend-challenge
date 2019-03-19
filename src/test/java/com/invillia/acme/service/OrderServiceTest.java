package com.invillia.acme.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.invillia.acme.entity.OrderPurchase;
import com.invillia.acme.enumerator.Status;
import com.invillia.acme.serviceimp.OrderServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

	@Autowired
	private OrderServiceImp orderServiceImp;
	private OrderPurchase order;

	@Before
	public void beforeMethod() {
		order = new OrderPurchase();
		order.setId(1);
		order.setAddress("address");
		order.setConfirmationDate(new Date());
		order.setStatus(Status.INITIAL);
	}

	@Test
	public void saveOrderPurchase() {
		OrderPurchase orderSaved = orderServiceImp.save(order);

		assertThat(orderSaved.getId()).isEqualTo(order.getId());
	}

	@Test
	public void updateOrderPurchase() {
		orderServiceImp.save(order);
		order.setAddress("altered");
		OrderPurchase orderSaved = orderServiceImp.updateOrderPurchase(order);

		assertThat(orderSaved.getAddress()).isEqualTo("altered");
	}

	@Test
	public void findOrderPurchaseByParameter() {
		orderServiceImp.save(order);
		OrderPurchase orderParam = new OrderPurchase();
		orderParam.setAddress("address");

		OrderPurchase orderSaved = orderServiceImp.findByParameter(orderParam).iterator().next();

		assertThat(orderSaved.getId()).isEqualTo(order.getId());
	}

	@Test
	public void getOrderPurchaseById() {
		orderServiceImp.save(order);

		OrderPurchase orderSaved = orderServiceImp.getOrderPurchaseById(1);

		assertThat(orderSaved.getId()).isEqualTo(order.getId());
	}
}
