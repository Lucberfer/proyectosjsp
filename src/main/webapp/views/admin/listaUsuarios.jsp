<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Usuarios</title>
    <!-- Add your CSS or styles here -->
</head>
<body>
    <h1>Listado de Usuarios</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Usuario</th>
                <th>Rol</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td>${usuario.id}</td>
                    <td>${usuario.username}</td>
                    <td>${usuario.rol}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/UsuarioServlet?action=edit&id=${usuario.id}">Editar</a>
                        <a href="${pageContext.request.contextPath}/UsuarioServlet?action=delete&id=${usuario.id}" onclick="return confirm('¿Está seguro de eliminar este usuario?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/views/admin/admin.jsp">Volver al Panel de Administración</a>
</body>
</html>
