package com.mycompany.proyectosjsp.dao.impl;

import com.mycompany.proyectosjsp.config.HibernateUtil;
import com.mycompany.proyectosjsp.dao.interfaces.TareaDAO;
import com.mycompany.proyectosjsp.model.Tarea;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TareaDAOImpl implements TareaDAO {

    @Override
    public void create(Tarea tarea) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tarea);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Tarea tarea) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(tarea);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Tarea tarea = em.find(Tarea.class, id);
            if (tarea != null) {
                em.remove(tarea);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public Tarea findById(int id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.find(Tarea.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tarea> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            TypedQuery<Tarea> query = em.createQuery("SELECT t FROM Tarea t", Tarea.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tarea> findByProjectId(int projectId) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            TypedQuery<Tarea> query = em.createQuery("SELECT t FROM Tarea t WHERE t.proyecto.id = :projectId", Tarea.class);
            query.setParameter("projectId", projectId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
