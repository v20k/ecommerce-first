package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.ecommerce.entity.Order;
import com.project.ecommerce.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long>{

	@Query(value = "select p from Payment p where p.order=?1")
	Payment findByOrder(Order order);

}
