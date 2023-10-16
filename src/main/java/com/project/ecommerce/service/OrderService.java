package com.project.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dao.AddToCartDao;
import com.project.ecommerce.Dao.AddToCartHistoryDao;
import com.project.ecommerce.Dao.OrderDao;
import com.project.ecommerce.Dao.UserDao;
import com.project.ecommerce.entity.AddToCart;
import com.project.ecommerce.entity.AddToCartHistory;
import com.project.ecommerce.entity.Address;
import com.project.ecommerce.entity.Order;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.exception.AddToCartsIsEmpty;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.exception.OrderIsConnectedWithPaymentException;
import com.project.ecommerce.util.ResponseStructure;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AddToCartDao addToCartDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AddToCartHistoryDao addToCartHistoryDao;

	public ResponseEntity<ResponseStructure<Order>> saveOrder(long userId, Order order) {
		// TODO Auto-generated method stub
		User userDb = userDao.findUser(userId);
		if(userDb==null) {
			throw new IdNotFoundException("userId is not found");
		}
//		List<AddToCart> addToCart = userDb.getAddTOCart();
		order.setUser(userDb);
//		order.setAddToCart(addToCart);
		List<AddToCart> addToCarts = addToCartDao.findAllAddToCartByUser(userDb);
		if(addToCarts.isEmpty()) {
			throw new AddToCartsIsEmpty("addToCarts in this user is empty");
		}
//		 List<AddToCartHistory> addToCartHistories = addToCartHistoryDao.findAllByUser(userDb);
//		if(addToCartHistories==null) {
//		  addToCartHistories=new ArrayList<>();
//		 }
		List<AddToCartHistory> addToCartHistories=new ArrayList<>();
		
		for(AddToCart addToCart:addToCarts) {
		 AddToCartHistory addTOCartHistory = this.modelMapper.map(addToCart,AddToCartHistory.class);
//		 addTOCartHistory.setUserName(addToCart.getUser().getUserName());
//		 addTOCartHistory.setProductName(addToCart.getProduct().getProductName());
		 AddToCartHistory addToCartHistory = addToCartHistoryDao.save(addTOCartHistory);
		 addToCartHistories.add(addToCartHistory);
		 
		 addToCartDao.deleteAddToCart(addToCart.getAddToCartId());
		}
		
		
		order.setAddToCart(addToCartHistories);
		Order orderDb = orderDao.saveOrder(order);
		
		
		
		
		ResponseStructure<Order> responseStructure=new ResponseStructure<Order>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Order is saved sucessfully");
        responseStructure.setData(orderDb);
        
        return new ResponseEntity<ResponseStructure<Order>>(responseStructure,HttpStatus.CREATED); 
	}

	public ResponseEntity<ResponseStructure<Order>> findOrder(long id) {
		// TODO Auto-generated method stub
		Order orderDb = orderDao.findOrder(id);
		if(orderDb==null) {
			throw new IdNotFoundException("Id is not found in database");
		}
		
		ResponseStructure<Order> responseStructure=new ResponseStructure<Order>();
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setMessage("Order is fetched sucessfully");
        responseStructure.setData(orderDb);
        
        return new ResponseEntity<ResponseStructure<Order>>(responseStructure,HttpStatus.FOUND); 

	}

	public ResponseEntity<ResponseStructure<Order>> updateOrder(long id, long userId, Order order) {
		// TODO Auto-generated method stub
		Order orderDb = orderDao.findOrder(id);
		if(orderDb==null) {
			throw new IdNotFoundException("Id is not found in database");
		}
		order.setOrderId(orderDb.getOrderId());
		
		User userDb = userDao.findUser(userId);
		if(userDb==null) {
			throw new IdNotFoundException("userId is not found");
		}
		order.setUser(userDb);
//		List<AddToCart> addToCarts = addToCartDao.findAllAddToCartByUser(userDb);
		//////
		List<AddToCart> addToCarts = addToCartDao.findAllAddToCartByUser(userDb);
		
		if(addToCarts.isEmpty()) {
			throw new AddToCartsIsEmpty("addToCarts in this user is empty");
		}
		
//		 List<AddToCartHistory> addToCartHistories = addToCartHistoryDao.findAllByUser(userDb);
//		if(addToCartHistories==null) {
//		  addToCartHistories=new ArrayList<>();
//		 }
		List<AddToCartHistory> addToCartHistories=new ArrayList<>();
		for(AddToCart addToCart:addToCarts) {
		 AddToCartHistory addTOCartHistory = this.modelMapper.map(addToCart,AddToCartHistory.class);
		 AddToCartHistory addToCartHistory = addToCartHistoryDao.save(addTOCartHistory);
		 addToCartHistories.add(addToCartHistory);
		 
		 addToCartDao.deleteAddToCart(addToCart.getAddToCartId());
		}
		
		order.setAddToCart(addToCartHistories);
		
		/////
		Order orderDb1 = orderDao.saveOrder(order);
		
		
		
		ResponseStructure<Order> responseStructure=new ResponseStructure<Order>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Order is updated sucessfully");
        responseStructure.setData(orderDb1);
        
        return new ResponseEntity<ResponseStructure<Order>>(responseStructure,HttpStatus.OK); 

		
	}

	public ResponseEntity<ResponseStructure<Order>> deleteOrder(long id) {
		// TODO Auto-generated method stub
		Order orderDb = orderDao.findOrder(id);
		if(orderDb==null) {
			throw new IdNotFoundException("Id is not found in database");
		}
		try {
		orderDao.deleteOrder(id);
		}catch (Exception e) {
			// TODO: handle exception
			throw new OrderIsConnectedWithPaymentException("Order is connected with payment, so we don't delete it");
		}
		ResponseStructure<Order> responseStructure=new ResponseStructure<Order>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Order is deleted sucessfully");
        responseStructure.setData(orderDb);
        
        return new ResponseEntity<ResponseStructure<Order>>(responseStructure,HttpStatus.OK); 
	}
	
}
