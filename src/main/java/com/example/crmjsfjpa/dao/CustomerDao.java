package com.example.crmjsfjpa.dao;


import com.example.crmjsfjpa.exception.DaoException;
import com.example.crmjsfjpa.model.Customer;

import java.util.List;

public interface CustomerDao {

	Customer getCustomerById(Integer id) throws DaoException;
	List<Customer> getAll() throws DaoException;
	void createCustomer(Customer customer) throws DaoException;
	void deleteCustomer(Customer customer) throws DaoException;
}
