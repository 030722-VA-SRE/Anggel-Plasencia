package com.revature.exceptions;

public class AuthFailedException extends RuntimeException{

	public AuthFailedException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AuthFailedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AuthFailedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AuthFailedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	
}
