<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/Style.css">
</head>
<body>
    <h1>Lista de usuarios</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td>${usuario.id}</td>
                    <td>${usuario.username}</td>
                    <td>${usuario.rol}</td>
                    <td>
                        <!-- Edit and Delete links for each user -->
                        <a href="${pageContext.request.contextPath}/UsuarioServlet?action=edit&id=${usuario.id}">Editar</a>
                        |
                        <a href="${pageContext.request.contextPath}/UsuarioServlet?action=delete&id=${usuario.id}" onclick="return confirm('Estás seguro de que quieres eliminar este usuario?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br/>
    <a href="${pageContext.request.contextPath}/views/admin/admin.jsp">Vuelta al menú Administrador</a>
</body>
</html>
