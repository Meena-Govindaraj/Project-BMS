package com.revature.bms.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bms.dao.BankDAO;
import com.revature.bms.dao.BranchDAO;
import com.revature.bms.dto.BranchDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.mapper.BranchMapper;
import com.revature.bms.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {

	private static final Logger logger = LogManager.getLogger(BranchService.class.getName());

	@Autowired
	BranchDAO branchDAO;

	@Autowired
	BankDAO bankDAO;

	@Override
	public String addBranch(BranchDto branchDto) {

		logger.debug(" Add Branch called in Service");

		if (branchDto != null) {

			if (!branchDAO.isBranchExistsBYCode(branchDto.getIfscCode()))
				throw new DuplicateException("Branch IFSC Code:" + branchDto.getIfscCode() + "Already Found!");
			else {
				// dto to entity
				Branch branch = BranchMapper.dtoToEntity(branchDto);
				System.out.println(branch);
				return branchDAO.addBranch(branch);
			}
		} else
			throw new InvalidInputException("Branch details Not Found to add Branch!");

	}

	@Override
	public List<Branch> viewAllBranch() {

		logger.debug("View AllBranch Called in Service.... ");

		List<Branch> branches = branchDAO.viewAllBranch();
		return (branches != null) ? branches : null;
	}

	@Override
	public String deleteBranch(Long branchId) {

		logger.debug("Delete Branch Called in Service.... ");

		if (branchDAO.isBranchExists(branchId))
			throw new IdNotFoundException("Branch Id:" + branchId + " Not Found to Delete!");
		else
			return branchDAO.deleteBranch(branchId);
	}

	@Override
	public boolean isBranchExists(Long branchId) {

		return branchDAO.isBranchExists(branchId);
	}

	@Override
	public String updateBranch(BranchDto branchDto) {

		logger.debug("Update Branch Called in Service.... ");

		if (branchDto != null) {

			if (branchDAO.isBranchExists(branchDto.getId()))
				throw new IdNotFoundException("Branch Id:" + branchDto.getId() + " Not Found to Update!");

			else {

				// dto to entity
				Branch branch = BranchMapper.dtoToEntity(branchDto);
				System.out.println(branch);
				return branchDAO.updateBranch(branch);
			}
		} else
			throw new InvalidInputException("Branch details Not Found to add Branch!");

	}

	@Override
	public Branch viewBranchById(Long branchId) {

		logger.debug("View BranchById Called in Service.... ");

		return branchDAO.viewBranchById(branchId);

	}

	@Override
	public boolean isBranchExistsBYCode(String ifscCode) {

		logger.debug("Is Branch Exists Called in Service.... ");

		return branchDAO.isBranchExistsBYCode(ifscCode);
	}

	@Override
	public Branch getBranchByIfscCode(String ifscCode) {

		logger.debug("View Branch By IFSC Called in Service.... ");

		return branchDAO.getBranchByIfscCode(ifscCode);

	}

	@Override
	public Branch viewBranchByName(String branchName) {

		logger.debug("View BranchBy Name Called in Service.... ");

		return branchDAO.viewBranchByName(branchName);
	}

}
