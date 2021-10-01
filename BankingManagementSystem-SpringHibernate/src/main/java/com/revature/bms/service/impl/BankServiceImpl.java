package com.revature.bms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bms.dao.BankDAO;
import com.revature.bms.dto.BankDto;
import com.revature.bms.entity.Bank;
import com.revature.bms.exception.DuplicateException;
import com.revature.bms.exception.IdNotFoundException;
import com.revature.bms.mapper.BankMapper;
import com.revature.bms.service.BankService;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDAO bankDAO;

	@Override
	public String addBank(BankDto bankDto) {

		// dto to entity
		Bank bank = BankMapper.dtoToEntity(bankDto);

		if (bankDAO.isBankExistsByName(bank))
			return bankDAO.addBank(bank);
		else
			throw new DuplicateException("Bank Already Found! BankName: " + bankDto.getBankName());

	}

	@Override
	public String deleteBank(Long bankId) {

		if (bankDAO.isBankExistsById(bankId))
			throw new IdNotFoundException("Bank Id:" + bankId + " Not Found to Delete!");
		else
			return bankDAO.deleteBank(bankId);
	}

	@Override
	public String updateBank(BankDto bankDto) {

		// dto to entity
		Bank bank = BankMapper.dtoToEntity(bankDto);
		if (bankDAO.isBankExistsById(bankDto.getId()))
			throw new IdNotFoundException("BankId:" + bankDto.getId() + " Not Found to update!");
		else
			return bankDAO.updateBank(bank);

	}

	@Override
	public List<Bank> viewAllBanks() {

		List<Bank> banks = bankDAO.viewAllBanks();
		return (banks != null) ? banks : null;
	}

	@Override
	public boolean isBankExistsByName(BankDto bankDto) {

		// dto to entity
		Bank bank = BankMapper.dtoToEntity(bankDto);
		return bankDAO.isBankExistsByName(bank);
	}

	@Override
	public boolean isBankExistsById(Long bankId) {

		return bankDAO.isBankExistsById(bankId);
	}

	@Override
	public Bank viewBankById(Long bankId) {

		if (bankDAO.isBankExistsById(bankId))
			throw new IdNotFoundException("Bank Id:" + bankId + " Not Found!");
		else
			return bankDAO.viewBankById(bankId);
	}

}
