package com.revature.bms.controller;

import static com.revature.bms.util.BankingManagementConstants.RETRIVED;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	/**
	 * to add customer with given details
	 * 
	 * @param customer
	 * @return string on successful creation
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addCustomer(@Valid @RequestBody CustomerDto customerDto) {

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

	/**
	 * to delete customer
	 * 
	 * @param customerId
	 * @return string on successful deletion
	 */
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

	/**
	 * to get details of customer on customer ID
	 * 
	 * @param customerId
	 * @return customer data on matched customer Id
	 */

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

	/**
	 * to rest customer login password
	 * 
	 * @param email
	 * @param password
	 * @return string successful updation of password
	 */

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

	/**
	 * to view all customers
	 * 
	 * @return list of customer that created
	 */

	@GetMapping
	public ResponseEntity<HttpResponseStatus> viewAllCustomer() {

		logger.info("viewAllCustomer Called in Controller.... ");

		try {

			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, customerService.viewAllCustomer()),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to update customer with given details
	 * 
	 * @param customer
	 * @return string on successful updation
	 */
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

	/**
	 * to get customer details on unique mobile no
	 * 
	 * @param mobileNo
	 * @return customer details on matched mobile no
	 */
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

	/**
	 * to get customer details on email
	 * 
	 * @param email
	 * @return customer details on matched email
	 */
	@GetMapping("/getCustomerByEmail/{email}")
	public ResponseEntity<HttpResponseStatus> getCustomerByEmail(@PathVariable("email") String email) {

		logger.info("Get CustomerBy email called in customer Controller");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, customerService.getCustomerByEmail(email)),
					HttpStatus.OK);

		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to update the login password of customer
	 * 
	 * @param mobileNo
	 * @param password
	 * @return string on successful updation
	 */
	@PutMapping("/updatePassword/{mobileNo}/{newPassword}")
	public ResponseEntity<HttpResponseStatus> updatePassword(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("newPassword") String newPassword) {

		logger.info("Update password called in customer Controller");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),
					customerService.updatePassword(mobileNo, newPassword)), HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to get customer details based on IFSC Code
	 * 
	 * @param ifscCode
	 * @return list of customers on given IFSC Code
	 */

	@GetMapping("/getCustomersByIFSC/{ifscCode}")
	public ResponseEntity<HttpResponseStatus> getCustomersByIFSC(@PathVariable("ifscCode") String ifscCode) {

		logger.info("Get CustomerBy IFSC called in customer Controller");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					customerService.getCustomersByIFSC(ifscCode)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

	//NOT NUL AND MIN MAX VALIDATION EXCEPTION
	
		@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public Map<String, String> handleValidationExceptions(
		  MethodArgumentNotValidException ex) {
		  
			logger.error("######Validation error");
			Map<String, String> errors = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
		    return errors;
		 
		}
}
