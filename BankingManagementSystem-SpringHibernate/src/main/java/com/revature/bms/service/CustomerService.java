package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.CustomerDto;
import com.revature.bms.entity.Customer;

public interface CustomerService {

	/**
	 * to add customer with given details
	 * 
	 * @param customerDto
	 * @return string on successful creation
	 */
	String addCustomer(CustomerDto customerDto);

	/**
	 * to delete customer
	 * 
	 * @param customerId
	 * @return string on successful deletion
	 */
	String deleteCustomer(Long customerId);

	/**
	 * to update customer with given details
	 * 
	 * @param customerDto
	 * @return string on successful updation
	 */
	String updateCustomer(CustomerDto customerDto);

	/**
	 * to view all customers
	 * 
	 * @return list of customer that created
	 */
	List<Customer> viewAllCustomer();

	/**
	 * to check customer existence of customer id
	 * 
	 * @param customerId
	 * @return based on presence of customer id
	 */
	Customer isCustomerExistsById(Long employeeId);

	/**
	 * to check customer already exists on mobile no
	 * 
	 * @param mobileNo
	 * @return boolean based on presence of customer mobile no
	 */
	boolean isCustomerExistsByMobileNo(String mobileNo);// to add account

	/**
	 * to get details of customer on customer ID
	 * 
	 * @param customerId
	 * @return customer data on matched customer Id
	 */

	Customer viewCustomerById(Long customerId);

	/**
	 * to check customer already exists on email
	 * 
	 * @param email
	 * @return boolean based on presence of customer email
	 */
	boolean isCustomerExistsByEmail(String email);// to add account

	/**
	 * to update the login password of customer
	 * 
	 * @param mobileNo
	 * @param password
	 * @return string on successful updation
	 */
	String updatePassword(String mobileNo, String password);// updation of password

	/**
	 * to get customer details on unique mobile no
	 * 
	 * @param mobileNo
	 * @return customer details on matched mobile no
	 */
	Customer getCustomerByMobileNo(String mobileNo);

	/**
	 * to get customer details on email
	 * 
	 * @param email
	 * @return customer details on matched email
	 */
	Customer getCustomerByEmail(String email);

	/**
	 * to get customer details based on IFSC Code
	 * 
	 * @param ifscCode
	 * @return list of customers on given IFSC Code
	 */
	List<Customer> getCustomersByIFSC(String ifscCode);

	/**
	 * to rest customer login password
	 * 
	 * @param email
	 * @param password
	 * @return string successful updation of password
	 */

	String forgetPassword(String email);

}
