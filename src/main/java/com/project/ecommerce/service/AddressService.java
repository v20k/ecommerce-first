package com.project.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dao.AddressDao;
import com.project.ecommerce.Dao.UserDao;
import com.project.ecommerce.entity.Address;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(long userId,Address address) {
		// TODO Auto-generated method stub
		User user = userDao.findUser(userId);
		address.setUser(user);
		
		Address addressDb = addressDao.saveAddress(address);
		
//		User user = userDao.findUser(userId);
//		List<Address> addresses= user.getAddress();
//		if(addresses.equals(null)) {
//			addresses=new ArrayList<Address>();	
//		}
//		addresses.add(addressDb);
		
//		try {
//		 addresses = user.getAddress();
//		 addresses.add(addressDb);
//		}catch (Exception e) {
//			addresses=new ArrayList<Address>();
//			addresses.add(addressDb);
//		}
		
//		user.setAddress(addresses);
//		userDao.saveUser(user);
		
		ResponseStructure<Address> responseStructure=new ResponseStructure<Address>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Address is saved sucessfully");
        responseStructure.setData(addressDb);
        
        return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.CREATED); 

	}

	public ResponseEntity<ResponseStructure<Address>> findAddress(long id) {
		// TODO Auto-generated method stub
		Address addressDb = addressDao.findAddress(id);
		if(addressDb==null) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		
		ResponseStructure<Address> responseStructure=new ResponseStructure<Address>();
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setMessage("Address is fetched sucessfully");
        responseStructure.setData(addressDb);
        
        return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.FOUND); 

		
	}
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(long id, Address address) {
		// TODO Auto-generated method stub
		Address addressDb = addressDao.findAddress(id);
		if(addressDb==null) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		address.setAddressId(addressDb.getAddressId());
		
		ResponseStructure<Address> responseStructure=new ResponseStructure<Address>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Address is updated sucessfully");
        responseStructure.setData(addressDb);
        
        return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.OK); 

	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddress(long id) {
		// TODO Auto-generated method stub
		Address addressDb = addressDao.findAddress(id);
		if(addressDb==null) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		
		User user=addressDb.getUser();
		if(user==null) {
		addressDao.deleteAddress(id);
		}else {
			List<Address> addresses = user.getAddress();
			addresses.remove(addressDb);
			user.setAddress(addresses);
			userDao.saveUser(user);
			addressDao.deleteAddress(id);
		}
		
		ResponseStructure<Address> responseStructure=new ResponseStructure<Address>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Address is deleted sucessfully");
        responseStructure.setData(addressDb);
        
        return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.OK); 

		
	}

	
}
