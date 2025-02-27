package com.mycompany.proyectosjsp.services;

import com.mycompany.proyectosjsp.dao.ProyectoDAO;
import com.mycompany.proyectosjsp.models.Proyecto;

import java.util.List;

/**
 * Service class for handling business logic related to projects.
 * It interacts with ProyectoDAO for database operations.
 * 
 * @author Lucas
 */
public class ProyectoService {

    private final ProyectoDAO proyectoDAO;

    public ProyectoService() {
        this.proyectoDAO = new ProyectoDAO();
    }

    /**
     * Registers a new project.
     *
     * @param proyecto The project object
     * @return true if the project is successfully registered, false otherwise
     */
    public boolean agregarProyecto(Proyecto proyecto) {
        if (proyecto.getNombreProyecto() == null || proyecto.getNombreProyecto().trim().isEmpty()) {
            return false; // Invalid project name
        }
        proyectoDAO.guardar(proyecto);
        return true;
    }

    /**
     * Retrieves a project by ID.
     *
     * @param id The project's ID
     * @return The found Proyecto entity or null if not found
     */
    public Proyecto obtenerProyectoPorId(Long id) {
        return proyectoDAO.obtenerPorId(id);
    }

    /**
     * Retrieves all projects.
     *
     * @return A list of Proyecto entities
     */
    public List<Proyecto> listarProyectos() {
        return proyectoDAO.obtenerTodos();
    }

    /**
     * Retrieves projects filtered by status.
     *
     * @param estado The project status ("en curso" or "completado")
     * @return A list of Proyecto entities filtered by status
     */
    public List<Proyecto> listarProyectosPorEstado(String estado) {
        return proyectoDAO.obtenerPorEstado(estado);
    }

    /**
     * Updates an existing project.
     *
     * @param proyecto The updated project object
     */
    public void actualizarProyecto(Proyecto proyecto) {
        proyectoDAO.actualizar(proyecto);
    }

    /**
     * Deletes a project by its ID.
     *
     * @param id The ID of the project to be deleted
     * @return true if deletion was successful, false otherwise
     */
    public boolean eliminarProyecto(Long id) {
        Proyecto proyecto = proyectoDAO.obtenerPorId(id);
        if (proyecto != null) {
            proyectoDAO.eliminar(id);
            return true;
        }
        return false; // Project not found
    }
}
