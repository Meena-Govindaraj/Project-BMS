package com.revature.bms.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.revature.bms.exception.DatabaseException;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.service.BranchService;

@RestController
@RequestMapping("/branch")
@CrossOrigin("http://localhost:4200")
public class BranchController {
	
	private static final Logger logger = LogManager.getLogger(BranchController.class.getName());

	@Autowired
	private BranchService branchService;

	/**
	 * To add branch 
	 * @param branchDto
	 * @return response as string with status code
	 */

	@PostMapping
	public ResponseEntity<String> addBranch(@RequestBody BranchDto branchDto) {

		logger.debug(" Add Branch called in Controller");

		return new ResponseEntity<>(branchService.addBranch(branchDto), HttpStatus.OK);
	}

	/**
	 * to get all branches that stored
	 * @return list of branches as response entity with status code
	 */
	
	@GetMapping
	public ResponseEntity<List<Branch>> viewAllBranch() {

		logger.debug("View AllBranch Called in Controller.... ");

		return new ResponseEntity<>(branchService.viewAllBranch(), HttpStatus.OK);
	}

	/**
	 * to delete the branch by Id
	 * @param branchId
	 * @return response string entity with status code
	 */
	
	@DeleteMapping("/{branchId}")
	public ResponseEntity<String> deleteBranch(@PathVariable("branchId") Long branchId) {

		logger.debug("Delete Branch Called in Controller.... ");

		return new ResponseEntity<>(branchService.deleteBranch(branchId), HttpStatus.OK);

	}

	/**
	 * to update branch details
	 * @param branchDto
	 * @return response entity as string status code
	 */
	
	@PutMapping
	public ResponseEntity<String> updateBranch(@RequestBody BranchDto branchDto) {

		logger.debug("Update Branch Called in Controller.... ");

		return new ResponseEntity<>(branchService.updateBranch(branchDto), HttpStatus.OK);
	}

	/**
	 * to view branch by BranchId
	 * @param branchId
	 * @return returns the data of particular branch
	 */

	@GetMapping("/{branchId}")
	public ResponseEntity<Branch> viewBranchById(@PathVariable("branchId") Long branchId) {

		logger.debug("View BranchById Called in Controller.... ");
		
		return new ResponseEntity<>(branchService.viewBranchById(branchId), HttpStatus.OK);

	}
	
	/**
	 * to get branches details on IFSC code
	 * @param ifscCode
	 * @return returns the data of particular branch on given IFSC CODE
	 */

	@GetMapping("getBranchByIfscCode/{ifscCode}")
	public ResponseEntity<Branch> getBranchByIfscCode(@PathVariable("ifscCode") String ifscCode) {

		logger.debug("View Branch By IFSC Called in Service.... ");

		return new ResponseEntity<>(branchService.getBranchByIfscCode(ifscCode), HttpStatus.OK);

	}

	/**
	 * to view branch by branch name
	 * @param branchName
	 * @return returns the data of particular branch on name
	 */

	@GetMapping("getBranchByName/{branchName}")
	public ResponseEntity<Branch> viewBranchByName(@PathVariable("branchName") String branchName) {

		logger.debug("View BranchBy Name Called in Service.... ");

		return new ResponseEntity<>(branchService.viewBranchByName(branchName), HttpStatus.OK);

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

	// database Exception
	@ExceptionHandler(DatabaseException.class)

	public ResponseEntity<String> databaseException(DatabaseException e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
