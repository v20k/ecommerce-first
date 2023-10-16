package com.project.ecommerce.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.User;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.repository.UserRepo;

@Repository
public class UserDao {

	@Autowired
	private UserRepo userRepo;

	public User saveUser(User user) {
       return userRepo.save(user);		
	}

	public User findUser(long id) {
      
		Optional<User> optional = userRepo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public void deleteUser(long id) {
       	userRepo.deleteById(id);	
	}
}
