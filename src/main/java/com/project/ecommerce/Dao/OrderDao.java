package com.project.ecommerce.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.AddToCartHistory;
import com.project.ecommerce.entity.Order;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.repository.OrderRepo;

@Repository
public class OrderDao {

	@Autowired
	private OrderRepo orderRepo;

	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		return orderRepo.save(order);
	}

	public Order findOrder(long id) {
		// TODO Auto-generated method stub
		Optional<Order> optional = orderRepo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public void deleteOrder(long id) {
		// TODO Auto-generated method stub
		orderRepo.deleteById(id);
		
	}


	public List<Order> findByAddToCartHistories(List<AddToCartHistory> addToCartHistories) {
		// TODO Auto-generated method stub
		return orderRepo.findByAddToCartHistories(addToCartHistories);
	}

	public List<Order> findByUser(User userDb) {
		// TODO Auto-generated method stub
		return orderRepo.findByUser(userDb);
	}
}
