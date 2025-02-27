package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.models.Tarea;
import com.mycompany.proyectosjsp.models.Proyecto;
import com.mycompany.proyectosjsp.services.TareaService;
import com.mycompany.proyectosjsp.services.ProyectoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Servlet for handling task-related HTTP requests.
 * It interacts with TareaService to manage tasks for a project.
 *
 * URL mapping: /tareas
 */
@WebServlet(name = "TareaServlet", urlPatterns = {"/tareas"})
public class TareaServlet extends HttpServlet {

    private TareaService tareaService;
    private ProyectoService proyectoService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the task service and project service
        tareaService = new TareaService();
        proyectoService = new ProyectoService();
    }

    /**
     * Handles GET requests: Lists tasks for a specific project.
     * Expects a parameter "id_proyecto" representing the project ID.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Safely convert the project ID from the request parameter
        Long idProyecto = Long.parseLong(request.getParameter("id_proyecto"));

        // Retrieve the project and its associated tasks using the service
        Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
        List<Tarea> tareas = tareaService.obtenerTareasPorProyecto(idProyecto);

        // Set the project and tasks as request attributes for the JSP
        request.setAttribute("proyecto", proyecto);
        request.setAttribute("tareas", tareas);

        // Forward the request to the tasks JSP page using an absolute path
        request.getRequestDispatcher("/views/tareas.jsp").forward(request, response);
    }

    /**
     * Handles POST requests: Creates a new task for a project.
     * Expects form parameters for task details including the project ID.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set character encoding to handle special characters
        request.setCharacterEncoding("UTF-8");

        // Retrieve form parameters
        String idProyectoParam = request.getParameter("id_proyecto");
        String descripcionTarea = request.getParameter("descripcion_tarea");
        String responsable = request.getParameter("responsable");
        String fechaInicio = request.getParameter("fecha_inicio");
        String fechaFin = request.getParameter("fecha_fin");
        String estado = request.getParameter("estado");

        HttpSession session = request.getSession();

        try {
            // Validate required fields
            if (idProyectoParam == null || idProyectoParam.trim().isEmpty()
                    || descripcionTarea == null || descripcionTarea.trim().isEmpty()
                    || responsable == null || responsable.trim().isEmpty()
                    || estado == null || estado.trim().isEmpty()) {

                session.setAttribute("error", "All fields are required.");
                response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
                return;
            }

            // Convert the project ID safely
            Long idProyecto = Long.parseLong(idProyectoParam);
            Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);

            if (proyecto == null) {
                session.setAttribute("error", "Project not found.");
                response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
                return;
            }

            // Convert dates safely
            Date fechaInicioSql = Date.valueOf(fechaInicio);
            Date fechaFinSql = (fechaFin != null && !fechaFin.trim().isEmpty()) ? Date.valueOf(fechaFin) : null;

            // Create the task object using the retrieved values
            Tarea tarea = new Tarea(proyecto, descripcionTarea, responsable, fechaInicioSql, fechaFinSql, estado);
            boolean success = tareaService.agregarTarea(tarea);

            if (success) {
                // If successful, set a success message and redirect to the tasks page
                session.setAttribute("exito", "Task created successfully.");
                response.sendRedirect("tareas?id_proyecto=" + idProyecto);
            } else {
                // If task creation fails, set an error message and redirect back to the add task form
                session.setAttribute("error", "Failed to create the task.");
                response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
            }
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid project ID.");
            response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
        } catch (IllegalArgumentException e) {
            session.setAttribute("error", "Invalid date format.");
            response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Unexpected error while creating the task.");
            response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
        }
    }
}
