package com.revature.bms.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.bms.dao.AccountTypeDAO;
import com.revature.bms.dao.CustomerDAO;
import com.revature.bms.dto.AccountTypeDto;
import com.revature.bms.entity.AccountType;
import com.revature.bms.entity.Customer;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.mapper.AccountTypeMapper;
import com.revature.bms.service.AccountTypeSevice;

@Service
public class AccountTypeServiceImpl implements AccountTypeSevice {

	@Autowired
	private AccountTypeDAO accountTypeDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public String addAccountType(AccountTypeDto accountTypeDto) {

		if (accountTypeDto != null && accountTypeDto.getCustomer() != null) {
			
			Long customerId = accountTypeDto.getCustomer().getId();
			if (customerDAO.isCustomerExistsById(customerId))
				throw new IdNotFoundException("Customer Id:" + customerId + " Not Found to add type!");
			
			
			Customer customer = customerDAO.viewCustomerById(customerId);

			System.out.println("###customer "+customer);
			// to check account type already exists or not
			if (accountTypeDAO.isAccountTypeExists(customer.getMobileNo(), customer.getEmail(),
					accountTypeDto.getType()) != null)
				throw new DuplicateException("Account Type: " + accountTypeDto.getType()
						+ "Already exists with this email and mobileno with customerID: " + customer.getId());

			accountTypeDto.setAccountNo(generateAccountNo());
			accountTypeDto.setAccountStatus("No");
			accountTypeDto.setCustomer(customer);

			AccountType accountType = AccountTypeMapper.dtoToEntity(accountTypeDto);
			
			return accountTypeDAO.addAccountType(accountType);
		
		} 
		else
			throw new InvalidInputException("Account details are Not Found to add!");

	}

	@Override
	public String updateAccountType(AccountTypeDto accountTypeDto) {

		if (accountTypeDto != null && accountTypeDto.getCustomer() != null) {

			System.out.println("###account no "+accountTypeDAO.getAccountByAccountNo(accountTypeDto.getAccountNo()));
			if (accountTypeDAO.getAccountByAccountNo(accountTypeDto.getAccountNo()) == null)
				throw new IdNotFoundException("Account NO:" + accountTypeDto.getAccountNo() + " Not Found to update!");
			if (accountTypeDAO.isAccountExists(accountTypeDto.getId()))
				throw new IdNotFoundException("Type Id:" + accountTypeDto.getId() + " Not Found to update!");

			Customer customer = accountTypeDto.getCustomer();

			customer = customerDAO.viewCustomerById(customer.getId());
			accountTypeDto.setCustomer(customer);

			AccountType accountType = AccountTypeMapper.dtoToEntity(accountTypeDto);
			return accountTypeDAO.updateAccountType(accountType);

		}

		else
			throw new InvalidInputException("Account details are Not Found to add!");

	}

	@Override
	public String deleteAccountType(Long typeId) {

		if (accountTypeDAO.isAccountExists(typeId))
			throw new IdNotFoundException("AccountType Id: " + typeId + " Not Found!");
		return accountTypeDAO.deleteAccountType(typeId);
	}

	@Override
	public boolean isAccountExists(Long typeId) {

		return accountTypeDAO.isAccountExists(typeId);
	}

	@Override
	public List<AccountType> viewAllAccount() {

		List<AccountType> types = accountTypeDAO.viewAllAccount();
		return (types != null) ? types : null;
	}

	@Override
	public List<AccountType> getAccountsByType(String type) {

		List<AccountType> types = accountTypeDAO.getAccountsByType(type);
		return (types != null) ? types : null;
	}

	@Override
	public AccountType getAccountByAccountNo(String accountNo) {

		if (accountTypeDAO.getAccountByAccountNo(accountNo) == null)
			throw new IdNotFoundException("Account  No:" + accountNo + " Not Found!");
		return accountTypeDAO.getAccountByAccountNo(accountNo);

	}

	

	@Override
	public List<AccountType> viewCustomerById(Long customerId) {

		return accountTypeDAO.viewCustomerById(customerId);

	}

	@Override
	public AccountType isAccountTypeExists(String mobileNo, String email, String type) {

		return accountTypeDAO.isAccountTypeExists(mobileNo, email, type);
	}
	
	@Override
	public String updateAccountStatus(String accountStatus,String accountNo) {
		
		if (accountTypeDAO.getAccountByAccountNo(accountNo)==null)
			throw new IdNotFoundException("Account No:" + accountNo + " Not Found to update status!");
		
		return accountTypeDAO.updateAccountStatus(accountStatus, accountNo);
		
	}
	
	public String generateAccountNo() {
		String number = "60";
		for (int i = 0; i < 10; i++) {
			Random rand = new Random();
			int n = rand.nextInt(10) + 0;
			number += Integer.toString(n);
		}
		System.out.println("Generated Account No: " + number);
		return number;
	}

	@Override
	public List<AccountType> getCustomersByIFSC(String ifscCode) {
		
		return accountTypeDAO.getCustomersByIFSC(ifscCode);
	}

	
}
