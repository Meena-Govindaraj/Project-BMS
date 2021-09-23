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
import com.revature.bms.dto.BranchDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.service.BranchService;

@RestController
@RequestMapping("/branch")
@CrossOrigin("http://localhost:4200")
public class BranchController {

	// private static final Logger logger =
	// Logger.getLogger(BranchController.class);

	@Autowired
	private BranchService branchService;

	// insert a branch
	@PostMapping
	public ResponseEntity<String> addBranch(@RequestBody BranchDto branchDto) {

		System.out.println("SYSOUT..Entering Add Branch Function");

		return new ResponseEntity<>(branchService.addBranch(branchDto), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Branch>> viewAllBranch() {

		return new ResponseEntity<>(branchService.viewAllBranch(), HttpStatus.OK);
	}

	@DeleteMapping("/{branchId}")
	public ResponseEntity<String> deleteBranch(@PathVariable("branchId") Long branchId) {

		return new ResponseEntity<>(branchService.deleteBranch(branchId), HttpStatus.OK);

	}

	@PutMapping
	public ResponseEntity<String> updateBranch(@RequestBody BranchDto branchDto) {

		return new ResponseEntity<>(branchService.updateBranch(branchDto), HttpStatus.OK);
	}

	@GetMapping("/{branchId}")
	public ResponseEntity<Branch> viewBranchById(@PathVariable("branchId") Long branchId) {

		return new ResponseEntity<>(branchService.viewBranchById(branchId), HttpStatus.OK);

	}

	@GetMapping("getBranchByIfscCode/{ifscCode}")
	public ResponseEntity<Branch> getBranchByIfscCode(@PathVariable("ifscCode") String ifscCode) {

		return new ResponseEntity<>(branchService.getBranchByIfscCode(ifscCode), HttpStatus.OK);

	}

	// exception for Duplicate branch insertion..
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateException e) {

		System.out.println(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// exception for Id not Found ..
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {

		System.out.println(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	// exception for invalid input
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<String> invalidInput(InvalidInputException e) {

		System.out.println(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
