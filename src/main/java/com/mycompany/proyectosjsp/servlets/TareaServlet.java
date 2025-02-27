package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.models.Tarea;
import com.mycompany.proyectosjsp.models.Proyecto;
import com.mycompany.proyectosjsp.services.TareaService;
import com.mycompany.proyectosjsp.services.ProyectoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling task-related HTTP requests.
 * Interacts with TareaService to manage task operations.
 * 
 * @author Lucas
 */
@WebServlet(name = "TareaServlet", urlPatterns = {"/tareas"})
public class TareaServlet extends BaseServlet {

    private TareaService tareaService;
    private ProyectoService proyectoService;

    @Override
    public void init() throws ServletException {
        super.init();
        tareaService = new TareaService();
        proyectoService = new ProyectoService();
    }

    /**
     * Handles GET requests: Lists all tasks for a specific project.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long idProyecto = getLongParameter(request, "id_proyecto");

        if (idProyecto == null) {
            redirectWithMessage(request, response, "proyectos", null, "ID de proyecto inválido.");
            return;
        }

        Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
        if (proyecto == null) {
            redirectWithMessage(request, response, "proyectos", null, "Proyecto no encontrado.");
            return;
        }

        List<Tarea> tareas = tareaService.obtenerTareasPorProyecto(idProyecto);
        request.setAttribute("proyecto", proyecto);
        request.setAttribute("tareas", tareas);
        request.getRequestDispatcher("views/tareas.jsp").forward(request, response);
    }
}
