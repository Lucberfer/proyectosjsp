package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.services.ProyectoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling project deletion requests.
 * Only admin users should have access to this functionality.
 * 
 * URL mapping: /eliminarProyecto
 */
@WebServlet(name = "EliminarProyectoServlet", urlPatterns = {"/eliminarProyecto"})
public class EliminarProyectoServlet extends HttpServlet {

    private ProyectoService proyectoService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the project service
        proyectoService = new ProyectoService();
    }

    /**
     * Handles GET requests to delete a project.
     * Only admin users are allowed to perform this action.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the current session
        HttpSession session = request.getSession();

        // Check if the user is logged in and has admin privileges
        String userRole = (String) session.getAttribute("rol");
        if (userRole == null || !userRole.equals("admin")) {
            session.setAttribute("error", "You do not have permission to delete projects.");
            response.sendRedirect("proyectos.jsp");
            return;
        }

        // Parse the project ID from the request parameter
        Long idProyecto = Long.parseLong(request.getParameter("id"));

        // Attempt to delete the project using the service
        boolean proyectoEliminado = proyectoService.eliminarProyecto(idProyecto);

        if (proyectoEliminado) {
            session.setAttribute("exito", "Project deleted successfully.");
        } else {
            session.setAttribute("error", "Failed to delete the project.");
        }

        // Redirect back to the projects list page
        response.sendRedirect("proyectos.jsp");
    }
}
