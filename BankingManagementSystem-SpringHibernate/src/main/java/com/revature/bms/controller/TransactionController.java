package com.revature.bms.controller;

import static com.revature.bms.util.BankingManagementConstants.RETRIVED;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.bms.dto.TransactionDetailsDto;
import com.revature.bms.exception.BusinessLogicException;
import com.revature.bms.response.HttpResponseStatus;
import com.revature.bms.service.TransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("http://localhost:4200")
public class TransactionController {

	private static final Logger logger = LogManager.getLogger(TransactionController.class.getName());

	@Autowired
	TransactionService transactionService;

	/**
	 * to view all transactions of all customers
	 * 
	 * @return list of transaction details that all customers
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> viewAllTransacations() {

		logger.info("view All Transaction Called in Controller.. ");
		try {

			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, transactionService.viewAllTransaction()),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to add transaction details for every transaction that made by customer
	 * 
	 * @param transactionDetailsDto
	 * @return string on successful creation of entry for fund transfer
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addTransacations(
			@Valid @RequestBody TransactionDetailsDto transactionDetailsDto) {

		logger.info("Add Transaction details Called in Controller.. ");

		try {

			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),
					transactionService.addTransaction(transactionDetailsDto)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to view transaction of particular customer on particular account
	 * 
	 * @param accountId
	 * @return list of transaction that customer made
	 */
	@GetMapping("/transactionById/{accountId}")
	public ResponseEntity<HttpResponseStatus> viewTransacationsById(@PathVariable("accountId") Long accountId) {

		logger.info("View A Transaction on account in Controller.. ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					transactionService.viewTransactionByAccount(accountId)), HttpStatus.OK);
		}

		catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

}
