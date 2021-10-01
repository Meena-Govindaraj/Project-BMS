package com.revature.bms.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.bms.dao.AccountDAO;
import com.revature.bms.entity.Account;

@SuppressWarnings("unchecked")
@Repository
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String addAccount(Account account) {

		System.out.println("Add Account Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(account);
			transaction.commit();
			Long accountId = account.getId();

		
			return account.getAccountType().getAccountNo() + " added successfully with account Id: " + accountId;

		}
	}

	@Override
	public List<Account> viewAllAccount() {

		System.out.println("viewAllAccount  Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Account> query = session.createQuery("from com.revature.bms.entity.Account");
			List<Account> account = query.list();

			return (account.isEmpty() ? null : account);
		}

	}

	@Override
	public List<Account> getCustomersByIFSC(String ifscCode) {

		System.out.println("viewAllCustomer Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Account> query = session
					.createQuery(
							"from com.revature.bms.entity.Account c where c.accountType.customer.branch.ifscCode=?1")
					.setParameter(1, ifscCode);
			List<Account> customers = query.list();

			return (customers.isEmpty() ? null : customers);
		}
	}

	@Override
	public List<Account> getCustomerByCustomerId(Long customerId) {

		try (Session session = sessionFactory.openSession()) {

			Query<Account> query = session.createQuery("select a from Account a where a.accountType.customer.id=?1")
					.setParameter(1, customerId);

			List<Account> customers = query.list();

			return (customers.isEmpty() ? null : customers);

		}
	}

	@Override
	public Account getAccountByAccountNo(String accountNo) {


		try (Session session = sessionFactory.openSession()) {

			List<Account> resultList = session
					.createQuery("select a from Account a where a.accountType.accountNo=?1")
					.setParameter(1, accountNo).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		}
		
	}

	@Override
	public Account getAccountsByType(Long customerId, String type) {

		try (Session session = sessionFactory.openSession()) {

			List<Account> resultList = session
					.createQuery("select a from Account a where a.accountType.customer.id=?1 and a.accountType.type=?2")
					.setParameter(1, customerId).setParameter(2, type).getResultList();

			System.out.println("##### account details by type" + resultList);
			return (resultList.isEmpty() ? null : resultList.get(0));

		}
	}

	@Override
	public String bankTransfer(Long senderId, Long receiverId, Long amount) {

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();

			Query<Account> query1 = session.createQuery("update Account set balance=balance-?1 where id=?2");
			query1.setParameter(1, amount);
			query1.setParameter(2, senderId);

			query1.executeUpdate();
			 
			Query<Account> query2 = session.createQuery("update Account set balance=balance+?1 where id=?2");
			query2.setParameter(1, amount);
			query2.setParameter(2, receiverId);

			query2.executeUpdate();
			
			transaction.commit();
		}
		return "Transaction success!!";

	}
	
	@Override
	public String updateTransactionPIN(Long typeId, String password) {
		
		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();


			Query<Account> query = session.createQuery("update Account a set a.transactionPIN=?1 where a.accountType.id=?2");
			query.setParameter(1,password);
			query.setParameter(2, typeId);

			query.executeUpdate();
			
			transaction.commit();
		}
		
		return "Transacation PIN Updated successfully!";
	
	}

	@Override
	public Account getAccountByMobileNo(String mobileNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
