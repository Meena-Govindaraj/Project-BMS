package com.revature.bms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.bms.dao.BankDAO;
import com.revature.bms.dao.BranchDAO;
import com.revature.bms.dto.BranchDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.exception.InvalidInputException;
import com.revature.bms.service.BranchService;
import com.revature.bms.util.BranchMapper;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	BranchDAO branchDAO;

	@Autowired
	BankDAO bankDAO;

	@Override
	public String addBranch(BranchDto branchDto) {

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

		List<Branch> branches = branchDAO.viewAllBranch();
		return (branches != null) ? branches : null;
	}

	@Override
	public String deleteBranch(Long branchId) {

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

		if (branchDAO.isBranchExists(branchId))
			throw new IdNotFoundException("Branch Id:" + branchId + " Not Found !");
		else
			return branchDAO.viewBranchById(branchId);

	}

	@Override
	public boolean isBranchExistsBYCode(String ifscCode) {

		return branchDAO.isBranchExistsBYCode(ifscCode);
	}

	@Override
	public Branch getBranchByIfscCode(String ifscCode) {
		if (branchDAO.isBranchExistsBYCode(ifscCode))
			throw new IdNotFoundException("ifscCode:" + ifscCode + " Not Found !");
		else
		return branchDAO.getBranchByIfscCode(ifscCode);

	}

}
