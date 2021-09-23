package com.revature.bms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.bms.dao.BranchDAO;
import com.revature.bms.dao.CustomerDAO;
import com.revature.bms.dto.CustomerDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.entity.Customer;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.service.CustomerService;
import com.revature.bms.util.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private BranchDAO branchDAO;

	@Override
	public String addCustomer(CustomerDto customerDto) {

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
					System.out.println(customer);
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

		if (customerDAO.isCustomerExistsById(customerId))
			throw new IdNotFoundException("Customer Id:" + customerId + " Not Found to Delete!");
		else
			return customerDAO.deleteCustomer(customerId);
	}

	@Override
	public String updateCustomer(CustomerDto customerDto) {

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
				System.out.println(customer);
				return customerDAO.updateCustomer(customer);

			}
		} else {
			throw new InvalidInputException("Customer details Not Found to add customer!");

		}
	}

	@Override
	public List<Customer> viewAllCustomer() {

		List<Customer> customers = customerDAO.viewAllCustomer();
		return (customers != null) ? customers : null;

	}

	@Override
	public Customer isCustomerExistsById(Long customerId) {

		if (customerDAO.isCustomerExistsById(customerId))
			throw new IdNotFoundException("Customer Id:" + customerId + " Not Found !");
		else
			return customerDAO.viewCustomerById(customerId);
	}

	@Override
	public boolean isCustomerExistsByMobileNo(String mobileNo) {

		return customerDAO.isCustomerExistsByMobileNo(mobileNo);
	}

	@Override
	public Customer viewCustomerById(Long customerId) {

		if (customerDAO.isCustomerExistsById(customerId))
			throw new IdNotFoundException("Customer Id:" + customerId + " Not Found View!");
		return customerDAO.viewCustomerById(customerId);
	}

	@Override
	public boolean isCustomerExistsByEmail(String email) {

		return customerDAO.isCustomerExistsByEmail(email);
	}

	@Override
	public String updatePassword(String mobileNo, String password) {

		if (customerDAO.isCustomerExistsByMobileNo(mobileNo))
			throw new IdNotFoundException("Customer Phone Number:" + mobileNo + " Not Found to Update Password!");
		else
			return customerDAO.updatePassword(mobileNo, password);

	}

	@Override
	public Customer validateCustomerLogin(String mobileNo, String password) {

		if (customerDAO.isCustomerExistsByMobileNo(mobileNo))
			throw new IdNotFoundException("Invalid Phone No:" + mobileNo + " Not Found !");
		Customer customer = customerDAO.validateCustomerLogin(mobileNo, password);
		if (customer == null)
			throw new InvalidInputException("Invalid Phone No or Password!");
		else
			return customer;
	}

	@Override
	public Customer getCustomerByMobileNo(String mobileNo) {

		if (customerDAO.isCustomerExistsByMobileNo(mobileNo))
			throw new IdNotFoundException("Customer Mobile No:" + mobileNo + " Not Found!");
		return customerDAO.getCustomerByMobileNo(mobileNo);

	}

}
