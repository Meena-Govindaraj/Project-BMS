package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.AccountDto;
import com.revature.bms.entity.Account;

public interface AccountService {

	 String addAccount(AccountDto accountDto);

	 List<Account> viewAllAccount();

	 Account getAccountByAccountNo(String accountNo);

	 List<Account> getCustomersByIFSC(String ifscCode);

	 List<Account> getCustomerByCustomerId(Long customerId);

	 Account getAccountsByType(Long customerId, String type);

	 String bankTransfer(Long senderId, Long receiverId, Long amount);

	 String updateTransactionPIN(Long typeId, String password);

}
