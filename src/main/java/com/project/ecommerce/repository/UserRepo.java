package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
