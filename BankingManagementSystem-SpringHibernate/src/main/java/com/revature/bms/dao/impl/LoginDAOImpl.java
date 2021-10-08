package com.revature.bms.dao.impl;

import static com.revature.bms.util.BankingManagementConstants.ERROR_IN_FETCH;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.bms.dao.LoginDAO;
import com.revature.bms.entity.Customer;
import com.revature.bms.entity.Employee;
import com.revature.bms.exception.DatabaseException;


@Repository
public class LoginDAOImpl implements LoginDAO {

	private static final Logger logger = LogManager.getLogger(LoginDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Customer validateCustomerLogin(String mobileNo, String password) {

		logger.info("validate Customer Login called in customer dao");

		try (Session session = sessionFactory.openSession()) {

			List<Customer> resultList = session
					.createQuery("select c from Customer c where c.mobileNo=:mobileNo and password=:password")
					.setParameter("mobileNo", mobileNo).setParameter("password", password).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			logger.error("Error in Customer Login "+mobileNo );
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public Employee validateEmployeeLogin(String mobileNo, String password) {

		logger.info("Validate Employee Login Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			List<Employee> resultList = session
					.createQuery("select e from Employee e where e.mobileNo=:mobileNo and password=:password")
					.setParameter("mobileNo", mobileNo).setParameter("password", password).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			logger.error("Error in Employee Login "+mobileNo );
			
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

}
