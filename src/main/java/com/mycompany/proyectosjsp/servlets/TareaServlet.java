package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.model.Tarea;
import com.mycompany.proyectosjsp.model.Proyecto;
import com.mycompany.proyectosjsp.service.TareaService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TareaServlet extends HttpServlet {

    private TareaService tareaService = new TareaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listTasks(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteTask(request, response);
                break;
            default:
                listTasks(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createTask(request, response);
                break;
            case "update":
                updateTask(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        List<Tarea> tareas = tareaService.findTareasByProjectId(projectId);
        request.setAttribute("tareas", tareas);
        request.setAttribute("projectId", projectId);
        request.getRequestDispatcher("/views/tareas/listaTareas.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tarea tarea = tareaService.findTareaById(id);
        request.setAttribute("tarea", tarea);
        request.getRequestDispatcher("/views/tareas/editarTarea.jsp").forward(request, response);
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        tareaService.deleteTarea(id);
        response.sendRedirect(request.getContextPath() + "/TareaServlet?action=list&projectId=" + projectId);
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Tarea tarea = new Tarea();
        tarea.setDescripcion(request.getParameter("descripcion"));
        tarea.setResponsable(request.getParameter("responsable"));

        try {
            // Usamos el formato "yyyy-MM-dd"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = sdf.parse(request.getParameter("fechaInicio"));
            Date fechaFin = sdf.parse(request.getParameter("fechaFin"));
            tarea.setFechaInicio(fechaInicio);
            tarea.setFechaFin(fechaFin);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Asignamos el estado seleccionado
        tarea.setEstado(request.getParameter("estado"));

        // Asociar la tarea al proyecto correspondiente
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        Proyecto proyecto = new Proyecto();
        proyecto.setId(projectId);
        tarea.setProyecto(proyecto);

        tareaService.createTarea(tarea);
        response.sendRedirect(request.getContextPath() + "/TareaServlet?action=list&projectId=" + projectId);
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tarea tarea = tareaService.findTareaById(id);
        if (tarea != null) {
            tarea.setDescripcion(request.getParameter("descripcion"));
            tarea.setResponsable(request.getParameter("responsable"));
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = sdf.parse(request.getParameter("fechaInicio"));
                Date fechaFin = sdf.parse(request.getParameter("fechaFin"));
                tarea.setFechaInicio(fechaInicio);
                tarea.setFechaFin(fechaFin);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tarea.setEstado(request.getParameter("estado"));
            tareaService.updateTarea(tarea);
        }
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        response.sendRedirect(request.getContextPath() + "/TareaServlet?action=list&projectId=" + projectId);
    }

}
