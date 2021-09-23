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

import com.revature.bms.dto.AccountTypeDto;
import com.revature.bms.entity.AccountType;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.service.AccountTypeSevice;

@RestController
@RequestMapping("/accounttype")
@CrossOrigin("http://localhost:4200")
public class AccountTypeController {

	@Autowired
	private AccountTypeSevice accountTypeSevice;

	@PostMapping
	public ResponseEntity<String> addAccountType(@RequestBody AccountTypeDto accountTypeDto) {

		System.out.println(accountTypeDto);
		return new ResponseEntity<>(accountTypeSevice.addAccountType(accountTypeDto), HttpStatus.OK);

	}

	@DeleteMapping("/{typeId}")
	public ResponseEntity<String> deleteAccountType(@PathVariable("typeId") Long typeId) {

		return new ResponseEntity<>(accountTypeSevice.deleteAccountType(typeId), HttpStatus.OK);

	}

	@GetMapping("getByAccountType/{accountType}")
	public ResponseEntity<List<AccountType>> viewAccountByAccountType(@PathVariable("accountType") String accountType) {

		return new ResponseEntity<>(accountTypeSevice.getAccountsByType(accountType), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<AccountType>> viewAllAccount() {

		return new ResponseEntity<>(accountTypeSevice.viewAllAccount(), HttpStatus.OK);
	}

	@GetMapping("getByAccountNumber/{accountNo}")
	public ResponseEntity<AccountType> viewAccountByAccountNumber(@PathVariable("accountNo") String accountNo) {

		return new ResponseEntity<>(accountTypeSevice.getAccountByAccountNo(accountNo), HttpStatus.OK);

	}

	@PutMapping
	public ResponseEntity<String> updateAccountType(@RequestBody AccountTypeDto accountTypeDto) {
		return new ResponseEntity<>(accountTypeSevice.updateAccountType(accountTypeDto), HttpStatus.OK);
	}

	// exception for Duplication
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateException e) {

		System.out.println(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// exception for Id not Found 
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
