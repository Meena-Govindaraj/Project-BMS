package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.TransactionDetails;

public interface TransactionDAO {
	
	 String addTransaction(TransactionDetails transactionDetails);
	
	 List<TransactionDetails> viewAllTransaction();
	
	 List<TransactionDetails> viewTransactionByAccount(Long accountId);
	
}
