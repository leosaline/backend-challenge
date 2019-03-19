package com.invillia.acme.service;

import java.util.Collection;

import com.invillia.acme.entity.Store;

public interface StoreService {
	public Store save(Store store);

	public Collection<Store> findByParameter(Store store);

	public Store updateStore(Store store);
}
