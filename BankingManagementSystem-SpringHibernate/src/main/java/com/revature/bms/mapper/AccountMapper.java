package com.revature.bms.mapper;

import com.revature.bms.dto.AccountDto;
import com.revature.bms.entity.Account;

public class AccountMapper {

	private AccountMapper() {
	}

	// dto to entity mapping
	public static Account dtoToEntity(AccountDto accountDto) {

		Account account = new Account();
		account.setId(accountDto.getId());
		account.setBalance(accountDto.getBalance());
		account.setTransactionPIN(accountDto.getTransactionPIN());
		account.setAccountType(accountDto.getAccountType());

		return account;
	}

}
