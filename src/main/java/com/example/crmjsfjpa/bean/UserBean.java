package com.example.crmjsfjpa.bean;

import com.example.crmjsfjpa.dao.UserDao;
import com.example.crmjsfjpa.dao.impl.UserDaoImpl;
import com.example.crmjsfjpa.exception.DaoException;
import com.example.crmjsfjpa.model.Customer;
import com.example.crmjsfjpa.model.User;
import com.example.crmjsfjpa.model.UserGrantsEnum;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class UserBean implements Serializable {

    Logger logger = LoggerFactory.getLogger(CustomerBean.class);

    private EntityManager em;

    private UserDao userDao;

    private User user;

    public UserBean() {
        ServletContext servletContext = (ServletContext) FacesContext
                .getCurrentInstance().getExternalContext().getContext();
        this.em = (EntityManager) servletContext.getAttribute("entityManager");
        this.userDao = new UserDaoImpl(em);
        this.user = new User();
    }

    public void createUser() {
        logger.info("createUser");
        try {
            user.setGrants(UserGrantsEnum.USER);
            this.em.getTransaction().begin();
            this.userDao.createUser(this.user);
            this.em.getTransaction().commit();
            FacesMessage message = new FacesMessage("Création de l'utilisateur OK !");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.user = new User();
        } catch (DaoException e) {
            logger.error("Erreur pendant la création de l'utilisateur : {}", e.getMessage());
            this.em.getTransaction().rollback();
            FacesMessage message = new FacesMessage("Erreur lors de la création de l'utilisateur : " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public User getUser() {
        return this.user;
    }

    public List<User> getUsers() {
        logger.info("getUsers()");
        return this.userDao.getAll();
    }


    public void delete(User user) {
        logger.info("deleteUser !");
        try {
            this.em.getTransaction().begin();
            this.userDao.deleteUser(user);
            this.em.getTransaction().commit();
            FacesMessage message = new FacesMessage("Suppression de l'utilisateur OK !");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (DaoException e) {
            logger.error("Erreur pendant la suppression de l'utilisateur : {}", e.getMessage());
            this.em.getTransaction().rollback();
            FacesMessage message = new FacesMessage("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

}
