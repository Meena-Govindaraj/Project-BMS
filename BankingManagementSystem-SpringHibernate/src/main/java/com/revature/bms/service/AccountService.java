package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.AccountDto;
import com.revature.bms.entity.Account;

public interface AccountService {

	public String addAccount(AccountDto accountDto);

	public List<Account> viewAllAccount();

	public Account getAccountByAccountNo(String accountNo);

	public List<Account> getCustomersByIFSC(String ifscCode);

	public List<Account> getCustomerByCustomerId(Long customerId);

	public Account getAccountsByType(Long customerId, String type);

	public String bankTransfer(Long senderId, Long receiverId, Long amount);

	public String updateTransactionPIN(Long typeId, String password);

}
