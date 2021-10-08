package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.bms.dto.BranchDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.service.BranchService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=com.revature.bms.entity.Branch.class)
class BranchTest {


	@MockBean
	private BranchService branchService;
	
	
	static final List<Branch> BRANCH_TEST_DATA=Stream
			.of(new  Branch(1L, "demoBranch", "demo", "IDIBDEMO000101",new Date()),
					new  Branch(2L,"demoBranch1","demo1","IDIBDEMO0001011",new Date()))
					.collect(Collectors.toList());

	Branch branch=new Branch(4L ,"demoBranch1", "demo1", "IDIBDEMO10001", new Date());
	BranchDto branchdto=new BranchDto(0L, "DEMO3", "DEMO3", "DEMO3", new Date());
	
	//view all Branches
	@Test
	void getAllBranches() {

		when(branchService.viewAllBranch()).thenReturn(BRANCH_TEST_DATA);

		assertEquals(2, branchService.viewAllBranch().size());
	}

	//view branch by ID
	@Test
	void viewBranchById() {
		
		when(branchService.viewBranchById(4L)).thenReturn(branch);
		assertEquals(branch, branchService.viewBranchById(4L));
	}
	
	//is branch exists by code
	@Test
	void isBranchExistsBYCode()
	{
		when(branchService.isBranchExistsBYCode("IDIBDEMO10001")).thenReturn(true);
		assertEquals(true, branchService.isBranchExistsBYCode("IDIBDEMO10001"));
	
	}
	
	//get branch by IFSC CODE
	@Test
	void getBranchByIfscCode() {
		
		when(branchService.getBranchByIfscCode("IDIBDEMO10001")).thenReturn(branch);
		assertEquals(branch, branchService.getBranchByIfscCode("IDIBDEMO10001"));
	}
	
	//get branch by branch name
	@Test
	void viewBranchByName()
	{
		when(branchService.viewBranchByName("demoBranch1")).thenReturn(branch);
		assertEquals(branch, branchService.viewBranchByName("demoBranch1"));
	
	}
	
	//to delete branch
	@Test
	void deleteBranchById() {

		branchService.deleteBranch(branchdto.getId());
		verify(branchService, times(1)).deleteBranch(branchdto.getId());
	}

	//to add branch
	@Test
	void addBranchTest() {
	
		branchService.addBranch(branchdto);
		verify(branchService, times(1)).addBranch(branchdto);
	}

	//to update branch
	@Test
	void updateBranchTest() {
	
		branchService.updateBranch(branchdto);
		verify(branchService, times(1)).updateBranch(branchdto);
	}
}
