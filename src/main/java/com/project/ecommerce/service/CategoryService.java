package com.project.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dao.CategoryDao;
import com.project.ecommerce.entity.Category;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.util.ResponseStructure;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	public ResponseEntity<ResponseStructure<Category>> saveCategory(Category category) {
		
		Category categoryDb = categoryDao.saveCategory(category);
		
		ResponseStructure<Category> responseStructure=new ResponseStructure<Category>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Category is saved sucessfully");
        responseStructure.setData(categoryDb);
        
        return new ResponseEntity<ResponseStructure<Category>>(responseStructure,HttpStatus.CREATED); 

	}

	public ResponseEntity<ResponseStructure<Category>> findCategory(long id) {
		
		Category categoryDb = categoryDao.findCategory(id);
		if(categoryDb.equals(null)) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		ResponseStructure<Category> responseStructure=new ResponseStructure<Category>();
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setMessage("Category is fetched sucessfully");
        responseStructure.setData(categoryDb);
        
        return new ResponseEntity<ResponseStructure<Category>>(responseStructure,HttpStatus.FOUND); 
	}

	public ResponseEntity<ResponseStructure<Category>> updateCategory(long id, Category category) {
		// TODO Auto-generated method stub
		Category categoryDb = categoryDao.findCategory(id);
		if(categoryDb.equals(null)) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		category.setCategoryId(categoryDb.getCategoryId());
		Category categoryDb1 = categoryDao.saveCategory(category);
		
		ResponseStructure<Category> responseStructure=new ResponseStructure<Category>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Category is updated sucessfully");
        responseStructure.setData(categoryDb);
        
        return new ResponseEntity<ResponseStructure<Category>>(responseStructure,HttpStatus.OK); 

	}

	public ResponseEntity<ResponseStructure<Category>> deleteCategory(long id) {
		// TODO Auto-generated method stub
		Category categoryDb = categoryDao.findCategory(id);
		if(categoryDb.equals(null)) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		categoryDao.deleteCategory(id);
		
		ResponseStructure<Category> responseStructure=new ResponseStructure<Category>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Category is deleted sucessfully");
        responseStructure.setData(categoryDb);
        
        return new ResponseEntity<ResponseStructure<Category>>(responseStructure,HttpStatus.OK); 

	}
	
}
