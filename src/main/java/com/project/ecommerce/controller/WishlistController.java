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

import com.project.ecommerce.entity.Wishlist;
import com.project.ecommerce.service.WishlistService;
import com.project.ecommerce.util.ResponseStructure;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Wishlist>> saveWishlist(@RequestParam long userId,@RequestParam long productId,@RequestBody Wishlist wishlist) {
		return wishlistService.saveWishlist(userId,productId,wishlist);	
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Wishlist>> findWishlist(@RequestParam long id) {
		return wishlistService.findWishlist(id);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Wishlist>> updateWishlist(@RequestParam long id,@RequestBody Wishlist wishlist) {
		return wishlistService.updateWishlist(id,wishlist);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Wishlist>> deleteWishlist(@RequestParam long id) {
		return wishlistService.deleteWishlist(id);
	}
}
