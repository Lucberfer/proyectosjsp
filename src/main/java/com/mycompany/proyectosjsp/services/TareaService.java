package com.mycompany.proyectosjsp.services;

import com.mycompany.proyectosjsp.dao.TareaDAO;
import com.mycompany.proyectosjsp.models.Tarea;

import java.util.List;

/**
 * Service class for handling business logic related to tasks. It interacts with
 * TareaDAO for database operations.
 *
 * @author Lucas
 */
public class TareaService {

    private final TareaDAO tareaDAO;

    public TareaService() {
        this.tareaDAO = new TareaDAO();
    }

    /**
     * Registers a new task.
     *
     * @param tarea The task object
     * @return true if the task is successfully registered, false otherwise
     */
    public boolean agregarTarea(Tarea tarea) {
        if (tarea.getDescripcionTarea() == null || tarea.getDescripcionTarea().trim().isEmpty()) {
            return false; // Task description is required
        }
        tareaDAO.guardar(tarea);
        return true;
    }

    /**
     * Retrieves a task by ID.
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
        return tareaDAO.obtenerTodas();
    }

    /**
     * Retrieves all tasks associated with a given project ID.
     *
     * @param idProyecto The project ID
     * @return A list of tasks belonging to the project
     */
    public List<Tarea> obtenerTareasPorProyecto(Long idProyecto) {
        return tareaDAO.obtenerTareasPorProyecto(idProyecto);
    }

    /**
     * Retrieves tasks filtered by project.
     *
     * @param idProyecto The project ID
     * @return A list of Tarea entities filtered by project
     */
    public List<Tarea> listarTareasPorProyecto(Long idProyecto) {
        return tareaDAO.obtenerPorProyecto(idProyecto);
    }

    /**
     * Updates an existing task.
     *
     * @param tarea The updated task object
     */
    public void actualizarTarea(Tarea tarea) {
        tareaDAO.actualizar(tarea);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to be deleted
     * @return true if deletion was successful, false otherwise
     */
    public boolean eliminarTarea(Long id) {
        Tarea tarea = tareaDAO.obtenerPorId(id);
        if (tarea != null) {
            tareaDAO.eliminar(id);
            return true;
        }
        return false; // Task not found
    }
}
