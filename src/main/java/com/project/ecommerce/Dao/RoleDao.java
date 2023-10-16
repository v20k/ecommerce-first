package com.project.ecommerce.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.repository.RoleRepo;

@Repository
public class RoleDao {

	@Autowired
	private RoleRepo roleRepo;
}
