package com.invillia.acme.controller;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.entity.OrderItem;
import com.invillia.acme.entity.OrderPurchase;
import com.invillia.acme.enumerator.Status;
import com.invillia.acme.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<OrderPurchase> createOrder(@RequestBody OrderPurchase order) {
		return ResponseEntity.ok(this.orderService.save(order));
	}

	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<OrderPurchase> getOrderPurchaseById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.orderService.getOrderPurchaseById(id));
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Collection<OrderPurchase>> getOrderByParameter(
			@RequestParam("confirmationDate") Date confirmationDate, @RequestParam("address") String address,
			@RequestParam("status") Status status) {
		OrderPurchase order = new OrderPurchase();
		order.setConfirmationDate(confirmationDate);
		order.setAddress(address);
		order.setStatus(status);

		return ResponseEntity.ok(this.orderService.findByParameter(order));
	}
	
	@RequestMapping(value = "/order/update", method = RequestMethod.PATCH, produces = "application/json")
	@ResponseBody
	public ResponseEntity<OrderPurchase> updateOrderPurchase(@RequestBody OrderPurchase order) {
		return ResponseEntity.ok(this.orderService.updateOrderPurchase(order));
	}	

	@RequestMapping(value = "/order/refund/order", method = RequestMethod.PATCH, produces = "application/json")
	@ResponseBody
	public ResponseEntity<OrderPurchase> refundOrderPurchase(@RequestBody OrderPurchase order) {
		return ResponseEntity.ok(this.orderService.refundOrderPurchase(order));
	}

	@RequestMapping(value = "/order/refund/orderitem", method = RequestMethod.PATCH, produces = "application/json")
	@ResponseBody
	public ResponseEntity<OrderItem> refundOrderItem(@RequestBody OrderItem orderItem) {
		return ResponseEntity.ok(this.orderService.refundOrderItem(orderItem));
	}
}
