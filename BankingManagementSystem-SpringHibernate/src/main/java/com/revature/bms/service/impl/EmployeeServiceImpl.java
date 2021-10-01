package com.revature.bms.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bms.controller.MailSend;
import com.revature.bms.dao.BranchDAO;
import com.revature.bms.dao.EmployeeDAO;
import com.revature.bms.dto.EmployeeDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.entity.Employee;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.mapper.EmployeeMapper;
import com.revature.bms.service.*;

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
				employee.setPassword(generatePassword());
				
				String password=employee.getPassword();
				MailSend.sendMail(employee.getEmail(),"Employee Account" , "Welcome! "+employee.getName()
									+"\n Thanks For joining with us \n Registered Mobile No: "+employee.getMobileNo()
									+"Password: "+password
									+"You can Change Your password once Your logged in..Thank You ");
				
				
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
	public String updatePassword(String mobileNo, String oldPassword,String newPassword) {

		if (employeeDAO.isEmployeeExistsByMobileNo(mobileNo))
			throw new IdNotFoundException("Employee Phone Number:" + mobileNo + " Not Found to Update Password!");
		else {
			return employeeDAO.updatePassword(mobileNo,oldPassword, newPassword);
		}
	}

	@Override
	public Employee getEmployeeByMobileNo(String mobileNo) {

		return employeeDAO.getEmployeeByMobileNo(mobileNo);
	}

	@Override
	public Employee validateEmployeeLogin(String mobileNo, String password) {

		return employeeDAO.validateEmployeeLogin(mobileNo, password);
	}

	@Override
	public String forgetPassword(String email) {
	
		if (employeeDAO.isEmployeeExistsByEmail(email))
			throw new IdNotFoundException("Employee email:" + email + " Not Found to reset Password!");
		
		String password=generatePassword();
		 MailSend.sendMail(email,"Reset Password" ,"Mail: "+email+"\nPassword:"+password);
		
			return employeeDAO.forgetPassword(email,password );		
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		
		return employeeDAO.getEmployeeByEmail(email);
	}

	
	public String generatePassword()
	{
		// It will generate 6 digit random Number.
	    // from 0 to 999999
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    // this will convert any number sequence into 6 character.
	    return String.format("%06d", number);

	}

}
