package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.CustomerDto;
import com.revature.bms.entity.Customer;

public interface CustomerService {

	public String addCustomer(CustomerDto customerDto);

	public String deleteCustomer(Long customerId);

	public String updateCustomer(CustomerDto customerDto);

	public List<Customer> viewAllCustomer();

	public Customer isCustomerExistsById(Long employeeId);

	public boolean isCustomerExistsByMobileNo(String mobileNo);// to add account

	public Customer viewCustomerById(Long customerId);

	public boolean isCustomerExistsByEmail(String email);// to add account

	public String updatePassword(String mobileNo, String password);// updation of password

	public Customer validateCustomerLogin(String mobileNo, String password);

	public Customer getCustomerByMobileNo(String mobileNo);
	
	public Customer getCustomerByEmail(String email);

	public List<Customer> getCustomersByIFSC(String ifscCode);
	
	public String forgetPassword(String email);
	


}
