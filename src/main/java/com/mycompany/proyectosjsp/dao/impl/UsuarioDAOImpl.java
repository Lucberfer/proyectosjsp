package com.mycompany.proyectosjsp.dao.impl;

import com.mycompany.proyectosjsp.config.HibernateUtil;
import com.mycompany.proyectosjsp.dao.interfaces.UsuarioDAO;
import com.mycompany.proyectosjsp.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void create(Usuario usuario) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Usuario usuario) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } catch(Exception ex) {
            if(em.getTransaction().isActive()){
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
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
        } catch(Exception ex) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario findById(int id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    @Override
public Usuario findByCredentials(String username, String password) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
        TypedQuery<Usuario> query = em.createQuery(
            "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password", 
            Usuario.class
        );
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Usuario> usuarios = query.getResultList();
        return usuarios.isEmpty() ? null : usuarios.get(0);
    } finally {
        em.close();
    }
}


    @Override
    public List<Usuario> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
