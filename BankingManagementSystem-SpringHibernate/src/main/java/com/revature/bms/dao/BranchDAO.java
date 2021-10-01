package com.revature.bms.dao;

import java.util.List;
import com.revature.bms.entity.Branch;

public interface BranchDAO {

	/**
	 * To add branch 
	 * @param branch
	 * @return string
	 */
	public String addBranch(Branch branch);
	
	/**
	 * to get all branches that stored
	 * @return list of branches
	 */

	public List<Branch> viewAllBranch();
	
	/**
	 * to view branch by BranchId
	 * @param branchId
	 * @return returns the data of particular branch
	 */

	public Branch viewBranchById(Long branchId);
	
	/**
	 * to delete the branch by Id
	 * @param branchId
	 * @return string
	 */

	public String deleteBranch(Long branchId);
	
	/**
	 * to check branch existence
	 * @param branchId
	 * @return boolean based on existence of branch
	 */

	public boolean isBranchExists(Long branchId);
	
	/**
	 * to update branch details
	 * @param branch
	 * @return string
	 */

	public String updateBranch(Branch branch);
	
	/**
	 * to check IFSC code existence
	 * @param ifscCode
	 * @return boolean ,based on existence of branch IFSC code
	 */

	public boolean isBranchExistsBYCode(String ifscCode);
	
	/**
	 * to get branches details on IFSC code
	 * @param ifscCode
	 * @return returns the data of particular branch on given IFSC CODE
	 */

	public Branch getBranchByIfscCode(String ifscCode);
	
	/**
	 * to view branch by branch name
	 * @param branchName
	 * @return returns the data of particular branch on name
	 */

	public Branch viewBranchByName(String branchName);
	
}
