package com.revature.bms.controller;

import java.util.List;

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

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customerDto) {

		return new ResponseEntity<>(customerService.addCustomer(customerDto), HttpStatus.OK);

	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Long customerId) {

		return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);

	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable("customerId") Long customerId) {

		return new ResponseEntity<>(customerService.isCustomerExistsById(customerId), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<Customer>> viewAllCustomer() {

		return new ResponseEntity<>(customerService.viewAllCustomer(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customerDto) {

		return new ResponseEntity<>(customerService.updateCustomer(customerDto), HttpStatus.OK);
	}

	@GetMapping("getCustomerBymobileNo/{mobileNo}")
	public ResponseEntity<Customer> viewCyustomerByMobileNo(@PathVariable("mobileNo") String mobileNo) {

		return new ResponseEntity<>(customerService.getCustomerByMobileNo(mobileNo), HttpStatus.OK);

	}

	@PutMapping("updatePassword/{mobileNo}/{password}")
	public ResponseEntity<String> updatePassword(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("password") String password) {

		return new ResponseEntity<>(customerService.updatePassword(mobileNo, password), HttpStatus.OK);

	}

	@GetMapping("/customerLogin/{mobileNo}/{password}")
	public ResponseEntity<Customer> validateEmployeeLogin(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("password") String password) {

		return new ResponseEntity<>(customerService.validateCustomerLogin(mobileNo, password), HttpStatus.OK);

	}

	// exception for Duplication
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateException e) {

		System.out.println(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// exception for Id not Found ..
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {

		System.out.println(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	// exception for invalid input
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<String> invalidInput(InvalidInputException e) {

		System.out.println(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
