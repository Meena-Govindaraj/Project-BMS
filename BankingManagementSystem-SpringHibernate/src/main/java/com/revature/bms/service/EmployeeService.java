package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.EmployeeDto;
import com.revature.bms.entity.Employee;

public interface EmployeeService {

	/**
	 * to add employee
	 * 
	 * @param employeeDto
	 * @return string on successful creation
	 */
	String addEmployee(EmployeeDto employeeDto);

	/**
	 * to delete employee on Id
	 * 
	 * @param employeeId
	 * @return string on successful deletion
	 */
	String deleteEmployee(Long employeeId);

	/**
	 * to update employee details
	 * 
	 * @param employeeDto
	 * @return string on successful updation
	 */
	String updateEmployee(EmployeeDto employeeDto);

	/**
	 * to retrieve all employees that created
	 * 
	 * @return list of employee details
	 */
	List<Employee> viewAllemployee();

	/**
	 * to view employee on employeeId
	 * 
	 * @param employeeId
	 * @return employee data in matched employee id
	 */
	Employee viewEmployeeById(Long employeeId);

	/**
	 * to check the existence of employee on ID
	 * 
	 * @param employeeId
	 * @return boolean on presence of employee
	 */
	boolean isEmployeeExistsById(Long employeeId);

	/**
	 * to check the existence of employee on mobile no
	 * 
	 * @param mobileNo
	 * @return boolean on presence of employee
	 */
	boolean isEmployeeExistsByMobileNo(String mobileNo);// to add account

	/**
	 * to check the existence of employee on email
	 * 
	 * @param email
	 * @return boolean on presence of employee
	 */
	boolean isEmployeeExistsByEmail(String email);// to add account

	/**
	 * to get employee details on unique mobile no
	 * 
	 * @param mobileNo
	 * @return
	 */
	Employee getEmployeeByMobileNo(String mobileNo);

	/**
	 * to update employee login password
	 * 
	 * @param mobileNo
	 * @param password
	 * @param newPassword
	 * @return string on successful updation
	 */
	String updatePassword(String mobileNo, String password, String newPassword);

	/**
	 * to reset employee login password
	 * 
	 * @param email
	 * @param password
	 * @return string on successful updation on password
	 */
	String forgetPassword(String email);

	/**
	 * to get employee details on unique email
	 * 
	 * @param email
	 * @return employee details on given email
	 */
	Employee getEmployeeByEmail(String email);

}
