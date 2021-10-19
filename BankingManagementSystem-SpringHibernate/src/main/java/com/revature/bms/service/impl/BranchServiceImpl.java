package com.revature.bms.service.impl;

import static com.revature.bms.util.BankingManagementConstants.DUPLICATE_RECORD;
import static com.revature.bms.util.BankingManagementConstants.ID_NOT_FOUND;
import static com.revature.bms.util.BankingManagementConstants.INVALID_DETAILS;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bms.dao.BranchDAO;
import com.revature.bms.dto.BranchDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.exception.BusinessLogicException;
import com.revature.bms.exception.DatabaseException;
import com.revature.bms.mapper.BranchMapper;
import com.revature.bms.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {

	private static final Logger logger = LogManager.getLogger(BranchServiceImpl.class.getName());

	@Autowired
	BranchDAO branchDAO;

	@Override
	public String addBranch(BranchDto branchDto) {

		logger.info(" Add Branch called in Service");
		try {
			if (branchDto == null)
				throw new BusinessLogicException("Branch " + INVALID_DETAILS);

			if (!branchDAO.isBranchExistsBYCode(branchDto.getIfscCode()))
				throw new BusinessLogicException("Branch IFSC Code: " + branchDto.getIfscCode() + " "+DUPLICATE_RECORD);

			// dto to entity
			Branch branch = BranchMapper.dtoToEntity(branchDto);
			return branchDAO.addBranch(branch);

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		} catch (ConstraintViolationException e) {
			throw new BusinessLogicException(" Branch Already exists");
		}
		
	}

	@Override
	public String deleteBranch(Long branchId) {

		logger.info("Delete Branch Called in Service.... ");
		try {
			if (branchDAO.isBranchExists(branchId))
				throw new BusinessLogicException("Branch Id:" + branchId + ID_NOT_FOUND);
			else
				return branchDAO.deleteBranch(branchId);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());

		}
	}

	@Override
	public List<Branch> viewAllBranch() {

		logger.info("View AllBranch Called in Service.... ");
		try {
			List<Branch> branches = null;
			branches = branchDAO.viewAllBranch();
			if (branches == null)
				throw new BusinessLogicException("No records Found");

			return branches;

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());

		}
	}

	@Override
	public String updateBranch(BranchDto branchDto) {

		logger.info("Update Branch Called in Service.... ");

		try {
			if (branchDto == null)
				throw new BusinessLogicException("Branch " + INVALID_DETAILS);

			if (branchDAO.isBranchExists(branchDto.getId()))
				throw new BusinessLogicException("Branch Id:" + branchDto.getId() + ID_NOT_FOUND);

			// dto to entity
			Branch branch = BranchMapper.dtoToEntity(branchDto);
			return branchDAO.updateBranch(branch);
		}
		
		catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}catch (ConstraintViolationException e) {
			throw new BusinessLogicException(" Branch Already exists");
		}
	}

	@Override
	public Branch viewBranchById(Long branchId) {

		logger.info("View BranchById Called in Service.... ");

		Branch branch = null;
		try {
			branch = branchDAO.viewBranchById(branchId);
			if (branch == null)
				throw new BusinessLogicException("No records Found");

			return branch;

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Branch getBranchByIfscCode(String ifscCode) {

		logger.info("View Branch By IFSC Called in Service.... ");

		Branch branch = null;
		try {
			branch = branchDAO.getBranchByIfscCode(ifscCode);
			if (branch == null)
				throw new BusinessLogicException("No Record Found");

			return branch;

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Branch viewBranchByName(String branchName) {

		logger.info("View Branch By Name Called in Service.... ");

		Branch branch = null;
		try {

			branch = branchDAO.viewBranchByName(branchName);
			if (branch == null)
				throw new BusinessLogicException("No Record Found");

			return branch;
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public boolean isBranchExistsBYCode(String ifscCode) {

		logger.info("Is Branch Exists Called in Service.... ");
		try {
			return branchDAO.isBranchExistsBYCode(ifscCode);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isBranchExists(Long branchId) {

		logger.info("Is Branch Exists By Id Called in Service.... ");

		try {
			return branchDAO.isBranchExists(branchId);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}
}
