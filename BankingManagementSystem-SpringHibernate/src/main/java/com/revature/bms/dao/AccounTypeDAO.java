package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.AccountType;

public interface AccounTypeDAO {

	public String addAccountType(AccountType accountType);

	public String updateAccountType(AccountType accountType);

	public String deleteAccountType(Long typeId);

	public boolean isAccountExists(Long typeId);

	public List<AccountType> viewAllAccount();

	public List<AccountType> getAccountsByType(String type);

	public AccountType getAccountByAccountNo(String accountNo);

}
