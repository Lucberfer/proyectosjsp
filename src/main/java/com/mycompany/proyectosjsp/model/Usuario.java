package com.mycompany.proyectosjsp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String rol; // e.g., "admin", "invitado"

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proyecto> proyectos;

    // Getters and Setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
}
