package com.project.ecommerce.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addToCartId;
	private int addToCartProductQuantity;
	private boolean addToCartStatus;
	
	@ManyToOne
	@JoinColumn
	private User user;
	
	@OneToOne
//	@JoinColumn
	private Product product;
	
//	@ManyToOne
//	private Order order;
}
