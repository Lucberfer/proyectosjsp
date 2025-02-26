package com.mycompany.proyectosjsp.services;

import com.mycompany.proyectosjsp.dao.TareaDAO;
import com.mycompany.proyectosjsp.models.Tarea;
import java.util.List;

/**
 * Service class for managing task-related business logic.
 * This class interacts with TareaDAO to handle database operations.
 * 
 * @author Lucas
 */
public class TareaService {

    // DAO instance for handling database operations
    private final TareaDAO tareaDAO;

    /**
     * Default constructor initializing the DAO
     */
    public TareaService() {
        this.tareaDAO = new TareaDAO();
    }

    /**
     * Retrieves a task by its ID.
     * 
     * @param id The task's ID
     * @return The found Tarea entity or null if not found
     */
    public Tarea obtenerTareaPorId(Long id) {
        return tareaDAO.obtenerPorId(id);
    }

    /**
     * Retrieves all tasks.
     * 
     * @return A list of Tarea entities
     */
    public List<Tarea> listarTareas() {
        return tareaDAO.obtenerTodos();
    }

    /**
     * Retrieves all tasks for a specific project.
     * 
     * @param idProyecto The ID of the project
     * @return A list of Tarea entities linked to the project
     */
    public List<Tarea> listarTareasPorProyecto(Long idProyecto) {
        return tareaDAO.obtenerPorProyecto(idProyecto);
    }

    /**
     * Adds a new task.
     * 
     * @param tarea The task entity to be saved
     * @return true if saved successfully, false otherwise
     */
    public boolean agregarTarea(Tarea tarea) {
        try {
            tareaDAO.guardar(tarea);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing task.
     * 
     * @param tarea The task entity to be updated
     * @return true if updated successfully, false otherwise
     */
    public boolean actualizarTarea(Tarea tarea) {
        try {
            tareaDAO.actualizar(tarea);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a task by its ID.
     * 
     * @param id The ID of the task to be deleted
     * @return true if deleted successfully, false otherwise
     */
    public boolean eliminarTarea(Long id) {
        try {
            tareaDAO.eliminar(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
