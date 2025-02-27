package com.mycompany.proyectosjsp.services;

import com.mycompany.proyectosjsp.dao.UsuarioDAO;
import com.mycompany.proyectosjsp.models.Usuario;
import java.util.List;

/**
 * Service class for handling business logic related to users.
 * It interacts with UsuarioDAO for database operations.
 * Ensures that an admin user exists upon initialization.
 * 
 * @author Lucas
 */
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
        crearAdminSiNoExiste();
    }

    /**
     * Ensures that an admin user exists in the system.
     * If not found, it creates one with default credentials.
     */
    private void crearAdminSiNoExiste() {
        Usuario admin = usuarioDAO.obtenerPorUsername("admin");
        if (admin == null) {
            Usuario usuarioAdmin = new Usuario("admin", "Administrador", "admin", "admin");
            usuarioDAO.guardar(usuarioAdmin);
            System.out.println("✅ Usuario administrador creado: admin / admin");
        }
    }

    /**
     * Registers a new user.
     *
     * @param usuario The user object with plain-text password
     * @return true if registration is successful, false otherwise
     */
    public boolean registrarUsuario(Usuario usuario) {
        // Check if username already exists
        if (usuarioDAO.obtenerPorUsername(usuario.getUsername()) != null) {
            return false; // Username already in use
        }

        // Ensure only one admin exists
        if ("admin".equals(usuario.getRol())) {
            return false; // No one can register as an admin manually
        }

        // Save user with default role "usuario"
        usuario.setRol("usuario");
        usuarioDAO.guardar(usuario);
        return true;
    }

    /**
     * Validates a user login (compares plain-text password).
     *
     * @param username The user's username
     * @param password The plain-text password
     * @return The authenticated user object, or null if login fails
     */
    public Usuario validarUsuario(String username, String password) {
        Usuario usuario = usuarioDAO.obtenerPorUsername(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario; // Password matches
        }
        return null; // Invalid login
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The user's ID
     * @return The found Usuario entity or null if not found
     */
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioDAO.obtenerPorId(id);
    }

    /**
     * Retrieves all users.
     *
     * @return A list of Usuario entities
     */
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.obtenerTodos();
    }

    /**
     * Updates an existing user's details.
     *
     * @param usuario The updated user object
     */
    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.actualizar(usuario);
    }

    /**
     * Deletes a user by its ID.
     * Ensures that the admin user cannot be deleted.
     *
     * @param id The ID of the user to be deleted
     * @return true if deleted, false if user is admin
     */
    public boolean eliminarUsuario(Long id) {
        Usuario usuario = usuarioDAO.obtenerPorId(id);

        if (usuario != null && "admin".equals(usuario.getRol())) {
            return false; // Prevent deletion of the admin account
        }

        usuarioDAO.eliminar(id);
        return true;
    }
}
