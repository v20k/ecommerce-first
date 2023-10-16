package com.project.ecommerce.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.ReviewBackup;
import com.project.ecommerce.repository.ReviewBackupRepo;

@Repository
public class ReviewBackupDao {

	@Autowired
	private ReviewBackupRepo reviewBackupRepo;

	public ReviewBackup saveReviewBackup(ReviewBackup reviewBackup) {
		// TODO Auto-generated method stub
		return reviewBackupRepo.save(reviewBackup);
	}
	
	
}
