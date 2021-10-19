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
@Table(name = "bms_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "mobile_no", nullable = false, unique = true)
	private String mobileNo;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "salary")
	private Integer salary;

	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;

	// FOREIGN KEY..
	@ManyToOne
	@JoinColumn(name = "branch_id", foreignKey = @ForeignKey(name = "FK_EMPLOYEE_BRANCHID"))
	private Branch branch;

}
