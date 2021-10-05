package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.Customer;

public interface CustomerDAO {

	/**
	 * to add customer with given details
	 * 
	 * @param customer
	 * @return string on successful creation
	 */
	String addCustomer(Customer customer);

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
	 * @param customer
	 * @return string on successful updation
	 */

	String updateCustomer(Customer customer);

	/**
	 * to view all customers
	 * 
	 * @return list of customer that created
	 */
	List<Customer> viewAllCustomer();

	/**
	 * to get details of customer on customer ID
	 * 
	 * @param customerId
	 * @return customer data on matched customer Id
	 */

	Customer viewCustomerById(Long customerId);

	/**
	 * to check customer already exists on mobile no
	 * 
	 * @param mobileNo
	 * @return boolean based on presence of customer mobile no
	 */
	boolean isCustomerExistsByMobileNo(String mobileNo);

	/**
	 * to check customer already exists on email
	 * 
	 * @param email
	 * @return boolean based on presence of customer email
	 */
	boolean isCustomerExistsByEmail(String email);

	/**
	 * to update the login password of customer
	 * 
	 * @param mobileNo
	 * @param password
	 * @return string on successful updation
	 */
	String updatePassword(String mobileNo, String password);

	/**
	 * to validate customer login on registered mobile no and password
	 * 
	 * @param mobileNo
	 * @param password
	 * @return customer details on matched mobile no and password
	 */
	Customer validateCustomerLogin(String mobileNo, String password);

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
	 * to check customer existence of customer id
	 * 
	 * @param customerId
	 * @return boolean based on presence of customer id
	 */

	boolean isCustomerExistsById(Long customerId);

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
	String forgetPassword(String email, String password);

}
