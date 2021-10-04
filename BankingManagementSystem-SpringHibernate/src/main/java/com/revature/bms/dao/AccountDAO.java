package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.Account;

public interface AccountDAO {

	 String addAccount(Account accountType);

	 List<Account> viewAllAccount();

	 Account getAccountsByType(Long customerId, String type);

	 Account getAccountByAccountNo(String accountNo);

	 List<Account> getCustomersByIFSC(String ifscCode);

	 List<Account> getCustomerByCustomerId(Long customerId);

	 String bankTransfer(Long senderId, Long receiverId, Long amount);

	 String updateTransactionPIN(Long typeId, String password);

}
