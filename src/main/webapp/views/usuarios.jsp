<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.proyectosjsp.models.Usuario" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession sessionObj = request.getSession(false);
    String userRole = (sessionObj != null) ? (String) sessionObj.getAttribute("rol") : null;

    // If the user is not an admin, redirect to the home page with an error message
    if (userRole == null || !userRole.equals("admin")) {
        session.setAttribute("error", "You do not have permission to access this section.");
        response.sendRedirect("inicio.jsp");
        return;
    }

    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
</head>
<body>
    <h2>User Management</h2>

    <%-- Display success or error messages --%>
    <% if (session.getAttribute("exito") != null) { %>
        <p style="color: green;"><%= session.getAttribute("exito") %></p>
        <% session.removeAttribute("exito"); %>
    <% } %>

    <% if (session.getAttribute("error") != null) { %>
        <p style="color: red;"><%= session.getAttribute("error") %></p>
        <% session.removeAttribute("error"); %>
    <% } %>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        <% for (Usuario usuario : usuarios) { %>
            <tr>
                <td><%= usuario.getId() %></td>
                <td><%= usuario.getUsername() %></td>
                <td><%= usuario.getRol() %></td>
                <td>
                    <%-- Admins can delete users, but not the admin itself --%>
                    <% if (!usuario.getUsername().equals("admin")) { %>
                        <form action="eliminarUsuario" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= usuario.getId() %>">
                            <button type="submit">Delete</button>
                        </form>
                    <% } else { %>
                        <span>Cannot modify</span>
                    <% } %>
                </td>
            </tr>
        <% } %>
    </table>

    <br>
    <a href="inicio.jsp">Back</a>
</body>
</html>
