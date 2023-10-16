package com.project.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class IdNotFoundException extends RuntimeException{

	private String message;
}
