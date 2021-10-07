package com.revature.bms.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.revature.bms.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	private Long id;

	private String name;

	@NotNull(message = "Mobile number cannot be null")
	@Size(min=10,max=10, message="Enter valid mobile no")
	private String mobileNo;

	private String password;

	@Min(value = 0, message = "Age should be greater than 0")
	@NotNull(message = "Age cannot be null")
	private Integer age;

	@NotNull(message = "Email number cannot be null")
	@Email(message = "Email should be valid")
	private String email;

	private Date createdDate;

	private Date updatedDate;

	private String state;

	@NotNull(message = "Branch Details cannot be null")
	private Branch branch;
}
