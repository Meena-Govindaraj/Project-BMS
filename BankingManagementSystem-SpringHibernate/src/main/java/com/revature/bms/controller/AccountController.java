package com.revature.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.bms.dto.AccountDto;
import com.revature.bms.entity.Account;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.service.AccountService;

@RestController
@RequestMapping("/account")
@CrossOrigin("http://localhost:4200")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping
	public ResponseEntity<List<Account>> viewAllAccount() {

		return new ResponseEntity<>(accountService.viewAllAccount(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> addAccount(@RequestBody AccountDto accountDto) {

		return new ResponseEntity<>(accountService.addAccount(accountDto), HttpStatus.OK);

	}

	@GetMapping("getCustomersByIFSC/{ifscCode}")
	public ResponseEntity<List<Account>> getCustomersByIFSC(@PathVariable("ifscCode") String ifscCode) {

		return new ResponseEntity<>(accountService.getCustomersByIFSC(ifscCode), HttpStatus.OK);
	}

	@GetMapping("getCustomerByCustomerId/{customerId}")
	public ResponseEntity<List<Account>> getCustomerByCustomerId(@PathVariable("customerId") Long customerId) {

		return new ResponseEntity<>(accountService.getCustomerByCustomerId(customerId), HttpStatus.OK);
	}

	@GetMapping("getAccountsByType/{customerId}/{type}")
	public ResponseEntity<Account> getAccountsByType(@PathVariable("customerId") Long customerId,
			@PathVariable("type") String type) {

		return new ResponseEntity<>(accountService.getAccountsByType(customerId, type), HttpStatus.OK);
	}
	
	@GetMapping("getaccountbyaccountno/{accountNo}")
	public ResponseEntity<Account> getAccountByAccountNo(@PathVariable("accountNo") String accountNo) {

		return new ResponseEntity<>(accountService.getAccountByAccountNo(accountNo), HttpStatus.OK);
	}

	@PutMapping("/banktransfer/{senderId}/{receiverId}/{amount}")
	public ResponseEntity<String> bankTransfer(@PathVariable("senderId") Long senderId,
			@PathVariable("receiverId") Long receiverId, @PathVariable("amount") Long amount) {
		return new ResponseEntity<>(accountService.bankTransfer(senderId, receiverId, amount), HttpStatus.OK);
	}
	

	@PutMapping("updatePassword/{typeId}/{newPassword}")
	public ResponseEntity<String> updatePassword(@PathVariable("typeId") Long typeId, @PathVariable("newPassword") String newPassaword) {

		return new ResponseEntity<>(accountService.updateTransactionPIN(typeId, newPassaword), HttpStatus.OK);

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
