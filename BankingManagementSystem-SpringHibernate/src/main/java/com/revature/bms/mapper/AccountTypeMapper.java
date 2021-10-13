package com.revature.bms.mapper;

import com.revature.bms.dto.AccountTypeDto;
import com.revature.bms.entity.AccountType;

public class AccountTypeMapper {

	private AccountTypeMapper() {
	}

	public static AccountType dtoToEntity(AccountTypeDto accountTypeDto) {

		AccountType at = new AccountType();
		at.setId(accountTypeDto.getId());
		at.setType(accountTypeDto.getType());
		at.setAccountStatus(accountTypeDto.getAccountStatus());
		at.setAccountNo(accountTypeDto.getAccountNo());
		at.setCustomer(accountTypeDto.getCustomer());

		return at;

	}
}
