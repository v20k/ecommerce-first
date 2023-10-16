package com.project.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddToCartsIsEmpty extends RuntimeException{

	private String message;
}
