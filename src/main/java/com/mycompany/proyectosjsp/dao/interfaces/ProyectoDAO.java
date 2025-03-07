package com.mycompany.proyectosjsp.dao.interfaces;

import com.mycompany.proyectosjsp.model.Proyecto;
import java.util.List;

public interface ProyectoDAO {

    // Create a new project
    void create(Proyecto proyecto);

    // Update an existing project
    void update(Proyecto proyecto);

    // Delete a project by its identifier
    void delete(int id);

    // Retrieve a project by its identifier
    Proyecto findById(int id);

    // Retrieve all projects
    List<Proyecto> findAll();
}
