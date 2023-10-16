package com.project.ecommerce.controller;

import java.util.List;

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

import com.project.ecommerce.entity.AddToCart;
import com.project.ecommerce.service.AddToCartService;
import com.project.ecommerce.util.ResponseStructure;

@RestController
@RequestMapping("/addToCart")
public class AddToCartController {

	@Autowired
	private AddToCartService addToCartService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AddToCart>> saveAddToCart(@RequestParam long userId,@RequestParam long productId,@RequestBody AddToCart addToCart) {
		return addToCartService.saveAddToCart(userId,productId,addToCart);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<AddToCart>> findAddTocart(@RequestParam long id) {
		return addToCartService.findAddToCart(id);
	}
	
	@GetMapping("/getAllAddToCartbyUser")
	public ResponseEntity<ResponseStructure<List<AddToCart>>> findAllAddToCartByUser(@RequestParam long userId) {
		return addToCartService.findAllAddToCartByUser(userId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<AddToCart>> updateAddToCart(@RequestParam long id,@RequestBody AddToCart addToCart) {
		return addToCartService.updateAddToCart(id,addToCart);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AddToCart>> deleteAddToCart(@RequestParam long id) {
		return addToCartService.deleteAddToCart(id);
	}
}
