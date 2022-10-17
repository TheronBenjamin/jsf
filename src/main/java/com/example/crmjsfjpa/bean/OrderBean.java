package com.example.crmjsfjpa.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.crmjsfjpa.dao.CustomerDao;
import com.example.crmjsfjpa.dao.OrderDao;
import com.example.crmjsfjpa.dao.impl.CustomerDaoImpl;
import com.example.crmjsfjpa.dao.impl.OrderDaoImpl;
import com.example.crmjsfjpa.exception.DaoException;
import com.example.crmjsfjpa.model.Customer;
import com.example.crmjsfjpa.model.Order;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;

@Named
@SessionScoped
public class OrderBean implements Serializable {

    private static final long serialVersionUID = 1750777382015480711L;

    Logger logger = LoggerFactory.getLogger(OrderBean.class);

    private EntityManager em;

    private OrderDao orderDao;

    private CustomerDao customerDao;

    private Order order;

    private Customer customer;

    private String customerChoice;

    private String selectedCustomerId;

    /**
     * Constructor
     */
    public OrderBean() {
        ServletContext servletContext = (ServletContext) FacesContext
                .getCurrentInstance().getExternalContext().getContext();
        this.em = (EntityManager) servletContext.getAttribute("entityManager");
        this.orderDao = new OrderDaoImpl(em);
        this.customerDao = new CustomerDaoImpl(em);
        this.customer = new Customer();
        this.order = new Order();
        this.customerChoice = "newCustomer";
    }

    /**
     * Creation of an order
     */
    public void createOrder() {
        logger.info("createOrder ! - CustomerChoice = {}", this.customerChoice);

        try {
            this.em.getTransaction().begin();
            if ("newCustomer".equals(this.customerChoice)) {
                logger.info("Nouveau client");
                this.customerDao.createCustomer(this.customer);
                FacesMessage message = new FacesMessage("CrÃ©ation du client OK !");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                logger.info("Client existant : id = {}", selectedCustomerId);
                this.customer = this.customerDao.getCustomerById(Integer.parseInt(this.selectedCustomerId));
                this.selectedCustomerId = null;
            }
            logger.info(this.customer.toString());

            this.order.setCustomer(this.customer);

            this.orderDao.createOrder(this.order);
            this.em.getTransaction().commit();
            FacesMessage message = new FacesMessage("CrÃ©ation de la commande OK !");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.customer = new Customer();
            this.order = new Order();
        } catch (DaoException e) {
            logger.error("Erreur pendant la crÃ©ation de la commande : {}", e.getMessage());
            this.em.getTransaction().rollback();
            FacesMessage message = new FacesMessage("Erreur lors de la crÃ©ation de la commande : " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * Get the order
     * @return
     */
    public Order getOrder() {
        return this.order;
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
            FacesMessage msg = new FacesMessage("Mauvais format : le numÃ©ro de tÃ©lÃ©phone doit contenir uniquement des chiffres");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else if (inputValue.length() < 4) {
            FacesMessage msg = new FacesMessage("Mauvais format : le numÃ©ro de tÃ©lÃ©phone doit contenir au moins 4 chiffres");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    /**
     * Get list of orders
     * @return
     */
    public List<Order> getOrders() {
        return this.orderDao.getAll();
    }

    /**
     * Get list of customers
     * @return
     */
    public List<Customer> getCustomers() {
        return this.customerDao.getAll();
    }

    /**
     * Delete an order
     * @param order
     */
    public void delete(Order order) {
        logger.info("deleteOrder !");
        try {
            this.em.getTransaction().begin();
            this.orderDao.deleteOrder(order);
            this.em.getTransaction().commit();
            FacesMessage message = new FacesMessage("Suppression de la commande OK !");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (DaoException e) {
            logger.error("Erreur pendant la suppression de la commande : {}", e.getMessage());
            this.em.getTransaction().rollback();
            FacesMessage message = new FacesMessage("Erreur lors de la suppression de la commande : " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String getCustomerChoice() {
        return this.customerChoice;
    }

    public void setCustomerChoice(String customerChoice) {
        this.customerChoice = customerChoice;
    }

    public String getSelectedCustomerId() {
        return this.selectedCustomerId;
    }

    public void setSelectedCustomerId(String selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }

}

