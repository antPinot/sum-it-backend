/**
 * 
 */
package com.pinot.sumitbackend.exceptions;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pinot.sumitbackend.controllers.UserController;

/**
 * 
 */
@ControllerAdvice
public class GlobalExceptionhandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionhandler.class);
	
	@ExceptionHandler(value= UserNotFoundException.class)
	public ResponseEntity<Object> handleNoSuchElementExcception(UserNotFoundException ex){
		LOGGER.warn(ex.getUsername() + " has not been founded in database ");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
