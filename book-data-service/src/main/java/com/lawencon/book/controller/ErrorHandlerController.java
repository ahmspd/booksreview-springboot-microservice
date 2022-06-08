package com.lawencon.book.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import com.lawencon.book.exception.ValidateException;

@ControllerAdvice
public class ErrorHandlerController {

	@ExceptionHandler(ValidateException.class)
	public ResponseEntity<?> handleValidate(ValidateException e) {
		Map<String, Object> result = new HashMap<>();
		result.put("message", NestedExceptionUtils.getMostSpecificCause(e).getMessage());

		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<?> handleRestClientException(RestClientException e) {
		Map<String, Object> result = new HashMap<>();
		result.put("message", NestedExceptionUtils.getMostSpecificCause(e).getMessage());

		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
}
