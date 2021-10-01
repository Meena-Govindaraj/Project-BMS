package com.revature.bms.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bms.controller.MailSend;
import com.revature.bms.dao.AccountDAO;
import com.revature.bms.dao.AccountTypeDAO;
import com.revature.bms.dto.AccountDto;
import com.revature.bms.entity.Account;
import com.revature.bms.entity.AccountType;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.mapper.AccountMapper;
import com.revature.bms.service.AccountService;

@Service

public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private AccountTypeDAO accountTypeDAO;
	@Override
	public String addAccount(AccountDto accountDto) {
		
		System.out.println("Add Account Called in service.... ");

		if (accountDto != null && accountDto.getAccountType() != null) {
			
			String accountNo=accountDto.getAccountType().getAccountNo();
			if(accountTypeDAO.getAccountByAccountNo(accountNo)==null)
				throw new IdNotFoundException("accountNo:" + accountNo + " Not Found to add Account!");
			
			AccountType accountType=accountTypeDAO.getAccountByAccountNo(accountNo);
			System.out.println("##accountType"+accountType);
			
			accountDto.setAccountType(accountType);
			accountDto.setTransactionPIN(generatePIN());
			
			System.out.println("##Account "+accountDto);
	
			
			Account account=AccountMapper.dtoToEntity(accountDto);
			
			String email = account.getAccountType().getCustomer().getEmail();
			String name = account.getAccountType().getCustomer().getName();

			String message = "Mrs./Mr. " + name + ", \n Account Created in our bank " + "\n Account Number: "
					+ account.getAccountType().getAccountNo() + "\n Mobile Number: "
					+ account.getAccountType().getCustomer().getMobileNo() + "\n IFSC Code: "
					+ account.getAccountType().getCustomer().getBranch().getIfscCode() + "\n Branch Name: "
					+ account.getAccountType().getCustomer().getBranch().getName() + "\n Transaction PIN: "
					+ account.getTransactionPIN() + "\n Account Type: " + account.getAccountType().getType();

			MailSend.sendMail(email, "Account Created Successfully", message);

			return accountDAO.addAccount(account);
			
		}
		else
			throw new InvalidInputException("Account details are Not Found to add!");

	}

	
	@Override
	public List<Account> viewAllAccount() {
		
		List<Account> types = accountDAO.viewAllAccount();
		return (types != null) ? types : null;
	}

	
	@Override
	public Account getAccountByAccountNo(String accountNo) {
		
		return accountDAO.getAccountByAccountNo(accountNo);
	}
	
	@Override
	public List<Account> getCustomersByIFSC(String ifscCode) {
		
		return accountDAO.getCustomersByIFSC(ifscCode);
	}
	

	@Override
	public Account getAccountsByType(Long customerId, String type) {
		
		return accountDAO.getAccountsByType(customerId, type);
	}

	
	@Override
	public List<Account> getCustomerByCustomerId(Long customerId) {
		
		return accountDAO.getCustomerByCustomerId(customerId);
	}
	


	@Override
	public String bankTransfer(Long senderId, Long receiverId, Long amount) {
		
		return accountDAO.bankTransfer(senderId, receiverId, amount);
	}


	@Override
	public String updateTransactionPIN(Long typeId, String password) {
		
		return accountDAO.updateTransactionPIN(typeId, password);
	}
	
	public String generatePIN()
	{
		
		// It will generate 6 digit random Number.
	    // from 0 to 999999
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    // this will convert any number sequence into 6 character.
	    return String.format("%06d", number);

		
	}


}
