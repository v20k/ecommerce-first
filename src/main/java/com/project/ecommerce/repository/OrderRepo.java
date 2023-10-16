package com.project.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.ecommerce.entity.AddToCartHistory;
import com.project.ecommerce.entity.Order;
import com.project.ecommerce.entity.User;

public interface OrderRepo extends JpaRepository<Order, Long>{

	@Query(value = "select o from Order o where o.addToCart=?1")
	List<Order> findByAddToCartHistories(List<AddToCartHistory> addToCartHistories);

	@Query(value = "select o from Order o where o.user=?1")
	List<Order> findByUser(User userDb);

}
