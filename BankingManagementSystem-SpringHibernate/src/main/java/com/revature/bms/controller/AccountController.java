package com.revature.bms.controller;

import static com.revature.bms.util.BankingManagementConstants.RETRIVED;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.revature.bms.dao.impl.AccountDAOImpl;
import com.revature.bms.dto.AccountDto;
import com.revature.bms.entity.Account;
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.response.HttpResponseStatus;
import com.revature.bms.service.AccountService;

@RestController
@RequestMapping("/account")
@CrossOrigin("http://localhost:4200")
public class AccountController {

	private static final Logger logger = LogManager.getLogger(AccountDAOImpl.class.getName());

	@Autowired
	private AccountService accountService;

	/**
	 * to view all accounts
	 * 
	 * @return response entity with status code,message and data
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> viewAllAccount() {

		logger.info("viewAllAccount  Called in Controller.... ");

		try {

			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, accountService.viewAllAccount()),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * * to add account on customer with minimum balance based on account type
	 * 
	 * @param accountDto
	 * @return response entity with status code,message
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addAccount(@RequestBody AccountDto accountDto) {

		logger.info("Add Account Called in Controller.... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), accountService.addAccount(accountDto)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * to retrieve account details on IFSC CODE
	 * 
	 * @param ifscCode
	 * @return response entity with status code,message and data
	 */
	@GetMapping("/getCustomersByIFSC/{ifscCode}")
	public ResponseEntity<HttpResponseStatus> getCustomersByIFSC(@PathVariable("ifscCode") String ifscCode) {

		logger.info("Get Customers ByIFSC Called in Controller.... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					accountService.getCustomersByIFSC(ifscCode)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to get customer account details on customer id
	 * 
	 * @param customerId
	 * @return list of 2 or 1 account based on account that customer have a customer
	 *         can have 2 accounts(SA/CA) if two accounts created on particular
	 *         customer then details of SA AND CA will be retrieved else returns the
	 *         single account as response entity
	 */

	@GetMapping("/getCustomerByCustomerId/{customerId}")
	public ResponseEntity<HttpResponseStatus> getCustomerByCustomerId(@PathVariable("customerId") Long customerId) {

		logger.info("Get Customer ByCustomerId Called in Controller.... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					accountService.getCustomerByCustomerId(customerId)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * to retrieve account on customer Id and account type(SA/CA)
	 * 
	 * @param customerId
	 * @param type
	 * @return account details based on customer id and type
	 */
	@GetMapping("/getAccountsByType/{customerId}/{type}")
	public ResponseEntity<HttpResponseStatus> getAccountsByType(@PathVariable("customerId") Long customerId,
			@PathVariable("type") String type) {

		logger.info("Get AccountsByType Called in Controller.... ");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					accountService.getAccountsByType(customerId, type)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * to retrieve account on customer account no
	 * 
	 * @param accountNo
	 * @return response as status code, message and data(account details on matched
	 *         account no)
	 */
	@GetMapping("/getaccountbyaccountno/{accountNo}")
	public ResponseEntity<HttpResponseStatus> getAccountByAccountNo(@PathVariable("accountNo") String accountNo) {

		logger.info("Get Account ByAccountNo Called in Controller.... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					accountService.getAccountByAccountNo(accountNo)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * to transfer fund form one account to others account on transaction pin
	 * transfered from sender account id to receiver account id on given amount
	 *
	 * @param senderId
	 * @param receiverId
	 * @param amount
	 * @return response as status code,message and data
	 */
	@PutMapping("/banktransfer/{senderId}/{receiverId}/{amount}")
	public ResponseEntity<HttpResponseStatus> bankTransfer(@PathVariable("senderId") Long senderId,
			@PathVariable("receiverId") Long receiverId, @PathVariable("amount") Long amount) {

		logger.info("Bank Transfer Called in Controller.... ");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					accountService.bankTransfer(senderId, receiverId, amount)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * * to change the transaction pin on account type id SA and CA have different
	 * transaction pin , so based on account type id the transaction pin get updated
	 * 
	 * @param typeId
	 * @param newPassaword
	 * @return response as status code,message
	 */
	@PutMapping("/updatePassword/{typeId}/{newPassword}")
	public ResponseEntity<HttpResponseStatus> updatePassword(@PathVariable("typeId") Long typeId,
			@PathVariable("newPassword") String newPassaword) {

		logger.info("Update TransactionPIN Called in Controller.... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					accountService.updateTransactionPIN(typeId, newPassaword)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}
}