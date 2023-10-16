package com.project.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dao.CategoryDao;
import com.project.ecommerce.Dao.ProductDao;
import com.project.ecommerce.entity.Category;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.util.ResponseStructure;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(String[] categories, Product product) {
      List<Category> categoriess=product.getCategory();
      if(categoriess==null) {
    	  categoriess=new ArrayList<Category>();
      }
      
		for(String category:categories) {
			 Category categoryDb = categoryDao.findByCategoryName(category);
			 categoriess.add(categoryDb);
			 
		}
		product.setCategory(categoriess);
		Product productDb = productDao.saveProduct(product);
       
        ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Product is saved sucessfully");
        responseStructure.setData(productDb);
        
        return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.CREATED); 
	}

	public ResponseEntity<ResponseStructure<Product>> findProduct(long id) {		
		Product productDb = productDao.findProduct(id);
		if(productDb.equals(null)) {
			throw new IdNotFoundException("Id is not found in database");
		}
		ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setMessage("Product is fetched sucessfully");
        responseStructure.setData(productDb);
        
        return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.FOUND); 
        
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(long id, Product product) {		
		Product productDb = productDao.findProduct(id);
		if(productDb.equals(null)) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		product.setProductId(productDb.getProductId());
	    Product productDb1 = productDao.saveProduct(product);
		
		ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Product is Updated sucessfully");
        responseStructure.setData(productDb1);
        
        return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.OK); 
        
		
	}

	public ResponseEntity<ResponseStructure<String>> deleteProduct(long id) {
		Product productDb = productDao.findProduct(id);
		if(productDb.equals(null)) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		productDao.deleteProduct(id);
		
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Product is deleted sucessfully");
        responseStructure.setData("deleted sucessfully");
        
        return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK); 
       
		
	}
	
}
