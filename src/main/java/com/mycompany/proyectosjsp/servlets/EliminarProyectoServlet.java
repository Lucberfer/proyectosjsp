package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.services.ProyectoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling project deletion requests.
 * Only admin users should have access to this functionality.
 * 
 * @author Lucas
 */
@WebServlet(name = "EliminarProyectoServlet", urlPatterns = {"/eliminarProyecto"})
public class EliminarProyectoServlet extends BaseServlet {

    private ProyectoService proyectoService;

    @Override
    public void init() throws ServletException {
        super.init();
        proyectoService = new ProyectoService();
    }

    /**
     * Handles GET requests to delete a project.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check if user is admin
        String userRole = (String) session.getAttribute("rol");
        if (userRole == null || !userRole.equals("admin")) {
            redirectWithMessage(request, response, "proyectos", null, "No tienes permisos para eliminar proyectos.");
            return;
        }

        Long idProyecto = getLongParameter(request, "id");
        if (idProyecto == null) {
            redirectWithMessage(request, response, "proyectos", null, "ID de proyecto inválido.");
            return;
        }

        try {
            boolean success = proyectoService.eliminarProyecto(idProyecto);

            if (success) {
                redirectWithMessage(request, response, "proyectos", "Proyecto eliminado exitosamente.", null);
            } else {
                redirectWithMessage(request, response, "proyectos", null, "Error al eliminar el proyecto.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectWithMessage(request, response, "proyectos", null, "Error inesperado al eliminar el proyecto.");
        }
    }
}
