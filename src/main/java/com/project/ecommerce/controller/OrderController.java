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

import com.project.ecommerce.entity.Order;
import com.project.ecommerce.service.OrderService;
import com.project.ecommerce.util.ResponseStructure;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Order>> saveOrder(@RequestParam long userId,@RequestBody Order order) {
		return orderService.saveOrder(userId,order);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Order>> findOrder(@RequestParam long id) {
		return orderService.findOrder(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Order>> updateOrder(@RequestParam long id,@RequestParam long userId,@RequestBody Order order) {
		return orderService.updateOrder(id,userId,order);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Order>> deleteOrder(@RequestParam long id) {
		return orderService.deleteOrder(id);
	}
}
