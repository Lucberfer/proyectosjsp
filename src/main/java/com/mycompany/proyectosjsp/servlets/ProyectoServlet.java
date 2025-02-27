package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.models.Proyecto;
import com.mycompany.proyectosjsp.services.ProyectoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling project-related HTTP requests.
 * It interacts with ProyectoService to manage project operations.
 * 
 * URL mapping: /proyectos
 */
@WebServlet(name = "ProyectoServlet", urlPatterns = {"/proyectos"})
public class ProyectoServlet extends HttpServlet {

    private ProyectoService proyectoService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the project service
        proyectoService = new ProyectoService();
    }

    /**
     * Handles GET requests: Lists all projects, optionally filtered by status.
     * If a status parameter is provided, only projects with that status are returned.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String estado = request.getParameter("estado");
        List<Proyecto> proyectos;

        if (estado != null && !estado.isEmpty()) {
            // Filter projects by the given status
            proyectos = proyectoService.listarProyectosPorEstado(estado);
        } else {
            // Retrieve all projects
            proyectos = proyectoService.listarProyectos();
        }

        // Set the projects list as a request attribute for the JSP to use
        request.setAttribute("proyectos", proyectos);

        // Forward the request to the projects JSP page using an absolute path
        request.getRequestDispatcher("/views/proyectos.jsp").forward(request, response);
    }

    /**
     * Handles POST requests: Creates a new project.
     * Reads form parameters, creates a Proyecto object, and calls the service to persist it.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String nombreProyecto = request.getParameter("nombre_proyecto");
        String descripcion = request.getParameter("descripcion");
        String fechaInicio = request.getParameter("fecha_inicio");
        String fechaFin = request.getParameter("fecha_fin");
        String estado = request.getParameter("estado");

        // Create the Proyecto object using form values
        Proyecto proyecto = new Proyecto(
                nombreProyecto,
                descripcion,
                java.sql.Date.valueOf(fechaInicio),
                (fechaFin != null && !fechaFin.isEmpty()) ? java.sql.Date.valueOf(fechaFin) : null,
                estado
        );

        // Attempt to add the project using the service
        boolean success = proyectoService.agregarProyecto(proyecto);

        if (success) {
            // If successful, redirect to the projects page with a success message
            response.sendRedirect("proyectos?exito=Project created successfully.");
        } else {
            // If not, forward back to the add project form with an error message
            request.setAttribute("error", "Failed to create the project.");
            request.getRequestDispatcher("/views/agregarProyecto.jsp").forward(request, response);
        }
    }
}
