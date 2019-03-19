package com.invillia.acme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.entity.Payment;
import com.invillia.acme.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@RequestMapping(value = "/payment/order/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Payment> getPaymentByOrderPurchaseId(@PathVariable Integer id) {
		return ResponseEntity.ok(this.paymentService.getPaymentByOrderPurchaseId(id));
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
		return ResponseEntity.ok(this.paymentService.save(payment));
	}

	@RequestMapping(value = "/payment/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public void deletePayment(@PathVariable Integer id) {
		Payment payment = new Payment();
		payment.setId(id);
		this.paymentService.delete(payment);
	}
}
