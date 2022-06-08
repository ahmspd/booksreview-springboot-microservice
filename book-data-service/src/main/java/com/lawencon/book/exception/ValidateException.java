package com.lawencon.book.exception;

public class ValidateException extends RuntimeException {

	private static final long serialVersionUID = 305956888311582728L;

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(Throwable t) {
		super(t);
	}

	public ValidateException(String message, Throwable t) {
		super(message, t);
	}
}
