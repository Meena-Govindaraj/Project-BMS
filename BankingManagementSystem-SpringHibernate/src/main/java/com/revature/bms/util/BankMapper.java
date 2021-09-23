package com.revature.bms.util;

import com.revature.bms.dto.BankDto;
import com.revature.bms.entity.Bank;

public class BankMapper {

	private BankMapper() {
	}

	//dto to entity mapping
	public static Bank dtoToEntity(BankDto b) {
		Bank bk = new Bank();
		bk.setId(b.getId());
		bk.setBankName(b.getBankName());
		bk.setAddress(b.getAddress());

		return bk;

	}
}
