package com.project.ecommerce.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.ecommerce.util.ResponseStructure;

@RestControllerAdvice
public class AppException extends ResponseEntityExceptionHandler  {

	@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
         
		   List<ObjectError> errors = ex.getAllErrors();
           Map<String, String> map=new HashMap<String, String>();
           for(ObjectError error:errors) {
        	  String message = error.getDefaultMessage();
        	  String field = ((FieldError)error).getField();
        	  map.put(field, message);
           }
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
		}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> idNotFound(IdNotFoundException ex) {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Id is Not Found");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> idNotFound(AddToCartsIsEmpty ex) {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("AddToCarts is Empty");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST); 
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> idNotFound(OrderIsConnectedWithPaymentException ex) {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Order is connected with payment, so we can't delete order");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST); 
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> idNotFound(BuyIsConnectedWithPaymentException ex) {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Buy is connected with payment, so we can't delete order");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST); 
	}
}
