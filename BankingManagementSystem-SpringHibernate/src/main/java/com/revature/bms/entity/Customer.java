package com.revature.bms.entity;

import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bms_customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "mobile_no", nullable = false, unique = true)
	private String mobileNo;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "age")
	private Integer age;

	@Column(name = "email", nullable = false,unique = true)
	private String email;

	@Column(name = "state")
	private String state;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToOne
	@JoinColumn(name = "branch_id", foreignKey = @ForeignKey(name = "FK_CUSTOMER_BRANCHID"))
	private Branch branch;

	// for cascade delete
	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AccountType> accountType;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobileNo=" + mobileNo + ", password=" + password + ", age="
				+ age + ", email=" + email + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
