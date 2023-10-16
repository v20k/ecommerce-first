package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Long>{

}
