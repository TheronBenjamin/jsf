package com.example.crmjsfjpa.dao;

import com.example.crmjsfjpa.exception.DaoException;
import com.example.crmjsfjpa.model.User;

import java.util.List;

public interface UserDao {

	User getByUsername(String username) throws DaoException;

	List<User> getAll() throws DaoException;

	void createUser(User user) throws DaoException;
}
