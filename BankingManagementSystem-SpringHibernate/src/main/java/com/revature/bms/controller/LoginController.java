package com.revature.bms.controller;

import static com.revature.bms.util.BankingManagementConstants.RETRIVED;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.response.HttpResponseStatus;
import com.revature.bms.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:4200")
public class LoginController {

	private static final Logger logger = LogManager.getLogger(CustomerController.class.getName());

	@Autowired
	private LoginService loginService;

	/**
	 * to validate customer login on registered mobile no and password
	 * 
	 * @param mobileNo
	 * @param password
	 * @return customer details on matched mobile no and password
	 */
	@GetMapping("/customerLogin/{mobileNo}/{password}")
	public ResponseEntity<HttpResponseStatus> validateCustomerLogin(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("password") String password) {

		logger.info("validate Customer Login called in customer Controller");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					loginService.validateCustomerLogin(mobileNo, password)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * to validate employee login on registered credentials
	 * 
	 * @param mobileNo
	 * @param password
	 * @return returns employee data if login credentails are matched
	 */

	@GetMapping("/employeeLogin/{mobileNo}/{password}")
	public ResponseEntity<HttpResponseStatus> validateEmployeeLogin(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("password") String password) {

		logger.info("Validate Employee Login Called in Controller... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					loginService.validateEmployeeLogin(mobileNo, password)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

}
