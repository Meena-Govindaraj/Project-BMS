package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.AccountTypeDto;
import com.revature.bms.entity.AccountType;

public interface AccountTypeSevice {

	public String addAccountType(AccountTypeDto accountTypeDto);

	public String updateAccountType(AccountTypeDto accountTypeDto);

	public String deleteAccountType(Long typeId);

	public boolean isAccountExists(Long typeId);

	public List<AccountType> viewAllAccount();

	public List<AccountType> getAccountsByType(String type);

	public AccountType getAccountByAccountNo(String accountNo);

}
