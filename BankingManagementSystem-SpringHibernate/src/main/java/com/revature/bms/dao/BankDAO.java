package com.revature.bms.dao;

import java.util.List;
import com.revature.bms.entity.Bank;

public interface BankDAO {

	public String addBank(Bank bank);

	public String deleteBank(Long bankId);

	public String updateBank(Bank bank);

	public List<Bank> viewAllBanks();

	public Bank viewBankById(Long bankId);

	public boolean isBankExistsByName(Bank bank);

	public boolean isBankExistsById(Long bankId);
}
