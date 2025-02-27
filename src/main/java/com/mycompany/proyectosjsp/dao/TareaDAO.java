package com.mycompany.proyectosjsp.dao;

import com.mycompany.proyectosjsp.config.HibernateCfg;
import com.mycompany.proyectosjsp.models.Tarea;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO class for managing Tarea entities.
 * Implements CRUD operations for Tarea using Hibernate.
 * 
 * @author Lucas
 */
public class TareaDAO {

    /**
     * Saves a new task to the database.
     *
     * @param tarea The Tarea entity to be saved
     */
    public void guardar(Tarea tarea) {
        Transaction tx = null;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(tarea);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

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
     * Retrieves tasks by project ID.
     *
     * @param idProyecto The project ID
     * @return A list of tasks associated with the specified project
     */
    public List<Tarea> obtenerTareasPorProyecto(Long idProyecto) {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Tarea WHERE proyecto.id = :idProyecto", Tarea.class)
                    .setParameter("idProyecto", idProyecto)
                    .list();
        }
    }

    /**
     * Updates an existing task in the database.
     *
     * @param tarea The Tarea entity to be updated
     */
    public void actualizar(Tarea tarea) {
        Transaction tx = null;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(tarea);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to be deleted
     */
    public void eliminar(Long id) {
        Transaction tx = null;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Tarea tarea = session.get(Tarea.class, id);
            if (tarea != null) {
                session.delete(tarea);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
