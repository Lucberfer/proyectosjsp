<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>

<%
    // Check if the user is already logged in and redirect them to home
    if (session.getAttribute("usuario") != null) {  // Changed from "username" to "usuario"
        response.sendRedirect("inicio.jsp");
        return;
    }

    // Retrieve success and error messages from the session
    String successMessage = (String) session.getAttribute("exito");
    String errorMessage = (String) session.getAttribute("error");

    // Remove messages from the session after displaying them
    session.removeAttribute("exito");
    session.removeAttribute("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>

    <!-- Display success message -->
    <% if (successMessage != null) { %>
        <p style="color: green;"><%= successMessage %></p>
    <% } %>

    <!-- Display error message -->
    <% if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %>

    <!-- Change action to "login" to match the servlet mapping -->
    <form action="login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <button type="submit">Login</button>
    </form>

    <br>
    <p>Don't have an account? <a href="registrar.jsp">Register here</a></p>
</body>
</html>
