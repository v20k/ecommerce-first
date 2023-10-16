package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
