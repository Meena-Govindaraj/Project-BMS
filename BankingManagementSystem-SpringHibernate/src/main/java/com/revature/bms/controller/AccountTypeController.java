package com.revature.bms.controller;

import static com.revature.bms.util.BankingManagementConstants.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.bms.dto.AccountTypeDto;
import com.revature.bms.entity.AccountType;
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

	@PostMapping
	public ResponseEntity<HttpResponseStatus> addAccountType(@RequestBody AccountTypeDto accountTypeDto) {

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

	@PutMapping
	public ResponseEntity<String> updateAccountType(@RequestBody AccountTypeDto accountTypeDto) {

		logger.info("Update AccountType Called in Controller.... ");
		return new ResponseEntity<>(accountTypeSevice.updateAccountType(accountTypeDto), HttpStatus.OK);
	}

	@PutMapping("/updateAccountStatus/{accountStatus}/{accountNo}")
	public ResponseEntity<HttpResponseStatus> updateAccountType(@PathVariable("accountStatus") String accountStatus,
			@PathVariable("accountNo") String accountNo) {

		logger.info("Update Status of account Called in Controller.... ");
	
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(),accountTypeSevice.updateAccountStatus(accountStatus, accountNo)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
		
	}

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

	@GetMapping("/accountExists/{mobileNo}/{email}/{type}")
	public ResponseEntity<HttpResponseStatus> isAccountTypeExists(@PathVariable("mobileNo") String mobileNo, String email,
			String type) {

		logger.info("Is AccountType Exists Called in Controller.... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, accountTypeSevice.isAccountTypeExists(mobileNo, email, type)),
					HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	
	}

	@GetMapping("/getCustomersByIFSC/{ifscCode}")
	public ResponseEntity<HttpResponseStatus> getCustomersByIFSC(@PathVariable("ifscCode") String ifscCode) {

		logger.info("viewAllCustomer BY Ifsc Called in Controller.... ");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, accountTypeSevice.getCustomersByIFSC(ifscCode)),
					HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

}
