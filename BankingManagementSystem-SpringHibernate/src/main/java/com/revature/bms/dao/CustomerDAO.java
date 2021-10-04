package com.revature.bms.dao;

import java.util.List;

import com.revature.bms.entity.Customer;

public interface CustomerDAO {

	 String addCustomer(Customer customer);

	 String deleteCustomer(Long customerId);

	 String updateCustomer(Customer customer);

	 List<Customer> viewAllCustomer();

	 Customer viewCustomerById(Long customerId);

	 boolean isCustomerExistsByMobileNo(String mobileNo);// to add account

	 boolean isCustomerExistsByEmail(String email);// to add account

	 String updatePassword(String mobileNo, String password);// updation of password

	 Customer validateCustomerLogin(String mobileNo, String password);

	 Customer getCustomerByMobileNo(String mobileNo);

	 Customer getCustomerByEmail(String email);

	 boolean isCustomerExistsById(Long customerId);// for deleting/updating

	 List<Customer> getCustomersByIFSC(String ifscCode);

	 String forgetPassword(String email, String password);

}
