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

import com.revature.bms.dto.BankDto;
import com.revature.bms.entity.Bank;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.service.BankService;

@RestController
@RequestMapping("/bank")
@CrossOrigin("http://localhost:4200")
public class BankController {

	@Autowired
	private BankService bankService;

	// insert a bank..
	@PostMapping
	public ResponseEntity<String> addBank(@RequestBody BankDto bankDto) {

		return new ResponseEntity<>(bankService.addBank(bankDto), HttpStatus.OK);

	}

	// get all banks..
	@GetMapping
	public ResponseEntity<List<Bank>> viewAllBanks() {

		return new ResponseEntity<>(bankService.viewAllBanks(), HttpStatus.OK);
	}

	// update bank..
	@PutMapping
	public ResponseEntity<String> updateBank(@RequestBody BankDto bankDto) {

		return new ResponseEntity<>(bankService.updateBank(bankDto), HttpStatus.OK);

	}

	// get bank by BankId..
	@GetMapping("/{bankId}")
	public ResponseEntity<Bank> viewBankById(@PathVariable("bankId") Long bankId) {

		return new ResponseEntity<>(bankService.viewBankById(bankId), HttpStatus.OK);

	}

	// delete bank by Id..
	@DeleteMapping("/{bankId}")
	public ResponseEntity<String> deleteBank(@PathVariable("bankId") Long bankId) {

		return new ResponseEntity<>(bankService.deleteBank(bankId), HttpStatus.OK);

	}

	// exception for Duplicate bank insertion..
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// exception for Id not Found ..
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
