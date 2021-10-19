package com.revature.bms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.revature.bms.util.BankingManagementConstants.*;

import com.revature.bms.dao.AccountDAO;
import com.revature.bms.dao.AccountTypeDAO;
import com.revature.bms.dao.TransactionDAO;
import com.revature.bms.dto.TransactionDetailsDto;
import com.revature.bms.entity.Account;
import com.revature.bms.entity.AccountType;
import com.revature.bms.entity.TransactionDetails;
import com.revature.bms.exception.BusinessLogicException;
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

	@Autowired
	private AccountTypeDAO accountTypeDAO;

	@Override
	public String addTransaction(TransactionDetailsDto transactionDetailsDto) {

		logger.info("Add Transaction details Called in Service ");
		try {

			if (transactionDetailsDto == null || transactionDetailsDto.getAccount() == null)
				throw new BusinessLogicException("Transaction details " + INVALID_DETAILS);

			Account account = accountDAO.getAccountByAccountId(transactionDetailsDto.getAccount().getId());

			if (account == null)
				throw new BusinessLogicException("Account Id " + ID_NOT_FOUND);

			AccountType accountType = accountTypeDAO.viewAccountByTypeId(account.getAccountType().getId());

			String accountNo = accountType.getAccountNo();

			if (accountDAO.getAccountByAccountNo(accountNo) == null)
				throw new BusinessLogicException("accountNo:" + accountNo + ID_NOT_FOUND);

			account = accountDAO.getAccountByAccountNo(accountNo);

			transactionDetailsDto.setAccount(account);

			TransactionDetails details = TransactionMapper.dtoToEntity(transactionDetailsDto);
			details.setTransactionDate(new Date());

			return transactionDAO.addTransaction(details);

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<TransactionDetails> viewAllTransaction() {

		logger.info("view All Transaction Called in Service ");
		List<TransactionDetails> details = null;

		try {
			details = transactionDAO.viewAllTransaction();
			if (details == null)
				throw new BusinessLogicException("No record Found");
			return details;

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public List<TransactionDetails> viewTransactionByAccount(Long accountId) {

		logger.info("View A Transaction on account in Service ");

		List<TransactionDetails> details = null;
		try {
			details = transactionDAO.viewTransactionByAccount(accountId);
			if (details == null)
				throw new BusinessLogicException("No record Found");

			return details;

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

}
