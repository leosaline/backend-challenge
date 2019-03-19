package com.invillia.acme.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

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
	OrderServiceImp orderServiceImp;

	@Test
	public void saveOrderPurchase() {
		OrderPurchase order = new OrderPurchase();
		order.setId(1);
		order.setAddress("address");
		order.setConfirmationDate(new Date());
		order.setStatus(Status.INITIAL);

		OrderPurchase orderSaved = orderServiceImp.save(order);

		assertThat(orderSaved.getId()).isEqualTo(order.getId());
	}
	
	@Test
	public void updateOrderPurchase() {
		OrderPurchase order = new OrderPurchase();
		order.setId(1);
		order.setAddress("address");
		order.setConfirmationDate(new Date());
		order.setStatus(Status.INITIAL);

		orderServiceImp.save(order);
		order.setAddress("altered");
		OrderPurchase orderSaved = orderServiceImp.updateOrderPurchase(order);
		
		assertThat(orderSaved.getAddress()).isEqualTo("altered");
	}
}
