package com.revature.bms.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotNull(message = "Mobile number cannot be null")
	@Size(min=10,max=10, message="Enter valid mobile no")
	private String mobileNo;

	@Email(message = "Email should be valid")
	@NotNull(message = "Email number cannot be null")
	private String email;

	private String address;

	@Min(value = 0, message = "Salary should be greater than 0")
	private Integer salary;

	private Date createdDate;

	private Date updatedDate;

	@NotNull(message = "Branch Details cannot be null")
	private Branch branch;

}
