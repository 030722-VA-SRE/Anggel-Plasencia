package com.revature.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User not found!")
	@ExceptionHandler(UserNotFoundException.class)
	public void handleUserNotFoundException() {
   // TODO document why this method is empty
		
 }
	
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Comic(s) not found!")
	@ExceptionHandler(ComicNotFoundException.class)
	public void handleComicNotFoundException() {
   // TODO document why this method is empty
 }
	
	
	
	
	@ResponseStatus(value=HttpStatus.EXPECTATION_FAILED, reason="Username or password was incorrect. Please try again.")
	@ExceptionHandler(AuthFailedException.class)
	public void handleAuthFailedException() {
		LOG.debug("Authentication Failed");
		}
	
	
	
	
}


