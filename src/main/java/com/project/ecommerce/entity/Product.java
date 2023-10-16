package com.project.ecommerce.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	private String productName;
	private double productPrice;
	private String productDescription;
	
	@ManyToMany
	private List<Category> category;
//	@OneToOne(mappedBy="product")
//	private AddToCart addToCart;
	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<Review> review;
	
	@OneToOne(mappedBy = "product")
	@JsonIgnore
	private Wishlist wishlist;
}
