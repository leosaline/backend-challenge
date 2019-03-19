package com.invillia.acme.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

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
			@RequestParam("confirmationDate") Optional<Date> confirmationDate,
			@RequestParam("address") Optional<String> address, @RequestParam("status") Optional<Status> status) {
		OrderPurchase order = new OrderPurchase();
		order.setConfirmationDate(confirmationDate.isPresent() ? confirmationDate.get() : null);
		order.setAddress(address.isPresent() ? address.get() : null);
		order.setStatus(status.isPresent() ? status.get() : null);

		return ResponseEntity.ok(this.orderService.findByParameter(order));
	}

	@RequestMapping(value = "/order/update", method = RequestMethod.PATCH, produces = "application/json")
	@ResponseBody
	public ResponseEntity<OrderPurchase> updateOrderPurchase(@RequestBody OrderPurchase order) {
		return ResponseEntity.ok(this.orderService.updateOrderPurchase(order));
	}

	@RequestMapping(value = "/order/refund/order/{id}", method = RequestMethod.PATCH, produces = "application/json")
	@ResponseBody
	public ResponseEntity<OrderPurchase> refundOrderPurchase(@PathVariable Integer id) {
		return ResponseEntity.ok(this.orderService.refundOrderPurchase(id));
	}

	@RequestMapping(value = "/order/refund/orderitem", method = RequestMethod.PATCH, produces = "application/json")
	@ResponseBody
	public ResponseEntity<OrderItem> refundOrderItem(@RequestBody OrderItem orderItem) {
		return ResponseEntity.ok(this.orderService.refundOrderItem(orderItem));
	}
}
