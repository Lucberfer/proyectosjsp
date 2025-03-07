package com.mycompany.proyectosjsp.dao.impl;

import com.mycompany.proyectosjsp.config.HibernateUtil;
import com.mycompany.proyectosjsp.dao.interfaces.ProyectoDAO;
import com.mycompany.proyectosjsp.model.Proyecto;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProyectoDAOImpl implements ProyectoDAO {

    @Override
    public void create(Proyecto proyecto) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(proyecto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Proyecto proyecto) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(proyecto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
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
            Proyecto proyecto = em.find(Proyecto.class, id);
            if (proyecto != null) {
                em.remove(proyecto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public Proyecto findById(int id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.find(Proyecto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Proyecto> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            TypedQuery<Proyecto> query = em.createQuery("SELECT p FROM Proyecto p", Proyecto.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
