package com.revature.bms.dto;

import java.util.Date;
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

	private String mobileNo;

	private String password;

	private Date dateOfBirth;

	private String email;

	private Date createdDate;

	private Date updatedDate;

	private Branch branch;
}
