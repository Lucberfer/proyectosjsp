package com.mycompany.proyectosjsp.service;

import com.mycompany.proyectosjsp.dao.interfaces.TareaDAO;
import com.mycompany.proyectosjsp.dao.impl.TareaDAOImpl;
import com.mycompany.proyectosjsp.model.Tarea;
import java.util.List;

public class TareaService {

    private TareaDAO tareaDAO;

    public TareaService() {
        // Initialize the TareaDAO implementation
        this.tareaDAO = new TareaDAOImpl();
    }

    // Create a new task
    public void createTarea(Tarea tarea) {
        tareaDAO.create(tarea);
    }

    // Update an existing task
    public void updateTarea(Tarea tarea) {
        tareaDAO.update(tarea);
    }

    // Delete a task by its id
    public void deleteTarea(int id) {
        tareaDAO.delete(id);
    }

    // Retrieve a task by its id
    public Tarea findTareaById(int id) {
        return tareaDAO.findById(id);
    }

    // Retrieve all tasks
    public List<Tarea> findAllTareas() {
        return tareaDAO.findAll();
    }

    // Retrieve tasks by project id
    public List<Tarea> findTareasByProjectId(int projectId) {
        return tareaDAO.findByProjectId(projectId);
    }
}
