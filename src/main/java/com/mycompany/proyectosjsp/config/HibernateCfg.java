package com.mycompany.proyectosjsp.config;

import com.mysql.cj.xdevapi.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 *
 * @author Lucas
 * 
 * Hibernate configuration class.
 * This class initializes Hibernate and provides a SessionsFactory instance.
 */
public class HibernateCfg {
    // Singleton instance of SessionFactory
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Builds and configures the SessionFactory.
     * This method loads Hibernate settings from hibernate.cfg.xml.
     * 
     * @return SessionFactory instance
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Load Hibernate configuration from hibernate.cfg.xml
            return (SessionFactory) new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception if Hibernate fails to initialize
            System.err.println("Error initializing Hibernate: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Provides access to the SessionFactory instance.
     * 
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}