package com.example.crmjsfjpa.config;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

import static com.example.crmjsfjpa.Constantes.ATTRIBUTE_EM_FACTORY;


@WebListener
public class InitDaoFactory implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();

        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        EntityManager em = Persistence.createEntityManagerFactory("crm", properties).createEntityManager();
        servletContext.setAttribute(ATTRIBUTE_EM_FACTORY, em);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Rien à réaliser lors de la fermeture de l'application
    }

}
