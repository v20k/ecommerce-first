package com.project.ecommerce.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.Category;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.repository.CategoryRepo;

@Repository
public class CategoryDao {

	@Autowired
	private CategoryRepo categoryRepo;

	public Category saveCategory(Category category) {
		
		return categoryRepo.save(category);
		
	}

	public Category findCategory(long id) {
		// TODO Auto-generated method stub
		Optional<Category> optional = categoryRepo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public void deleteCategory(long id) {
		// TODO Auto-generated method stub
		categoryRepo.deleteById(id);
	}

	public Category findByCategoryName(String category) {
		// TODO Auto-generated method stub
		return categoryRepo.findByCategoryName(category);
		
	}
}
