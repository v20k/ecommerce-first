package com.project.ecommerce.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private String userName;
	private String userEmail;
	private String userPhoneNumber;
	private String password;
	
//	@OneToMany
//	private List<Role> role;
	@OneToMany(mappedBy = "user")
	private List<Address> address;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<AddToCart> addTOCart;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Review> review;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Wishlist> wishlist;
	
}
