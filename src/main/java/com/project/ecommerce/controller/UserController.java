package com.project.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entity.User;
import com.project.ecommerce.service.UserService;
import com.project.ecommerce.util.ResponseStructure;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<User>> findUser(@RequestParam long id) {
		return userService.findUser(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestParam long id,@RequestBody User user) {
		return userService.updateUser(id,user);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<String>> deleteUser(@RequestParam long id) {
		return userService.deleteUser(id);
	}
}
