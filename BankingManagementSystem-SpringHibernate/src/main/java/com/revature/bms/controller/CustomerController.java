package com.revature.bms.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.bms.dao.impl.CustomerDAOImpl;
import com.revature.bms.dto.CustomerDto;
import com.revature.bms.entity.Customer;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {

	private static final Logger logger = LogManager.getLogger(CustomerDAOImpl.class.getName());

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customerDto) {

		logger.debug("Add Customer Called in controller.... ");

		return new ResponseEntity<>(customerService.addCustomer(customerDto), HttpStatus.OK);

	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Long customerId) {

		logger.debug("Delete Customer Called in Controller.... ");

		return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);

	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable("customerId") Long customerId) {

		logger.debug("Is Customer Exists By customerId Called in Controller.... ");

		return new ResponseEntity<>(customerService.viewCustomerById(customerId), HttpStatus.OK);

	}

	@PutMapping("forgetPassword/{email}")
	public ResponseEntity<String> forgetPassword(@PathVariable("email") String email) {

		logger.debug("Forget Password called in customer Controller");

		return new ResponseEntity<>(customerService.forgetPassword(email), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<Customer>> viewAllCustomer() {

		logger.debug("viewAllCustomer Called in Controller.... ");

		return new ResponseEntity<>(customerService.viewAllCustomer(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customerDto) {

		logger.debug("update customer Called in Controller.... ");

		return new ResponseEntity<>(customerService.updateCustomer(customerDto), HttpStatus.OK);
	}

	@GetMapping("getCustomerByMobileNo/{mobileNo}")
	public ResponseEntity<Customer> viewCyustomerByMobileNo(@PathVariable("mobileNo") String mobileNo) {

		logger.debug("Get CustomerBy MobileNo called in customer Controller");

		return new ResponseEntity<>(customerService.getCustomerByMobileNo(mobileNo), HttpStatus.OK);

	}

	@GetMapping("getCustomerByEmail/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email") String email) {

		logger.debug("Get CustomerBy email called in customer Controller");

		return new ResponseEntity<>(customerService.getCustomerByEmail(email), HttpStatus.OK);

	}

	@PutMapping("updatePassword/{mobileNo}/{newPassword}")
	public ResponseEntity<String> updatePassword(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("newPassword") String newPassword) {

		logger.debug("Update password called in customer Controller");

		return new ResponseEntity<>(customerService.updatePassword(mobileNo, newPassword), HttpStatus.OK);

	}

	@GetMapping("/customerLogin/{mobileNo}/{password}")
	public ResponseEntity<Customer> validateCustomerLogin(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("password") String password) {

		logger.debug("validate Customer Login called in customer Controller");

		return new ResponseEntity<>(customerService.validateCustomerLogin(mobileNo, password), HttpStatus.OK);

	}

	@GetMapping("getCustomersByIFSC/{ifscCode}")
	public ResponseEntity<List<Customer>> getCustomersByIFSC(@PathVariable("ifscCode") String ifscCode) {

		logger.debug("Get CustomerBy IFSC called in customer Controller");

		return new ResponseEntity<>(customerService.getCustomersByIFSC(ifscCode), HttpStatus.OK);
	}

	// exception for Duplication
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateException e) {

		logger.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// exception for Id not Found ..
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {

		logger.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	// exception for invalid input
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<String> invalidInput(InvalidInputException e) {

		logger.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
