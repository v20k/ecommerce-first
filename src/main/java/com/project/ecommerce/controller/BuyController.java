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

import com.project.ecommerce.entity.Buy;
import com.project.ecommerce.service.BuyService;
import com.project.ecommerce.util.ResponseStructure;

@RestController
@RequestMapping("/buy")
public class BuyController {

	@Autowired
	private BuyService buyService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Buy>> saveBuy(@RequestParam long userId,@RequestParam long productId,@RequestBody Buy buy) {
		return buyService.saveBuy(userId,productId,buy);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Buy>> findBuy(@RequestParam long id) {
		return buyService.findBuy(id);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Buy>> updateBuy(@RequestParam long id,@RequestBody Buy buy) {
		return buyService.updateBuy(id,buy);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Buy>> deleteBuy(@RequestParam long id) {
		return buyService.deleteBuy(id);
	}
}
