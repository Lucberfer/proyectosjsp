package com.mycompany.proyectosjsp.service;

import com.mycompany.proyectosjsp.dao.interfaces.ProyectoDAO;
import com.mycompany.proyectosjsp.dao.impl.ProyectoDAOImpl;
import com.mycompany.proyectosjsp.model.Proyecto;
import java.util.List;

public class ProyectoService {

    private ProyectoDAO proyectoDAO;

    public ProyectoService() {
        // Initialize the ProyectoDAO implementation
        this.proyectoDAO = new ProyectoDAOImpl();
    }

    // Create a new project
    public void createProyecto(Proyecto proyecto) {
        proyectoDAO.create(proyecto);
    }

    // Update an existing project
    public void updateProyecto(Proyecto proyecto) {
        proyectoDAO.update(proyecto);
    }

    // Delete a project by its id
    public void deleteProyecto(int id) {
        proyectoDAO.delete(id);
    }

    // Retrieve a project by its id
    public Proyecto findProyectoById(int id) {
        return proyectoDAO.findById(id);
    }

    // Retrieve all projects
    public List<Proyecto> findAllProyectos() {
        return proyectoDAO.findAll();
    }
}
