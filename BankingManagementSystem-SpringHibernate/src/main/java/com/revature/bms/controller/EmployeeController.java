package com.revature.bms.controller;

import static com.revature.bms.util.BankingManagementConstants.RETRIVED;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.bms.dto.EmployeeDto;
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.response.HttpResponseStatus;
import com.revature.bms.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class.getName());

	@Autowired
	private EmployeeService employeeService;

	/**
	 * to add employee
	 * 
	 * @param employeeDto
	 * @return string on successful creation
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {

		logger.info("Add Employee Called in Controller... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), employeeService.addEmployee(employeeDto)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * to update employee details
	 * 
	 * @param employeeDto
	 * @return string on successful updation
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateEmployee(@RequestBody EmployeeDto employeeDto) {

		logger.info("Update Employee Called in Controller... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), employeeService.updateEmployee(employeeDto)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to retrieve all employees that created
	 * 
	 * @return list of employee details
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> viewAllemployee() {

		logger.info("View All Employee Called in Controller... ");

		try {

			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, employeeService.viewAllemployee()),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to delete employee on Id
	 * 
	 * @param employeeId
	 * @return string on successful deletion
	 */
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<HttpResponseStatus> deleteEmployee(@PathVariable("employeeId") Long employeeId) {

		logger.info("Delete Employee Called in Controller... ");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), employeeService.deleteEmployee(employeeId)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to update employee login password
	 * 
	 * @param mobileNo
	 * @param password
	 * @param newPassword
	 * @return string on successful updation
	 */

	@PutMapping("/updatePassword/{mobileNo}/{oldPassword}/{newPassaword}")
	public ResponseEntity<HttpResponseStatus> updatePassword(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassaword") String newPassaword) {

		logger.info("Update Password of employee Called in Controller... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),
					employeeService.updatePassword(mobileNo, oldPassword, newPassaword)), HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to reset employee login password
	 * 
	 * @param email
	 * @param password
	 * @return string on successful updation on password
	 */
	@PutMapping("/forgetPassword/{email}")
	public ResponseEntity<HttpResponseStatus> forgetPassword(@PathVariable("email") String email) {

		logger.info("Forget Password od employee Called in Controller... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), employeeService.forgetPassword(email)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to view employee on employeeId
	 * 
	 * @param employeeId
	 * @return employee data in matched employee id
	 */
	@GetMapping("/{employeeId}")
	public ResponseEntity<HttpResponseStatus> viewEmployeeById(@PathVariable("employeeId") Long employeeId) {

		logger.info("View EmployeeById Called in Controller... ");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					employeeService.viewEmployeeById(employeeId)), HttpStatus.OK);

		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * to get employee details on unique mobile no
	 * 
	 * @param mobileNo
	 * @return
	 */
	@GetMapping("/getEmployeeByMobileNo/{mobileNo}")
	public ResponseEntity<HttpResponseStatus> getEmployeeByMobileNo(@PathVariable("mobileNo") String mobileNo) {

		logger.info("Get Employee By MobileNo Called in Controller... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					employeeService.getEmployeeByMobileNo(mobileNo)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to get employee details on unique email
	 * 
	 * @param email
	 * @return employee details on given email
	 */
	@GetMapping("/getEmployeeByEmail/{email}")
	public ResponseEntity<HttpResponseStatus> getEmployeeByEmail(@PathVariable("email") String email) {

		logger.info("Is Employee Exists By Email Called in Controller... ");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, employeeService.getEmployeeByEmail(email)),
					HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

	//NOT NULL AND MIN MAX VALIDATION EXCEPTION
	
			@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
			@ExceptionHandler(MethodArgumentNotValidException.class)
			public Map<String, String> handleValidationExceptions(
			  MethodArgumentNotValidException ex) {
			  
				logger.error("######Validation error");
				Map<String, String> errors = new HashMap<>();
			    ex.getBindingResult().getAllErrors().forEach((error) -> {
			        String fieldName = ((FieldError) error).getField();
			        String errorMessage = error.getDefaultMessage();
			        errors.put(fieldName, errorMessage);
			    });
			    return errors;
			 
			}
}
