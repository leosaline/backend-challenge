package com.invillia.acme.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
				.andExpect(status().isOk());

	}

}
