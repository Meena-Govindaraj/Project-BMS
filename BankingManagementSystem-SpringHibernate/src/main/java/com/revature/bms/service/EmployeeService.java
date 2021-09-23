package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.EmployeeDto;
import com.revature.bms.entity.Employee;

public interface EmployeeService {

	public String addEmployee(EmployeeDto employeeDto);

	public String deleteEmployee(Long employeeId);

	public String updateEmployee(EmployeeDto employeeDto);

	public List<Employee> viewAllemployee();

	public Employee viewEmployeeById(Long employeeId);

	public boolean isEmployeeExistsById(Long employeeId);

	public boolean isEmployeeExistsByMobileNo(String mobileNo);// to add account

	public boolean isEmployeeExistsByEmail(String email);// to add account

	public Employee getEmployeeByMobileNo(String mobileNo);

	public String updatePassword(String mobileNo, String password);

	public Employee validateEmployeeLogin(String mobileNo, String password);

}
