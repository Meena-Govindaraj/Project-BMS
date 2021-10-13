package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.AccountDto;
import com.revature.bms.entity.Account;

public interface AccountService {

	/**
	 * to add account on customer with minimum balance based on account type
	 * 
	 * @param accountTypeDto
	 * @return string if account added successfully
	 */
	String addAccount(AccountDto accountDto);

	/**
	 * to view all accounts
	 * 
	 * @return list of accounts that created
	 */
	List<Account> viewAllAccount();

	/**
	 * to retrieve account on customer account no
	 * 
	 * @param accountNo
	 * @return account details on matched account no
	 */
	Account getAccountByAccountNo(String accountNo);

	/**
	 * to retrieve account details on IFSC CODE
	 * 
	 * @param ifscCode
	 * @return list of account on matched IFSC code
	 */
	List<Account> getCustomersByIFSC(String ifscCode);

	/**
	 * to get customer account details on customer id
	 * 
	 * @param customerId
	 * @return list of 2 or 1 account based on account that customer have a customer
	 *         can have 2 accounts(SA/CA) if two accounts created on particular
	 *         customer then details of SA AND CA will be retrieved else returns the
	 *         single account
	 */

	List<Account> getCustomerByCustomerId(Long customerId);

	/**
	 * to retrieve account on customer Id and account type(SA/CA)
	 * 
	 * @param customerId
	 * @param type
	 * @return account details based on customer id and type
	 */

	Account getAccountsByType(Long customerId, String type);

	/**
	 * to transfer fund form one account to others account on transaction pin
	 * transfered from sender account id to receiver account id on given amount
	 * 
	 * @param senderId
	 * @param receiverId
	 * @param amount
	 * @return string on successful transaction
	 */
	String bankTransfer(Long senderId, Long receiverId, Long amount);

	/**
	 * to change the transaction pin on account type id SA and CA have different
	 * transaction pin , so based on account type id the transaction pin get updated
	 * 
	 * @param typeId
	 * @param password
	 * @return string on successful updation of Transaction PIN
	 */
	String updateTransactionPIN(Long accountId, String oldPassword,String newPassword);

}
