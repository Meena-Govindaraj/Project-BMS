package com.revature.bms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bms_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component

public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;

	@Column(name = "balance")
	private Long balance;

	@Column(name = "transaction_pin")
	private String transactionPIN;

	@ManyToOne
	@JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "FK_ACCOUNT_TYPEID"))
	private AccountType accountType;

	// for cascade delete
	@JsonIgnore
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TransactionDetails> transaction;

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", transactionPIN=" + transactionPIN + "]";
	}

	
}
