package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entity.Wishlist;

public interface WishlistRepo extends JpaRepository<Wishlist, Long>{

}
