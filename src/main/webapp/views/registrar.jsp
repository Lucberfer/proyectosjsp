<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    // If the user is already logged in, redirect to home
    if (session.getAttribute("username") != null) {
        response.sendRedirect("inicio.jsp");
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
    <title>Register</title>
</head>
<body>
    <h2>Create an Account</h2>

    <!-- Display success message -->
    <% if (successMessage != null) { %>
        <p style="color: green;"><%= successMessage %></p>
    <% } %>

    <!-- Display error message -->
    <% if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %>

    <form action="${pageContext.request.contextPath}/registro" method="post"> 
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <button type="submit">Register</button>
    </form>

    <br>
    <p>Already have an account? <a href="login.jsp">Login here</a></p>
</body>
</html>
