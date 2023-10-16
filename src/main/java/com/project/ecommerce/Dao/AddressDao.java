package com.project.ecommerce.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.Address;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.repository.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo addressRepo;

	public Address saveAddress(Address address) {
		// TODO Auto-generated method stub
		return addressRepo.save(address);
		
	}

	public Address findAddress(long id) {
		// TODO Auto-generated method stub
		Optional<Address> optional = addressRepo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
		
	}

	public void deleteAddress(long id) {
		// TODO Auto-generated method stub
		addressRepo.deleteById(id);
		
	}
}
