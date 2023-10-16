package com.project.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dao.AddToCartDao;
import com.project.ecommerce.Dao.AddToCartHistoryDao;
import com.project.ecommerce.Dao.AddressDao;
import com.project.ecommerce.Dao.OrderDao;
import com.project.ecommerce.Dao.PaymentDao;
import com.project.ecommerce.Dao.ReviewDao;
import com.project.ecommerce.Dao.UserDao;
import com.project.ecommerce.Dao.WishlistDao;
import com.project.ecommerce.entity.AddToCart;
import com.project.ecommerce.entity.AddToCartHistory;
import com.project.ecommerce.entity.Address;
import com.project.ecommerce.entity.Order;
import com.project.ecommerce.entity.Payment;
import com.project.ecommerce.entity.Review;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.entity.Wishlist;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.util.ResponseStructure;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private WishlistDao wishlistDao;
	@Autowired
	private AddToCartDao addToCartDao;
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private AddToCartHistoryDao addToCartHistoryDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private PaymentDao paymentDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
      User userDb = userDao.saveUser(user);		
      
      ResponseStructure<User> responseStructure=new ResponseStructure<User>();
      responseStructure.setStatus(HttpStatus.CREATED.value());
      responseStructure.setMessage("User is created sucessfully");
      responseStructure.setData(userDb);
      return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.CREATED); 
	}

	public ResponseEntity<ResponseStructure<User>> findUser(long id) {
      
		User userDb = userDao.findUser(id);
		if(userDb==null) {
			throw new IdNotFoundException("Id is not found in database");
		}
		
		 ResponseStructure<User> responseStructure=new ResponseStructure<User>();
	     responseStructure.setStatus(HttpStatus.OK.value());
	     responseStructure.setMessage("User is fetched sucessfully");
	     responseStructure.setData(userDb);
	     return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK); 
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(long id, User user) {
		User userDb = userDao.findUser(id);
		if(userDb==null) {
			throw new IdNotFoundException("Id is not present inside the database");
		}
		user.setUserId(userDb.getUserId());
		//////
//		user.setAddress(userDb.getAddress());
		
		//////
		User userDb1 = userDao.saveUser(user);
		
		 ResponseStructure<User> responseStructure=new ResponseStructure<User>();
	     responseStructure.setStatus(HttpStatus.OK.value());
	     responseStructure.setMessage("User is updated sucessfully");
	     responseStructure.setData(userDb1);
	     return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK); 

	}

	public ResponseEntity<ResponseStructure<String>> deleteUser(long id) {
		User userDb = userDao.findUser(id);
		if(userDb==null) {
			throw new IdNotFoundException("Id is not present inside the database");
		}
		//
		List<Address> addresses = userDb.getAddress();
		for(Address address:addresses) {
			addressDao.deleteAddress(address.getAddressId());
		}
//		userDb.setAddress(null);
		List<Wishlist> wishlists = userDb.getWishlist();
		for(Wishlist wishlist:wishlists) {
			wishlistDao.deleteWishlist(wishlist.getWishlistId());
		}
//		userDb.setWishlist(null);
		List<AddToCart> addTocarts = userDb.getAddTOCart();
		for(AddToCart addToCart:addTocarts) {
			addToCartDao.deleteAddToCart(addToCart.getAddToCartId());
		}
//		userDb.setAddTOCart(null);
		List<Review> reviews = userDb.getReview();
		for(Review review:reviews) {
			reviewDao.deleteReview(review.getReviewId());
		}
//		userDb.setReview(null);
		List<AddToCartHistory> addToCartHistories = addToCartHistoryDao.findAllByUser(userDb);
		List<Order> orders = orderDao.findByUser(userDb);
		for(Order order:orders) {
			Payment payment=paymentDao.findByOrder(order);
			paymentDao.deletePayment(payment.getPaymentId());
			orderDao.deleteOrder(order.getOrderId());
		}
		for(AddToCartHistory addToCartHistory:addToCartHistories) {
			
			addToCartHistoryDao.deleteAddToCartHistory(addToCartHistory);
		}
		//
        userDao.deleteUser(id);	
        
        ResponseStructure<String> responseStructure=new ResponseStructure<String>();
	     responseStructure.setStatus(HttpStatus.OK.value());
	     responseStructure.setMessage("User is deleted sucessfully");
	     responseStructure.setData("Deleted sucessfully");
	     return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK); 

	}
	
	
}
