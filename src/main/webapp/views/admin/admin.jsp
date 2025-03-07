<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración</title>
    <!-- Agrega tus estilos CSS o links a hojas de estilo aquí -->
</head>
<body>
    <h1>Bienvenido, Administrador</h1>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/ProyectoServlet?action=list">Gestionar Proyectos</a></li>
            <li><a href="${pageContext.request.contextPath}/TareaServlet?action=list&projectId=0">Gestionar Tareas</a></li>
            <li><a href="${pageContext.request.contextPath}/views/admin/listaUsuarios.jsp">Gestionar Usuarios</a></li>
            <li><a href="${pageContext.request.contextPath}/LogoutServlet">Cerrar Sesión</a></li>
        </ul>
    </nav>
</body>
</html>
