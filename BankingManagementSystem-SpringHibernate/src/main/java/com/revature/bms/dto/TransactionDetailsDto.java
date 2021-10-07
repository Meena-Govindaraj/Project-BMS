package com.revature.bms.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.revature.bms.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailsDto {

	private Long id;

	private Date transactionDate;

	private String message;

	@Min(value = 0, message = "Deposit amount should be greater than 0")
	private Double deposit;

	@Min(value = 0, message = "Withdraw amount should be greater than 0")
	private Double withdraw;

	@Min(value = 0, message = "Balance should be greater than 0")
	private Double balance;

	@NotNull(message = "Account Details cannot be null")
	private Account account;

}
