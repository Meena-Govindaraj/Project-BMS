package com.revature.bms.service.impl;

import static com.revature.bms.util.BankingManagementConstants.ID_NOT_FOUND;
import static com.revature.bms.util.BankingManagementConstants.INVALID_DETAILS;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.bms.controller.MailSend;
import com.revature.bms.dao.AccountDAO;
import com.revature.bms.dao.AccountTypeDAO;
import com.revature.bms.dao.CustomerDAO;
import com.revature.bms.dto.AccountDto;
import com.revature.bms.entity.Account;
import com.revature.bms.entity.AccountType;
import com.revature.bms.entity.Customer;
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.exception.DatabaseException;
import com.revature.bms.mapper.AccountMapper;
import com.revature.bms.service.AccountService;
import com.revature.bms.util.GeneratePassword;

@Service

public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class.getName());

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private AccountTypeDAO accountTypeDAO;

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	public String addAccount(AccountDto accountDto) {

		logger.info("Add Account Called in service.... ");
		try {

			if (accountDto == null || accountDto.getAccountType() == null)
				throw new BussinessLogicException("Account " + INVALID_DETAILS);

			AccountType accountType = null;

			if (accountTypeDAO.viewAccountByTypeId(accountDto.getAccountType().getId()) == null)
				throw new BussinessLogicException("Type Id not found to add account");

			accountType = accountTypeDAO.viewAccountByTypeId(accountDto.getAccountType().getId());

			String accountNo = accountType.getAccountNo();
			if (accountTypeDAO.getAccountByAccountNo(accountNo) == null)
				throw new BussinessLogicException("accountNo:" + accountNo + ID_NOT_FOUND);

			accountType = accountTypeDAO.getAccountByAccountNo(accountNo);

			accountDto.setAccountType(accountType);
			accountDto.setTransactionPIN(GeneratePassword.generatePassword());

			logger.info(accountDto);

			Account account = AccountMapper.dtoToEntity(accountDto);

			String email = account.getAccountType().getCustomer().getEmail();
			String name = account.getAccountType().getCustomer().getName();

			String message = "Mrs./Mr. " + name + ", \n Account Created in our bank " + "\n Account Number: "
					+ account.getAccountType().getAccountNo() + "\n Mobile Number: "
					+ account.getAccountType().getCustomer().getMobileNo() + "\n IFSC Code: "
					+ account.getAccountType().getCustomer().getBranch().getIfscCode() + "\n Branch Name: "
					+ account.getAccountType().getCustomer().getBranch().getName() + "\n Transaction PIN: "
					+ account.getTransactionPIN() + "\n Account Type: " + account.getAccountType().getType();


			MailSend.sendMail(email, "Account Created Successfully", message);

		//	account.setTransactionPIN(encoder.encode(account.getTransactionPIN()));
		
			/*
			 * Customer customer=account.getAccountType().getCustomer();
			 * customer.setPassword(encoder.encode(customer.getPassword()));
			 * customerDAO.updatePassword(customer.getMobileNo(), customer.getPassword());
			 */
			return accountDAO.addAccount(account);
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Account> viewAllAccount() {

		logger.info("View All Account Called in service.... ");

		List<Account> account = null;
		try {
			account = accountDAO.viewAllAccount();
			if (account == null)
				throw new BussinessLogicException("No record Found");
			return account;

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Account getAccountByAccountNo(String accountNo) {

		Account account = null;
		logger.info("Get Account ByAccountNo Called in service.... ");
		try {
			account = accountDAO.getAccountByAccountNo(accountNo);
			if (account == null)
				throw new BussinessLogicException("No records Found");
			return account;

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Account> getCustomersByIFSC(String ifscCode) {

		logger.info("Get Customers ByIFSC Called in service.... ");

		List<Account> account = null;
		try {
			account = accountDAO.getCustomersByIFSC(ifscCode);
			if (account == null)
				throw new BussinessLogicException("No records Found");
			return account;

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}

	}

	@Override
	public Account getAccountsByType(Long customerId, String type) {

		logger.info("Get AccountsByType Called in service.... ");
		Account account = null;
		try {
			account = accountDAO.getAccountsByType(customerId, type);
			if (account == null)
				throw new BussinessLogicException("No Records Found");
			return account;

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Account> getCustomerByCustomerId(Long customerId) {

		logger.info("Get Customer ByCustomerId Called in service.... ");

		List<Account> account = null;
		try {
			account = accountDAO.getCustomerByCustomerId(customerId);
			if (account == null)
				throw new BussinessLogicException("No records Found");

			return account;

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}

	}

	@Override
	public String bankTransfer(Long senderId, Long receiverId, Long amount) {

		logger.info("Bank Transfer Called in service.... ");

		String transfer = null;
		try {
			transfer = accountDAO.bankTransfer(senderId, receiverId, amount);
			if (transfer == null)
				throw new BussinessLogicException("Transfer failed");
			return transfer;
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateTransactionPIN(Long accountId, String oldPassword,String newPassword) {

		logger.info("Update TransactionPIN Called in service.... ");

		String transfer = null;
				
		try {
			 Account account = accountDAO.getAccountByAccountId(accountId);
			
			  if( oldPassword.equals(account.getTransactionPIN())) {
			  
			 // newPassword= encoder.encode(newPassword);
			  transfer = accountDAO.updateTransactionPIN(accountId, newPassword);
			  return transfer;
			  }
			
			 throw new BussinessLogicException("Old Password not matched");
			
		
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}
}