<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
  This is the user registration page.
  Users can register by providing a username and password.
-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/Style.css">
</head>
<body>
    <h1>User Registration</h1>
    <!-- Display error message if exists -->
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/RegistroServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required />
        <br/><br/>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required />
        <br/><br/>
        <input type="submit" value="Register" />
    </form>
    <br/>
    <a href="${pageContext.request.contextPath}/views/login.jsp">Vuelta al Inicio</a>
</body>
</html>
