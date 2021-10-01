package com.revature.bms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.bms.dto.EmployeeDto;
import com.revature.bms.entity.Employee;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employeeDto) {

		return new ResponseEntity<>(employeeService.addEmployee(employeeDto), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDto employeeDto) {

		return new ResponseEntity<>(employeeService.updateEmployee(employeeDto), HttpStatus.OK);

	}

	@PutMapping("updatePassword/{mobileNo}/{oldPassword}/{newPassaword}")
	public ResponseEntity<String> updatePassword(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassaword") String newPassaword) {

		return new ResponseEntity<>(employeeService.updatePassword(mobileNo, oldPassword, newPassaword), HttpStatus.OK);

	}

	@PutMapping("forgetPassword/{email}")
	public ResponseEntity<String> forgetPassword(@PathVariable("email") String email) {

		return new ResponseEntity<>(employeeService.forgetPassword(email), HttpStatus.OK);

	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long employeeId) {

		return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<Employee>> viewAllemployee() {

		return new ResponseEntity<>(employeeService.viewAllemployee(), HttpStatus.OK);
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> viewEmployeeById(@PathVariable("employeeId") Long employeeId) {

		return new ResponseEntity<>(employeeService.viewEmployeeById(employeeId), HttpStatus.OK);

	}

	@GetMapping("getEmployeeByMobileNo/{mobileNo}")
	public ResponseEntity<Employee> getEmployeeByMobileNo(@PathVariable("mobileNo") String mobileNo) {

		return new ResponseEntity<>(employeeService.getEmployeeByMobileNo(mobileNo), HttpStatus.OK);

	}

	@GetMapping("getEmployeeByEmail/{email}")
	public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable("email") String email) {

		return new ResponseEntity<>(employeeService.getEmployeeByEmail(email), HttpStatus.OK);

	}

	
	@GetMapping("/employeeLogin/{mobileNo}/{password}")
	public ResponseEntity<Employee> validateEmployeeLogin(@PathVariable("mobileNo") String mobileNo,
			@PathVariable("password") String password) {

		return new ResponseEntity<>(employeeService.validateEmployeeLogin(mobileNo, password), HttpStatus.OK);

	}

	// exception for Duplicate branch insertion..
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// exception for Id not Found ..
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	// exception for invalid input
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<String> invalidInput(InvalidInputException e) {

		System.out.println(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
