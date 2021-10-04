package com.revature.bms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.revature.bms.util.BankingManagementConstants.*;

import com.revature.bms.dao.AccountDAO;
import com.revature.bms.dao.TransactionDAO;
import com.revature.bms.dto.TransactionDetailsDto;
import com.revature.bms.entity.Account;
import com.revature.bms.entity.TransactionDetails;
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.exception.DatabaseException;
import com.revature.bms.mapper.TransactionMapper;
import com.revature.bms.service.TransactionService;

@Service
public class TransacationServiceImpl implements TransactionService {

	private static final Logger logger = LogManager.getLogger(TransacationServiceImpl.class.getName());

	@Autowired
	TransactionDAO transactionDAO;

	@Autowired
	AccountDAO accountDAO;

	@Override
	public String addTransaction(TransactionDetailsDto transactionDetailsDto) {

		logger.info("Add Transaction details Called in Service ");
		try {
			if (transactionDetailsDto != null && transactionDetailsDto.getAccount() != null) {
				if (transactionDetailsDto.getAccount().getAccountType() == null)
					throw new BussinessLogicException("Account details "+ID_NOT_FOUND);

				String accountNo = transactionDetailsDto.getAccount().getAccountType().getAccountNo();
				if (accountDAO.getAccountByAccountNo(accountNo) == null)
					throw new BussinessLogicException("accountNo:" + accountNo + ID_NOT_FOUND);

				Account account = accountDAO.getAccountByAccountNo(accountNo);
				transactionDetailsDto.setAccount(account);

				TransactionDetails details = TransactionMapper.dtoToEntity(transactionDetailsDto);
				details.setTransactionDate(new Date());

				return transactionDAO.addTransaction(details);
			}
			throw new BussinessLogicException("Transaction details "+INVALID_DETAILS );

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<TransactionDetails> viewAllTransaction() {

		logger.info("view All Transaction Called in Service ");
		try {
			List<TransactionDetails> transactions = transactionDAO.viewAllTransaction();
			return (transactions != null) ? transactions : null;
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}

	}

	@Override
	public List<TransactionDetails> viewTransactionByAccount(Long accountId) {

		logger.info("View A Transaction on account in Service ");
		try {
			List<TransactionDetails> transactions = transactionDAO.viewTransactionByAccount(accountId);
			return (transactions != null) ? transactions : null;

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

}
