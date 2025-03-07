<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
</head>
<body>
    <h1>Registro de Usuario</h1>
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/RegistroServlet" method="post">
        <label for="username">Usuario:</label>
        <input type="text" id="username" name="username" required>
        <br><br>
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required>
        <br><br>
        <input type="submit" value="Registrarse">
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/views/login.jsp">Volver a Login</a>
</body>
</html>
