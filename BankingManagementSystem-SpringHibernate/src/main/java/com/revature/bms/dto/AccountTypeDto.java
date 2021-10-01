package com.revature.bms.dto;

import com.revature.bms.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTypeDto {

	private Long id;

	private String type;

	private String accountNo;
	
	private String accountStatus;

	private Customer customer;
	
	@Override
	public String toString() {
		return "AccountTypeDto [id=" + id + ", type=" + type + ", accountNo=" + accountNo + ", accountStatus="
				+ accountStatus + "]";
	}


}
