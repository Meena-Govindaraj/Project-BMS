package com.revature.bms.mapper;

import com.revature.bms.dto.TransactionDetailsDto;
import com.revature.bms.entity.TransactionDetails;

public class TransactionMapper {

	private TransactionMapper() {
	}

	// dto to entity mapping
	public static TransactionDetails dtoToEntity(TransactionDetailsDto transactionDto) {

		TransactionDetails transaction=new TransactionDetails();
		transaction.setId(transactionDto.getId());
		transaction.setTransactionDate(transactionDto.getTransactionDate());
		transaction.setMessage(transactionDto.getMessage());
		transaction.setDeposit(transactionDto.getDeposit());
		transaction.setWithdraw(transactionDto.getWithdraw());
		transaction.setBalance(transactionDto.getBalance());
		transaction.setAccount(transactionDto.getAccount());
		
		return transaction;
	}

}
