package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.EmployeeDto;
import com.revature.bms.entity.Employee;

public interface EmployeeService {

	 String addEmployee(EmployeeDto employeeDto);

	 String deleteEmployee(Long employeeId);

	 String updateEmployee(EmployeeDto employeeDto);

	 List<Employee> viewAllemployee();

	 Employee viewEmployeeById(Long employeeId);

	 boolean isEmployeeExistsById(Long employeeId);

	 boolean isEmployeeExistsByMobileNo(String mobileNo);// to add account

	 boolean isEmployeeExistsByEmail(String email);// to add account
	 
	 Employee getEmployeeByMobileNo(String mobileNo);

	 String updatePassword(String mobileNo, String password,String newPassword);

	 Employee validateEmployeeLogin(String mobileNo, String password);
	
	 String forgetPassword(String email);

	 Employee getEmployeeByEmail(String email);

}
