package com.revature.bms.controller;

import static com.revature.bms.util.BankingManagementConstants.RETRIVED;

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
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.response.HttpResponseStatus;
import com.revature.bms.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {

	private static final Logger logger = LogManager.getLogger(CustomerController.class.getName());

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<HttpResponseStatus> addCustomer(@RequestBody CustomerDto customerDto) {

		logger.info("Add Customer Called in controller.... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), customerService.addCustomer(customerDto)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<HttpResponseStatus> deleteCustomer(@PathVariable("customerId") Long customerId) {

		logger.info("Delete Customer Called in Controller.... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), customerService.deleteCustomer(customerId)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/{customerId}")
	public ResponseEntity<HttpResponseStatus> viewCustomerById(@PathVariable("customerId") Long customerId) {

		logger.info("Is Customer Exists By customerId Called in Controller.... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					customerService.viewCustomerById(customerId)), HttpStatus.OK);

		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	@PutMapping("/forgetPassword/{email}")
	public ResponseEntity<HttpResponseStatus> forgetPassword(@PathVariable("email") String email) {

		logger.info("Forget Password called in customer Controller");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), customerService.forgetPassword(email)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
		
	}

	@GetMapping
	public ResponseEntity<HttpResponseStatus> viewAllCustomer() {

		logger.info("viewAllCustomer Called in Controller.... ");

		try {
			
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(),RETRIVED , customerService.viewAllCustomer()),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateCustomer(@RequestBody CustomerDto customerDto) {

		logger.info("update customer Called in Controller.... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), customerService.updateCustomer(customerDto)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/getCustomerByMobileNo/{mobileNo}")
	public ResponseEntity<HttpResponseStatus> viewCustomerByMobileNo(@PathVariable("mobileNo") String mobileNo) {

		logger.info("Get CustomerBy MobileNo called in customer Controller");
		
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					customerService.getCustomerByMobileNo(mobileNo)), HttpStatus.OK);

		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
		
	}

	@GetMapping("/getCustomerByEmail/{email}")
	public ResponseEntity<HttpResponseStatus> getCustomerByEmail(@PathVariable("email") String email) {

		logger.info("Get CustomerBy email called in customer Controller");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					customerService.getCustomerByEmail(email)), HttpStatus.OK);

		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
		
	}

	@PutMapping("/updatePassword/{mobileNo}/{newPassword}")
	public ResponseEntity<HttpResponseStatus> updatePassword(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("newPassword") String newPassword) {

		logger.info("Update password called in customer Controller");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), customerService.updatePassword(mobileNo, newPassword)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	
	}

	@GetMapping("/customerLogin/{mobileNo}/{password}")
	public ResponseEntity<HttpResponseStatus> validateCustomerLogin(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("password") String password) {

		logger.info("validate Customer Login called in customer Controller");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					customerService.validateCustomerLogin(mobileNo, password)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/getCustomersByIFSC/{ifscCode}")
	public ResponseEntity<HttpResponseStatus> getCustomersByIFSC(@PathVariable("ifscCode") String ifscCode) {

		logger.info("Get CustomerBy IFSC called in customer Controller");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, customerService.getCustomersByIFSC(ifscCode)),
					HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

}
