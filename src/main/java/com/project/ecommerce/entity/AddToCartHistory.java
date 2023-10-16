package com.project.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long historyId;
	
	private long addToCartId;
	private int addToCartProductQuantity;
	private boolean addToCartStatus;
	
	@ManyToOne
	private User user;
//	private String userName;
	
	@OneToOne
	private Product product;
//	private String productName;
}
