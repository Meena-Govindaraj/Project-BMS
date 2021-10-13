package com.revature.bms.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.revature.bms.util.BankingManagementConstants.*;
import com.revature.bms.controller.MailSend;
import com.revature.bms.dao.BranchDAO;
import com.revature.bms.dao.CustomerDAO;
import com.revature.bms.dto.CustomerDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.entity.Customer;
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.exception.DatabaseException;
import com.revature.bms.mapper.CustomerMapper;
import com.revature.bms.service.CustomerService;
import com.revature.bms.util.GeneratePassword;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class.getName());

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private BranchDAO branchDAO;

	@Override
	public String addCustomer(CustomerDto customerDto) {

		logger.info("Add Customer Called in Service.... ");
		try {
			if (customerDto == null || customerDto.getBranch() == null)
				throw new BussinessLogicException("Customer " + INVALID_DETAILS);

			long branchId = customerDto.getBranch().getId();

			if (branchDAO.isBranchExists(branchId))
				throw new BussinessLogicException("Branch Id:" + branchId + ID_NOT_FOUND);

			// to check customer Existence...
			if (!customerDAO.isCustomerExistsByMobileNo(customerDto.getMobileNo()))
				throw new BussinessLogicException("Customer !" + DUPLICATE_RECORD);

			// getting branch details to set in customer..
			Branch branch = branchDAO.viewBranchById(branchId);
			customerDto.setBranch(branch);

			// dto to entity..
			Customer customer = CustomerMapper.dtoToEntity(customerDto);
			customer.setPassword(GeneratePassword.generatePassword());

			return customerDAO.addCustomer(customer);

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		} catch (ConstraintViolationException e) {
			throw new BussinessLogicException("Customer mobile no already found");
		}
	}

	@Override
	public String deleteCustomer(Long customerId) {

		logger.info("Delete Customer Called in Service.... ");
		try {
			if (customerDAO.isCustomerExistsById(customerId))
				throw new BussinessLogicException("Customer Id:" + customerId + ID_NOT_FOUND);
			return customerDAO.deleteCustomer(customerId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateCustomer(CustomerDto customerDto) {

		logger.info("update customer Called in Service.... ");
		try {
			if (customerDto == null || customerDto.getBranch() == null)
				throw new BussinessLogicException("Customer " + INVALID_DETAILS);

			long branchId = customerDto.getBranch().getId();

			if (branchDAO.isBranchExists(branchId))
				throw new BussinessLogicException("Branch Id:" + branchId + ID_NOT_FOUND);

			// getting branch details to set in customer..
			Branch branch = branchDAO.viewBranchById(branchId);
			customerDto.setBranch(branch);

			// dto to entity..
			Customer customer = CustomerMapper.dtoToEntity(customerDto);

			return customerDAO.updateCustomer(customer);

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Customer> viewAllCustomer() {

		logger.info("viewAllCustomer Called in Service.... ");

		List<Customer> customers = null;
		try {
			customers = customerDAO.viewAllCustomer();
			if (customers != null)
				return customers;
			throw new BussinessLogicException("No records Found");
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Customer isCustomerExistsById(Long customerId) {

		logger.info("View CustomerById Called in Service.... ");
		try {
			if (customerDAO.isCustomerExistsById(customerId))
				throw new BussinessLogicException("Customer Id:" + customerId + ID_NOT_FOUND);

			return customerDAO.viewCustomerById(customerId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isCustomerExistsByMobileNo(String mobileNo) {

		logger.info("Is Customer Exists By MobileNo Called in Service.... ");
		try {
			return customerDAO.isCustomerExistsByMobileNo(mobileNo);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Customer viewCustomerById(Long customerId) {

		logger.info("Is Customer Exists By customerId Called in Service.... ");

		try {
			if (customerDAO.isCustomerExistsById(customerId))
				throw new BussinessLogicException("Customer Id:" + customerId + ID_NOT_FOUND);
			return customerDAO.viewCustomerById(customerId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isCustomerExistsByEmail(String email) {

		logger.info("Is Customer Exists By email Called in Service.... ");
		try {
			return customerDAO.isCustomerExistsByEmail(email);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updatePassword(String mobileNo, String oldPassword,String newPassword) {

		logger.info("Update password called in customer Sevice");

		try {
			if (customerDAO.isCustomerExistsByMobileNo(mobileNo))
				throw new BussinessLogicException(
						"Customer Phone Number:" + mobileNo + " Not Found to Update Password!");
			
			Customer customer = customerDAO.getCustomerByMobileNo(mobileNo);
			
			if(encoder.matches(oldPassword, customer.getPassword()))
			{
			newPassword = encoder.encode(newPassword);
			return customerDAO.updatePassword(mobileNo, newPassword);
			}
			
			throw new BussinessLogicException("Incorrect Old Password");
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Customer getCustomerByMobileNo(String mobileNo) {

		logger.info("Get CustomerBy MobileNo called in customer Service");

		Customer customer = null;
		try {
			customer = customerDAO.getCustomerByMobileNo(mobileNo);
			if (customer != null)
				return customer;
			throw new BussinessLogicException("No Records Found");
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}

	}

	@Override
	public Customer getCustomerByEmail(String email) {

		logger.info("Get CustomerBy email called in customer Service");

		Customer customer = null;
		try {
			customer = customerDAO.getCustomerByEmail(email);
			if (customer != null)
				return customer;
			throw new BussinessLogicException("No Records Found");
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Customer> getCustomersByIFSC(String ifscCode) {

		logger.info("Get CustomerBy IFSC called in customer Service");

		List<Customer> customers = null;
		try {
			customers = customerDAO.getCustomersByIFSC(ifscCode);
			if (customers != null)
				return customers;
			throw new BussinessLogicException("No records Found");
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}

	}

	@Override
	public String forgetPassword(String email) {

		logger.info("Forget Password called in customer Service");
		try {
			if (customerDAO.isCustomerExistsByEmail(email))
				throw new BussinessLogicException("Customer email:" + email + " Not Found to reset Password!");

			String password = GeneratePassword.generatePassword();
			
			MailSend.sendMail(email, "Reset Password", "Mail: " + email + "\nPassword:" + password);
			
			password = encoder.encode(password);
			return customerDAO.forgetPassword(email, password);

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String sendAlertMail(String email) {
		
		Customer customer=null;
		try
		{
			customer=customerDAO.getCustomerByEmail(email);
			if(customer==null)
				throw new BussinessLogicException("Error in sending email");
			
			MailSend.sendMail(customer.getEmail(),"Check Balance","Please Check your Balance !!\n Your balance is less than 5000 \n Deposit As soon as possible");

			return "Mail sent successfully!";
		}
		catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
		
	}

}
