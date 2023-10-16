package com.project.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BuyIsConnectedWithPaymentException extends RuntimeException {

	private String message;
}
