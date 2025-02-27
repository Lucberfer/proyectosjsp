package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.services.TareaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling task deletion requests.
 * Only admin users should have access to this functionality.
 * 
 * URL mapping: /eliminarTarea
 */
@WebServlet(name = "EliminarTareaServlet", urlPatterns = {"/eliminarTarea"})
public class EliminarTareaServlet extends HttpServlet {

    private TareaService tareaService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the task service
        tareaService = new TareaService();
    }

    /**
     * Handles POST requests to delete a task.
     * Only admin users are allowed to perform this action.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the current session
        HttpSession session = request.getSession();

        // Check if the user is logged in and has admin privileges
        String userRole = (String) session.getAttribute("rol");
        if (userRole == null || !userRole.equals("admin")) {
            session.setAttribute("error", "You do not have permission to delete tasks.");
            response.sendRedirect("proyectos.jsp");
            return;
        }

        // Parse the task ID and project ID from the request parameters
        Long idTarea = Long.parseLong(request.getParameter("id"));
        Long idProyecto = Long.parseLong(request.getParameter("id_proyecto"));

        // Attempt to delete the task using the task service
        boolean tareaEliminada = tareaService.eliminarTarea(idTarea);

        if (tareaEliminada) {
            session.setAttribute("exito", "Task deleted successfully.");
        } else {
            session.setAttribute("error", "Failed to delete the task.");
        }

        // Redirect back to the tasks page for the corresponding project
        response.sendRedirect("tareas?id_proyecto=" + idProyecto);
    }
}
