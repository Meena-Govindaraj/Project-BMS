package com.revature.bms.dto;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BankDto {

	private Long id;

	private String bankName;

	private String address;

	@Override
	public String toString() {
		return "BankDto [id=" + id + ", bankName=" + bankName + ", address=" + address + "]";
	}

}
