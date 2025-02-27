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
 * @author Lucas
 */
@WebServlet(name = "ProyectoServlet", urlPatterns = {"/proyectos"})
public class ProyectoServlet extends HttpServlet {

    private ProyectoService proyectoService;

    @Override
    public void init() throws ServletException {
        super.init();
        proyectoService = new ProyectoService();
    }

    /**
     * Handles GET request: List all projects.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String estadoFiltro = request.getParameter("estado");

        List<Proyecto> proyectos;
        if (estadoFiltro != null && !estadoFiltro.trim().isEmpty()) {
            proyectos = proyectoService.listarProyectosPorEstado(estadoFiltro);
        } else {
            proyectos = proyectoService.listarProyectos();
        }

        request.setAttribute("proyectos", proyectos);
        request.getRequestDispatcher("views/proyectos.jsp").forward(request, response);
    }
}
