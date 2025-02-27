<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    // Retrieve user session details
    String username = (String) session.getAttribute("usuario");
    String rol = (String) session.getAttribute("rol");

    // Ensure user is logged in
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Retrieve success and error messages from the session
    String successMessage = (String) session.getAttribute("exito");
    String errorMessage = (String) session.getAttribute("error");

    // Remove messages after retrieving them
    session.removeAttribute("exito");
    session.removeAttribute("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
    <h2>Welcome, <%= username %>!</h2>

    <!-- Display success message -->
    <% if (successMessage != null) { %>
        <p style="color: green;"><%= successMessage %></p>
    <% } %>

    <!-- Display error message -->
    <% if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %>

    <h3>Navigation</h3>
    <ul>
        <li><a href="proyectos.jsp">View Projects</a></li>

        <%-- If the user is an admin, show management options --%>
        <% if ("admin".equals(rol)) { %>
            <li><a href="usuarios.jsp">Manage Users</a></li>
        <% } %>

        <li><a href="logout.jsp">Log Out</a></li>
    </ul>
</body>
</html>
