<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    // Retrieve the error message from the session
    String errorMessage = (String) session.getAttribute("error");

    // If there is no error message, redirect to the home page
    if (errorMessage == null) {
        response.sendRedirect("inicio.jsp");
        return;
    }

    // Remove the error message from the session after displaying it
    session.removeAttribute("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
</head>
<body>
    <h2>Error</h2>
    <p style="color: red;"><%= errorMessage %></p>

    <a href="inicio.jsp">Return to Home</a>
</body>
</html>
