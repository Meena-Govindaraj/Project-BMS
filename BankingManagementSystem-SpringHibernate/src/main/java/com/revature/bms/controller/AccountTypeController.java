package com.revature.bms.controller;

import static com.revature.bms.util.BankingManagementConstants.RETRIVED;

import java.util.HashMap;
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

import com.revature.bms.dto.AccountTypeDto;
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.response.HttpResponseStatus;
import com.revature.bms.service.AccountTypeSevice;

@RestController
@RequestMapping("/accounttype")
@CrossOrigin("http://localhost:4200")
public class AccountTypeController {

	private static final Logger logger = LogManager.getLogger(AccountTypeController.class.getName());

	@Autowired
	private AccountTypeSevice accountTypeSevice;

	/**
	 * to add account type of customer with auto generated account type a customer
	 * can have two accounts SA AND CA for each account account will be generated
	 * with same customer Id
	 * 
	 * @param accountTypeDto
	 * @return string if account added successfully
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addAccountType(@Valid @RequestBody AccountTypeDto accountTypeDto) {

		logger.info("Add AccountType Called in Controller.... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), accountTypeSevice.addAccountType(accountTypeDto)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * to delete particular account type of customers type id
	 * 
	 * @param typeId
	 * @return string if account deleted successfully
	 */
	@DeleteMapping("/{typeId}")
	public ResponseEntity<HttpResponseStatus> deleteAccountType(@PathVariable("typeId") Long typeId) {

		logger.info("Delete AccountType Called in Controller.... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), accountTypeSevice.deleteAccountType(typeId)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * to retrieve account details on account type
	 * 
	 * @param type
	 * @return list of accounts on type
	 */
	@GetMapping("/getByAccountType/{accountType}")
	public ResponseEntity<HttpResponseStatus> viewAccountByAccountType(
			@PathVariable("accountType") String accountType) {

		logger.info("Get AccountsBy Type Called in Controller.... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					accountTypeSevice.getAccountsByType(accountType)), HttpStatus.OK);

		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to get account details on account no
	 * 
	 * @param accountNo
	 * @return particular account on matched account no
	 */

	@GetMapping("/getByAccountNumber/{accountNo}")
	public ResponseEntity<HttpResponseStatus> viewAccountByAccountNumber(@PathVariable("accountNo") String accountNo) {

		logger.info("Get AccountBy AccountNo Called in Controller.... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					accountTypeSevice.getAccountByAccountNo(accountNo)), HttpStatus.OK);

		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to update account type
	 * 
	 * @param accountTypeDto
	 * @return string if account updated successfully
	 */

	@PutMapping
	public ResponseEntity<String> updateAccountType(@RequestBody AccountTypeDto accountTypeDto) {

		logger.info("Update AccountType Called in Controller.... ");
		return new ResponseEntity<>(accountTypeSevice.updateAccountType(accountTypeDto), HttpStatus.OK);
	}

	/**
	 * to update the status of account while creating account status will be no
	 * after accepting by employee the status get updated to yes
	 * 
	 * @param accountStatus
	 * @param accountNo
	 * @return string on successful updation
	 */
	@PutMapping("/updateAccountStatus/{accountStatus}/{accountNo}")
	public ResponseEntity<HttpResponseStatus> updateAccountTypeStatus(
			@PathVariable("accountStatus") String accountStatus, @PathVariable("accountNo") String accountNo) {

		logger.info("Update Status of account Called in Controller.... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),
					accountTypeSevice.updateAccountStatus(accountStatus, accountNo)), HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * get account on IFSC CODE to show the employees branch customer
	 * 
	 * @param ifscCode
	 * @return list of customer on matched IFSC CODE
	 */
	@GetMapping("/getCustomerById/{customerId}")
	public ResponseEntity<HttpResponseStatus> viewCustomerById(@PathVariable("customerId") Long customerId) {

		logger.info("View CustomerBy Id Called in Controller.... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					accountTypeSevice.viewCustomerById(customerId)), HttpStatus.OK);

		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to check account already exists on this type with same mobile no
	 * 
	 * @param mobileNo
	 * @param email
	 * @param type
	 * @return account if exists
	 */
	@GetMapping("/accountExists/{mobileNo}/{email}/{type}")
	public ResponseEntity<HttpResponseStatus> isAccountTypeExists(@PathVariable("mobileNo") String mobileNo,
			String email, String type) {

		logger.info("Is AccountType Exists Called in Controller.... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					accountTypeSevice.isAccountTypeExists(mobileNo, email, type)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * get account on IFSC CODE to show the employees branch customer
	 * 
	 * @param ifscCode
	 * @return list of customer on matched IFSC CODE
	 */
	@GetMapping("/getCustomersByIFSC/{ifscCode}")
	public ResponseEntity<HttpResponseStatus> getCustomersByIFSC(@PathVariable("ifscCode") String ifscCode) {

		logger.info("viewAllCustomer BY Ifsc Called in Controller.... ");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					accountTypeSevice.getCustomersByIFSC(ifscCode)), HttpStatus.OK);
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
