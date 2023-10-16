package com.project.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	private int doorNo;
	private String streetName;
	private String city;
	private long pincode;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private User user;
	
}
