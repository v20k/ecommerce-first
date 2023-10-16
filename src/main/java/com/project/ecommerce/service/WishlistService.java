package com.project.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dao.ProductDao;
import com.project.ecommerce.Dao.UserDao;
import com.project.ecommerce.Dao.WishlistDao;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.entity.Wishlist;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.util.ResponseStructure;

@Service
public class WishlistService {

	@Autowired
	private WishlistDao wishlistDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;
	
	public ResponseEntity<ResponseStructure<Wishlist>> saveWishlist(long userId, long productId, Wishlist wishlist) {
		// TODO Auto-generated method stub
		User userDb = userDao.findUser(userId);
		Product productDb = productDao.findProduct(productId);
		wishlist.setUser(userDb);
		wishlist.setProduct(productDb);
		Wishlist wishlistDb = wishlistDao.saveWishlist(wishlist);
		
		ResponseStructure<Wishlist> responseStructure=new ResponseStructure<Wishlist>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Wishlist is saved sucessfully");
        responseStructure.setData(wishlistDb);
        
        return new ResponseEntity<ResponseStructure<Wishlist>>(responseStructure,HttpStatus.CREATED); 

	}

	public ResponseEntity<ResponseStructure<Wishlist>> findWishlist(long id) {
		// TODO Auto-generated method stub
		Wishlist wishlistDb = wishlistDao.findWishlist(id);
		if(wishlistDb.equals(null)) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		ResponseStructure<Wishlist> responseStructure=new ResponseStructure<Wishlist>();
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setMessage("Wishlist is fetched sucessfully");
        responseStructure.setData(wishlistDb);
        
        return new ResponseEntity<ResponseStructure<Wishlist>>(responseStructure,HttpStatus.FOUND); 
	}

	public ResponseEntity<ResponseStructure<Wishlist>> updateWishlist(long id, Wishlist wishlist) {
		// TODO Auto-generated method stub
		Wishlist wishlistDb = wishlistDao.findWishlist(id);
		if(wishlistDb==null) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		wishlist.setWishlistId(wishlistDb.getWishlistId());
		wishlist.setUser(wishlistDb.getUser());
		wishlist.setProduct(wishlistDb.getProduct());
		Wishlist wishlistDb1 = wishlistDao.saveWishlist(wishlist);
		
		ResponseStructure<Wishlist> responseStructure=new ResponseStructure<Wishlist>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Wishlist is updated sucessfully");
        responseStructure.setData(wishlistDb1);
        
        return new ResponseEntity<ResponseStructure<Wishlist>>(responseStructure,HttpStatus.OK); 

		
		
	}

	public ResponseEntity<ResponseStructure<Wishlist>> deleteWishlist(long id) {
		// TODO Auto-generated method stub
		Wishlist wishlistDb = wishlistDao.findWishlist(id);
		if(wishlistDb.equals(null)) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		wishlistDao.deleteWishlist(id);
		
		ResponseStructure<Wishlist> responseStructure=new ResponseStructure<Wishlist>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Wishlist is deleted sucessfully");
        responseStructure.setData(wishlistDb);
        
        return new ResponseEntity<ResponseStructure<Wishlist>>(responseStructure,HttpStatus.OK); 

		
	}
}
