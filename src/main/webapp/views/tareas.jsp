<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.proyectosjsp.models.Tarea" %>
<%@ page import="com.mycompany.proyectosjsp.models.Proyecto" %>

<%
    // Retrieve the project and task list from the request
    Proyecto proyecto = (Proyecto) request.getAttribute("proyecto");
    List<Tarea> tareas = (List<Tarea>) request.getAttribute("tareas");

    // Retrieve success and error messages from the session
    String successMessage = (String) session.getAttribute("exito");
    String errorMessage = (String) session.getAttribute("error");

    // Remove messages after retrieving them
    session.removeAttribute("exito");
    session.removeAttribute("error");

    // Get user role for permissions
    String rol = (String) session.getAttribute("rol");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Tareas del Proyecto: <%= proyecto.getNombreProyecto() %></title>
</head>
<body>
    <h2>Tareas para el Proyecto: <%= proyecto.getNombreProyecto() %></h2>

    <!-- Display success message -->
    <% if (successMessage != null) { %>
        <p style="color: green;"><%= successMessage %></p>
    <% } %>

    <!-- Display error message -->
    <% if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %>

    <!-- If there are tasks, display them -->
    <% if (tareas != null && !tareas.isEmpty()) { %>
        <table border="1">
            <tr>
                <th>Descripción</th>
                <th>Responsable</th>
                <th>Fecha de Inicio</th>
                <th>Fecha de Fin</th>
                <th>Estado</th>
                <% if ("admin".equals(rol)) { %>
                    <th>Acciones</th>
                <% } %>
            </tr>
            <% for (Tarea tarea : tareas) { %>
                <tr>
                    <td><%= tarea.getDescripcionTarea() %></td>
                    <td><%= tarea.getResponsable() %></td>
                    <td><%= tarea.getFechaInicio() %></td>
                    <td><%= tarea.getFechaFin() != null ? tarea.getFechaFin() : "N/A" %></td>
                    <td><%= tarea.getEstado() %></td>
                    <% if ("admin".equals(rol)) { %>
                        <td>
                            <form action="EliminarTareaServlet" method="post">
                                <input type="hidden" name="id" value="<%= tarea.getId() %>">
                                <button type="submit">Eliminar</button>
                            </form>
                        </td>
                    <% } %>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No hay tareas disponibles para este proyecto.</p>
    <% } %>

    <%-- Admins can add tasks --%>
    <% if ("admin".equals(rol)) { %>
        <a href="agregarTarea.jsp?id_proyecto=<%= proyecto.getId() %>">Agregar Nueva Tarea</a>
    <% } %>

    <br><br>
    <a href="proyectos.jsp">Volver a Proyectos</a>
</body>
</html>
