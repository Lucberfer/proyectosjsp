package com.mycompany.proyectosjsp.dao;

import com.mycompany.proyectosjsp.config.HibernateCfg;
import com.mycompany.proyectosjsp.dao.interfaces.CrudDAO;
import com.mycompany.proyectosjsp.models.Proyecto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * DAO class for managing Proyecto entities.
 * Implements the generic CRUD operations from CrudDAO.
 *
 * @author Lucas
 */
public class ProyectoDAO implements CrudDAO<Proyecto> {

    /**
     * Retrieves a project by its ID.
     *
     * @param id The project's ID
     * @return The found Proyecto entity or null if not found
     */
    @Override
    public Proyecto obtenerPorId(Long id) {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.get(Proyecto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all projects.
     *
     * @return A list of Proyecto entities
     */
    @Override
    @SuppressWarnings("unchecked")  // Avoids Hibernate warning
    public List<Proyecto> obtenerTodos() {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Proyecto", Proyecto.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Return an empty list instead of null
        }
    }

    /**
     * Saves a new project to the database.
     *
     * @param proyecto The project entity to be saved
     */
    @Override
    public void guardar(Proyecto proyecto) {
        Transaction tx = null;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(proyecto);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();  // Rollback transaction on error
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing project in the database.
     *
     * @param proyecto The project entity to be updated
     */
    @Override
    public void actualizar(Proyecto proyecto) {
        Transaction tx = null;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(proyecto);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();  // Rollback transaction on error
            e.printStackTrace();
        }
    }

    /**
     * Deletes a project by its ID.
     *
     * @param id The ID of the project to be deleted
     */
    @Override
    public void eliminar(Long id) {
        Transaction tx = null;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Proyecto proyecto = session.get(Proyecto.class, id);
            if (proyecto != null) {
                session.delete(proyecto);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();  // Rollback transaction on error
            e.printStackTrace();
        }
    }
}
