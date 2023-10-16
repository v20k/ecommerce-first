package com.project.ecommerce.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.AddToCartHistory;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.repository.AddToCartHistoryRepo;

@Repository
public class AddToCartHistoryDao {

	@Autowired
	private AddToCartHistoryRepo addToCartHistoryRepo;
	
	public AddToCartHistory save(AddToCartHistory addTOCartHistory) {
		return addToCartHistoryRepo.save(addTOCartHistory);
	}
	
	public List<AddToCartHistory> findAllByUser(User user) {
		return addToCartHistoryRepo.findAllByUser(user);
	}

	public void deleteAddToCartHistory(AddToCartHistory addTocartHistory) {
		// TODO Auto-generated method stub
		addToCartHistoryRepo.delete(addTocartHistory);
		
	}
}
