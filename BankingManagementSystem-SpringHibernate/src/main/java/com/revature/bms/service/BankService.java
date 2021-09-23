package com.revature.bms.service;

import java.util.List;

import com.revature.bms.dto.BankDto;
import com.revature.bms.entity.Bank;

public interface BankService {

	public String addBank(BankDto bankDto);

	public String deleteBank(Long bankId);

	public String updateBank(BankDto bankDto);

	public List<Bank> viewAllBanks();

	public Bank viewBankById(Long bankId);

	public boolean isBankExistsByName(BankDto bankDto);

	public boolean isBankExistsById(Long bankId);

}
