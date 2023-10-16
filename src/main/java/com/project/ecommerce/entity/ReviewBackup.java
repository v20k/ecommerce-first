package com.project.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewBackup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reviewBackupId;
	
	private long reviewId;
	private String description;
	
//	private User user;
	private String userName;
	
//	private Product product;
	private String productName;
}
