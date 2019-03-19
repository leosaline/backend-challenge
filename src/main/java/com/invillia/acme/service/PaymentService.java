package com.invillia.acme.service;

import com.invillia.acme.entity.Payment;

public interface PaymentService {

	public Payment save(Payment payment);

	public Payment getPaymentByOrderPurchaseId(Integer id);

	public void delete(Payment payment);

}
