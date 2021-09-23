package com.revature.bms.dto;

import java.util.Date;
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

	private String name;

	private String city;
	
	private String ifscCode;

	private Date createdDate;

	@Override
	public String toString() {
		return "BranchDto [id=" + id + ", name=" + name + ", city=" + city + ", ifscCode=" + ifscCode + ", createdDate="
				+ createdDate + "]";
	}

	

}
