package com.revature.bms.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

	@Min(value = 0, message = "balance should be greater than 0")
	private Long balance;
		
	private String transactionPIN;
	
	@NotNull(message = "Account Type cannot be null")
	private AccountType accountType;

}
