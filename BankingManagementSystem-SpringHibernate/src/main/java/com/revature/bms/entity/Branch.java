package com.revature.bms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bms_branch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branch_id")
	private Long id;

	@Column(name = "branch_name", nullable = false)
	private String name;

	@Column(name = "city")
	private String city;

	@Column(name = "ifsc_code", nullable = false, unique = true)
	private String ifscCode;

	@Column(name = "created_date")
	private Date createdDate;

	// for cascade delete
	@JsonIgnore
	@OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Employee> employee;

	// for cascade delete
	@JsonIgnore
	@OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Customer> customer;

	@Override
	public String toString() {
		return "BranchDto [id=" + id + ", name=" + name + ", city=" + city + ", ifscCode=" + ifscCode + ", createdDate="
				+ createdDate + "]";
	}

	public Branch(Long id, String name, String city, String ifscCode, Date createdDate) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.ifscCode = ifscCode;
		this.createdDate = createdDate;
	}

}
