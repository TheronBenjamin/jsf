package com.example.crmjsfjpa.dao.impl;

import com.example.crmjsfjpa.dao.CustomerDao;
import com.example.crmjsfjpa.exception.DaoException;
import com.example.crmjsfjpa.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
	private EntityManager em;

	public CustomerDaoImpl(EntityManager em) {
		this.em = em;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Customer> getAll() throws DaoException {
		try {
			TypedQuery<Customer> query = em.createQuery("SELECT c from Customer c order by c.id", Customer.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Customer getCustomerById(Integer id) throws DaoException {
		try {
			return em.find(Customer.class, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void createCustomer(Customer customer) throws DaoException {
		try {
			em.persist(customer);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteCustomer(Customer customer) throws DaoException {
		try {
			em.remove(em.merge(customer));
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
