package com.invillia.acme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping(value = "/payment", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
		return ResponseEntity.ok(this.paymentService.save(payment));
	}
}
