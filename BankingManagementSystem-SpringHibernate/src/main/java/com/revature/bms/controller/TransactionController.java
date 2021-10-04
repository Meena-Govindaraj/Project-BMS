package com.revature.bms.controller;

import java.util.List;

import static com.revature.bms.util.BankingManagementConstants.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.bms.dao.impl.TransactionDAOImpl;
import com.revature.bms.dto.TransactionDetailsDto;
import com.revature.bms.entity.TransactionDetails;
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.response.HttpResponseStatus;
import com.revature.bms.service.TransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("http://localhost:4200")
public class TransactionController {

	private static final Logger logger = LogManager.getLogger(TransactionController.class.getName());

	@Autowired
	TransactionService transactionService;

	@GetMapping
	public ResponseEntity<HttpResponseStatus> viewAllTransacations() {

		logger.info("view All Transaction Called in Controller.. ");
		try {

			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,transactionService.viewAllTransaction()),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	@PostMapping
	public ResponseEntity<HttpResponseStatus> addTransacations(@RequestBody TransactionDetailsDto transactionDetailsDto) {

		logger.info("Add Transaction details Called in Controller.. ");

		try {

			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, transactionService.addTransaction(transactionDetailsDto)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/transactionById/{accountId}")
	public ResponseEntity<HttpResponseStatus> viewTransacationsById(@PathVariable("accountId") Long accountId) {

		logger.info("View A Transaction on account in Controller.. ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,transactionService.viewTransactionByAccount(accountId)),
					HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}
}
