package com.example.crmjsfjpa.dao;


import com.example.crmjsfjpa.exception.DaoException;
import com.example.crmjsfjpa.model.Order;

import java.util.List;

public interface OrderDao {

	Order getOrderById(Integer id) throws DaoException;

	Order getByLabel(String label) throws DaoException;

	List<Order> getAll() throws DaoException;

	void createOrder(Order order) throws DaoException;
}
