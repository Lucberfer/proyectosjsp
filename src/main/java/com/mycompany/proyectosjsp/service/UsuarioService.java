package com.mycompany.proyectosjsp.service;

import com.mycompany.proyectosjsp.dao.interfaces.UsuarioDAO;
import com.mycompany.proyectosjsp.dao.impl.UsuarioDAOImpl;
import com.mycompany.proyectosjsp.model.Usuario;
import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        // Initialize the UsuarioDAO implementation
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    // Create a new user
    public void createUsuario(Usuario usuario) {
        usuarioDAO.create(usuario);
    }

    // Update an existing user
    public void updateUsuario(Usuario usuario) {
        usuarioDAO.update(usuario);
    }

    // Delete a user by its id
    public void deleteUsuario(int id) {
        usuarioDAO.delete(id);
    }

    // Retrieve a user by its id
    public Usuario findUsuarioById(int id) {
        return usuarioDAO.findById(id);
    }

    // Retrieve a user by credentials (username and password)
    public Usuario findUsuarioByCredentials(String username, String password) {
        return usuarioDAO.findByCredentials(username, password);
    }

    // Retrieve all users
    public List<Usuario> findAllUsuarios() {
        return usuarioDAO.findAll();
    }
}
