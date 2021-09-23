package com.revature.bms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.bms.dao.BranchDAO;
import com.revature.bms.dao.EmployeeDAO;
import com.revature.bms.dto.EmployeeDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.entity.Employee;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.service.*;
import com.revature.bms.util.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private BranchDAO branchDAO;

	@Override
	public String addEmployee(EmployeeDto employeeDto) {

		if (employeeDto != null && employeeDto.getBranch() != null) {
			long branchId = employeeDto.getBranch().getId();

			if (branchDAO.isBranchExists(branchId))
				throw new IdNotFoundException("Branch Id:" + branchId + " Not Found to add employee!");
			if (employeeDAO.isEmployeeExistsByMobileNo(employeeDto.getMobileNo())
					&& employeeDAO.isEmployeeExistsByEmail(employeeDto.getEmail())) {
				// getting branch details to set in employee..
				Branch branch = branchDAO.viewBranchById(employeeDto.getBranch().getId());
				employeeDto.setBranch(branch);

				// dto to entity..
				Employee employee = EmployeeMapper.dtoToEntity(employeeDto);
				System.out.println(employee);
				return employeeDAO.addEmployee(employee);
			} else
				throw new DuplicateException("Employee Mobile Number Or Email Already exists!!");

		} else
			throw new InvalidInputException("employee details Not Found to add employee!");

	}

	@Override
	public String deleteEmployee(Long employeeId) {

		if (employeeDAO.isEmployeeExistsById(employeeId))
			throw new IdNotFoundException("Employee Id:" + employeeId + " Not Found to Delete!");
		else
			return employeeDAO.deleteEmployee(employeeId);
	}

	@Override
	public String updateEmployee(EmployeeDto employeeDto) {

		if (employeeDto != null && employeeDto.getBranch() != null) {
			if (!employeeDAO.isEmployeeExistsById(employeeDto.getId())) {
				long branchId = employeeDto.getBranch().getId();

				if (branchDAO.isBranchExists(branchId))
					throw new IdNotFoundException("Branch Id:" + branchId + " Not Found to add Employee!");

				Branch branch = branchDAO.viewBranchById(employeeDto.getBranch().getId());
				employeeDto.setBranch(branch);

				// dto to entity..
				Employee employee = EmployeeMapper.dtoToEntity(employeeDto);
				System.out.println(employee);
				return employeeDAO.updateEmployee(employee);

			} else
				throw new IdNotFoundException("EmployeeId:" + employeeDto.getId() + " Not Found to add employee!");

		}

		else
			throw new InvalidInputException("Employee details Not Found to add employee!");

	}

	@Override
	public List<Employee> viewAllemployee() {

		List<Employee> employees = employeeDAO.viewAllemployee();
		return (employees != null) ? employees : null;
	}

	@Override
	public Employee viewEmployeeById(Long employeeId) {

		if (employeeDAO.isEmployeeExistsById(employeeId))
			throw new IdNotFoundException("Employee Id:" + employeeId + " Not Found View!");
		return employeeDAO.viewEmployeeById(employeeId);
	}

	@Override
	public boolean isEmployeeExistsById(Long employeeId) {

		return employeeDAO.isEmployeeExistsById(employeeId);
	}

	@Override
	public boolean isEmployeeExistsByMobileNo(String mobileNo) {

		return employeeDAO.isEmployeeExistsByMobileNo(mobileNo);
	}

	@Override
	public boolean isEmployeeExistsByEmail(String email) {
		return employeeDAO.isEmployeeExistsByEmail(email);
	}

	@Override
	public String updatePassword(String mobileNo, String password) {

		if (employeeDAO.isEmployeeExistsByMobileNo(mobileNo))
			throw new IdNotFoundException("Employee Phone Number:" + mobileNo + " Not Found to Update Password!");
		else
			return employeeDAO.updatePassword(mobileNo, password);
	}

	@Override
	public Employee getEmployeeByMobileNo(String mobileNo) {

		if (employeeDAO.isEmployeeExistsByMobileNo(mobileNo))
			throw new IdNotFoundException("Employee Phone Number:" + mobileNo + " Not Found get details!");
		return employeeDAO.getEmployeeByMobileNo(mobileNo);
	}

	@Override
	public Employee validateEmployeeLogin(String mobileNo, String password) {

		if (employeeDAO.isEmployeeExistsByMobileNo(mobileNo))
			throw new IdNotFoundException("Invalid Phone No:" + mobileNo + " Not Found !");
		Employee employee = employeeDAO.validateEmployeeLogin(mobileNo, password);
		if (employee == null)
			throw new InvalidInputException("Invalid Phone No or Password!");
		else
			return employee;
	}

}
