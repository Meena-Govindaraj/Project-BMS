package com.revature.bms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bms_transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TransactionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long id;

	@Column(name = "transaction_date")
	private Date transactionDate;

	@Column(name = "message")
	private String message;

	@Column(name = "deposit")
	private Double deposit;

	@Column(name = "withdraw")
	private Double withdraw;

	@Column(name = "balance")
	private Double balance;

	// FOREIGN KEY..
	@ManyToOne
	@JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "FK_TRANSACATION_ACCOUNTID"))
	private Account account;

}
