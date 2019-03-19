package com.invillia.acme.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.entity.Store;
import com.invillia.acme.service.StoreService;

@RestController
public class StoreController {

	@Autowired
	StoreService storeService;

	@RequestMapping(value = "/store", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Store> createStore(@RequestBody Store store) {
		return ResponseEntity.ok(this.storeService.save(store));
	}

	@RequestMapping(value = "/store", method = RequestMethod.PATCH, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Store> updateStore(@RequestBody Store store) {
		return ResponseEntity.ok(this.storeService.updateStore(store));
	}

	@RequestMapping(value = "/store", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Collection<Store>> getStoreByParameter(
			@RequestParam(value = "name", required = false) Optional<String> name,
			@RequestParam(value = "address", required = false) Optional<String> address) {
		Store store = new Store();
		store.setName(name.isPresent() ? name.get() : null);
		store.setAddress(address.isPresent() ? address.get() : null);
		return ResponseEntity.ok(this.storeService.findByParameter(store));
	}

}
