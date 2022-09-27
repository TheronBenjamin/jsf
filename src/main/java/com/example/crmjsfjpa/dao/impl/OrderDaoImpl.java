package com.example.crmjsfjpa.dao.impl;


import com.example.crmjsfjpa.dao.OrderDao;
import com.example.crmjsfjpa.exception.DaoException;
import com.example.crmjsfjpa.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
	private final EntityManager em;

	public OrderDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Order getByLabel(String label) throws DaoException {
		try {
			Query namedQuery = em.createNamedQuery("Order.findByLabel", Order.class);
			namedQuery.setParameter("label", label);
			return (Order) namedQuery.getSingleResult();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Order> getAll() throws DaoException {
		try {
			TypedQuery<Order> query = em.createQuery("SELECT o from Order o order by o.id", Order.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Order getOrderById(Integer id) throws DaoException {
		try {
			return em.find(Order.class, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createOrder(Order order) throws DaoException {
		try {
			em.persist(order);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
