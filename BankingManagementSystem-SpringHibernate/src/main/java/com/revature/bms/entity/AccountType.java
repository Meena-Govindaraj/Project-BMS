package com.revature.bms.entity;

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
@Table(name = "bms_accounttype")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AccountType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "type_id")
	private Long id;

	@Column(name = "account_type", nullable = false)
	private String type;

	@Column(name = "account_no", nullable = false)
	private String accountNo;

	@ManyToOne
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_CUSTOMER_BRANCHID"))
	private Customer customer;

}
