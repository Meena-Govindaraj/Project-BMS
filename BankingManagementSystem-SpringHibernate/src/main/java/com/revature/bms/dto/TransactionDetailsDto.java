package com.revature.bms.dto;

import java.util.Date;

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

	private Double deposit;

	private Double withdraw;

	private Double balance;

	private Account account;

}
