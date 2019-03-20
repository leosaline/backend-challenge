package com.invillia.acme.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.acme.entity.Payment;
import com.invillia.acme.enumerator.Status;
import com.invillia.acme.service.PaymentService;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

	@Autowired
	private MockMvc mvc;

	private Payment payment;

	@MockBean
	private PaymentService service;

	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void beforeMethod() {
		payment = new Payment();
		payment.setId(1);
		payment.setStatus(Status.INITIAL);
		payment.setCreditCardNumber(new Long(123456));
	}

	@Test
	public void whenGetPaymentByOrderPurchaseIdThenReturnPayment() throws Exception {
		when(service.getPaymentByOrderPurchaseId(1)).thenReturn(payment);

		mvc.perform(get("/payment/order/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(payment.getId())));
	}

	@Test
	public void whenCreatePaymentThenReturnNewPaymento() throws Exception {
		Payment paymentResult = new Payment();
		paymentResult.setId(1);

		when(service.save(payment)).thenReturn(paymentResult);

		mvc.perform(post("/payment").content(mapper.writeValueAsString(payment))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(payment.getId())));
	}

	@Test
	public void whenDeletePaymentThenReturnNothing() throws Exception {
		when(service.save(payment)).thenReturn(null);

		mvc.perform(delete("/payment/1").content(mapper.writeValueAsString(payment))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk());
	}

}
