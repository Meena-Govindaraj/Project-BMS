package com.revature.bms.dto;

import java.util.Date;

import com.revature.bms.entity.Branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private Long id;

	private String name;

	private String password;

	private String mobileNo;

	private String email;

	private String address;

	private Integer salary;

	private Date createdDate;

	private Date updatedDate;

	private Branch branch;

}
