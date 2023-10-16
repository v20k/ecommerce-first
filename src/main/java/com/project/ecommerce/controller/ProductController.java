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

import com.project.ecommerce.entity.Category;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.service.ProductService;
import com.project.ecommerce.util.ResponseStructure;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestParam String[] categories,@RequestBody Product product) {
		return productService.saveProduct(categories,product);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Product>> findProduct(@RequestParam long id) {
		return productService.findProduct(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestParam long id,@RequestBody Product product) {
		return productService.updateProduct(id,product);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@RequestParam long id) {
		return productService.deleteProduct(id);
	}
}
