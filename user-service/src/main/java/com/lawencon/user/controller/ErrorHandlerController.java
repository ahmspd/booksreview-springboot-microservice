package com.lawencon.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lawencon.user.exception.ValidateException;

@ControllerAdvice
public class ErrorHandlerController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorHandlerController.class);

	@ExceptionHandler(ValidateException.class)
	public ResponseEntity<?> handleValidate(ValidateException e) {
		String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}
