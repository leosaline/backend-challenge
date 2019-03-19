package com.invillia.acme.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.invillia.acme.entity.Store;
import com.invillia.acme.serviceimp.StoreServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreServiceTest {

	@Autowired
	private StoreServiceImp storeServiceImp;
	private Store store;

	@Before
	public void beforeMethod() {
		store = new Store();
		store.setId(1);
		store.setAddress("address");
		store.setName("store");
	}

	@Test
	public void saveStore() {
		Store storeSaved = storeServiceImp.save(store);

		assertThat(storeSaved.getId()).isEqualTo(store.getId());
	}

	@Test
	public void findStoreByParameter() {
		storeServiceImp.save(store);
		Store storeSaved = new Store();
		storeSaved.setAddress("address");

		Collection<Store> list = storeServiceImp.findByParameter(storeSaved);

		assertThat(store.getId()).isEqualTo(list.iterator().next().getId());
	}

	@Test
	public void updateStore() {
		storeServiceImp.save(store);
		store.setAddress("altered");

		Store storeSaved = storeServiceImp.updateStore(store);

		assertThat(storeSaved.getAddress()).isEqualTo("altered");
	}

}
