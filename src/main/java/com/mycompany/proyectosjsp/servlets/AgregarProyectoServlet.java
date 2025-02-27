package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.models.Proyecto;
import com.mycompany.proyectosjsp.services.ProyectoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Servlet for handling project creation requests.
 * Interacts with ProyectoService to add a new project.
 * 
 * @author Lucas
 */
@WebServlet(name = "AgregarProyectoServlet", urlPatterns = {"/agregarProyecto"})
public class AgregarProyectoServlet extends HttpServlet {

    private ProyectoService proyectoService;

    @Override
    public void init() throws ServletException {
        super.init();
        proyectoService = new ProyectoService(); // Initialize service
    }

    /**
     * Displays the form to add a new project.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/agregarProyecto.jsp").forward(request, response);
    }

    /**
     * Handles the project creation process.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Handle special characters

        String nombreProyecto = request.getParameter("nombre_proyecto");
        String descripcion = request.getParameter("descripcion");
        String fechaInicio = request.getParameter("fecha_inicio");
        String fechaFin = request.getParameter("fecha_fin");
        String estado = request.getParameter("estado");

        // Validate required fields
        if (nombreProyecto == null || nombreProyecto.trim().isEmpty()
                || descripcion == null || descripcion.trim().isEmpty()
                || estado == null || estado.trim().isEmpty()) {

            request.setAttribute("error", "Todos los campos son necesarios.");
            request.getRequestDispatcher("views/agregarProyecto.jsp").forward(request, response);
            return;
        }

        try {
            Date fechaInicioSql = Date.valueOf(fechaInicio);
            Date fechaFinSql = (fechaFin != null && !fechaFin.isEmpty()) ? Date.valueOf(fechaFin) : null;

            Proyecto proyecto = new Proyecto(nombreProyecto, descripcion, fechaInicioSql, fechaFinSql, estado);
            boolean success = proyectoService.agregarProyecto(proyecto);

            if (success) {
                response.sendRedirect("proyectos?exito=Proyecto creado exitosamente.");
            } else {
                request.setAttribute("error", "Fallo al crear el proyecto.");
                request.getRequestDispatcher("views/agregarProyecto.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Formato inválido de fechas.");
            request.getRequestDispatcher("views/agregarProyecto.jsp").forward(request, response);
        }
    }
}
