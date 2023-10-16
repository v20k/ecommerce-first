package com.project.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dao.RoleDao;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
}
