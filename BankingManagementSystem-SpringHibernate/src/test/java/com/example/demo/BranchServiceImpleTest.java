package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.revature.bms.dto.BranchDto;
import com.revature.bms.entity.Branch;
import com.revature.bms.service.BranchService;
import com.revature.bms.service.impl.BranchServiceImpl;

class BranchServiceImpleTest {

	BranchService branchService;
	Branch branch;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		branchService=new BranchServiceImpl();
		branch=new Branch();
	}

	@AfterEach
	void tearDown() throws Exception {
		branchService=null;
		branch=null;
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

	//is branch exixts by code..
	@Test
	 void isBranchExistsBYCode() {
	String ifsc="IDIB0000107";
	String name="Chrompet";
	Branch branch=branchService.getBranchByIfscCode(ifsc);
	String excpeted=branch.getName();
	 assertEquals(excpeted,name);
	}
	

}
