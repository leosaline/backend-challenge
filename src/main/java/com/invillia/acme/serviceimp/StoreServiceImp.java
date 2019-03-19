package com.invillia.acme.serviceimp;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.entity.Store;
import com.invillia.acme.repository.StoreRepository;
import com.invillia.acme.service.StoreService;

@Service
public class StoreServiceImp implements StoreService {
	@Autowired
	StoreRepository storeRepository;

	@Override
	public Store save(Store store) {
		return storeRepository.save(store);
	}

	@Override
	public Collection<Store> findByParameter(Store store) {
		Set<Store> listStore = new HashSet<Store>();

		if (store.getName() != null) {
			listStore.addAll(storeRepository.findStoreByName(store.getName()));
		}
		if (store.getAddress() != null) {
			listStore.addAll(storeRepository.fiindStoreByAddress(store.getAddress()));
		}

		return listStore;
	}

	@Override
	public Store updateStore(Store store) {
		return storeRepository.save(store);
	}

}