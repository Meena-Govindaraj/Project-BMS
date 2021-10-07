package com.revature.bms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static com.revature.bms.util.BankingManagementConstants.*;
import com.revature.bms.dao.AccountTypeDAO;
import com.revature.bms.entity.AccountType;
import com.revature.bms.entity.Branch;
import com.revature.bms.entity.Customer;
import com.revature.bms.exception.DatabaseException;

@SuppressWarnings("unchecked")
@Repository
public class AccountTypeDAOImpl implements AccountTypeDAO {

	private static final Logger logger = LogManager.getLogger(AccountTypeDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public String addAccountType(AccountType accountType) {

		logger.info("Add AccountType Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.save(accountType);

			logger.info(accountType);

			return "Account:" + accountType.getAccountNo() + SAVED;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_INSERT);
		}
	}

	@Override
	public String updateAccountType(AccountType accountType) {

		logger.info("Update AccountType Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.update(accountType);
			session.getTransaction().commit();

			logger.info(accountType);

			return "Account: " + accountType.getAccountNo() + UPDATED;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_UPDATE);
		}
	}

	@Override
	public String deleteAccountType(Long typeId) {

		logger.info("Delete AccountType Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			AccountType accountType = session.get(AccountType.class, typeId);
			session.delete(accountType);
			session.getTransaction().commit();

			return "Account: " + accountType.getAccountNo() + DELETED;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_DELETE);
		}
	}

	@Override
	public boolean isAccountExists(Long typeId) {

		logger.info("Is Account Exists Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<AccountType> query = session.createQuery("select a from AccountType a where a.id=" + typeId);

			logger.info(query.list());
			return query.list().isEmpty();
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<AccountType> viewAllAccount() {

		logger.info("View All Account Types Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<AccountType> query = session.createQuery("select a AccountType a");
			List<AccountType> accountTypes = query.list();

			return (accountTypes.isEmpty() ? null : accountTypes);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<AccountType> getAccountsByType(String type) {

		logger.info("Get AccountsBy Type Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<AccountType> query = session.createQuery("select a from AccountType a where a.type:type")
					.setParameter("type", type);

			List<AccountType> accountTypes = query.list();

			return (accountTypes.isEmpty() ? null : accountTypes);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public AccountType getAccountByAccountNo(String accountNo) {

		logger.info("Get AccountBy AccountNo Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			List<AccountType> resultList = session
					.createQuery("select a from AccountType a where a.accountNo=:accountNo")
					.setParameter("accountNo", accountNo).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<AccountType> viewCustomerById(Long customerId) {

		logger.info("View CustomerBy Id Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<AccountType> query = session
					.createQuery("select a from AccountType a where a.customer.id=:customerId")
					.setParameter("customerId", customerId);

			List<AccountType> accountTypes = query.list();

			return (accountTypes.isEmpty() ? null : accountTypes);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public AccountType isAccountTypeExists(String mobileNo, String email, String type) {

		logger.info("Is AccountType Exists Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			List<AccountType> resultList = session.createQuery(
					"select a from AccountType  a where a.customer.mobileNo=:mobileNo and a.customer.email=:email and a.type=:type")
					.setParameter("mobileNo", mobileNo).setParameter("email", email).setParameter("type", type)
					.getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public String updateAccountStatus(String accountStatus, String accountNo) {

		logger.info("Update AccountStatus Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			AccountType account = getAccountByAccountNo(accountNo);
			account.setAccountStatus(accountStatus);

			session.update(account);
			session.getTransaction().commit();

			return "Status of Customer Account :" + accountNo + UPDATED;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<AccountType> getCustomersByIFSC(String ifscCode) {

		logger.info("viewAllCustomer Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<AccountType> query = session
					.createQuery(
							"select a from AccountType a where a.customer.branch.ifscCode=:ifscCode")
					.setParameter("ifscCode", ifscCode);
			List<AccountType> customers = query.list();

			return (customers.isEmpty() ? null : customers);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public AccountType viewAccountByTypeId(Long typeId) {
	
		logger.info("View Account Type By Id Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			return session.get(AccountType.class, typeId);
		} 
		catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

}
