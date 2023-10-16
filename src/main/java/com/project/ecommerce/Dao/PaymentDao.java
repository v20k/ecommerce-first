package com.project.ecommerce.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.Order;
import com.project.ecommerce.entity.Payment;
import com.project.ecommerce.repository.PaymentRepo;

@Repository
public class PaymentDao {

	@Autowired
	private PaymentRepo paymentRepo;

	public Payment savePayment(Payment payment) {
		// TODO Auto-generated method stub
		return paymentRepo.save(payment);
	}

	public Payment findPayment(long id) {
		// TODO Auto-generated method stub
		Optional<Payment> optional = paymentRepo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public void deletePayment(long id) {
		// TODO Auto-generated method stub
		paymentRepo.deleteById(id);
	}

	public Payment findByOrder(Order order) {
		// TODO Auto-generated method stub
		return paymentRepo.findByOrder(order);
	}
	
	
}
