package com.revature.bms.exceptionhandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.revature.bms.exception.BusinessLogicException;
import com.revature.bms.exception.DatabaseException;
import com.revature.bms.response.HttpResponseStatus;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	// EXCEPTION HANDLER FOR DATABASE EXCEPTION
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<HttpResponseStatus> databaseException(DatabaseException e) {

		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	// EXCEPTION HANDLER FOR BUSSINESSLOGICEXCEPTION
	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<HttpResponseStatus> bussinessException(BusinessLogicException e) {
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	// METHOD ARGUMENT TYPE MISMATCH EXCEPTION
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<HttpResponseStatus> invalidInputArgumentsFound(MethodArgumentTypeMismatchException e) {
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), "Input not matched for given URL"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<HttpResponseStatus> inputMismatchException(HttpMessageNotReadableException e) {
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getRootCause().toString()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}
	

	//NOT NUL AND MIN MAX VALIDATION EXCEPTION
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HttpResponseStatus> handleValidationExceptions(MethodArgumentNotValidException ex) {
	Map<String, String> errors = new HashMap<>();
	List<String > l=new ArrayList<String>();
	ex.getBindingResult().getAllErrors().forEach((error) -> {
	String fieldName = error.getObjectName();
	String errorMessage = error.getDefaultMessage();
	l.add(errorMessage);
	errors.put(fieldName, errorMessage);
	});
	return new ResponseEntity<>(
	new HttpResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY.value(),l.get(0)),
	HttpStatus.UNPROCESSABLE_ENTITY); }
	
	
}
