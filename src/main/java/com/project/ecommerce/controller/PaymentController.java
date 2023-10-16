package com.project.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entity.Payment;
import com.project.ecommerce.service.PaymentService;
import com.project.ecommerce.util.ResponseStructure;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Payment>> savePayment(@RequestParam long buyId,@RequestParam long orderId,@RequestBody Payment payment) {
		return paymentService.savePayment(buyId,orderId,payment);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Payment>> findPayment(@RequestParam long id) {
		return paymentService.findPayment(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Payment>> updatePayment(@RequestParam long id,@RequestBody Payment payment) {
		return paymentService.updatePayment(id,payment);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Payment>> deletePayment(@RequestParam long id) {
		return paymentService.deletePayment(id);
	}
}
