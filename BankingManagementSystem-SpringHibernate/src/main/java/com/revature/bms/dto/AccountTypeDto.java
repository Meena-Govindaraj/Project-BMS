package com.revature.bms.dto;

import javax.validation.constraints.NotNull;

import com.revature.bms.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTypeDto {

	private Long id;

	@NotNull(message = "Account Type cannot be null")
	private String type;

	private String accountNo;

	private String accountStatus;

	@NotNull(message = "customer details cannot be null")
	private Customer customer;

	@Override
	public String toString() {
		return "AccountTypeDto [id=" + id + ", type=" + type + ", accountNo=" + accountNo + ", accountStatus="
				+ accountStatus + "]";
	}

}
