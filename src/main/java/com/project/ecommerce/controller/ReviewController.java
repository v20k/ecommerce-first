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

import com.project.ecommerce.entity.Review;
import com.project.ecommerce.service.ReviewService;
import com.project.ecommerce.util.ResponseStructure;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Review>> saveReview(@RequestParam long userId,@RequestParam long productId,@RequestBody Review review) {
		return reviewService.saveReview(userId,productId,review);	
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Review>> findReview(@RequestParam long id) {
		return reviewService.findReview(id);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Review>> updateReview(@RequestParam long id,@RequestBody Review review) {
		return reviewService.updateReview(id,review);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Review>> deleteReview(@RequestParam long id) {
		return reviewService.deleteReview(id);
	}
	
}
