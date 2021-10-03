package com.revature.bms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bms.dao.AccountDAO;
import com.revature.bms.dao.TransactionDAO;
import com.revature.bms.dto.TransactionDetailsDto;
import com.revature.bms.entity.Account;
import com.revature.bms.entity.TransactionDetails;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.mapper.TransactionMapper;
import com.revature.bms.service.TransactionService;

@Service
public class TransacationServiceImpl implements TransactionService {

	@Autowired
	TransactionDAO transactionDAO;

	@Autowired
	AccountDAO accountDAO;

	@Override
	public String addTransaction(TransactionDetailsDto transactionDetailsDto) {

		if (transactionDetailsDto != null && transactionDetailsDto.getAccount() != null) {
			if (transactionDetailsDto.getAccount().getAccountType() == null)
				throw new InvalidInputException("Account details are Not Found to add!");

			String accountNo = transactionDetailsDto.getAccount().getAccountType().getAccountNo();
			if (accountDAO.getAccountByAccountNo(accountNo) == null)
				throw new IdNotFoundException("accountNo:" + accountNo + " Not Found to add Transacation!");

			Account account = accountDAO.getAccountByAccountNo(accountNo);
			transactionDetailsDto.setAccount(account);

			TransactionDetails details = TransactionMapper.dtoToEntity(transactionDetailsDto);
			details.setTransactionDate(new Date());

			return transactionDAO.addTransaction(details);
		}
		throw new InvalidInputException("Transaction details are Not Found to add!");

	}

	@Override
	public List<TransactionDetails> viewAllTransaction() {

		List<TransactionDetails> transactions = transactionDAO.viewAllTransaction();
		return (transactions != null) ? transactions : null;

	}

	@Override
	public List<TransactionDetails> viewTransactionByAccount(Long accountId) {

		List<TransactionDetails> transactions = transactionDAO.viewTransactionByAccount(accountId);
		return (transactions != null) ? transactions : null;

	}

}
