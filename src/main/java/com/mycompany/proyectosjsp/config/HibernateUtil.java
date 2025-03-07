package com.mycompany.proyectosjsp.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory emf;
    
    static {
        try {
            // Create the EntityManagerFactory using the persistence unit defined in persistence.xml
            emf = Persistence.createEntityManagerFactory("proyectosjspPU");
        } catch (Throwable ex) {
            // Terminate if creation fails
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public static void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
