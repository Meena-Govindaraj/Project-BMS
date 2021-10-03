package com.revature.bms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.bms.dto.TransactionDetailsDto;
import com.revature.bms.entity.TransactionDetails;
import com.revature.bms.service.TransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("http://localhost:4200")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@GetMapping
	public ResponseEntity<List<TransactionDetails>> viewAllTransacations() {

		return new ResponseEntity<>(transactionService.viewAllTransaction(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> addTransacations(@RequestBody TransactionDetailsDto transactionDetailsDto) {

		return new ResponseEntity<>(transactionService.addTransaction(transactionDetailsDto), HttpStatus.OK);
	}

	@GetMapping("transactionById/{accountId}")
	public ResponseEntity<List<TransactionDetails>> viewTransacationsById(@PathVariable("accountId") Long accountId) {

		return new ResponseEntity<>(transactionService.viewTransactionByAccount(accountId), HttpStatus.OK);
	}
}
