package com.revature.bms.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bms.dao.LoginDAO;
import com.revature.bms.entity.Customer;
import com.revature.bms.entity.Employee;
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.exception.DatabaseException;
import com.revature.bms.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	private static final Logger logger = LogManager.getLogger(LoginServiceImpl.class.getName());
	
	@Autowired
	private LoginDAO loginDAO;
	@Override
	public Customer validateCustomerLogin(String mobileNo, String password) {
		
		logger.info("validate Customer Login called in customer Service");
		
		Customer customer=null;
		try {
			customer= loginDAO.validateCustomerLogin(mobileNo, password);
			if(customer!=null)
				return customer;
			throw new BussinessLogicException("No Records Found");
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}
	

	@Override
	public Employee validateEmployeeLogin(String mobileNo, String password) {

		logger.info("Validate Employee Login Called in Service... ");

		Employee employee = null;
		try {
			employee = loginDAO.validateEmployeeLogin(mobileNo, password);
			if (employee != null)
				return employee;
			else
				throw new BussinessLogicException("No records found");

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}


}
