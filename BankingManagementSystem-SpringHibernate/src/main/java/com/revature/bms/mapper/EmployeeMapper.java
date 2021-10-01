package com.revature.bms.mapper;

import com.revature.bms.dto.EmployeeDto;
import com.revature.bms.entity.Employee;

public class EmployeeMapper {

	private EmployeeMapper() {
	}

	// dto to entity
	public static Employee dtoToEntity(EmployeeDto e) {
		return new Employee(e.getId(), e.getName(), e.getPassword(), e.getMobileNo(), e.getEmail(), e.getAddress(),
				e.getSalary(), e.getCreatedDate(), e.getUpdatedDate(), e.getBranch());
	}

}
