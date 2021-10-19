package com.revature.bms.dao.impl;

import static com.revature.bms.util.BankingManagementConstants.*;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.bms.dao.TransactionDAO;
import com.revature.bms.entity.TransactionDetails;
import com.revature.bms.exception.DatabaseException;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

	private static final Logger logger = LogManager.getLogger(TransactionDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	static final LocalDateTime localTime = LocalDateTime.now();

	@Override
	public String addTransaction(TransactionDetails transactionDetails) {

		logger.info("Add Transaction details Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.save(transactionDetails);
			session.getTransaction().commit();

			logger.info(transactionDetails);

			return "Transacation of " + transactionDetails.getAccount().getAccountType().getAccountNo() + SAVED;
		} catch (Exception e) {
			logger.error("Error in adding transactions "+transactionDetails );
			
			throw new DatabaseException(ERROR_IN_INSERT);
		}
	}

	@Override
	public List<TransactionDetails> viewAllTransaction() {

		logger.info("view All Transaction Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<TransactionDetails> query = session.createQuery("select t from TransactionDetails t",TransactionDetails.class);
			List<TransactionDetails> transactions = query.list();

			return (transactions.isEmpty() ? null : transactions);
		} catch (Exception e) {
			logger.error("Error Fetching all transactions" );
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<TransactionDetails> viewTransactionByAccount(Long accountId) {

		logger.info("View A Transaction on account in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<TransactionDetails> query = session
					.createQuery("from TransactionDetails a where a.account.id=:accountId order by transactionDate desc",TransactionDetails.class)
					.setParameter("accountId", accountId);

			List<TransactionDetails> accounts = query.list();

			return (accounts.isEmpty() ? null : accounts);

		} catch (Exception e) {
			logger.error("Error in Fetching transactions of account "+accountId);
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

}
