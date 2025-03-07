package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.model.Proyecto;
import com.mycompany.proyectosjsp.service.ProyectoService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProyectoServlet extends HttpServlet {

    private ProyectoService proyectoService = new ProyectoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Determine the action to perform
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listProjects(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteProject(request, response);
                break;
            default:
                listProjects(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Determine the action to perform
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProject(request, response);
                break;
            case "update":
                updateProject(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    private void listProjects(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve all projects and forward to the listing JSP
        List<Proyecto> proyectos = proyectoService.findAllProyectos();
        request.setAttribute("proyectos", proyectos);
        request.getRequestDispatcher("/views/proyectos/listaProyectos.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the project to edit and forward to the edit form JSP
        int id = Integer.parseInt(request.getParameter("id"));
        Proyecto proyecto = proyectoService.findProyectoById(id);
        request.setAttribute("proyecto", proyecto);
        request.getRequestDispatcher("/views/proyectos/editarProyecto.jsp").forward(request, response);
    }

    private void deleteProject(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Delete the specified project and redirect to the project list
        int id = Integer.parseInt(request.getParameter("id"));
        proyectoService.deleteProyecto(id);
        response.sendRedirect(request.getContextPath() + "/ProyectoServlet?action=list");
    }

    private void createProject(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(request.getParameter("nombre"));
        proyecto.setDescripcion(request.getParameter("descripcion"));

        try {
            // Utilizamos el formato "yyyy-MM-dd" que es el que devuelve el input type="date"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = sdf.parse(request.getParameter("fechaInicio"));
            Date fechaFin = sdf.parse(request.getParameter("fechaFin"));
            proyecto.setFechaInicio(fechaInicio);
            proyecto.setFechaFin(fechaFin);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Asignamos el estado desde el desplegable
        proyecto.setEstado(request.getParameter("estado"));

        // Aquí podrías asignar el usuario creador, si lo obtienes de la sesión
        proyectoService.createProyecto(proyecto);
        response.sendRedirect(request.getContextPath() + "/ProyectoServlet?action=list");
    }

    private void updateProject(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Proyecto proyecto = proyectoService.findProyectoById(id);
        if (proyecto != null) {
            proyecto.setNombre(request.getParameter("nombre"));
            proyecto.setDescripcion(request.getParameter("descripcion"));
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = sdf.parse(request.getParameter("fechaInicio"));
                Date fechaFin = sdf.parse(request.getParameter("fechaFin"));
                proyecto.setFechaInicio(fechaInicio);
                proyecto.setFechaFin(fechaFin);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            proyecto.setEstado(request.getParameter("estado"));
            proyectoService.updateProyecto(proyecto);
        }
        response.sendRedirect(request.getContextPath() + "/ProyectoServlet?action=list");
    }

}
