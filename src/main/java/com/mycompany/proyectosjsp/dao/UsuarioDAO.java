package com.mycompany.proyectosjsp.dao;

import com.mycompany.proyectosjsp.config.HibernateCfg;
import com.mycompany.proyectosjsp.models.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * DAO class for managing Usuario entities. Implements CRUD operations using
 * Hibernate.
 *
 * @author Lucas
 */
public class UsuarioDAO {

    /**
     * Retrieves a user by its ID.
     *
     * @param id The user's ID
     * @return The found Usuario entity or null if not found
     */
    public Usuario obtenerPorId(Long id) {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.get(Usuario.class, id);
        }
    }

    /**
     * Retrieves a user by username.
     *
     * @param username The user's username
     * @return The found Usuario entity or null if not found
     */
    public Usuario obtenerPorUsername(String username) {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Usuario WHERE username = :username", Usuario.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }

    /**
     * Retrieves all users.
     *
     * @return A list of Usuario entities
     */
    public List<Usuario> obtenerTodos() {
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Usuario", Usuario.class).list();
        }
    }

    /**
     * Saves a new user to the database.
     *
     * @param usuario The Usuario entity to be saved
     */
    public void guardar(Usuario usuario) {
        Transaction tx;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(usuario);
            tx.commit();
        }
    }

    /**
     * Updates an existing user in the database.
     *
     * @param usuario The Usuario entity to be updated
     */
    public void actualizar(Usuario usuario) {
        Transaction tx;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(usuario);
            tx.commit();
        }
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id The ID of the user to be deleted
     */
    public void eliminar(Long id) {
        Transaction tx;
        try (Session session = HibernateCfg.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Usuario usuario = session.get(Usuario.class, id);
            if (usuario != null) {
                session.delete(usuario);
            }
            tx.commit();
        }
    }

    /**
     * Ensures that an admin user exists in the system. If not found, it creates
     * one with default credentials.
     */
    public void crearAdminSiNoExiste() {
        Usuario admin = obtenerPorUsername("admin");
        if (admin == null) {
            // Crear usuario administrador con las credenciales predeterminadas
            Usuario usuarioAdmin = new Usuario("admin", "admin", "admin");
            guardar(usuarioAdmin);
            System.out.println("✅ Usuario administrador creado: admin / admin");
        }
    }
}
