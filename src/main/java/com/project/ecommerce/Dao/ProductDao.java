package com.project.ecommerce.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.Product;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.repository.ProductRepo;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepo productRepo;

	public Product saveProduct(Product product) {
		return productRepo.save(product);
        		
	}

	public Product findProduct(long id) {
		
		 Optional<Product> optional = productRepo.findById(id);
		 if(optional.isEmpty()) {
			 return null;
		 }
		 return optional.get();
	}

	public void deleteProduct(long id) {		
		
		productRepo.deleteById(id);
	}
	
}
