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

	public List<AccountType> viewCustomerById(Long customerId);

	public AccountType isAccountTypeExists(String mobileNo,String email,String type);
	
	public String updateAccountStatus(String accountStatus,String accountNo);
	
	public List<AccountType> getCustomersByIFSC(String ifscCode);
	
}
