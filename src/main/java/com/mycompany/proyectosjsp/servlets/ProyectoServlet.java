package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.models.Proyecto;
import com.mycompany.proyectosjsp.services.ProyectoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling project-related HTTP requests.
 * Interacts with ProyectoService to manage project operations.
 * 
 * @author Lucas
 */
@WebServlet(name = "ProyectoServlet", urlPatterns = {"/proyectos"})
public class ProyectoServlet extends BaseServlet {

    private ProyectoService proyectoService;

    @Override
    public void init() throws ServletException {
        super.init();
        proyectoService = new ProyectoService();
    }

    /**
     * Handles GET requests: Lists all projects.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Proyecto> proyectos = proyectoService.listarProyectos();
        request.setAttribute("proyectos", proyectos);
        request.getRequestDispatcher("views/proyectos.jsp").forward(request, response);
    }

    /**
     * Handles POST requests: Create a new project.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreProyecto = request.getParameter("nombre_proyecto");
        String descripcion = request.getParameter("descripcion");
        String fechaInicio = request.getParameter("fecha_inicio");
        String fechaFin = request.getParameter("fecha_fin");
        String estado = request.getParameter("estado");

        try {
            Proyecto proyecto = new Proyecto(
                    nombreProyecto, 
                    descripcion, 
                    java.sql.Date.valueOf(fechaInicio), 
                    java.sql.Date.valueOf(fechaFin), 
                    estado
            );

            boolean success = proyectoService.agregarProyecto(proyecto);

            if (success) {
                response.sendRedirect("proyectos?exito=Proyecto creado exitosamente.");
            } else {
                request.setAttribute("error", "Error al crear el proyecto.");
                request.getRequestDispatcher("views/agregarProyecto.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Formato inválido de la información.");
            request.getRequestDispatcher("views/agregarProyecto.jsp").forward(request, response);
        }
    }
}
