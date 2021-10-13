package com.revature.bms.dao.impl;

import static com.revature.bms.util.BankingManagementConstants.DELETED;
import static com.revature.bms.util.BankingManagementConstants.ERROR_IN_DELETE;
import static com.revature.bms.util.BankingManagementConstants.ERROR_IN_FETCH;
import static com.revature.bms.util.BankingManagementConstants.ERROR_IN_INSERT;
import static com.revature.bms.util.BankingManagementConstants.ERROR_IN_UPDATE;
import static com.revature.bms.util.BankingManagementConstants.PASSWORDUPDATED;
import static com.revature.bms.util.BankingManagementConstants.SAVED;
import static com.revature.bms.util.BankingManagementConstants.UPDATED;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.bms.dao.CustomerDAO;
import com.revature.bms.entity.Customer;
import com.revature.bms.exception.DatabaseException;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private static final Logger logger = LogManager.getLogger(CustomerDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	static final LocalDateTime localTime = LocalDateTime.now();

	@Override
	public String addCustomer(Customer customer) {

		logger.info("Add Customer Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			customer.setCreatedDate(new Date());
			customer.setUpdatedDate(new Date());
			session.save(customer);
			session.getTransaction().commit();

			logger.info(customer);

			return "Customer: " + customer.getName() + SAVED + " at " + localTime;
		} catch (Exception e) {
			logger.error("Error in inserting customer " + customer);
			
			
			throw new DatabaseException(ERROR_IN_INSERT);
		}
	}

	@Override
	public String deleteCustomer(Long customerId) {

		logger.info("Delete Customer Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			Customer customer = session.get(Customer.class, customerId);
			session.delete(customer);
			session.flush();
			session.getTransaction().commit();

			return "Customer: " + customerId + DELETED;
		} catch (Exception e) {
			logger.error("Error in deleting customer " + customerId);
			
			throw new DatabaseException(ERROR_IN_DELETE);
		}
	}

	@Override
	public String updateCustomer(Customer customer) {

		logger.info("update customer Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			customer.setUpdatedDate(new Date());
			session.update(customer);
			session.getTransaction().commit();

			logger.info(customer);

			return "customer" + UPDATED;
		} catch (Exception e) {
			logger.error("Error in updating customer " + customer);
			
			throw new DatabaseException(ERROR_IN_UPDATE);
		}
	}

	@Override
	public List<Customer> viewAllCustomer() {

		logger.info("viewAllCustomer Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query query = session.createQuery("select c from Customer c");
			List<Customer> customers = query.list();

			return (customers.isEmpty() ? null : customers);
		} catch (Exception e) {
			logger.error("Error in Fetching all customers");
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public Customer viewCustomerById(Long customerId) {

		logger.info("viewCustomerById Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			return session.get(Customer.class, customerId);
		} catch (Exception e) {
			logger.error("Error in Fetching customer " + customerId);
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public boolean isCustomerExistsByMobileNo(String mobileNo) {

		logger.info("Is Customer Exists By MobileNo Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query query = session
					.createQuery("from Customer c where c.mobileNo ='" + mobileNo + "'");

			return query.list().isEmpty();
		} catch (Exception e) {
			logger.error("Error in fetching customer of mobile No" + mobileNo);
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public boolean isCustomerExistsById(Long customerId) {

		logger.info("Is Customer Exists By customerId Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query query = session.createQuery("from Customer c where c.id=" + customerId);
			return query.list().isEmpty();

		} catch (Exception e) {
			logger.error("Error in fetching customer " + customerId);
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public boolean isCustomerExistsByEmail(String email) {

		logger.info("Is Customer Exists By email Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Query query = session
					.createQuery("from Customer c where c.email ='" + email + "'");

			return query.list().isEmpty();
		} catch (Exception e) {
			logger.error("Error in fetching customer on email " + email);
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public String updatePassword(String mobileNo, String password) {

		logger.info("Update password called in customer dao");

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();

			Customer customer = getCustomerByMobileNo(mobileNo);
			customer.setPassword(password);

			session.update(customer);
			transaction.commit();

			return "Customer" + PASSWORDUPDATED;
		}catch (Exception e) {
			logger.error("Error in Updating customer password" );
			
			throw new DatabaseException(ERROR_IN_UPDATE);
		}

	}

	@Override
	public Customer getCustomerByMobileNo(String mobileNo) {

		logger.info("Get CustomerBy MobileNo called in customer dao");

		try (Session session = sessionFactory.openSession()) {

			List<Customer> resultList = session
					.createQuery("from Customer c where c.mobileNo=:mobileNo")
					.setParameter("mobileNo", mobileNo).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			
			logger.error("Error in Fecting customer "+mobileNo );
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public Customer getCustomerByEmail(String email) {

		logger.info("Get CustomerBy email called in customer dao");

		try (Session session = sessionFactory.openSession()) {

			List<Customer> resultList = session
					.createQuery("from Customer c where c.email=:email")
					.setParameter("email", email).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			logger.error("Error in Fetching customer "+email );
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<Customer> getCustomersByIFSC(String ifscCode) {

		logger.info("Get CustomerBy IFSC called in customer dao");

		try (Session session = sessionFactory.openSession()) {

			Query query = session
					.createQuery("from Customer c where c.branch.ifscCode=:ifscCode")
					.setParameter("ifscCode", ifscCode);
			List<Customer> customers = query.list();

			return (customers.isEmpty() ? null : customers);
		} catch (Exception e) {
			logger.error("Error in fetching customers on ifsc "+ifscCode );
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public String forgetPassword(String email, String password) {

		logger.info("Forget Password called in customer dao");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			Customer customer = getCustomerByEmail(email);
			customer.setPassword(password);

			session.update(customer);
			session.getTransaction().commit();

			return "customer" + PASSWORDUPDATED;

		} catch (Exception e) {
			logger.error("Error in rest password in customer" );
			
			throw new DatabaseException(ERROR_IN_UPDATE);
		}
	}

}
