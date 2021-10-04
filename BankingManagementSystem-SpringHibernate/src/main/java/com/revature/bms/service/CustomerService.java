package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.CustomerDto;
import com.revature.bms.entity.Customer;

public interface CustomerService {

	 String addCustomer(CustomerDto customerDto);

	 String deleteCustomer(Long customerId);

	 String updateCustomer(CustomerDto customerDto);

	 List<Customer> viewAllCustomer();

	 Customer isCustomerExistsById(Long employeeId);

	 boolean isCustomerExistsByMobileNo(String mobileNo);// to add account

	 Customer viewCustomerById(Long customerId);

	 boolean isCustomerExistsByEmail(String email);// to add account

	 String updatePassword(String mobileNo, String password);// updation of password

	 Customer validateCustomerLogin(String mobileNo, String password);

	 Customer getCustomerByMobileNo(String mobileNo);
	
	 Customer getCustomerByEmail(String email);

	 List<Customer> getCustomersByIFSC(String ifscCode);
	
	 String forgetPassword(String email);
	


}
