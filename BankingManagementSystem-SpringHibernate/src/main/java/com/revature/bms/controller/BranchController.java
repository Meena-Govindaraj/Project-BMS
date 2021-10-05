package com.revature.bms.controller;

import static com.revature.bms.util.BankingManagementConstants.RETRIVED;

import java.util.List;

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

import com.revature.bms.dto.BranchDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.exception.BussinessLogicException;
import com.revature.bms.response.HttpResponseStatus;
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
	 * 
	 * @param branchDto
	 * @return HttpResponse with status code, message
	 */

	@PostMapping
	public ResponseEntity<HttpResponseStatus> addBranch(@RequestBody BranchDto branchDto) {

		logger.info(" Add Branch called in Controller");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), branchService.addBranch(branchDto)), HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * to get all branches that stored
	 * 
	 * // * @return list of branches as HttpResponse entity with status code,message
	 * and branch details
	 */

	@GetMapping
	public ResponseEntity<HttpResponseStatus> viewAllBranch() {

		logger.info("View AllBranch Called in Controller.... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, branchService.viewAllBranch()),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * to delete the branch by Id
	 * 
	 * @param branchId
	 * @return HttpResponse with status code and string
	 */

	@DeleteMapping("/{branchId}")
	public ResponseEntity<HttpResponseStatus> deleteBranch(@PathVariable("branchId") Long branchId) {

		logger.info("Delete Branch Called in Controller.... ");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), branchService.deleteBranch(branchId)), HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * to update branch details
	 * 
	 * @param branchDto
	 * @return HttpResponse with status code and string
	 */

	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateBranch(@RequestBody BranchDto branchDto) {

		logger.info("Update Branch Called in Controller.... ");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), branchService.updateBranch(branchDto)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * to view branch by BranchId
	 * 
	 * @param branchId
	 * @return returns HttpResponse with status code, message and branch details
	 */

	@GetMapping("/{branchId}")
	public ResponseEntity<HttpResponseStatus> viewBranchById(@PathVariable("branchId") Long branchId) {

		logger.info("View BranchById Called in Controller.... ");

		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, branchService.viewBranchById(branchId)),
					HttpStatus.OK);

		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * to get branches details on IFSC code
	 * 
	 * @param ifscCode
	 * @return returns HttpResponse with status code , message and data of
	 *         particular branch on given IFSC CODE
	 */

	@GetMapping("/getBranchByIfscCode/{ifscCode}")
	public ResponseEntity<HttpResponseStatus> getBranchByIfscCode(@PathVariable("ifscCode") String ifscCode) {

		logger.info("View Branch By IFSC Called in Service.... ");

		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED,
					branchService.getBranchByIfscCode(ifscCode)), HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * to view branch by branch name
	 * 
	 * @param branchName
	 * @return returns HttpResponse with status code ,message and the data of
	 *         particular branch on name
	 */

	@GetMapping("/getBranchByName/{branchName}")
	public ResponseEntity<HttpResponseStatus> viewBranchByName(@PathVariable("branchName") String branchName) {

		logger.info("View BranchBy Name Called in Service.... ");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), RETRIVED, branchService.viewBranchByName(branchName)),
					HttpStatus.OK);
		} catch (BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}

	}

}
