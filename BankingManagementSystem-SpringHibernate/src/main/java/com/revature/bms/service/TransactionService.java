package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.TransactionDetailsDto;
import com.revature.bms.entity.TransactionDetails;

public interface TransactionService {

	public String addTransaction(TransactionDetailsDto transactionDetailsDto);

	public List<TransactionDetails> viewAllTransaction();

	public List<TransactionDetails> viewTransactionByAccount(Long accountId);

}
