package com.project.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderIsConnectedWithPaymentException extends RuntimeException {

	private String message;
}
