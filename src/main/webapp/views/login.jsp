<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Iniciar Sesión</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/Style.css">
    </head>
    <body>
        <h1>Iniciar Sesión</h1>
        <c:if test="${not empty error}">
            <div style="color: red;">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <label for="username">Usuario:</label>
            <input type="text" id="username" name="username" required>
            <br><br>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>
            <br><br>
            <input type="submit" value="Ingresar">
        </form>

        <!-- Botón o enlace para registrarse -->
        <p>
            ¿No tienes cuenta? <a href="${pageContext.request.contextPath}/RegistroServlet">Regístrate aquí</a>
        </p>

    </body>
</html>
