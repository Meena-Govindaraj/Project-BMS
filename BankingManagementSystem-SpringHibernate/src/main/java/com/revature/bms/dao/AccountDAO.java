package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.Account;

public interface AccountDAO {

	public String addAccount(Account accountType);

	public List<Account> viewAllAccount();

	public Account getAccountsByType(Long customerId, String type);

	public Account getAccountByAccountNo(String accountNo);

	public Account getAccountByMobileNo(String mobileNo);

	public List<Account> getCustomersByIFSC(String ifscCode);

	public List<Account> getCustomerByCustomerId(Long customerId);

	public String bankTransfer(Long senderId, Long receiverId, Long amount);

	public String updateTransactionPIN(Long typeId, String password);

}
