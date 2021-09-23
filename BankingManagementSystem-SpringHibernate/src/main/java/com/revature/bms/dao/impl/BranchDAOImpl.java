package com.revature.bms.dao.impl;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.revature.bms.dao.BranchDAO;
import com.revature.bms.entity.Branch;
import org.hibernate.SessionFactory;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
@Repository
public class BranchDAOImpl implements BranchDAO {

	private static final Logger logger = LogManager.getLogger(BranchDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String addBranch(Branch branch) {

		System.out.println("Add Branch Called in Dao.... ");
		logger.info("Entering Add Branch Function");
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			branch.setCreatedDate(new Date());
			session.save(branch);
			transaction.commit();
			Long branchId = branch.getId();

			return branch.getName() + " added successfully with Branch Id: " + branchId;
		}
	}

	@Override
	public List<Branch> viewAllBranch() {

		System.out.println("viewAllBranch Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Branch> query = session.createQuery("from com.revature.bms.entity.Branch");
			List<Branch> branches = query.list();

			return (branches.isEmpty() ? null : branches);
		}
	}

	@Override
	public String deleteBranch(Long branchId) {

		System.out.println("deleteBranch Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();
			Branch branch = session.get(Branch.class, branchId);
			session.delete(branch);
			session.flush();
			transaction.commit();

			return " Branch Id:" + branchId + ", Branch deleted successfully!";
		}

	}

	@Override
	public boolean isBranchExists(Long branchId) {

		try (Session session = sessionFactory.openSession()) {

			Query<Branch> query = session.createQuery("from com.revature.bms.entity.Branch where id=" + branchId);

			// System.err.println(query.list());

			return query.list().isEmpty();
		}

	}

	@Override
	public String updateBranch(Branch branch) {

		System.out.println("update Branch Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(branch);
			transaction.commit();
			Long branchId = branch.getId();

			return "Branch Updated successfully with Branch Id: " + branchId;
		}

	}

	@Override
	public Branch viewBranchById(Long branchId) {

		System.out.println("viewBranchById Called in Dao.... ");
		try (Session session = sessionFactory.openSession()) {

			return session.get(Branch.class, branchId);
		}
	}

	@Override
	public boolean isBranchExistsBYCode(String ifscCode) {

		System.out.println("viewBranchById Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Branch> query = session
					.createQuery("from com.revature.bms.entity.Branch where ifscCode='" + ifscCode + "'");

			return query.list().isEmpty();

		}

	}

	@Override
	public Branch getBranchByIfscCode(String ifscCode) {

		try (Session session = sessionFactory.openSession()) {

			List<Branch> resultList = session.createQuery("select b from Branch b where b.ifscCode=?1")
					.setParameter(1, ifscCode).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		}
	}
}
