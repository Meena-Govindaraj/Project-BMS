package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.Employee;

public interface EmployeeDAO {

	/**
	 * to add employee
	 * 
	 * @param employee
	 * @return string on successful creation
	 */
	String addEmployee(Employee employee);

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
	 * @param employee
	 * @return string on successful updation
	 */
	String updateEmployee(Employee employee);

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
	boolean isEmployeeExistsByMobileNo(String mobileNo);

	/**
	 * to check the existence of employee on email
	 * 
	 * @param email
	 * @return boolean on presence of employee
	 */
	boolean isEmployeeExistsByEmail(String email);

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
	 * to get employee details on unique mobile no
	 * 
	 * @param mobileNo
	 * @return
	 */
	Employee getEmployeeByMobileNo(String mobileNo);

	/**
	 * to get employee details on unique email
	 * 
	 * @param email
	 * @return employee details on given email
	 */
	Employee getEmployeeByEmail(String email);

	/**
	 * to reset employee login password
	 * 
	 * @param email
	 * @param password
	 * @return string on successful updation on password
	 */
	String forgetPassword(String email, String password);

}
