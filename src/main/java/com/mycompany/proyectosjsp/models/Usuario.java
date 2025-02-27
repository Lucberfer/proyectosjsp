package com.mycompany.proyectosjsp.models;

import jakarta.persistence.*;

/**
 * Entity class representing a user.
 * This class is mapped to the "usuarios" table in the database.
 *
 * @author Lucas
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    // Primary key with auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User's username (must be unique)
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    // User's password (plain text)
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    // User role (admin or regular user)
    @Column(name = "rol", nullable = false, length = 10)
    private String rol;

    // Default constructor
    public Usuario() {
    }

    // Constructor with parameters (ONLY username, password, and role)
    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Override toString()
    @Override
    public String toString() {
        return "Usuario{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", rol='" + rol + '\''
                + '}';
    }
}
