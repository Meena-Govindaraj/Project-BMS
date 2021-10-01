package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.TransactionDetails;

public interface TransactionDAO {
	
	public String addTransaction(TransactionDetails transactionDetails);
	
	public List<TransactionDetails> viewAllTransaction();
	
	public List<TransactionDetails> viewTransactionByAccount(Long accountId);
	
}
