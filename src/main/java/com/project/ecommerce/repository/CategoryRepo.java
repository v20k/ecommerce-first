package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.ecommerce.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

	@Query(value = "select c from Category c where c.categoryName=?1")
	Category findByCategoryName(String category);

}
