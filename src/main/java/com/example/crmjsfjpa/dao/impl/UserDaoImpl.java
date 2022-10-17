package com.example.crmjsfjpa.dao.impl;

import com.example.crmjsfjpa.dao.UserDao;
import com.example.crmjsfjpa.exception.DaoException;
import com.example.crmjsfjpa.model.User;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.TypedQuery;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private EntityManager em;

    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User getByUsername(String username) throws DaoException {
        return null;
    }

    @Override
    public List<User> getAll() throws DaoException {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u from User u order by u.id", User.class);
            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException("Droit du user inconnu: " + e.getMessage());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void createUser(User user) throws DaoException {
        try {
            em.persist(user);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteUser(User user) throws DaoException {
        try{
            em.remove(user);
        } catch (Exception e){
            throw new DaoException(e);
        }
    }
}
