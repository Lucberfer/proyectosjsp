package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.models.Proyecto;
import com.mycompany.proyectosjsp.models.Tarea;
import com.mycompany.proyectosjsp.services.ProyectoService;
import com.mycompany.proyectosjsp.services.TareaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

/**
 * Servlet for handling task creation requests.
 * Interacts with TareaService to add a new task.
 * 
 * @author Lucas
 */
@WebServlet(name = "AgregarTareaServlet", urlPatterns = {"/agregarTarea"})
public class AgregarTareaServlet extends HttpServlet {

    private TareaService tareaService;
    private ProyectoService proyectoService;

    @Override
    public void init() throws ServletException {
        super.init();
        tareaService = new TareaService();
        proyectoService = new ProyectoService();
    }

    /**
     * Displays the form to add a new task.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProyecto = request.getParameter("id_proyecto");

        if (idProyecto == null || idProyecto.trim().isEmpty()) {
            response.sendRedirect("proyectos");
            return;
        }

        request.setAttribute("idProyecto", idProyecto);
        request.getRequestDispatcher("views/agregarTarea.jsp").forward(request, response);
    }

    /**
     * Handles the task creation process.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Handle special characters

        String idProyectoParam = request.getParameter("id_proyecto");
        String descripcionTarea = request.getParameter("descripcion_tarea");
        String responsable = request.getParameter("responsable");
        String fechaInicio = request.getParameter("fecha_inicio");
        String fechaFin = request.getParameter("fecha_fin");
        String estado = request.getParameter("estado");

        HttpSession session = request.getSession();

        try {
            // Validate required fields
            if (idProyectoParam == null || idProyectoParam.trim().isEmpty()
                    || descripcionTarea == null || descripcionTarea.trim().isEmpty()
                    || responsable == null || responsable.trim().isEmpty()
                    || estado == null || estado.trim().isEmpty()) {

                session.setAttribute("error", "Todos los campos son necesarios.");
                response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
                return;
            }

            // Convert project ID safely
            Long idProyecto = Long.parseLong(idProyectoParam);
            Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);

            if (proyecto == null) {
                session.setAttribute("error", "Proyecto no encontrado.");
                response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
                return;
            }

            // Convert dates safely
            Date fechaInicioSql = Date.valueOf(fechaInicio);
            Date fechaFinSql = (fechaFin != null && !fechaFin.trim().isEmpty()) ? Date.valueOf(fechaFin) : null;

            // Create task object
            Tarea tarea = new Tarea(proyecto, descripcionTarea, responsable, fechaInicioSql, fechaFinSql, estado);
            boolean success = tareaService.agregarTarea(tarea);

            if (success) {
                session.setAttribute("exito", "Tarea creada exitosamente.");
                response.sendRedirect("tareas?id_proyecto=" + idProyecto);
            } else {
                session.setAttribute("error", "Fallo al crear la tarea.");
                response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
            }
        } catch (NumberFormatException e) {
            session.setAttribute("error", "ID de proyecto inválido.");
            response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
        } catch (IllegalArgumentException e) {
            session.setAttribute("error", "Formato inválido de fechas.");
            response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Error inesperado al crear la tarea.");
            response.sendRedirect("agregarTarea?id_proyecto=" + idProyectoParam);
        }
    }
}
