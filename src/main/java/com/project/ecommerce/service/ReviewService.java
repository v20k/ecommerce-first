package com.project.ecommerce.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dao.ProductDao;
import com.project.ecommerce.Dao.ReviewBackupDao;
import com.project.ecommerce.Dao.ReviewDao;
import com.project.ecommerce.Dao.UserDao;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.entity.Review;
import com.project.ecommerce.entity.ReviewBackup;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.util.ResponseStructure;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ReviewBackupDao reviewBackupDao;

	public ResponseEntity<ResponseStructure<Review>> saveReview(long userId, long productId, Review review) {
		// TODO Auto-generated method stub
		User userDb = userDao.findUser(userId);
		Product productDb = productDao.findProduct(productId);
		review.setUser(userDb);
		review.setProduct(productDb);
		Review reviewDb = reviewDao.saveReview(review);
		
		     ReviewBackup reviewBackup = this.modelMapper.map(reviewDb, ReviewBackup.class);
		     reviewBackup.setUserName(reviewDb.getUser().getUserName());
		     reviewBackup.setProductName(reviewDb.getProduct().getProductName());
		     reviewBackupDao.saveReviewBackup(reviewBackup);
		
		ResponseStructure<Review> responseStructure=new ResponseStructure<Review>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Review is saved sucessfully");
        responseStructure.setData(reviewDb);
        
        return new ResponseEntity<ResponseStructure<Review>>(responseStructure,HttpStatus.CREATED); 

	}

	public ResponseEntity<ResponseStructure<Review>> findReview(long id) {
		// TODO Auto-generated method stub
		Review reviewDb = reviewDao.findReview(id);
		if(reviewDb.equals(null)) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		ResponseStructure<Review> responseStructure=new ResponseStructure<Review>();
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setMessage("Review is fetched sucessfully");
        responseStructure.setData(reviewDb);
        
        return new ResponseEntity<ResponseStructure<Review>>(responseStructure,HttpStatus.FOUND); 
	}

	public ResponseEntity<ResponseStructure<Review>> updateReview(long id, Review review) {
		// TODO Auto-generated method stub
		Review reviewDb = reviewDao.findReview(id);
		if(reviewDb.equals(null)) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		review.setReviewId(reviewDb.getReviewId());
		review.setUser(reviewDb.getUser());
		review.setProduct(reviewDb.getProduct());
		Review reviewDb1 = reviewDao.saveReview(review);
		
		ResponseStructure<Review> responseStructure=new ResponseStructure<Review>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Review is updated sucessfully");
        responseStructure.setData(reviewDb1);
        
        return new ResponseEntity<ResponseStructure<Review>>(responseStructure,HttpStatus.OK); 

		
		
	}

	public ResponseEntity<ResponseStructure<Review>> deleteReview(long id) {
		// TODO Auto-generated method stub
		Review reviewDb = reviewDao.findReview(id);
		if(reviewDb.equals(null)) {
			throw new IdNotFoundException("Id is not found in the database");
		}
		reviewDao.deleteReview(id);
		
		ResponseStructure<Review> responseStructure=new ResponseStructure<Review>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Review is deleted sucessfully");
        responseStructure.setData(reviewDb);
        
        return new ResponseEntity<ResponseStructure<Review>>(responseStructure,HttpStatus.OK); 

		
	}
	
}
