package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.BranchDto;
import com.revature.bms.entity.Branch;

public interface BranchService {

	public String addBranch(BranchDto branchDto);

	public List<Branch> viewAllBranch();

	public Branch viewBranchById(Long branchId);

	public String deleteBranch(Long branchId);

	public boolean isBranchExists(Long branchId);

	public String updateBranch(BranchDto branchDto);

	public boolean isBranchExistsBYCode(String ifscCode);

	public Branch getBranchByIfscCode(String ifscCode);
}
