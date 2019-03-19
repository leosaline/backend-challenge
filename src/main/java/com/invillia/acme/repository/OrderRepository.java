package com.invillia.acme.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.invillia.acme.entity.OrderPurchase;
import com.invillia.acme.enumerator.Status;

@Repository
public interface OrderRepository extends JpaRepository<OrderPurchase, Integer> {
	@Query("SELECT o FROM OrderPurchase o WHERE o.confirmationDate = ?1")
	public Collection<OrderPurchase> findOrderByConfirmationDate(Date confirmationDate);

	@Query("SELECT o FROM OrderPurchase o WHERE o.address = ?1")
	public Collection<OrderPurchase> findOrderByAddress(String address);

	@Query("SELECT o FROM OrderPurchase o WHERE o.status = ?1")
	public Collection<OrderPurchase> findOrderByStatus(Status status);
}