<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%
    // Check if the user is already logged in; if so, redirect to home page
    if (session.getAttribute("usuario") != null) {
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

    <form action="login" method="post">
        <label for="username">USUARIO:</label>
        <input type="text" id="username" name="username" required><br><br>
        
        <!-- Two buttons: one for regular user login and one for admin login -->
        <button type="submit" name="action" value="login_user">USUARIO</button>
        <button type="submit" name="action" value="login_admin">ADMIN</button>
    </form>
</body>
</html>
