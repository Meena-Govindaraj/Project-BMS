package com.revature.bms.dao.impl;

import org.hibernate.Transaction;
import static com.revature.bms.util.BankingManagementConstants.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.revature.bms.dao.BranchDAO;
import com.revature.bms.entity.Branch;
import com.revature.bms.exception.DatabaseException;

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

		logger.debug(" Add Branch called in DAO");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			branch.setCreatedDate(new Date());
			session.save(branch);
			transaction.commit();
			Long branchId = branch.getId();

			return branch.getName() + " added successfully with Branch Id: " + branchId;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_INSERT);
		}

	}

	@Override
	public List<Branch> viewAllBranch() {

		logger.debug("View AllBranch Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Branch> query = session.createQuery("from com.revature.bms.entity.Branch");
			List<Branch> branches = query.list();

			return (branches.isEmpty() ? null : branches);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public String deleteBranch(Long branchId) {

		logger.debug("Delete Branch Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();
			Branch branch = session.get(Branch.class, branchId);
			session.delete(branch);
			session.flush();
			transaction.commit();

			return " Branch Id:" + branchId + ", Branch deleted successfully!";
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_DELETE);
		}

	}

	@Override
	public boolean isBranchExists(Long branchId) {

		logger.debug("Is Branch Exists Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Branch> query = session.createQuery("from com.revature.bms.entity.Branch where id=" + branchId);

			return query.list().isEmpty();
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public String updateBranch(Branch branch) {

		logger.debug("Update Branch Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(branch);
			transaction.commit();
			Long branchId = branch.getId();

			return "Branch Updated successfully with Branch Id: " + branchId;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_UPDATE);
		}

	}

	@Override
	public Branch viewBranchById(Long branchId) {

		logger.debug("View BranchById Called in Dao.... ");
		
		try (Session session = sessionFactory.openSession()) {

			return session.get(Branch.class, branchId);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public boolean isBranchExistsBYCode(String ifscCode) {

		logger.debug("View BranchById Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Branch> query = session
					.createQuery("from com.revature.bms.entity.Branch where ifscCode='" + ifscCode + "'");

			return query.list().isEmpty();

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public Branch getBranchByIfscCode(String ifscCode) {

		logger.debug("View Branch By IFSC Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			List<Branch> resultList = session.createQuery("select b from Branch b where b.ifscCode=?1")
					.setParameter(1, ifscCode).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public Branch viewBranchByName(String branchName) {

		logger.debug("View BranchBy Name Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			List<Branch> resultList = session.createQuery("select b from Branch b where b.name=?1")
					.setParameter(1, branchName).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}
}
