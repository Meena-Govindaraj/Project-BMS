package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.Customer;
import com.revature.bms.entity.Employee;

public interface CustomerDAO {

	public String addCustomer(Customer customer);

	public String deleteCustomer(Long customerId);

	public String updateCustomer(Customer customer);

	public List<Customer> viewAllCustomer();

	public Customer viewCustomerById(Long customerId);

	public boolean isCustomerExistsByMobileNo(String mobileNo);// to add account

	public boolean isCustomerExistsByEmail(String email);// to add account

	public String updatePassword(String mobileNo, String password);// updation of password

	public Customer validateCustomerLogin(String mobileNo, String password);

	public Customer getCustomerByMobileNo(String mobileNo);

	public boolean isCustomerExistsById(Long customerId);// for deleting/updating

}
