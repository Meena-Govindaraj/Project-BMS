package com.revature.bms.dao;

import java.util.List;
import com.revature.bms.entity.Branch;

public interface BranchDAO {

	public String addBranch(Branch branch);

	public List<Branch> viewAllBranch();

	public Branch viewBranchById(Long branchId);

	public String deleteBranch(Long branchId);

	public boolean isBranchExists(Long branchId);

	public String updateBranch(Branch branch);

	public boolean isBranchExistsBYCode(String ifscCode);

	public Branch getBranchByIfscCode(String ifscCode);

}
