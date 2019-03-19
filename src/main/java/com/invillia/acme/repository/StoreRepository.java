package com.invillia.acme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.invillia.acme.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
	@Query("SELECT s FROM Store s WHERE s.name = ?1")
	public List<Store> findStoreByName(String name);

	@Query("SELECT s FROM Store s WHERE s.address = ?1")
	public List<Store> fiindStoreByAddress(String address);
}
