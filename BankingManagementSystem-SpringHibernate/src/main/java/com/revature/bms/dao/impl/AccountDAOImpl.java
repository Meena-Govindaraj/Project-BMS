package com.revature.bms.dao.impl;

import static com.revature.bms.util.BankingManagementConstants.ERROR_IN_FETCH;
import static com.revature.bms.util.BankingManagementConstants.ERROR_IN_INSERT;
import static com.revature.bms.util.BankingManagementConstants.SAVED;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.bms.dao.AccountDAO;
import com.revature.bms.entity.Account;
import com.revature.bms.exception.DatabaseException;

@Repository
public class AccountDAOImpl implements AccountDAO {

	private static final Logger logger = LogManager.getLogger(AccountDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String addAccount(Account account) {

		logger.info("Add Account Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.save(account);
			session.getTransaction().commit();

			logger.info(account);

			return "Account: " + account.getAccountType().getAccountNo() + SAVED;

		} catch (Exception e) {
			logger.error("Error in Adding Account" + account);
			throw new DatabaseException(ERROR_IN_INSERT);
		}
	}

	@Override
	public List<Account> viewAllAccount() {

		logger.info("viewAllAccount  Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Account> query = session.createQuery("select a from Account a");
			List<Account> account = query.list();

			return (account.isEmpty() ? null : account);
		} catch (Exception e) {
			logger.error("Error in Fetching Accounts");
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public List<Account> getCustomersByIFSC(String ifscCode) {

		logger.info("Get Customers ByIFSC Called in dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query query = null;
			query = session.createQuery("from Account c where c.accountType.customer.branch.ifscCode=:ifscCode")
					.setParameter("ifscCode", ifscCode);

			List<Account> customers = query.list();

			return (customers.isEmpty() ? null : customers);
		} catch (Exception e) {
			logger.error("Error in Feteching Account on IFSC" + ifscCode);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<Account> getCustomerByCustomerId(Long customerId) {

		logger.info("Get Customer ByCustomerId Called in dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query query = session
					.createQuery("select a from Account a where a.accountType.customer.id=:customerId")
					.setParameter("customerId", customerId);

			List<Account> customers = query.list();

			return (customers.isEmpty() ? null : customers);

		} catch (Exception e) {
			logger.error("Error in Fetching Account of customer " + customerId);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public Account getAccountByAccountNo(String accountNo) {

		logger.info("Get Account ByAccountNo Called in dao.... ");
		try (Session session = sessionFactory.openSession()) {

			List<Account> resultList = session
					.createQuery("select a from Account a where a.accountType.accountNo=:accountNo")
					.setParameter("accountNo", accountNo).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			logger.error("Error in Fetching Account of account No: " + accountNo);
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public Account getAccountsByType(Long customerId, String type) {

		logger.info("Get AccountsByType Called in dao.... ");
		try (Session session = sessionFactory.openSession()) {

			List<Account> resultList = session.createQuery(
					"select a from Account a where a.accountType.customer.id=:customerId and a.accountType.type=:type")
					.setParameter("customerId", customerId).setParameter("type", type).getResultList();

			logger.info(resultList);
			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			logger.error("Error in Fetching Account of customer " + customerId);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public String bankTransfer(Long senderId, Long receiverId, Long amount) {

		logger.info("Bank Transfer Called in dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			Query query1 = session
					.createQuery("update Account set balance=balance-:amount where id=:senderId");
			query1.setParameter("amount", amount);
			query1.setParameter("senderId", senderId);

			query1.executeUpdate();

			Query query2 = session
					.createQuery("update Account set balance=balance+:amount where id=:receiverId");
			query2.setParameter("amount", amount);
			query2.setParameter("receiverId", receiverId);

			query2.executeUpdate();

			session.getTransaction().commit();

			return "Transaction success";

		} catch (Exception e) {
			logger.error("Error in Transfering fund ");
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public String updateTransactionPIN(Long typeId, String password) {

		logger.info("Update TransactionPIN Called in dao.... ");
		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			Query query = session
					.createQuery("update Account a set a.transactionPIN=:password where a.accountType.id=:typeId");
			query.setParameter("password", password);
			query.setParameter("typeId", typeId);

			query.executeUpdate();

			session.getTransaction().commit();
			return "Transacation PIN Updated successfully!";

		} catch (Exception e) {
			logger.error("Error in Updating Transaction PIN");
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public Account getAccountByAccountId(Long accountId) {
		
		logger.info("Get Account By AccountId Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			return session.get(Account.class, accountId);
		} catch (Exception e) {
			logger.error("Error in Fetching Account of accountId: " + accountId);
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

}
