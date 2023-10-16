package com.project.ecommerce.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.AddToCart;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.repository.AddToCartRepo;

@Repository
public class AddToCartDao {

	@Autowired
	private AddToCartRepo addToCartRepo;

	public AddToCart saveAddToCart(AddToCart addToCart) {
		// TODO Auto-generated method stub
		return addToCartRepo.save(addToCart);
	}

	public AddToCart findAddTocart(long id) {
		// TODO Auto-generated method stub
		Optional<AddToCart> optional = addToCartRepo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public void deleteAddToCart(long id) {
		// TODO Auto-generated method stub
		addToCartRepo.deleteById(id);
		
	}

	public List<AddToCart> findAllAddToCartByUser(User userDb) {
		// TODO Auto-generated method stub
		return addToCartRepo.findAllAddToCartByUser(userDb);
	}
}
