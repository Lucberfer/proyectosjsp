<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>

<%
    // Retrieve success or error messages from the session
    String successMessage = (String) session.getAttribute("exito");
    String errorMessage = (String) session.getAttribute("error");

    // Remove messages from the session after displaying them
    session.removeAttribute("exito");
    session.removeAttribute("error");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Add Task</title>
</head>
<body>
    <h2>Task Addition Form</h2>

    <!-- Display success or error messages -->
    <% if (successMessage != null) { %>
        <p style="color: green;"><%= successMessage %></p>
    <% } %>

    <% if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %>

    <form action="tareas" method="post">
        <input type="hidden" name="id_proyecto" value="<%= request.getParameter("id_proyecto") %>">

        <label for="descripcion_tarea">Task Description:</label>
        <textarea name="descripcion_tarea" id="descripcion_tarea" required></textarea>
        <br><br>

        <label for="responsable">Responsible:</label>
        <input type="text" name="responsable" id="responsable" required>
        <br><br>

        <label for="fecha_inicio">Start Date:</label>
        <input type="date" name="fecha_inicio" id="fecha_inicio" required>
        <br><br>

        <label for="fecha_fin">End Date:</label>
        <input type="date" name="fecha_fin" id="fecha_fin">
        <br><br>

        <label for="estado">Status:</label>
        <select name="estado" id="estado" required>
            <option value="pendiente">Pending</option>
            <option value="en progreso">In Progress</option>
            <option value="completado">Completed</option>
        </select>
        <br><br>

        <button type="submit">Add Task</button>
        <a href="tareas.jsp?id_proyecto=<%= request.getParameter("id_proyecto") %>">Cancel</a>
    </form>
</body>
</html>
