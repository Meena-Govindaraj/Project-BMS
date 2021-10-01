package com.revature.bms.mapper;

import com.revature.bms.dto.BranchDto;
import com.revature.bms.entity.Branch;

public class BranchMapper {

	private BranchMapper() {
	}

	// dto to entity mapping
	public static Branch dtoToEntity(BranchDto b) {
		Branch bk = new Branch();
		bk.setId(b.getId());
		bk.setName(b.getName());
		bk.setCity(b.getCity());
		bk.setIfscCode(b.getIfscCode());
		bk.setCreatedDate(b.getCreatedDate());

		return bk;

	}
}
