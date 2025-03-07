package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.model.Proyecto;
import com.mycompany.proyectosjsp.model.Tarea;
import com.mycompany.proyectosjsp.service.ProyectoService;
import com.mycompany.proyectosjsp.service.TareaService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TareaServlet extends HttpServlet {

    private TareaService tareaService = new TareaService();
    private ProyectoService proyectoService = new ProyectoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Determine the action parameter to decide the operation
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listTasks(request, response);
                break;
            case "new":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteTask(request, response);
                break;
            default:
                listTasks(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Determine the action parameter for POST requests
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createTask(request, response);
                break;
            case "update":
                updateTask(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    /**
     * Lists tasks for a given project.
     */
    private void listTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectIdStr = request.getParameter("projectId");
        int projectId = 0;
        if (projectIdStr != null && !projectIdStr.isEmpty()) {
            projectId = Integer.parseInt(projectIdStr);
        }
        List<Tarea> tareas = tareaService.findTareasByProjectId(projectId);
        request.setAttribute("tareas", tareas);
        request.setAttribute("projectId", projectId);
        request.getRequestDispatcher("/views/tareas/listaTareas.jsp").forward(request, response);
    }

    /**
     * Shows the add task form with a dropdown list of all available projects.
     */
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Proyecto> proyectos = proyectoService.findAllProyectos();
        request.setAttribute("proyectos", proyectos);
        request.getRequestDispatcher("/views/tareas/agregarTarea.jsp").forward(request, response);
    }

    /**
     * Shows the edit task form with a dropdown list of all available projects.
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tarea tarea = tareaService.findTareaById(id);
        request.setAttribute("tarea", tarea);
        List<Proyecto> proyectos = proyectoService.findAllProyectos();
        request.setAttribute("proyectos", proyectos);
        request.getRequestDispatcher("/views/tareas/editarTarea.jsp").forward(request, response);
    }

    /**
     * Deletes the specified task and redirects to the task list.
     */
    private void deleteTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        tareaService.deleteTarea(id);
        response.sendRedirect(request.getContextPath() + "/TareaServlet?action=list&projectId=" + projectId);
    }

    /**
     * Creates a new task from the form data and associates it with the selected project.
     */
    private void createTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Tarea tarea = new Tarea();
        tarea.setDescripcion(request.getParameter("descripcion"));
        tarea.setResponsable(request.getParameter("responsable"));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = sdf.parse(request.getParameter("fechaInicio"));
            Date fechaFin = sdf.parse(request.getParameter("fechaFin"));
            tarea.setFechaInicio(fechaInicio);
            tarea.setFechaFin(fechaFin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tarea.setEstado(request.getParameter("estado"));
        
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        Proyecto proyecto = proyectoService.findProyectoById(projectId);
        if (proyecto == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project not found");
            return;
        }
        tarea.setProyecto(proyecto);
        tareaService.createTarea(tarea);
        response.sendRedirect(request.getContextPath() + "/TareaServlet?action=list&projectId=" + projectId);
    }

    /**
     * Updates an existing task with new data from the form.
     */
    private void updateTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tarea tarea = tareaService.findTareaById(id);
        if (tarea != null) {
            tarea.setDescripcion(request.getParameter("descripcion"));
            tarea.setResponsable(request.getParameter("responsable"));
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = sdf.parse(request.getParameter("fechaInicio"));
                Date fechaFin = sdf.parse(request.getParameter("fechaFin"));
                tarea.setFechaInicio(fechaInicio);
                tarea.setFechaFin(fechaFin);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tarea.setEstado(request.getParameter("estado"));
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            Proyecto proyecto = proyectoService.findProyectoById(projectId);
            if (proyecto == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project not found");
                return;
            }
            tarea.setProyecto(proyecto);
            tareaService.updateTarea(tarea);
            response.sendRedirect(request.getContextPath() + "/TareaServlet?action=list&projectId=" + projectId);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found");
        }
    }
}
