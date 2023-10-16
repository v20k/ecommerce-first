package com.project.ecommerce.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.Review;
import com.project.ecommerce.repository.ReviewRepo;

@Repository
public class ReviewDao {

	@Autowired
	private ReviewRepo reviewRepo;

	public Review saveReview(Review review) {
		// TODO Auto-generated method stub
		return reviewRepo.save(review);
	}

	public Review findReview(long id) {
		// TODO Auto-generated method stub
		Optional<Review> optional = reviewRepo.findById(id);
		if(optional.isEmpty()){
			return null;
		}
		return optional.get();
	}

	public void deleteReview(long id) {
		// TODO Auto-generated method stub
		reviewRepo.deleteById(id);
	}
}
