package com.mycompany.proyectosjsp.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate configuration class.
 * This class initializes Hibernate and provides a SessionFactory instance.
 * 
 * @author Lucas
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
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
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
