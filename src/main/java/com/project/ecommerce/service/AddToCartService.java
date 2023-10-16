package com.project.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dao.AddToCartDao;
import com.project.ecommerce.Dao.ProductDao;
import com.project.ecommerce.Dao.UserDao;
import com.project.ecommerce.entity.AddToCart;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.util.ResponseStructure;

@Service
public class AddToCartService {

	@Autowired
	private AddToCartDao addToCartDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<AddToCart>> saveAddToCart(long userId, long productId, AddToCart addToCart) {
		// TODO Auto-generated method stub
		User userDb = userDao.findUser(userId);
		if(userDb==null) {
			throw new IdNotFoundException("userId is not found ");
		}
		addToCart.setUser(userDb);
		
		Product productDb = productDao.findProduct(productId);
		if(productDb==null) {
			throw new IdNotFoundException("productId is not found");
		}
		addToCart.setProduct(productDb);
		
		AddToCart addToCartDb = addToCartDao.saveAddToCart(addToCart);
		
		
		ResponseStructure<AddToCart> responseStructure=new ResponseStructure<AddToCart>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("AddToCart is saved sucessfully");
        responseStructure.setData(addToCartDb);
        
        return new ResponseEntity<ResponseStructure<AddToCart>>(responseStructure,HttpStatus.CREATED); 

	}

	public ResponseEntity<ResponseStructure<AddToCart>> findAddToCart(long id) {
		// TODO Auto-generated method stub
		AddToCart addToCartDb = addToCartDao.findAddTocart(id);
		if(addToCartDb==null) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		ResponseStructure<AddToCart> responseStructure=new ResponseStructure<AddToCart>();
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setMessage("AddToCart is fetched sucessfully");
        responseStructure.setData(addToCartDb);
        
        return new ResponseEntity<ResponseStructure<AddToCart>>(responseStructure,HttpStatus.FOUND); 

	}

	public ResponseEntity<ResponseStructure<AddToCart>> updateAddToCart(long id, AddToCart addToCart) {
		// TODO Auto-generated method stub
		AddToCart addToCartDb = addToCartDao.findAddTocart(id);
		if(addToCartDb==null) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		addToCart.setAddToCartId(addToCartDb.getAddToCartId());
		addToCart.setUser(addToCartDb.getUser());
		addToCart.setProduct(addToCartDb.getProduct());
		AddToCart addToCartDb1 = addToCartDao.saveAddToCart(addToCart);
		
		ResponseStructure<AddToCart> responseStructure=new ResponseStructure<AddToCart>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("AddToCart is updated sucessfully");
        responseStructure.setData(addToCartDb1);
        
        return new ResponseEntity<ResponseStructure<AddToCart>>(responseStructure,HttpStatus.OK); 

	}

	public ResponseEntity<ResponseStructure<AddToCart>> deleteAddToCart(long id) {
		// TODO Auto-generated method stub
		AddToCart addToCartDb = addToCartDao.findAddTocart(id);
		if(addToCartDb==null) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		addToCartDao.deleteAddToCart(id);
		
		ResponseStructure<AddToCart> responseStructure=new ResponseStructure<AddToCart>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("AddToCart is deleted sucessfully");
        responseStructure.setData(addToCartDb);
        
        return new ResponseEntity<ResponseStructure<AddToCart>>(responseStructure,HttpStatus.OK); 

	}

	public ResponseEntity<ResponseStructure<List<AddToCart>>> findAllAddToCartByUser(long userId) {
		// TODO Auto-generated method stub
		User userDb = userDao.findUser(userId);
		if(userDb==null) {
			throw new IdNotFoundException("userId is not found");
		}
		List<AddToCart> addToCarts = addToCartDao.findAllAddToCartByUser(userDb);
		
		ResponseStructure<List<AddToCart>> responseStructure=new ResponseStructure<List<AddToCart>>();
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setMessage("AddToCart is fetched sucessfully");
        responseStructure.setData(addToCarts);
        
        return new ResponseEntity<ResponseStructure<List<AddToCart>>>(responseStructure,HttpStatus.FOUND); 

		
	}
}
