<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Administrador</title>
    <!-- Add your CSS files or styles here -->
</head>
<body>
    <h1>Bienvenido Administrador</h1>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/ProyectoServlet?action=list">Gestionar Proyectos</a></li>
            <li><a href="${pageContext.request.contextPath}/TareaServlet?action=list&projectId=0">Gestionar Tareas</a></li>
            <li><a href="${pageContext.request.contextPath}/UsuarioServlet?action=list">Gestionar usuarios</a></li>
            <li><a href="${pageContext.request.contextPath}/LogoutServlet">Salir</a></li>
        </ul>
    </nav>
</body>
</html>
