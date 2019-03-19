package com.invillia.acme.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.invillia.acme.enumerator.Status;

@Entity
public class Payment {
	@Id
	private Integer id;
	private Status status;
	private Long creditCardNumber;
	private Date paymentDate;
	private Integer orderPurchase;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(Long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Integer getOrderPurchase() {
		return orderPurchase;
	}

	public void setOrderPurchase(Integer orderPurchase) {
		this.orderPurchase = orderPurchase;
	}

}
