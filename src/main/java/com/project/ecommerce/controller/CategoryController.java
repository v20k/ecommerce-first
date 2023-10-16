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
import com.project.ecommerce.service.CategoryService;
import com.project.ecommerce.util.ResponseStructure;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Category>> saveCategory(@RequestBody Category category) {
		return categoryService.saveCategory(category);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Category>> findCategory(@RequestParam long id) {
		return categoryService.findCategory(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Category>> updateCategory(@RequestParam long id,@RequestBody Category category) {
		return categoryService.updateCategory(id,category);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Category>> deleteCategory(@RequestParam long id) {
		return categoryService.deleteCategory(id);
	}
}
