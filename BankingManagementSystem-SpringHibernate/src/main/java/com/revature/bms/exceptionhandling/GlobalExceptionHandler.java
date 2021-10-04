package com.revature.bms.exceptionhandling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.exception.DatabaseException;
import com.revature.bms.response.HttpResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	// Database Exception
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<HttpResponseStatus> databaseException(DatabaseException e) {

		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	// EXCEPTION HANDLER FOR BUSSINESSLOGICEXCEPTION.
	@ExceptionHandler(BussinessLogicException.class)
	public ResponseEntity<HttpResponseStatus> bussinessException (BussinessLogicException e) {
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}
		
	
}
