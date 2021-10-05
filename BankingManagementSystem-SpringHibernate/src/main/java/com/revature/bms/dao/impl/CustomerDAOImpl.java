package com.revature.bms.dao.impl;


import static com.revature.bms.util.BankingManagementConstants.*;

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

@SuppressWarnings("unchecked")
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
			
			return "Customer: "+customer.getName() + SAVED + " at " + localTime;
		} catch (Exception e) {
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

			return "Customer: "+customerId +DELETED;
		} catch (Exception e) {
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
			
			return "customer"+UPDATED;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_UPDATE);
		}
	}

	@Override
	public List<Customer> viewAllCustomer() {

		logger.info("viewAllCustomer Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Customer> query = session.createQuery("from com.revature.bms.entity.Customer");
			List<Customer> customers = query.list();

			return (customers.isEmpty() ? null : customers);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	
	@Override
	public Customer viewCustomerById(Long customerId) {

		logger.info("viewCustomerById Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			return session.get(Customer.class, customerId);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public boolean isCustomerExistsByMobileNo(String mobileNo) {

		logger.info("Is Customer Exists By MobileNo Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Customer> query = session
					.createQuery("from com.revature.bms.entity.Customer where mobileNo ='" + mobileNo + "'");

			return query.list().isEmpty();
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public boolean isCustomerExistsById(Long customerId) {

		logger.info("Is Customer Exists By customerId Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Customer> query = session.createQuery("from com.revature.bms.entity.Customer where id=" + customerId);
			return query.list().isEmpty();
			
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public boolean isCustomerExistsByEmail(String email) {

		logger.info("Is Customer Exists By email Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Query<Customer> query = session
					.createQuery("from com.revature.bms.entity.Customer where email ='" + email + "'");

			return query.list().isEmpty();
		} catch (Exception e) {
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

			return "Customer"+PASSWORDUPDATED;
		}

	}

	@Override
	public Customer getCustomerByMobileNo(String mobileNo) {

		logger.info("Get CustomerBy MobileNo called in customer dao");
		
		try (Session session = sessionFactory.openSession()) {

			List<Customer> resultList = session
					.createQuery("from com.revature.bms.entity.Customer c where c.mobileNo=:mobileNo")
					.setParameter("mobileNo", mobileNo).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public Customer getCustomerByEmail(String email) {


		logger.info("Get CustomerBy email called in customer dao");
		
		try (Session session = sessionFactory.openSession()) {

			List<Customer> resultList = session.createQuery("from com.revature.bms.entity.Customer c where c.email=:email")
					.setParameter("email", email).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<Customer> getCustomersByIFSC(String ifscCode) {


		logger.info("Get CustomerBy IFSC called in customer dao");
		
		
		try (Session session = sessionFactory.openSession()) {

			Query<Customer> query = session
					.createQuery("from com.revature.bms.entity.Customer c where c.branch.ifscCode=:ifscCode")
					.setParameter("ifscCode", ifscCode);
			List<Customer> customers = query.list();

			return (customers.isEmpty() ? null : customers);
		} catch (Exception e) {
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

			return "customer"+PASSWORDUPDATED;
			
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

}
