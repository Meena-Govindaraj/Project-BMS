package com.revature.bms.dao.impl;

import static com.revature.bms.util.BankingManagementConstants.DELETED;
import static com.revature.bms.util.BankingManagementConstants.ERROR_IN_DELETE;
import static com.revature.bms.util.BankingManagementConstants.ERROR_IN_FETCH;
import static com.revature.bms.util.BankingManagementConstants.ERROR_IN_INSERT;
import static com.revature.bms.util.BankingManagementConstants.ERROR_IN_UPDATE;
import static com.revature.bms.util.BankingManagementConstants.SAVED;
import static com.revature.bms.util.BankingManagementConstants.UPDATED;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.bms.dao.BranchDAO;
import com.revature.bms.entity.Branch;
import com.revature.bms.exception.DatabaseException;

@SuppressWarnings("unchecked")
@Repository
public class BranchDAOImpl implements BranchDAO {

	private static final Logger logger = LogManager.getLogger(BranchDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public String addBranch(Branch branch) {

		logger.info(" Add Branch called in DAO");

		try (Session session = sessionFactory.openSession()) {
			branch.setCreatedDate(new Date());
			session.save(branch);
			logger.info(branch);

			return "Branch :" + branch.getName() + SAVED;
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_INSERT);
		}

	}

	@Override
	public List<Branch> viewAllBranch() {

		logger.info("View AllBranch Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Branch> query = session.createQuery("select b from Branch b");
			List<Branch> branches = query.list();

			return (branches.isEmpty() ? null : branches);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public String deleteBranch(Long branchId) {

		logger.info("Delete Branch Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			Branch branch = session.get(Branch.class, branchId);
			session.delete(branch);
			session.getTransaction().commit();
			logger.info(branchId);

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_DELETE);
		}

		return " Branch :" + branchId + DELETED;

	}

	@Override
	public boolean isBranchExists(Long branchId) {

		logger.info("Is Branch Exists Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Branch> query = session.createQuery("from Branch b where b.id=" + branchId);

			return query.list().isEmpty();
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	public String updateBranch(Branch branch) {

		logger.info("Update Branch Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.merge(branch);
			session.getTransaction().commit();
			return "Branch " + UPDATED;

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_UPDATE);
		}

	}

	@Override
	public Branch viewBranchById(Long branchId) {

		logger.info("View BranchById Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			return session.get(Branch.class, branchId);
		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public boolean isBranchExistsBYCode(String ifscCode) {

		logger.info("View BranchById Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			Query<Branch> query = session
					.createQuery("from Branch b where b.ifscCode='" + ifscCode + "'");

			return query.list().isEmpty();

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public Branch getBranchByIfscCode(String ifscCode) {

		logger.info("View Branch By IFSC Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			List<Branch> resultList = session.createQuery("select b from Branch b where b.ifscCode=:ifscCode")
					.setParameter("ifscCode", ifscCode).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public Branch viewBranchByName(String branchName) {

		logger.info("View BranchBy Name Called in Dao.... ");

		try (Session session = sessionFactory.openSession()) {

			List<Branch> resultList = session.createQuery("select b from Branch b where b.name=:branchName")
					.setParameter("branchName", branchName).getResultList();

			return (resultList.isEmpty() ? null : resultList.get(0));

		} catch (Exception e) {
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	
}
