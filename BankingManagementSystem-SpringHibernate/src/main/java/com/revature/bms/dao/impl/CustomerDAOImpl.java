package com.revature.bms.dao.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.bms.dao.CustomerDAO;
import com.revature.bms.entity.Customer;

@SuppressWarnings("unchecked")
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	static final LocalDateTime localTime = LocalDateTime.now();

	@Override
	public String addCustomer(Customer customer) {

		System.out.println("addCustomer Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			customer.setCreatedDate(new Date());
			customer.setUpdatedDate(new Date());
			session.save(customer);
			transaction.commit();
			Long customerId = customer.getId();

			return customer.getName() + " added successfully with Customer Id: " + customerId + " at " + localTime;
		}
	}

	@Override
	public String deleteCustomer(Long customerId) {

		System.out.println("deleteCustomer Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();
			Customer customer = session.get(Customer.class, customerId);
			session.delete(customer);
			session.flush();
			transaction.commit();

			return "Customer deleted successfully!, Customer Id: " + customerId;
		}
	}

	@Override
	public String updateCustomer(Customer customer) {

		System.out.println("update customer Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			customer.setUpdatedDate(new Date());
			session.update(customer);
			transaction.commit();
			Long customerId = customer.getId();

			return "customer Updated successfully with customer Id: " + customerId;
		}
	}

	@Override
	public List<Customer> viewAllCustomer() {
		System.out.println("viewAllCustomer Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Customer> query = session.createQuery("from com.revature.bms.entity.Customer");
			List<Customer> customers = query.list();

			return (customers.isEmpty() ? null : customers);
		}
	}

	@Override
	public Customer viewCustomerById(Long customerId) {

		System.out.println("viewCustomerById Called in Dao.... ");
		try (Session session = sessionFactory.openSession()) {

			return session.get(Customer.class, customerId);
		}

	}

	@Override
	public boolean isCustomerExistsByMobileNo(String mobileNo) {

		try (Session session = sessionFactory.openSession()) {

			Query<Customer> query = session
					.createQuery("from com.revature.bms.entity.Customer where mobileNo ='" + mobileNo + "'");

			return query.list().isEmpty();
		}
	}

	@Override
	public boolean isCustomerExistsById(Long customerId) {

		try (Session session = sessionFactory.openSession()) {

			Query<Customer> query = session.createQuery("from com.revature.bms.entity.Customer where id=" + customerId);
			System.out.println(query.list());
			return query.list().isEmpty();
		}
	}

	@Override
	public boolean isCustomerExistsByEmail(String email) {

		try (Session session = sessionFactory.openSession()) {
			Query<Customer> query = session
					.createQuery("from com.revature.bms.entity.Customer where email ='" + email + "'");

			return query.list().isEmpty();
		}
	}

	@Override
	public String updatePassword(String mobileNo, String password) {

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();

			Customer customer = getCustomerByMobileNo(mobileNo);
			customer.setPassword(password);

			session.update(customer);
			transaction.commit();
		}
		return "Employee Password Updated successfully!";
	}

	@Override
	public Customer validateCustomerLogin(String mobileNo, String password) {

		try (Session session = sessionFactory.openSession()) {

			List<Customer> resultList = session
					.createQuery("select c from Customer c where c.mobileNo=?1 and password=?2")
					.setParameter(1, mobileNo).setParameter(2, password).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));
		}
	}

	@Override
	public Customer getCustomerByMobileNo(String mobileNo) {

		try (Session session = sessionFactory.openSession()) {

			List<Customer> resultList = session
					.createQuery("from com.revature.bms.entity.Customer c where c.mobileNo=?1")
					.setParameter(1, mobileNo).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		}
	}

}
