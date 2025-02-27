<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.proyectosjsp.models.Proyecto" %>

<%
    // Retrieve the list of projects from the request
    List<Proyecto> proyectos = (List<Proyecto>) request.getAttribute("proyectos");

    // Retrieve success and error messages from the session
    String successMessage = (String) session.getAttribute("exito");
    String errorMessage = (String) session.getAttribute("error");

    // Remove messages after retrieving them
    session.removeAttribute("exito");
    session.removeAttribute("error");

    // Retrieve user role to determine access permissions
    String rol = (String) session.getAttribute("rol");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Proyectos</title>
</head>
<body>
    <h2>Lista de Proyectos</h2>

    <!-- Display success message -->
    <% if (successMessage != null) { %>
        <p style="color: green;"><%= successMessage %></p>
    <% } %>

    <!-- Display error message -->
    <% if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %>

    <!-- Filter projects by status -->
    <form action="proyectos" method="get">
        <label for="estado">Filtrar por estado:</label>
        <select name="estado" id="estado">
            <option value="">Todos</option>
            <option value="en curso">En curso</option>
            <option value="completado">Completado</option>
        </select>
        <button type="submit">Filtrar</button>
    </form>

    <!-- If there are projects, display them -->
    <% if (proyectos != null && !proyectos.isEmpty()) { %>
        <table border="1">
            <tr>
                <th>Nombre del Proyecto</th>
                <th>Descripción</th>
                <th>Fecha de Inicio</th>
                <th>Fecha de Fin</th>
                <th>Estado</th>
                <% if ("admin".equals(rol)) { %>
                    <th>Acciones</th>
                <% } %>
            </tr>
            <% for (Proyecto proyecto : proyectos) { %>
                <tr>
                    <td><%= proyecto.getNombreProyecto() %></td>
                    <td><%= proyecto.getDescripcion() %></td>
                    <td><%= proyecto.getFechaInicio() %></td>
                    <td><%= proyecto.getFechaFin() != null ? proyecto.getFechaFin() : "N/A" %></td>
                    <td><%= proyecto.getEstado() %></td>
                    <% if ("admin".equals(rol)) { %>
                        <td>
                            <form action="EliminarProyectoServlet" method="post">
                                <input type="hidden" name="id" value="<%= proyecto.getId() %>">
                                <button type="submit">Eliminar</button>
                            </form>
                        </td>
                    <% } %>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No hay proyectos disponibles.</p>
    <% } %>

    <%-- Admins can add new projects --%>
    <% if ("admin".equals(rol)) { %>
        <a href="agregarProyecto.jsp">Agregar Nuevo Proyecto</a>
    <% } %>

    <br><br>
    <a href="inicio.jsp">Volver al Inicio</a>
</body>
</html>
