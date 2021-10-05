package com.revature.bms.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bms.controller.MailSend;
import com.revature.bms.dao.BranchDAO;
import com.revature.bms.dao.EmployeeDAO;
import com.revature.bms.dto.EmployeeDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.entity.Employee;
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.exception.DatabaseException;
import com.revature.bms.mapper.EmployeeMapper;
import com.revature.bms.service.EmployeeService;
import com.revature.bms.util.GeneratePassword;
import static com.revature.bms.util.BankingManagementConstants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class.getName());

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private BranchDAO branchDAO;

	@Override
	public String addEmployee(EmployeeDto employeeDto) {

		logger.info("Add Employee Called in Service... ");
		try {
			if (employeeDto != null && employeeDto.getBranch() != null) {
				long branchId = employeeDto.getBranch().getId();

				if (branchDAO.isBranchExists(branchId))
					throw new BussinessLogicException("Branch Id:" + branchId + ID_NOT_FOUND);

				if (employeeDAO.isEmployeeExistsByMobileNo(employeeDto.getMobileNo())) {

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

					return employeeDAO.addEmployee(employee);

				} else
					throw new BussinessLogicException("Employee " + DUPLICATE_RECORD);

			} else
				throw new BussinessLogicException("Employee " + INVALID_DETAILS);

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		} catch (ConstraintViolationException e) {
			throw new BussinessLogicException("Mobile No already exists");
		}
	}

	@Override
	public String deleteEmployee(Long employeeId) {

		logger.info("Delete Employee Called in Service... ");
		try {
			if (employeeDAO.isEmployeeExistsById(employeeId))
				throw new BussinessLogicException("Employee Id:" + employeeId + ID_NOT_FOUND);
			else
				return employeeDAO.deleteEmployee(employeeId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateEmployee(EmployeeDto employeeDto) {

		logger.info("Update Employee Called in Service... ");

		try {
			if (employeeDto != null && employeeDto.getBranch() != null) {
				if (!employeeDAO.isEmployeeExistsById(employeeDto.getId())) {
					long branchId = employeeDto.getBranch().getId();

					if (branchDAO.isBranchExists(branchId))
						throw new BussinessLogicException("Branch Id:" + branchId + INVALID_DETAILS);

					Branch branch = branchDAO.viewBranchById(employeeDto.getBranch().getId());
					employeeDto.setBranch(branch);

					// dto to entity..
					Employee employee = EmployeeMapper.dtoToEntity(employeeDto);
					logger.info(employee);

					return employeeDAO.updateEmployee(employee);

				} else
					throw new BussinessLogicException("EmployeeId:" + employeeDto.getId() + ID_NOT_FOUND);

			}

			else
				throw new BussinessLogicException("Employee " + INVALID_DETAILS);

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
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
			throw new BussinessLogicException("No records found");
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee viewEmployeeById(Long employeeId) {

		logger.info("View EmployeeById Called in Service... ");
		try {
			if (employeeDAO.isEmployeeExistsById(employeeId))
				throw new BussinessLogicException("Employee Id:" + employeeId + ID_NOT_FOUND);
			return employeeDAO.viewEmployeeById(employeeId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isEmployeeExistsById(Long employeeId) {

		logger.info("Is Employee Exists By Id Called in Service... ");
		try {
			return employeeDAO.isEmployeeExistsById(employeeId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isEmployeeExistsByMobileNo(String mobileNo) {

		logger.info("Is Employee MobileNo Exists Called in Service... ");

		try {
			return employeeDAO.isEmployeeExistsByMobileNo(mobileNo);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isEmployeeExistsByEmail(String email) {

		logger.info("Is Employee Exists ByMobileNo Called in Service... ");
		try {
			return employeeDAO.isEmployeeExistsByEmail(email);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updatePassword(String mobileNo, String oldPassword, String newPassword) {

		logger.info("Update Password of employee Called in Service... ");
		try {
			if (employeeDAO.isEmployeeExistsByMobileNo(mobileNo))
				throw new BussinessLogicException("Employee Phone Number:" + mobileNo + ID_NOT_FOUND);
			else {
				return employeeDAO.updatePassword(mobileNo, oldPassword, newPassword);
			}
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
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
			employee = employeeDAO.validateEmployeeLogin(mobileNo, password);
			if (employee != null)
				return employee;
			else
				throw new BussinessLogicException("No records found");

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String forgetPassword(String email) {

		logger.info("Forget Password od employee Called in Service... ");
		try {
			if (employeeDAO.isEmployeeExistsByEmail(email))
				throw new BussinessLogicException("Employee email:" + email + ID_NOT_FOUND);

			String password = GeneratePassword.generatePassword();
			MailSend.sendMail(email, "Reset Password", "Mail: " + email + "\nPassword:" + password);

			return employeeDAO.forgetPassword(email, password);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
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
			throw new BussinessLogicException("No Records Found");
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}

	}

}
