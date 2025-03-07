package com.mycompany.proyectosjsp.dao.interfaces;

import com.mycompany.proyectosjsp.model.Tarea;
import java.util.List;

public interface TareaDAO {

    // Create a new task
    void create(Tarea tarea);

    // Update an existing task
    void update(Tarea tarea);

    // Delete a task by its identifier
    void delete(int id);

    // Retrieve a task by its identifier
    Tarea findById(int id);

    // Retrieve all tasks
    List<Tarea> findAll();

    // Retrieve tasks by project id
    List<Tarea> findByProjectId(int projectId);
}
