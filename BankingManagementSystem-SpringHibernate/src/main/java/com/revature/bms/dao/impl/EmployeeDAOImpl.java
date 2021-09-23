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

import com.revature.bms.dao.EmployeeDAO;
import com.revature.bms.entity.Employee;

@SuppressWarnings("unchecked")
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	static final LocalDateTime localTime = LocalDateTime.now();

	@Override
	public String addEmployee(Employee employee) {

		System.out.println("addEmployee Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			employee.setCreatedDate(new Date());
			employee.setUpdatedDate(new Date());
			session.save(employee);
			transaction.commit();
			Long employeeId = employee.getId();

			return employee.getName() + " added successfully with Employee Id: " + employeeId + " at " + localTime;
		}
	}

	@Override
	public String deleteEmployee(Long employeeId) {

		System.out.println("deleteEmployee Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();
			Employee employee = session.get(Employee.class, employeeId);
			session.delete(employee);
			session.flush();
			transaction.commit();

			return "Employee deleted successfully!, employee Id: " + employeeId;
		}

	}

	@Override
	public String updateEmployee(Employee employee) {

		System.out.println("update Employee Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			employee.setUpdatedDate(new Date());
			session.update(employee);
			transaction.commit();
			Long employeeId = employee.getId();

			return "Employee Updated successfully with Employee Id: " + employeeId;
		}
	}

	@Override
	public List<Employee> viewAllemployee() {

		System.out.println("viewAllemployee Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Employee> query = session.createQuery("from com.revature.bms.entity.Employee");
			List<Employee> employees = query.list();

			return (employees.isEmpty() ? null : employees);
		}
	}

	@Override
	public Employee viewEmployeeById(Long employeeId) {

		System.out.println("viewEmployeeById Called in Dao.... ");
		try (Session session = sessionFactory.openSession()) {

			return session.get(Employee.class, employeeId);
		}
	}

	@Override
	public boolean isEmployeeExistsById(Long employeeId) {

		try (Session session = sessionFactory.openSession()) {

			Query<Employee> query = session.createQuery("from com.revature.bms.entity.Employee where id=" + employeeId);
			System.out.println(query.list());
			return query.list().isEmpty();
		}

	}

	@Override
	public Employee getEmployeeByMobileNo(String mobileNo) {

		try (Session session = sessionFactory.openSession()) {

			List<Employee> resultList = session.createQuery("select e from Employee e where e.mobileNo=?1")
					.setParameter(1, mobileNo).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		}

	}

	@Override
	public boolean isEmployeeExistsByMobileNo(String mobileNo) {

		try (Session session = sessionFactory.openSession()) {
			Query<Employee> query = session
					.createQuery("from com.revature.bms.entity.Employee where mobileNo ='" + mobileNo + "'");

			return query.list().isEmpty();
		}
	}

	@Override
	public String updatePassword(String mobileNo, String password) {

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();

			Employee employee = getEmployeeByMobileNo(mobileNo);
			employee.setPassword(password);

			session.update(employee);
			transaction.commit();
		}
		return "Employee Password Updated successfully!";
	}

	@Override
	public Employee validateEmployeeLogin(String mobileNo, String password) {

		try (Session session = sessionFactory.openSession()) {

			List<Employee> resultList = session
					.createQuery("select e from Employee e where e.mobileNo=?1 and password=?2")
					.setParameter(1, mobileNo).setParameter(2, password).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));
		}

	}

	@Override
	public boolean isEmployeeExistsByEmail(String email) {

		try (Session session = sessionFactory.openSession()) {
			Query<Employee> query = session
					.createQuery("from com.revature.bms.entity.Employee where email ='" + email + "'");

			return query.list().isEmpty();
		}
	}

}
