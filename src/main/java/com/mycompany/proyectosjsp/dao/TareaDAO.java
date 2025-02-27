package com.mycompany.proyectosjsp.dao;

import com.mycompany.proyectosjsp.config.HibernateCfg;
import com.mycompany.proyectosjsp.models.Tarea;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * DAO class for managing Tarea entities. Implements CRUD operations using
 * Hibernate.
 *
 * @author Lucas
 */
public class TareaDAO {

    /**
     * Retrieves a task by its ID.
     *
     * @param id The task's ID
     * @return The found Tarea entity or null if not found
     */
    public Tarea obtenerPorId(Long id) {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.get(Tarea.class, id);
        }
    }

    /**
     * Retrieves all tasks.
     *
     * @return A list of Tarea entities
     */
    public List<Tarea> obtenerTodas() {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Tarea", Tarea.class).list();
        }
    }

    /**
     * Retrieves all tasks for a specific project.
     *
     * @param idProyecto The project ID
     * @return A list of tasks linked to the project
     */
    public List<Tarea> obtenerTareasPorProyecto(Long idProyecto) {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Tarea WHERE proyecto.id = :idProyecto", Tarea.class)
                    .setParameter("idProyecto", idProyecto)
                    .list();
        }
    }

    /**
     * Retrieves all tasks for a specific project.
     *
     * @param idProyecto The project ID
     * @return A list of Tarea entities linked to the project
     */
    public List<Tarea> obtenerPorProyecto(Long idProyecto) {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Tarea WHERE proyecto.id = :idProyecto", Tarea.class)
                    .setParameter("idProyecto", idProyecto)
                    .list();
        }
    }

    /**
     * Saves a new task to the database.
     *
     * @param tarea The Tarea entity to be saved
     */
    public void guardar(Tarea tarea) {
        Transaction tx;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(tarea);
            tx.commit();
        }
    }

    /**
     * Updates an existing task in the database.
     *
     * @param tarea The Tarea entity to be updated
     */
    public void actualizar(Tarea tarea) {
        Transaction tx;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(tarea);
            tx.commit();
        }
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to be deleted
     */
    public void eliminar(Long id) {
        Transaction tx;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Tarea tarea = session.get(Tarea.class, id);
            if (tarea != null) {
                session.delete(tarea);
            }
            tx.commit();
        }
    }
}
