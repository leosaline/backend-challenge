package com.invillia.acme.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.acme.entity.OrderItem;
import com.invillia.acme.entity.OrderPurchase;
import com.invillia.acme.enumerator.Status;
import com.invillia.acme.service.OrderService;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private OrderService service;

	private OrderPurchase order;

	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void beforeMethod() {
		order = new OrderPurchase();
		order.setId(1);
		order.setAddress("address");
		order.setConfirmationDate(new Date());
		order.setStatus(Status.INITIAL);
	}

	@Test
	public void whenGetOrderPurchaseByIdThenReturnOrderPurchase() throws Exception {
		when(service.getOrderPurchaseById(1)).thenReturn(order);

		mvc.perform(get("/order/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.address", is(order.getAddress())));
	}

	@Test
	public void whenCreateOrderThenReturnOrder() throws Exception {
		OrderPurchase orderResult = new OrderPurchase();
		orderResult.setAddress("address");

		when(service.save(order)).thenReturn(orderResult);

		mvc.perform(post("/order").content(mapper.writeValueAsString(order)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.address", is(order.getAddress())));

	}

	@Test
	public void whenGetOrderByParameterThenReturnOrderPurchase() throws Exception {
		Collection<OrderPurchase> collOrder = new ArrayList<OrderPurchase>();
		OrderPurchase orderAdd = new OrderPurchase();
		orderAdd.setId(1);
		orderAdd.setAddress("address");
		orderAdd.setStatus(Status.INITIAL);
		collOrder.add(orderAdd);

		when(service.findByParameter(order)).thenReturn(collOrder);

		mvc.perform(get("/order").param("address", "address").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void whenUpdateOrderPurchaseThenReturnOrderUpdated() throws Exception {
		OrderPurchase orderUpdate = new OrderPurchase();
		orderUpdate.setAddress("altered");
		orderUpdate.setId(1);

		when(service.updateOrderPurchase(order)).thenReturn(orderUpdate);

		mvc.perform(patch("/order/update").content(mapper.writeValueAsString(order))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.address", is(orderUpdate.getAddress())));
	}

	@Test
	public void whenRefundOrderPurchaseThenReturnOrderRefunded() throws Exception {
		OrderPurchase orderUpdate = new OrderPurchase();
		orderUpdate.setAddress("address");
		orderUpdate.setId(1);

		when(service.refundOrderPurchase(1)).thenReturn(orderUpdate);

		mvc.perform(patch("/order/refund/order/1").content(mapper.writeValueAsString(order))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.address", is(orderUpdate.getAddress())));
	}

	@Test
	public void whenRefundOrderItemThenReturnOrderRefunded() throws Exception {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(1);

		OrderItem orderItemRefunded = new OrderItem();
		orderItemRefunded.setDescription("description");
		orderItemRefunded.setId(1);

		when(service.refundOrderItem(orderItem)).thenReturn(orderItemRefunded);

		mvc.perform(patch("/order/refund/orderitem").content(mapper.writeValueAsString(orderItem))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.description", is(orderItemRefunded.getDescription())));
	}
}
