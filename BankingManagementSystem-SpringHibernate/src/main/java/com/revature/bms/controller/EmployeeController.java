package com.revature.bms.controller;

import static com.revature.bms.util.BankingManagementConstants.RETRIVED;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping
	public ResponseEntity<HttpResponseStatus> addEmployee(@RequestBody EmployeeDto employeeDto) {

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


	@PutMapping("/updatePassword/{mobileNo}/{oldPassword}/{newPassaword}")
	public ResponseEntity<HttpResponseStatus> updatePassword(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassaword") String newPassaword) {

		logger.info("Update Password of employee Called in Controller... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), employeeService.updatePassword(mobileNo, oldPassword, newPassaword)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}
		
	}

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

	@GetMapping("/employeeLogin/{mobileNo}/{password}")
	public ResponseEntity<HttpResponseStatus> validateEmployeeLogin(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("password") String password) {

		logger.info("Validate Employee Login Called in Controller... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					employeeService.validateEmployeeLogin(mobileNo, password)), HttpStatus.OK);
		}

		catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

}
