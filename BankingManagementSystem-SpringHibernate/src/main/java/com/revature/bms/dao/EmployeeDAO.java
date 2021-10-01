package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.Employee;

public interface EmployeeDAO {

	public String addEmployee(Employee employee);

	public String deleteEmployee(Long employeeId);

	public String updateEmployee(Employee employee);

	public List<Employee> viewAllemployee();

	public Employee viewEmployeeById(Long employeeId);

	public boolean isEmployeeExistsById(Long employeeId);// for deleting/updating

	public boolean isEmployeeExistsByMobileNo(String mobileNo);// to add account

	public boolean isEmployeeExistsByEmail(String email);// to add account

	public String updatePassword(String mobileNo, String password,String newPassword);// updation of password

	public Employee getEmployeeByMobileNo(String mobileNo);// to update password needs number

	public Employee getEmployeeByEmail(String email);

	public String forgetPassword(String email,String password);
	
	public Employee validateEmployeeLogin(String mobileNo, String password);
}
