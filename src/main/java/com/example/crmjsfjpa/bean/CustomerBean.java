package com.example.crmjsfjpa.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import com.example.crmjsfjpa.dao.CustomerDao;
import com.example.crmjsfjpa.dao.impl.CustomerDaoImpl;
import com.example.crmjsfjpa.exception.DaoException;
import com.example.crmjsfjpa.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;

@Named
@RequestScoped
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = -826429283022955874L;
	
	Logger logger = LoggerFactory.getLogger(CustomerBean.class);
	
	private EntityManager em;
	
	private CustomerDao customerDao;
	
	private Customer customer;
	
	/**
	 * Constructor
	 */
    public CustomerBean() {
    	ServletContext servletContext = (ServletContext) FacesContext
			    .getCurrentInstance().getExternalContext().getContext();
    	this.em = (EntityManager) servletContext.getAttribute("entityManager");
		this.customerDao = new CustomerDaoImpl(em);
		this.customer = new Customer();
    }

    /**
     * Creation of a customer
     */
    public void createCustomer() {
    	logger.info("createCustomer");
    	try {
    		this.em.getTransaction().begin();
	        this.customerDao.createCustomer(this.customer);
	        this.em.getTransaction().commit();
	        FacesMessage message = new FacesMessage("Création du client OK !");
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        this.customer = new Customer();
    	} catch (DaoException e) {
    		logger.error("Erreur pendant la création du client : {}", e.getMessage());
    		this.em.getTransaction().rollback();
    		FacesMessage message = new FacesMessage("Erreur lors de la création du client : " + e.getMessage());
	        FacesContext.getCurrentInstance().addMessage(null, message);
    	}
    }
    
    /**
     * Get the customer
     * @return
     */
    public Customer getCustomer() {
        return this.customer;
    }
    
    /**
     * Specific Phone validation
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException
     */
    public void validatePhone(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    	String inputValue = (String) value;
        if (!inputValue.matches("^\\d+$")) {
            FacesMessage msg = new FacesMessage("Mauvais format : le numéro de téléphone doit contenir uniquement des chiffres");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else if (inputValue.length() < 4) {
        	FacesMessage msg = new FacesMessage("Mauvais format : le numéro de téléphone doit contenir au moins 4 chiffres");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
    /**
     * Get list of customers
     * @return
     */
    public List<Customer> getCustomers() {
    	logger.info("getCustomers()");
    	return this.customerDao.getAll();
    }
    
    /**
     * Delete a customer
     * @param customer
     */
    public void delete(Customer customer) {
    	logger.info("deleteCustomer !");
    	try {
    		this.em.getTransaction().begin();
	        this.customerDao.deleteCustomer(customer);
	        this.em.getTransaction().commit();
	        FacesMessage message = new FacesMessage("Suppression du client OK !");
	        FacesContext.getCurrentInstance().addMessage(null, message);
    	} catch (DaoException e) {
    		logger.error("Erreur pendant la suppression du client : {}", e.getMessage());
    		this.em.getTransaction().rollback();
    		FacesMessage message = new FacesMessage("Erreur lors de la suppression du client : " + e.getMessage());
	        FacesContext.getCurrentInstance().addMessage(null, message);
    	}
    }

}
