package com.project.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dao.BuyDao;
import com.project.ecommerce.Dao.ProductDao;
import com.project.ecommerce.Dao.UserDao;
import com.project.ecommerce.entity.Buy;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.exception.BuyIsConnectedWithPaymentException;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.exception.OrderIsConnectedWithPaymentException;
import com.project.ecommerce.util.ResponseStructure;

@Service
public class BuyService {

	@Autowired
	private BuyDao buyDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Buy>> saveBuy(long userId, long productId, Buy buy) {
		// TODO Auto-generated method stub
		User userDb = userDao.findUser(userId);
		if(userDb==null) {
			throw new IdNotFoundException("userId is not found");
		}
		buy.setUser(userDb);
		Product productDb = productDao.findProduct(productId);
		if(productDb==null) {
			throw new IdNotFoundException("productId is not found");
		}
		buy.setProduct(productDb);
		Buy buyDb = buyDao.saveBuy(buy);
		
		ResponseStructure<Buy> responseStructure=new ResponseStructure<Buy>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Buy is saved sucessfully");
        responseStructure.setData(buyDb);
        
        return new ResponseEntity<ResponseStructure<Buy>>(responseStructure,HttpStatus.CREATED); 

	}

	public ResponseEntity<ResponseStructure<Buy>> findBuy(long id) {
		// TODO Auto-generated method stub
		Buy buyDb = buyDao.findBuy(id);
		if(buyDb==null) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		
		ResponseStructure<Buy> responseStructure=new ResponseStructure<Buy>();
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setMessage("Buy is fetched sucessfully");
        responseStructure.setData(buyDb);
        
        return new ResponseEntity<ResponseStructure<Buy>>(responseStructure,HttpStatus.FOUND); 
	}

	public ResponseEntity<ResponseStructure<Buy>> updateBuy(long id, Buy buy) {
		// TODO Auto-generated method stub
		Buy buyDb = buyDao.findBuy(id);
		if(buyDb==null) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		buy.setBuyId(buyDb.getBuyId());
		buy.setUser(buyDb.getUser());
		buy.setProduct(buyDb.getProduct());
		Buy buyDb1 = buyDao.saveBuy(buy);
		
		ResponseStructure<Buy> responseStructure=new ResponseStructure<Buy>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Buy is fetched sucessfully");
        responseStructure.setData(buyDb1);
        
        return new ResponseEntity<ResponseStructure<Buy>>(responseStructure,HttpStatus.OK); 
	}

	public ResponseEntity<ResponseStructure<Buy>> deleteBuy(long id) {
		// TODO Auto-generated method stub
		Buy buyDb = buyDao.findBuy(id);
		if(buyDb==null) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		try {
			buyDao.deleteBuy(id);
		}catch (Exception e) {
			// TODO: handle exception
			throw new BuyIsConnectedWithPaymentException("Buy is connected with payment, so we don't delete it");
		}
		
		
		
		ResponseStructure<Buy> responseStructure=new ResponseStructure<Buy>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Buy is deleted sucessfully");
        responseStructure.setData(buyDb);
        
        return new ResponseEntity<ResponseStructure<Buy>>(responseStructure,HttpStatus.OK); 

		
		
	}
	
	
	
}
