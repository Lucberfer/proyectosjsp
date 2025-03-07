<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/Style.css">
</head>
<body>
    <h1>Editar Usuario</h1>
    <form action="${pageContext.request.contextPath}/UsuarioServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${usuario.id}">
        
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${usuario.username}" required>
        <br/><br/>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${usuario.password}" required>
        <br/><br/>
        
        <label for="rol">Role:</label>
        <select id="rol" name="rol" required>
            <option value="admin" <c:if test="${usuario.rol == 'admin'}">selected</c:if>>Admin</option>
            <option value="invitado" <c:if test="${usuario.rol == 'invitado'}">selected</c:if>>Invitado</option>
        </select>
        <br/><br/>
        
        <input type="submit" value="Actualizar Usuario">
    </form>
    <br/>
    <a href="${pageContext.request.contextPath}/UsuarioServlet?action=list">Volver a la Lista de Usuarios</a>
</body>
</html>
