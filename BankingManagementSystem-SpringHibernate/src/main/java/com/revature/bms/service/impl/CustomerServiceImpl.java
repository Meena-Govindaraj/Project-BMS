package com.revature.bms.service.impl;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bms.controller.MailSend;
import com.revature.bms.dao.BranchDAO;
import com.revature.bms.dao.CustomerDAO;
import com.revature.bms.dao.impl.CustomerDAOImpl;
import com.revature.bms.dto.CustomerDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.entity.Customer;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.mapper.CustomerMapper;
import com.revature.bms.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LogManager.getLogger(CustomerDAOImpl.class.getName());

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private BranchDAO branchDAO;

	@Override
	public String addCustomer(CustomerDto customerDto) {

		logger.debug("Add Customer Called in Service.... ");

		if (customerDto != null && customerDto.getBranch() != null) {

			long branchId = customerDto.getBranch().getId();

			if (branchDAO.isBranchExists(branchId))
				throw new IdNotFoundException("Branch Id:" + branchId + " Not Found to add Customer!");
			else {

				// to check customer Existence...
				if (customerDAO.isCustomerExistsByMobileNo(customerDto.getMobileNo())
						&& customerDAO.isCustomerExistsByEmail(customerDto.getEmail())) {

					// getting branch details to set in customer..
					Branch branch = branchDAO.viewBranchById(branchId);
					customerDto.setBranch(branch);

					// dto to entity..
					Customer customer = CustomerMapper.dtoToEntity(customerDto);
					customer.setPassword(generatePIN());

					MailSend.sendMail(customer.getEmail(), " Account Credentials", "Regsitered Phone No: "
							+ customer.getMobileNo() + "\n Password: " + customer.getPassword());

					return customerDAO.addCustomer(customer);

				} else
					throw new DuplicateException("Customer Mobile Number and Email Already exists!!");

			}
		} else {
			throw new InvalidInputException("Customer details Not Found to add customer!");

		}
	}

	@Override
	public String deleteCustomer(Long customerId) {

		logger.debug("Delete Customer Called in Service.... ");

		if (customerDAO.isCustomerExistsById(customerId))
			throw new IdNotFoundException("Customer Id:" + customerId + " Not Found to Delete!");
		else
			return customerDAO.deleteCustomer(customerId);
	}

	@Override
	public String updateCustomer(CustomerDto customerDto) {

		logger.debug("update customer Called in Service.... ");

		if (customerDto != null && customerDto.getBranch() != null) {

			long branchId = customerDto.getBranch().getId();

			if (branchDAO.isBranchExists(branchId))
				throw new IdNotFoundException("Branch Id:" + branchId + " Not Found to add Customer!");
			else {

				// getting branch details to set in customer..
				Branch branch = branchDAO.viewBranchById(branchId);
				customerDto.setBranch(branch);

				// dto to entity..
				Customer customer = CustomerMapper.dtoToEntity(customerDto);

				return customerDAO.updateCustomer(customer);

			}
		} else {
			throw new InvalidInputException("Customer details Not Found to update customer!");

		}
	}

	@Override
	public List<Customer> viewAllCustomer() {

		logger.debug("viewAllCustomer Called in Service.... ");

		List<Customer> customers = customerDAO.viewAllCustomer();
		return (customers != null) ? customers : null;

	}

	@Override
	public Customer isCustomerExistsById(Long customerId) {

		logger.debug("View CustomerById Called in Service.... ");

		if (customerDAO.isCustomerExistsById(customerId))
			throw new IdNotFoundException("Customer Id:" + customerId + " Not Found !");
		else
			return customerDAO.viewCustomerById(customerId);
	}

	@Override
	public boolean isCustomerExistsByMobileNo(String mobileNo) {

		logger.debug("Is Customer Exists By MobileNo Called in Service.... ");

		return customerDAO.isCustomerExistsByMobileNo(mobileNo);
	}

	@Override
	public Customer viewCustomerById(Long customerId) {

		logger.debug("Is Customer Exists By customerId Called in Service.... ");

		if (customerDAO.isCustomerExistsById(customerId))
			throw new IdNotFoundException("Customer Id:" + customerId + " Not Found View!");
		return customerDAO.viewCustomerById(customerId);
	}

	@Override
	public boolean isCustomerExistsByEmail(String email) {

		logger.debug("Is Customer Exists By email Called in Service.... ");

		return customerDAO.isCustomerExistsByEmail(email);
	}

	@Override
	public String updatePassword(String mobileNo, String password) {

		logger.debug("Update password called in customer Sevice");

		if (customerDAO.isCustomerExistsByMobileNo(mobileNo))
			throw new IdNotFoundException("Customer Phone Number:" + mobileNo + " Not Found to Update Password!");
		else
			return customerDAO.updatePassword(mobileNo, password);

	}

	@Override
	public Customer validateCustomerLogin(String mobileNo, String password) {

		logger.debug("validate Customer Login called in customer Service");

		return customerDAO.validateCustomerLogin(mobileNo, password);

	}

	@Override
	public Customer getCustomerByMobileNo(String mobileNo) {

		logger.debug("Get CustomerBy MobileNo called in customer Service");

		return customerDAO.getCustomerByMobileNo(mobileNo);

	}

	@Override
	public Customer getCustomerByEmail(String email) {

		logger.debug("Get CustomerBy email called in customer Service");

		return customerDAO.getCustomerByEmail(email);
	}

	@Override
	public List<Customer> getCustomersByIFSC(String ifscCode) {

		logger.debug("Get CustomerBy IFSC called in customer Service");

		return customerDAO.getCustomersByIFSC(ifscCode);
	}

	@Override
	public String forgetPassword(String email) {

		logger.debug("Forget Password called in customer Service");

		if (customerDAO.isCustomerExistsByEmail(email))
			throw new IdNotFoundException("Customer email:" + email + " Not Found to reset Password!");

		String password = generatePIN();
		MailSend.sendMail(email, "Reset Password", "Mail: " + email + "\nPassword:" + password);

		return customerDAO.forgetPassword(email, password);

	}

	public String generatePIN() {

		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
		return String.format("%06d", number);

	}
}
