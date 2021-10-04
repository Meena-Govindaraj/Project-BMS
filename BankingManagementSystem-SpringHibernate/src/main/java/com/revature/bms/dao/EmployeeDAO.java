package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.Employee;

public interface EmployeeDAO {

	 String addEmployee(Employee employee);

	 String deleteEmployee(Long employeeId);

	 String updateEmployee(Employee employee);

	 List<Employee> viewAllemployee();

	 Employee viewEmployeeById(Long employeeId);

	 boolean isEmployeeExistsById(Long employeeId);// for deleting/updating

	 boolean isEmployeeExistsByMobileNo(String mobileNo);// to add account

	 boolean isEmployeeExistsByEmail(String email);// to add account

	 String updatePassword(String mobileNo, String password,String newPassword);// updation of password

	 Employee getEmployeeByMobileNo(String mobileNo);// to update password needs number

	 Employee getEmployeeByEmail(String email);

	 String forgetPassword(String email,String password);
	
	 Employee validateEmployeeLogin(String mobileNo, String password);
}
