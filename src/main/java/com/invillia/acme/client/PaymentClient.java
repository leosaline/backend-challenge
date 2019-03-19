package com.invillia.acme.client;

import com.invillia.acme.entity.Payment;

public interface PaymentClient {
	public Payment getPayment(Integer id);
	
	public void deletePayment(Integer integer);

}
