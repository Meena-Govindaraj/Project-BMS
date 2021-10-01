package com.revature.bms.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.bms.dao.TransactionDAO;
import com.revature.bms.entity.TransactionDetails;

@SuppressWarnings("unchecked")
@Repository
public class TransactionDAOImpl implements TransactionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	static final LocalDateTime localTime = LocalDateTime.now();

	@Override
	public String addTransaction(TransactionDetails transactionDetails) {

		System.out.println("Add AccountType Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(transactionDetails);
			transaction.commit();

			return "Transacation of " + transactionDetails.getAccount().getAccountType().getAccountNo()
					+ " added successfully with Type Id: " + transactionDetails;
		}

	}

	@Override
	public List<TransactionDetails> viewAllTransaction() {

		System.out.println("viewAllTransaction Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<TransactionDetails> query = session.createQuery("from com.revature.bms.entity.TransactionDetails");
			List<TransactionDetails> transactions = query.list();

			return (transactions.isEmpty() ? null : transactions);
		}
	}

	@Override
	public List<TransactionDetails> viewTransactionByAccount(Long accountId) {
		System.out.println("viewATransaction in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<TransactionDetails> query = session
					.createQuery("from com.revature.bms.entity.TransactionDetails a where a.account.id=?1")
					.setParameter(1, accountId);

			List<TransactionDetails> accounts = query.list();
		
			return (accounts.isEmpty() ? null : accounts);

		}

	}

}
