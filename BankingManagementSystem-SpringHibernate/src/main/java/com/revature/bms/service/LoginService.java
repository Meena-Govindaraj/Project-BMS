package com.revature.bms.service;

import com.revature.bms.entity.Customer;
import com.revature.bms.entity.Employee;

public interface LoginService {

	/**
	 * to validate customer login on registered mobile no and password
	 * 
	 * @param mobileNo
	 * @param password
	 * @return customer details on matched mobile no and password
	 */
	Customer validateCustomerLogin(String email, String password);

	/**
	 * to validate employee login on registered credentials
	 * 
	 * @param mobileNo
	 * @param password
	 * @return returns employee data if login credentails are matched
	 */
	Employee validateEmployeeLogin(String email, String password);

}
