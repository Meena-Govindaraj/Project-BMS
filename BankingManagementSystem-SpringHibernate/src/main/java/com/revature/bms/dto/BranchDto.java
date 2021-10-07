package com.revature.bms.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BranchDto {

	private Long id;

	@NotNull(message = "Branch name cannot be null")
	private String name;

	@NotNull(message = "Branch city cannot be null")
	private String city;
	
	@NotNull(message = "IFSC Code cannot be null")
	private String ifscCode;

	private Date createdDate;

	@Override
	public String toString() {
		return "BranchDto [id=" + id + ", name=" + name + ", city=" + city + ", ifscCode=" + ifscCode + ", createdDate="
				+ createdDate + "]";
	}

}
