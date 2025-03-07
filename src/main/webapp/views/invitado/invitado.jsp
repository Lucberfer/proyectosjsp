<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Invitado</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/Style.css">
</head>
<body>
    <h1>Bienvenido</h1>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/ProyectoServlet?action=list">Ver Proyectos</a></li>
            <li><a href="${pageContext.request.contextPath}/TareaServlet?action=list&projectId=0">Ver Tareas</a></li>
            <li><a href="${pageContext.request.contextPath}/LogoutServlet">Desconectar</a></li>
        </ul>
    </nav>
</body>
</html>
