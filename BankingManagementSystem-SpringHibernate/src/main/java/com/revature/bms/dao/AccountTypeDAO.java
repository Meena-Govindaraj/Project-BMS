package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.AccountType;

public interface AccountTypeDAO {

	/**
	 * to add account type of customer with auto generated account type a customer
	 * can have two accounts SA AND CA for each account account will be generated
	 * with same customer Id
	 * 
	 * @param accountType
	 * @return string if account added successfully
	 */
	String addAccountType(AccountType accountType);

	/**
	 * to update account type
	 * 
	 * @param accountType
	 * @return string if account updated successfully
	 */
	String updateAccountType(AccountType accountType);

	/**
	 * to delete particular account type of customers type id
	 * 
	 * @param typeId
	 * @return string if account deleted successfully
	 */
	String deleteAccountType(Long typeId);

	/**
	 * to check account exists on this account type
	 * 
	 * @param typeId
	 * @return boolean on based on presence of account type id
	 */

	boolean isAccountExists(Long typeId);

	/**
	 * to retrieve all accounts
	 * 
	 * @return list of accounts that created
	 */
	List<AccountType> viewAllAccount();

	/**
	 * to retrieve account details on account type
	 * 
	 * @param type
	 * @return list of accounts on type
	 */
	List<AccountType> getAccountsByType(String type);

	/**
	 * to get account details on account no
	 * 
	 * @param accountNo
	 * @return particular account on matched account no
	 */
	AccountType getAccountByAccountNo(String accountNo);

	/**
	 * to retrieve account on customer id a customer can have two accounts SA and CA
	 * 
	 * @return list of 2 or 1 account based on account that customer have a customer
	 *         can have 2 accounts(SA/CA) if two accounts created on particular
	 *         customer then details of SA AND CA will be retrieved else returns the
	 *         single account
	 */

	List<AccountType> viewCustomerById(Long customerId);

	/**
	 * to check account already exists on this type with same mobile no
	 * 
	 * @param mobileNo
	 * @param email
	 * @param type
	 * @return account if exists
	 */

	AccountType isAccountTypeExists(String mobileNo, String email, String type);

	/**
	 * to update the status of account while creating account status will be no
	 * after accepting by employee the status get updated to yes
	 * 
	 * @param accountStatus
	 * @param accountNo
	 * @return string on successful updation
	 */
	String updateAccountStatus(String accountStatus, String accountNo);

	/**
	 * get account on IFSC CODE to show the employees branch customer
	 * 
	 * @param ifscCode
	 * @return list of customer on matched IFSC CODE
	 */

	List<AccountType> getCustomersByIFSC(String ifscCode);

}
