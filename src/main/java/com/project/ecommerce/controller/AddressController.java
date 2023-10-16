package com.project.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entity.Address;
import com.project.ecommerce.service.AddressService;
import com.project.ecommerce.util.ResponseStructure;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestParam long userId,@RequestBody Address address) {
		return addressService.saveAddress(userId,address);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Address>> findAddress(@RequestParam long id) {
		return addressService.findAddress(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestParam long id,@RequestBody Address address) {
		return addressService.updateAddress(id,address);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Address>> deleteAddress(@RequestParam long id) {
		return addressService.deleteAddress(id);
	}
	
}
