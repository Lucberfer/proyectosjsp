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
 * Servlet para manejar la creación de proyectos.
 * Interactúa con ProyectoService para agregar un nuevo proyecto.
 * 
 * URL de acceso: /agregarProyecto
 */
@WebServlet(name = "AgregarProyectoServlet", urlPatterns = {"/agregarProyecto"})
public class AgregarProyectoServlet extends HttpServlet {

    private ProyectoService proyectoService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Inicializa el servicio de proyectos
        proyectoService = new ProyectoService();
    }

    /**
     * Muestra el formulario para agregar un nuevo proyecto.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a la vista agregarProyecto.jsp
        request.getRequestDispatcher("views/agregarProyecto.jsp").forward(request, response);
    }

    /**
     * Procesa el envío del formulario para crear un nuevo proyecto.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Permite manejar caracteres especiales (UTF-8) en los formularios
        request.setCharacterEncoding("UTF-8");

        // Obtiene los campos enviados por el formulario
        String nombreProyecto = request.getParameter("nombre_proyecto");
        String descripcion = request.getParameter("descripcion");
        String fechaInicio = request.getParameter("fecha_inicio");
        String fechaFin = request.getParameter("fecha_fin");
        String estado = request.getParameter("estado");

        // Validación de campos obligatorios
        if (nombreProyecto == null || nombreProyecto.trim().isEmpty()
                || descripcion == null || descripcion.trim().isEmpty()
                || estado == null || estado.trim().isEmpty()) {

            // Si faltan campos requeridos, devuelve un mensaje de error y recarga la vista
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("views/agregarProyecto.jsp").forward(request, response);
            return;
        }

        try {
            // Convierte las fechas a tipo Date de SQL
            Date fechaInicioSql = Date.valueOf(fechaInicio);
            Date fechaFinSql = (fechaFin != null && !fechaFin.isEmpty()) ? Date.valueOf(fechaFin) : null;

            // Crea el objeto Proyecto
            Proyecto proyecto = new Proyecto(nombreProyecto, descripcion, fechaInicioSql, fechaFinSql, estado);

            // Llama al servicio para agregar el proyecto
            boolean success = proyectoService.agregarProyecto(proyecto);

            if (success) {
                // Si se creó con éxito, redirige al servlet de proyectos con un mensaje de éxito
                response.sendRedirect("proyectos?exito=Project created successfully.");
            } else {
                // Si hubo un problema al crear el proyecto, muestra un mensaje de error
                request.setAttribute("error", "Failed to create the project.");
                request.getRequestDispatcher("views/agregarProyecto.jsp").forward(request, response);
            }

        } catch (IllegalArgumentException e) {
            // Si las fechas tienen un formato inválido, muestra un mensaje de error
            request.setAttribute("error", "Invalid date format.");
            request.getRequestDispatcher("views/agregarProyecto.jsp").forward(request, response);
        }
    }
}
