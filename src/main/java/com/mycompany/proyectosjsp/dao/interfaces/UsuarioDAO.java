package com.mycompany.proyectosjsp.dao.interfaces;

import com.mycompany.proyectosjsp.model.Usuario;
import java.util.List;

public interface UsuarioDAO {

    // Create a new user
    void create(Usuario usuario);

    // Update an existing user
    void update(Usuario usuario);

    // Delete a user by its identifier
    void delete(int id);

    // Retrieve a user by its identifier
    Usuario findById(int id);

    // Retrieve a user by credentials (username and password)
    Usuario findByCredentials(String username, String password);

    // Retrieve all users
    List<Usuario> findAll();
}
