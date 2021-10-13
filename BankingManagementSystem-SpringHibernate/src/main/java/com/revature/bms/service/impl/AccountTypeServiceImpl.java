package com.revature.bms.service.impl;

import static com.revature.bms.util.BankingManagementConstants.DUPLICATE_RECORD;
import static com.revature.bms.util.BankingManagementConstants.ID_NOT_FOUND;
import static com.revature.bms.util.BankingManagementConstants.INVALID_DETAILS;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.bms.controller.MailSend;
import com.revature.bms.dao.AccountTypeDAO;
import com.revature.bms.dao.CustomerDAO;
import com.revature.bms.dto.AccountTypeDto;
import com.revature.bms.entity.AccountType;
import com.revature.bms.entity.Customer;
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.exception.DatabaseException;
import com.revature.bms.mapper.AccountTypeMapper;
import com.revature.bms.service.AccountTypeSevice;
import com.revature.bms.util.GeneratePassword;

@Service
public class AccountTypeServiceImpl implements AccountTypeSevice {

	private static final Logger logger = LogManager.getLogger(AccountTypeServiceImpl.class.getName());

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AccountTypeDAO accountTypeDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public String addAccountType(AccountTypeDto accountTypeDto) {

		logger.info("Add AccountType Called in Service.... ");

		try {
			if (accountTypeDto != null && accountTypeDto.getCustomer() != null) {

				Long customerId = accountTypeDto.getCustomer().getId();
				if (customerDAO.isCustomerExistsById(customerId))
					throw new BussinessLogicException("Customer Id:" + customerId + ID_NOT_FOUND);

				Customer customer = customerDAO.viewCustomerById(customerId);

				// to check account type already exists or not
				if (accountTypeDAO.isAccountTypeExists(customer.getMobileNo(), customer.getEmail(),
						accountTypeDto.getType()) != null)
					throw new BussinessLogicException("Account Type: " + accountTypeDto.getType() + DUPLICATE_RECORD
							+ " customerID: " + customer.getId());

				accountTypeDto.setAccountNo(GeneratePassword.generateAccountNo());
				accountTypeDto.setAccountStatus("No");
			
				MailSend.sendMail(customer.getEmail(), " Account Credentials",
						"Regsitered Phone No: " + customer.getMobileNo() + "\n Password: " + customer.getPassword());

				customer.setPassword(encoder.encode(customer.getPassword()));
				customerDAO.updatePassword(customer.getMobileNo(), customer.getPassword());
				
				accountTypeDto.setCustomer(customer);
				
				AccountType accountType = AccountTypeMapper.dtoToEntity(accountTypeDto);

				return accountTypeDAO.addAccountType(accountType);

			} else
				throw new BussinessLogicException("Account " + INVALID_DETAILS);

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateAccountType(AccountTypeDto accountTypeDto) {

		logger.info("Update AccountType Called in Service.... ");
		try {
			if (accountTypeDto != null && accountTypeDto.getCustomer() != null) {

				if (accountTypeDAO.getAccountByAccountNo(accountTypeDto.getAccountNo()) == null)
					throw new BussinessLogicException("Account NO:" + accountTypeDto.getAccountNo() + ID_NOT_FOUND);
				if (accountTypeDAO.isAccountExists(accountTypeDto.getId()))
					throw new BussinessLogicException("Type Id:" + accountTypeDto.getId() + ID_NOT_FOUND);

				Customer customer = accountTypeDto.getCustomer();

				customer = customerDAO.viewCustomerById(customer.getId());
				accountTypeDto.setCustomer(customer);

				AccountType accountType = AccountTypeMapper.dtoToEntity(accountTypeDto);
				return accountTypeDAO.updateAccountType(accountType);

			}

			else
				throw new BussinessLogicException("Account " + INVALID_DETAILS);

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String deleteAccountType(Long typeId) {

		logger.info("Delete AccountType Called in Service.... ");
		try {
			if (accountTypeDAO.isAccountExists(typeId))
				throw new BussinessLogicException("AccountType Id: " + typeId + ID_NOT_FOUND);

			return accountTypeDAO.deleteAccountType(typeId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<AccountType> getAccountsByType(String type) {

		logger.info("Get AccountsBy Type Called in Service.... ");
		List<AccountType> accountType = null;

		try {
			accountType = accountTypeDAO.getAccountsByType(type);
			if (accountType != null)
				return accountType;
			throw new BussinessLogicException("No Record Found");
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public AccountType getAccountByAccountNo(String accountNo) {

		logger.info("Get AccountBy AccountNo Called in Service.... ");
		try {
			if (accountTypeDAO.getAccountByAccountNo(accountNo) == null)
				throw new BussinessLogicException("Account  No:" + accountNo + ID_NOT_FOUND);
			return accountTypeDAO.getAccountByAccountNo(accountNo);

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<AccountType> viewCustomerById(Long customerId) {

		logger.info("View CustomerBy Id Called in Service.... ");

		List<AccountType> accountType = null;

		try {
			accountType = accountTypeDAO.viewCustomerById(customerId);
			if (accountType != null)
				return accountType;
			throw new BussinessLogicException("No Record Found");
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}

	}

	@Override
	public AccountType isAccountTypeExists(String mobileNo, String email, String type) {

		logger.info("Is AccountType Exists Called in Service.... ");

		AccountType accountType = null;
		try {
			accountType = accountTypeDAO.isAccountTypeExists(mobileNo, email, type);
			if (accountType != null)
				return accountType;
			throw new BussinessLogicException("No Record Found");
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateAccountStatus(String accountStatus, String accountNo) {

		logger.info("Update AccountStatus Called in Service.... ");
		try {
			if (accountTypeDAO.getAccountByAccountNo(accountNo) == null)
				throw new BussinessLogicException("Account No:" + accountNo + ID_NOT_FOUND);

			return accountTypeDAO.updateAccountStatus(accountStatus, accountNo);

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<AccountType> getCustomersByIFSC(String ifscCode) {

		logger.info("viewAllCustomer BY Ifsc Called in Service.... ");

		List<AccountType> accountType = null;
		try {
			accountType = accountTypeDAO.getCustomersByIFSC(ifscCode);
			if (accountType != null)
				return accountType;
			throw new BussinessLogicException("No Record Found");
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

}
