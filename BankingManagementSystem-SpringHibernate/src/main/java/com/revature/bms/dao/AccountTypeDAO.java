package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.AccountType;

public interface AccountTypeDAO {

	 String addAccountType(AccountType accountType);

	 String updateAccountType(AccountType accountType);

	 String deleteAccountType(Long typeId);

	 boolean isAccountExists(Long typeId);

	 List<AccountType> viewAllAccount();

	 List<AccountType> getAccountsByType(String type);

	 AccountType getAccountByAccountNo(String accountNo);

	 List<AccountType> viewCustomerById(Long customerId);

	 AccountType isAccountTypeExists(String mobileNo, String email, String type);

	 String updateAccountStatus(String accountStatus, String accountNo);

	 List<AccountType> getCustomersByIFSC(String ifscCode);

}
