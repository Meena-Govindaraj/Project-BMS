package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.TransactionDetailsDto;
import com.revature.bms.entity.TransactionDetails;

public interface TransactionService {

	/**
	 * to add transaction details for every transaction that made by customer
	 * 
	 * @param transactionDetailsDto
	 * @return string on successful creation of entry for fund transfer
	 */
	String addTransaction(TransactionDetailsDto transactionDetailsDto);

	/**
	 * to view all transactions of all customers
	 * 
	 * @return list of transaction details that all customers
	 */
	List<TransactionDetails> viewAllTransaction();

	/**
	 * to view transaction of particular customer on particular account
	 * 
	 * @param accountId
	 * @return list of transaction that customer made
	 */
	List<TransactionDetails> viewTransactionByAccount(Long accountId);
}
