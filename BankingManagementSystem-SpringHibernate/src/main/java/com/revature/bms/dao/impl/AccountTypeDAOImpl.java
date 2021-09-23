package com.revature.bms.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.revature.bms.dao.AccounTypeDAO;
import com.revature.bms.entity.AccountType;
import com.revature.bms.entity.Customer;

@SuppressWarnings("unchecked")
@Repository
public class AccountTypeDAOImpl implements AccounTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String addAccountType(AccountType accountType) {

		System.out.println("Add AccountType Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(accountType);
			transaction.commit();
			Long typeId = accountType.getId();

			return accountType.getAccountNo() + " added successfully with Type Id: " + typeId;
		}
	}

	@Override
	public String updateAccountType(AccountType accountType) {

		System.out.println("update accountType Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(accountType);
			transaction.commit();
			return "Account type Updated successfully with Account No: " + accountType.getAccountNo();
		}
	}

	@Override
	public String deleteAccountType(Long typeId) {

		System.out.println("deleteAccountType Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();
			AccountType accountType = session.get(AccountType.class, typeId);
			session.delete(accountType);
			session.flush();
			transaction.commit();

			return "AccountType deleted successfully!, Account No: " + accountType.getAccountNo();
		}
	}

	@Override
	public boolean isAccountExists(Long typeId) {

		try (Session session = sessionFactory.openSession()) {

			Query<Customer> query = session.createQuery("from com.revature.bms.entity.AccountType where id=" + typeId);
			System.out.println(query.list());
			return query.list().isEmpty();
		}
	}

	@Override
	public List<AccountType> viewAllAccount() {

		System.out.println("viewAllAccount Types Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<AccountType> query = session.createQuery("from com.revature.bms.entity.AccountType");
			List<AccountType> accountTypes = query.list();

			return (accountTypes.isEmpty() ? null : accountTypes);
		}
	}

	@Override
	public List<AccountType> getAccountsByType(String type) {

		System.out.println("viewAllAccount Types Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<AccountType> query = session.createQuery("select a from AccountType a where a.type=?1")
					.setParameter(1, type);

			List<AccountType> accountTypes = query.list();

			return (accountTypes.isEmpty() ? null : accountTypes);
		}
	}

	@Override
	public AccountType getAccountByAccountNo(String accountNo) {

		try (Session session = sessionFactory.openSession()) {

			List<AccountType> resultList = session
					.createQuery("from com.revature.bms.entity.AccountType a where a.accountNo=?1")
					.setParameter(1, accountNo).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		}
	}

}
