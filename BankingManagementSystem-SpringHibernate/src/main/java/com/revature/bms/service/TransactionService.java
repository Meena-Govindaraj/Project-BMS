package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.TransactionDetailsDto;
import com.revature.bms.entity.TransactionDetails;

public interface TransactionService {

	 String addTransaction(TransactionDetailsDto transactionDetailsDto);

	 List<TransactionDetails> viewAllTransaction();

	 List<TransactionDetails> viewTransactionByAccount(Long accountId);
}
