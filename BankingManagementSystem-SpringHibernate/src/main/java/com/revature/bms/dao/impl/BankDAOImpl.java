package com.revature.bms.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.bms.dao.BankDAO;
import com.revature.bms.entity.Bank;
import org.hibernate.query.Query;

@SuppressWarnings("unchecked")
@Repository
public class BankDAOImpl implements BankDAO {

	@Autowired
	private SessionFactory sessionFactory;

	static final LocalDateTime localTime = LocalDateTime.now();

	@Override
	public String addBank(Bank bank) {

		System.out.println("addBank Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(bank);
			transaction.commit();
			Long bankId = bank.getId();

			return bank.getBankName() + " added successfully with Bank Id: " + bankId + " at " + localTime;
		}
	}

	@Override
	public String deleteBank(Long bankId) {

		System.out.println("deleteBank Called in Dao.... ");
		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();
			Bank bank = session.get(Bank.class, bankId);

			session.delete(bank);
			session.flush();

			transaction.commit();
			return bank.getBankName() + " deleted successfully with Bank Id: " + bankId + " at " + localTime;
		}
	}

	@Override
	public String updateBank(Bank bank) {

		System.out.println("updateBank Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(bank);
			transaction.commit();
			Long bankId = bank.getId();

			return bank.getBankName() + " Updated successfully with Bank Id: " + bankId + " at " + localTime;
		}
	}

	@Override
	public List<Bank> viewAllBanks() {

		System.out.println("viewAllBanks Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Bank> query = session.createQuery("from com.revature.bms.entity.Bank");
			List<Bank> banks = query.list();

			return (banks.isEmpty() ? null : banks);
		}
	}

	@Override
	public Bank viewBankById(Long bankId) {

		System.out.println("viewBankById Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			return session.get(Bank.class, bankId);
		}
	}

	@Override
	public boolean isBankExistsByName(Bank bank) {

		System.out.println("isBankExists Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Bank> query = session
					.createQuery("from com.revature.bms.entity.Bank where bankName='" + bank.getBankName() + "'");

			return query.list().isEmpty();
		}
	}

	@Override
	public boolean isBankExistsById(Long bankId) {

		System.out.println("isBankExistsById Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Bank> query = session.createQuery("from com.revature.bms.entity.Bank where id=" + bankId);

			return query.list().isEmpty();
		}

	}

}
