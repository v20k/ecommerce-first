package com.project.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.ecommerce.entity.AddToCart;
import com.project.ecommerce.entity.User;

public interface AddToCartRepo extends JpaRepository<AddToCart, Long>{

	@Query(value = "select a from AddToCart a where a.user=?1")
	List<AddToCart> findAllAddToCartByUser(User userDb);

}
