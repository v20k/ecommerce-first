package com.project.ecommerce.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.Buy;
import com.project.ecommerce.repository.BuyRepo;

@Repository
public class BuyDao {

	@Autowired
	private BuyRepo buyRepo;

	public Buy saveBuy(Buy buy) {
		// TODO Auto-generated method stub
		return buyRepo.save(buy);
	}

	public Buy findBuy(long id) {
		// TODO Auto-generated method stub
		Optional<Buy> optional = buyRepo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public void deleteBuy(long id) {
		// TODO Auto-generated method stub
		buyRepo.deleteById(id);
	}
}
