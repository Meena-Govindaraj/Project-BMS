package com.revature.bms.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bms.dao.AccounTypeDAO;
import com.revature.bms.dao.CustomerDAO;
import com.revature.bms.dto.AccountTypeDto;
import com.revature.bms.entity.AccountType;
import com.revature.bms.entity.Customer;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.service.AccountTypeSevice;
import com.revature.bms.util.AccountTypeMapper;

@Service
public class AccountTypeServiceImpl implements AccountTypeSevice {

	@Autowired
	private AccounTypeDAO accounTypeDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public String addAccountType(AccountTypeDto accountTypeDto) {

		if (accountTypeDto != null && accountTypeDto.getCustomer() != null) {
			Long customerId = accountTypeDto.getCustomer().getId();
			Customer customer = customerDAO.viewCustomerById(customerId);
			accountTypeDto.setCustomer(customer);
			accountTypeDto.setAccountNo(generateAccountNo());

			AccountType accountType = AccountTypeMapper.dtoToEntity(accountTypeDto);
			return accounTypeDAO.addAccountType(accountType);
		} else
			throw new InvalidInputException("Account details are Not Found to add!");

	}

	@Override
	public String updateAccountType(AccountTypeDto accountTypeDto) {

		if (accountTypeDto != null && accountTypeDto.getCustomer() != null) {

			if (accounTypeDAO.getAccountByAccountNo(accountTypeDto.getAccountNo()) == null)
				throw new IdNotFoundException("Account NO:" + accountTypeDto.getAccountNo() + " Not Found to update!");
			if (accounTypeDAO.isAccountExists(accountTypeDto.getId()))
				throw new IdNotFoundException("Type Id:" + accountTypeDto.getId() + " Not Found to update!");

			Long customerId = accountTypeDto.getCustomer().getId();
			Customer customer = customerDAO.viewCustomerById(customerId);
			accountTypeDto.setCustomer(customer);

			AccountType accountType = AccountTypeMapper.dtoToEntity(accountTypeDto);
			return accounTypeDAO.updateAccountType(accountType);
		} else
			throw new InvalidInputException("Account details are Not Found to add!");

	}

	@Override
	public String deleteAccountType(Long typeId) {

		if (accounTypeDAO.isAccountExists(typeId))
			throw new IdNotFoundException("AccountType Id: " + typeId + " Not Found!");
		return accounTypeDAO.deleteAccountType(typeId);
	}

	@Override
	public boolean isAccountExists(Long typeId) {

		return accounTypeDAO.isAccountExists(typeId);
	}

	@Override
	public List<AccountType> viewAllAccount() {

		List<AccountType> types = accounTypeDAO.viewAllAccount();
		return (types != null) ? types : null;
	}

	@Override
	public List<AccountType> getAccountsByType(String type) {

		List<AccountType> types = accounTypeDAO.getAccountsByType(type);
		return (types != null) ? types : null;
	}

	@Override
	public AccountType getAccountByAccountNo(String accountNo) {

		if (accounTypeDAO.getAccountByAccountNo(accountNo) == null)
			throw new IdNotFoundException("Account  No:" + accountNo + " Not Found!");
		return accounTypeDAO.getAccountByAccountNo(accountNo);

	}

	public String generateAccountNo() {
		String number = "60";
		for (int i = 0; i < 10; i++) {
			Random rand = new Random();
			int n = rand.nextInt(10) + 0;
			number += Integer.toString(n);
		}
		System.out.println("Generated No: " + number);
		return number;
	}

}
