package com.revature.bms.dto;

import org.springframework.stereotype.Component;

import com.revature.bms.entity.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AccountDto {

	private Long id;
	
	private Long balance;
	
	private String transactionPIN;

	private AccountType accountType;

}
