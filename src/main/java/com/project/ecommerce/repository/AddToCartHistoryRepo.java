package com.project.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.ecommerce.entity.AddToCartHistory;
import com.project.ecommerce.entity.User;

public interface AddToCartHistoryRepo extends JpaRepository<AddToCartHistory, Long> {

	@Query(value = "select a from AddToCartHistory a where a.user=?1")
	List<AddToCartHistory> findAllByUser(User user);

}
