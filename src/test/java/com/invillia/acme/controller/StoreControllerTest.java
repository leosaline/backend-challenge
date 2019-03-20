package com.invillia.acme.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.acme.entity.Store;
import com.invillia.acme.service.StoreService;

@RunWith(SpringRunner.class)
@WebMvcTest(StoreController.class)
public class StoreControllerTest {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private StoreService service;
	private Store store;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void beforeMethod() {
		store = new Store();
		store.setId(1);
		store.setAddress("address");
		store.setName("store");
	}

	@Test
	public void whenGetStoreByParameterThenReturnCollectionStore() throws Exception {
		Collection<Store> collStore = new ArrayList<Store>();
		Store storeAdd = new Store();
		storeAdd.setId(1);
		storeAdd.setAddress("address");
		storeAdd.setName("bob");
		collStore.add(storeAdd);

		when(service.findByParameter(store)).thenReturn(collStore);

		mvc.perform(get("/store").param("name", "bob").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCreateStoreThenReturnStore() throws Exception {
		when(service.save(store)).thenReturn(store);

		mvc.perform(post("/store").content(mapper.writeValueAsString(store)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.address", is(store.getAddress())));
	}

	@Test
	public void whenUpdateStoreThenReturnStoreUpdated() throws Exception {
		Store storeUpdate = new Store();
		storeUpdate.setAddress("altered");
		storeUpdate.setId(1);

		when(service.updateStore(store)).thenReturn(storeUpdate);

		mvc.perform(patch("/store").content(mapper.writeValueAsString(store))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.address", is(storeUpdate.getAddress())));
	}
}
