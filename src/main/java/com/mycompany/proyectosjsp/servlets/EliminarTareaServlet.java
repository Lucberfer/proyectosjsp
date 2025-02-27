package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.services.TareaService;
import com.mycompany.proyectosjsp.models.Tarea;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling task deletion requests.
 * Only admin users should have access to this functionality.
 * 
 * @author Lucas
 */
@WebServlet(name = "EliminarTareaServlet", urlPatterns = {"/eliminarTarea"})
public class EliminarTareaServlet extends BaseServlet {

    private TareaService tareaService;

    @Override
    public void init() throws ServletException {
        super.init();
        tareaService = new TareaService();
    }

    /**
     * Handles POST requests to delete a task.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check if user is admin
        String userRole = (String) session.getAttribute("rol");
        if (userRole == null || !userRole.equals("admin")) {
            redirectWithMessage(request, response, "proyectos", null, "No tienes permisos para eliminar tareas.");
            return;
        }

        Long idTarea = getLongParameter(request, "id");
        if (idTarea == null) {
            redirectWithMessage(request, response, "proyectos", null, "ID de tarea inválido.");
            return;
        }

        // Check if the task exists before attempting deletion
        Tarea tarea = tareaService.obtenerTareaPorId(idTarea);
        if (tarea == null) {
            redirectWithMessage(request, response, "proyectos", null, "La tarea no existe.");
            return;
        }

        try {
            boolean success = tareaService.eliminarTarea(idTarea);
            if (success) {
                redirectWithMessage(request, response, "proyectos", "Tarea eliminada exitosamente.", null);
            } else {
                redirectWithMessage(request, response, "proyectos", null, "Error al eliminar la tarea.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectWithMessage(request, response, "proyectos", null, "Error inesperado al eliminar la tarea.");
        }
    }
}
