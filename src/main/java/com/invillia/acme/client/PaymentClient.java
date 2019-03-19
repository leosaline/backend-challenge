package com.invillia.acme.client;

import java.util.concurrent.CompletableFuture;

import com.invillia.acme.entity.Payment;

public interface PaymentClient {
	public CompletableFuture<Payment> getPayment(Integer id);
	
	public void deletePayment(Integer integer);

}
