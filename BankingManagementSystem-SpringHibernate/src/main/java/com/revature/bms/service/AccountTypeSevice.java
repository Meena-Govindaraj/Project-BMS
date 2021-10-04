package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.AccountTypeDto;
import com.revature.bms.entity.AccountType;
public interface AccountTypeSevice {

	 String addAccountType(AccountTypeDto accountTypeDto);

	 String updateAccountType(AccountTypeDto accountTypeDto);

	 String deleteAccountType(Long typeId);

	 boolean isAccountExists(Long typeId);

	 List<AccountType> viewAllAccount();

	 List<AccountType> getAccountsByType(String type);

	 AccountType getAccountByAccountNo(String accountNo);

	 List<AccountType> viewCustomerById(Long customerId);

	 AccountType isAccountTypeExists(String mobileNo,String email,String type);
	
	 String updateAccountStatus(String accountStatus,String accountNo);
	
	 List<AccountType> getCustomersByIFSC(String ifscCode);
	
}
