package com.revature.bms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bms_bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_id")
	private Long id;

	@Column(name = "bank_name", nullable = false, unique = false)
	private String bankName;

	@Column(name = "address", nullable = false)
	private String address;

	@Override
	public String toString() {
		return "Bank [id=" + id + ", bankName=" + bankName + ", address=" + address + "]";
	}
}
