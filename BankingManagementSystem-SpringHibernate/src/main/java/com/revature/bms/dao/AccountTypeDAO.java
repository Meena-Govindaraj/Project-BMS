package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.AccountType;

public interface AccountTypeDAO {

	public String addAccountType(AccountType accountType);

	public String updateAccountType(AccountType accountType);

	public String deleteAccountType(Long typeId);

	public boolean isAccountExists(Long typeId);

	public List<AccountType> viewAllAccount();

	public List<AccountType> getAccountsByType(String type);

	public AccountType getAccountByAccountNo(String accountNo);

	public List<AccountType> viewCustomerById(Long customerId);

	public AccountType isAccountTypeExists(String mobileNo, String email, String type);

	public String updateAccountStatus(String accountStatus, String accountNo);

	public List<AccountType> getCustomersByIFSC(String ifscCode);

}
