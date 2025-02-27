package com.mycompany.proyectosjsp.dao;

import com.mycompany.proyectosjsp.config.HibernateCfg;
import com.mycompany.proyectosjsp.models.Proyecto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * DAO class for managing Proyecto entities.
 * Implements CRUD operations using Hibernate.
 * 
 * @author Lucas
 */
public class ProyectoDAO {

    /**
     * Retrieves a project by its ID.
     *
     * @param id The project's ID
     * @return The found Proyecto entity or null if not found
     */
    public Proyecto obtenerPorId(Long id) {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.get(Proyecto.class, id);
        }
    }

    /**
     * Retrieves all projects.
     *
     * @return A list of Proyecto entities
     */
    public List<Proyecto> obtenerTodos() {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Proyecto", Proyecto.class).list();
        }
    }

    /**
     * Retrieves projects by status.
     *
     * @param estado The project status ("en curso" or "completado")
     * @return A list of Proyecto entities filtered by status
     */
    public List<Proyecto> obtenerPorEstado(String estado) {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Proyecto WHERE estado = :estado", Proyecto.class)
                    .setParameter("estado", estado)
                    .list();
        }
    }

    /**
     * Saves a new project to the database.
     *
     * @param proyecto The Proyecto entity to be saved
     */
    public void guardar(Proyecto proyecto) {
        Transaction tx;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(proyecto);
            tx.commit();
        }
    }

    /**
     * Updates an existing project in the database.
     *
     * @param proyecto The Proyecto entity to be updated
     */
    public void actualizar(Proyecto proyecto) {
        Transaction tx;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(proyecto);
            tx.commit();
        }
    }

    /**
     * Deletes a project by its ID.
     *
     * @param id The ID of the project to be deleted
     */
    public void eliminar(Long id) {
        Transaction tx;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Proyecto proyecto = session.get(Proyecto.class, id);
            if (proyecto != null) {
                session.delete(proyecto);
            }
            tx.commit();
        }
    }
}
