package com.revature.bms.dao.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.bms.dao.EmployeeDAO;
import com.revature.bms.entity.Employee;
import com.revature.bms.exception.DatabaseException;

import static com.revature.bms.util.BankingManagementConstants.*;

@SuppressWarnings("unchecked")
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private static final Logger logger = LogManager.getLogger(EmployeeDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	static final LocalDateTime localTime = LocalDateTime.now();

	@Transactional
	@Override
	public String addEmployee(Employee employee) {

		logger.info("Add Employee Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			employee.setCreatedDate(new Date());
			employee.setUpdatedDate(new Date());
			session.save(employee);

			logger.info(employee);

			return employee.getName() + SAVED + localTime;

		}

		catch (Exception e) {
			throw new DatabaseException(ERROR_IN_INSERT);
		}

	}

	@Override
	public String deleteEmployee(Long employeeId) {

		logger.info("Delete Employee Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

		    session.beginTransaction();
		    Employee employee = session.get(Employee.class, employeeId);
			session.delete(employee);
			session.getTransaction().commit();

			return "Employee Id: " + employeeId + DELETED;
		
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_DELETE);
		}

	}

	@Override
	public String updateEmployee(Employee employee) {

		logger.info("Update Employee Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			employee.setUpdatedDate(new Date());
			session.update(employee);
			session.getTransaction().commit();

			logger.info(employee);
			return "Employee :" + employee.getName() + UPDATED;
		
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_UPDATE);
		}
	}

	@Override
	public List<Employee> viewAllemployee() {

		logger.info("View All Employee Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Employee> query = session.createQuery("from com.revature.bms.entity.Employee");
			List<Employee> employees = query.list();

			return (employees.isEmpty() ? null : employees);
	
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public Employee viewEmployeeById(Long employeeId) {

		logger.info("View EmployeeById Called in Dao.... ");
		
		try (Session session = sessionFactory.openSession()) {

			return session.get(Employee.class, employeeId);
		
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public boolean isEmployeeExistsById(Long employeeId) {

		logger.info("Is Employee Exists By Id Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Employee> query = session.createQuery("from com.revature.bms.entity.Employee where id=" + employeeId);
	
			logger.info(query.list());
			return query.list().isEmpty();
		
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public Employee getEmployeeByMobileNo(String mobileNo) {

		logger.info("Get Employee By MobileNo Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			List<Employee> resultList = session.createQuery("select e from Employee e where e.mobileNo=:mobileNo")
					.setParameter("mobileNo", mobileNo).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public boolean isEmployeeExistsByMobileNo(String mobileNo) {

		logger.info("Is Employee Exists ByMobileNo Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Query<Employee> query = session
					.createQuery("from com.revature.bms.entity.Employee where mobileNo ='" + mobileNo + "'");

			return query.list().isEmpty();
		
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public String updatePassword(String mobileNo, String oldPassword, String newPassword) {

		logger.info("Update Password of employee Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();

			Employee employee = getEmployeeByMobileNo(mobileNo);
			employee.setPassword(newPassword);
			session.update(employee);
			transaction.commit();
			
			return "Employee" + PASSWORDUPDATED;
	
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
		
	}

	@Override
	public boolean isEmployeeExistsByEmail(String email) {

		logger.info("Is Employee Exists By Email Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Query<Employee> query = session
					.createQuery("from com.revature.bms.entity.Employee where email ='" + email + "'");

			return query.list().isEmpty();
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public String forgetPassword(String email, String password) {

		logger.info("Forget Password od employee Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			Employee employee = getEmployeeByEmail(email);
			employee.setPassword(password);

			session.update(employee);

			session.getTransaction().commit();
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
		return "Employee " + PASSWORDUPDATED;
	}

	@Override
	public Employee getEmployeeByEmail(String email) {

		logger.info("Get Employee By Email Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			List<Employee> resultList = session.createQuery("select e from Employee e where e.email=:email")
					.setParameter("email", email).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

}
