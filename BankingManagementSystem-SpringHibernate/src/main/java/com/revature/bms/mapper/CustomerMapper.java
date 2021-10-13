package com.revature.bms.mapper;

import com.revature.bms.dto.CustomerDto;
import com.revature.bms.entity.Customer;

public class CustomerMapper {

	private CustomerMapper() {
	}

	public static Customer dtoToEntity(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setId(customerDto.getId());
		customer.setName(customerDto.getName());
		customer.setMobileNo(customerDto.getMobileNo());
		customer.setPassword(customerDto.getPassword());
		customer.setState(customerDto.getState());
		customer.setAge(customerDto.getAge());
		customer.setEmail(customerDto.getEmail());
		customer.setCreatedDate(customerDto.getCreatedDate());
		customer.setUpdatedDate(customerDto.getUpdatedDate());
		customer.setBranch(customerDto.getBranch());
		return customer;
	}
}
