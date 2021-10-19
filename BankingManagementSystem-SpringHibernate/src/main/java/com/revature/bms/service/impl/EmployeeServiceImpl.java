package com.revature.bms.service.impl;

import static com.revature.bms.util.BankingManagementConstants.DUPLICATE_RECORD;
import static com.revature.bms.util.BankingManagementConstants.ID_NOT_FOUND;
import static com.revature.bms.util.BankingManagementConstants.INVALID_DETAILS;

import java.util.InputMismatchException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.bms.controller.MailSend;
import com.revature.bms.dao.BranchDAO;
import com.revature.bms.dao.EmployeeDAO;
import com.revature.bms.dto.EmployeeDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.entity.Employee;
import com.revature.bms.exception.BusinessLogicException;
import com.revature.bms.exception.DatabaseException;
import com.revature.bms.mapper.EmployeeMapper;
import com.revature.bms.service.EmployeeService;
import com.revature.bms.util.GeneratePassword;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class.getName());

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private BranchDAO branchDAO;

	@Override
	public String addEmployee(EmployeeDto employeeDto) {

		logger.info("Add Employee Called in Service... ");
		try {
			if (employeeDto == null || employeeDto.getBranch() == null)
				throw new BusinessLogicException("Employee " + INVALID_DETAILS);

			long branchId = employeeDto.getBranch().getId();

			if (branchDAO.isBranchExists(branchId))
				throw new BusinessLogicException("Branch Id:" + branchId + ID_NOT_FOUND);

			if (!employeeDAO.isEmployeeExistsByMobileNo(employeeDto.getMobileNo()))
				throw new BusinessLogicException("Employee " + DUPLICATE_RECORD);

			// getting branch details to set in employee..
			Branch branch = branchDAO.viewBranchById(employeeDto.getBranch().getId());
			employeeDto.setBranch(branch);

			// dto to entity..
			Employee employee = EmployeeMapper.dtoToEntity(employeeDto);
			employee.setPassword(GeneratePassword.generatePassword());

			String password = employee.getPassword();

			MailSend.sendMail(employee.getEmail(), "Employee Account",
					"Welcome! " + employee.getName() + "\n Thanks For joining with us \n Registered Mobile No: "
							+ employee.getMobileNo() + "Password: " + password
							+ "You can Change Your password once Your logged in..Thank You ");

			employee.setPassword(encoder.encode(employee.getPassword()));
			return employeeDAO.addEmployee(employee);

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		} catch (ConstraintViolationException e) {
			throw new BusinessLogicException("Employee already exists with mobileNo/email");
		} catch (InputMismatchException e) {
			throw new BusinessLogicException("Wrong Input!!");
		}

	}
	
	@Override
	public String deleteEmployee(Long employeeId) {

		logger.info("Delete Employee Called in Service... ");
		try {
			if (employeeDAO.isEmployeeExistsById(employeeId))
				throw new BusinessLogicException("Employee Id:" + employeeId + ID_NOT_FOUND);

			return employeeDAO.deleteEmployee(employeeId);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateEmployee(EmployeeDto employeeDto) {

		logger.info("Update Employee Called in Service... ");

		try {
			if (employeeDto == null || employeeDto.getBranch() == null)
				throw new BusinessLogicException("Employee " + INVALID_DETAILS);

			if (employeeDAO.isEmployeeExistsById(employeeDto.getId()))
				throw new BusinessLogicException("EmployeeId:" + employeeDto.getId() + ID_NOT_FOUND);

			long branchId = employeeDto.getBranch().getId();

			if (branchDAO.isBranchExists(branchId))
				throw new BusinessLogicException("Branch Id:" + branchId + INVALID_DETAILS);

			Branch branch = branchDAO.viewBranchById(employeeDto.getBranch().getId());
			employeeDto.setBranch(branch);

			// dto to entity..
			Employee employee = EmployeeMapper.dtoToEntity(employeeDto);
			logger.info(employee);

			return employeeDAO.updateEmployee(employee);

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}catch (ConstraintViolationException e) {
			throw new BusinessLogicException("Employee already exists with mobileNo/email");
		} 
	}

	@Override
	public List<Employee> viewAllemployee() {

		logger.info("View All Employee Called in Service... ");

		List<Employee> employees = null;
		try {
			employees = employeeDAO.viewAllemployee();
			if (employees != null)
				return employees;
			throw new BusinessLogicException("No records found");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee viewEmployeeById(Long employeeId) {

		logger.info("View EmployeeById Called in Service... ");
		try {
			if (employeeDAO.isEmployeeExistsById(employeeId))
				throw new BusinessLogicException("Employee Id:" + employeeId + ID_NOT_FOUND);
			return employeeDAO.viewEmployeeById(employeeId);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isEmployeeExistsById(Long employeeId) {

		logger.info("Is Employee Exists By Id Called in Service... ");
		try {
			return employeeDAO.isEmployeeExistsById(employeeId);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isEmployeeExistsByMobileNo(String mobileNo) {

		logger.info("Is Employee MobileNo Exists Called in Service... ");

		try {
			return employeeDAO.isEmployeeExistsByMobileNo(mobileNo);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isEmployeeExistsByEmail(String email) {

		logger.info("Is Employee Exists ByMobileNo Called in Service... ");
		try {
			return employeeDAO.isEmployeeExistsByEmail(email);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updatePassword(String mobileNo, String oldPassword, String newPassword) {

		logger.info("Update Password of employee Called in Service... ");
		try {
			if (employeeDAO.isEmployeeExistsByMobileNo(mobileNo))
				throw new BusinessLogicException("Employee Phone Number:" + mobileNo + ID_NOT_FOUND);

			Employee employee = employeeDAO.getEmployeeByMobileNo(mobileNo);

			if (encoder.matches(oldPassword, employee.getPassword())) {
				newPassword = encoder.encode(newPassword);
				return employeeDAO.updatePassword(mobileNo, oldPassword, newPassword);
			}

			throw new BusinessLogicException("Incorrect Old Password");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeByMobileNo(String mobileNo) {

		Employee employee = null;

		logger.info("Get Employee By MobileNo Called in Service... ");

		try {
			employee = employeeDAO.getEmployeeByMobileNo(mobileNo);
			if (employee != null)
				return employee;
			throw new BusinessLogicException("No Records Found");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public String forgetPassword(String email) {

		logger.info("Forget Password od employee Called in Service... ");
		try {
			if (employeeDAO.isEmployeeExistsByEmail(email))
				throw new BusinessLogicException("Employee email:" + email + ID_NOT_FOUND);

			String password = GeneratePassword.generatePassword();
			MailSend.sendMail(email, "Reset Password", "Mail: " + email + "\nPassword:" + password);

			password = encoder.encode(password);

			return employeeDAO.forgetPassword(email, password);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeByEmail(String email) {

		logger.info("Is Employee Exists By Email Called in Service... ");

		Employee employee = null;
		try {
			employee = employeeDAO.getEmployeeByEmail(email);
			if (employee != null)
				return employee;
			throw new BusinessLogicException("No Records Found");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

}
