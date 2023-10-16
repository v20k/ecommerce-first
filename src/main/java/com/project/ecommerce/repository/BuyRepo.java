package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entity.Buy;

public interface BuyRepo extends JpaRepository<Buy, Long>{

}
