<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Tareas</title>
    <!-- Puedes agregar tus hojas de estilo CSS aquí -->
</head>
<body>
    <h1>Lista de Tareas</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Descripción</th>
                <th>Responsable</th>
                <th>Fecha Inicio</th>
                <th>Fecha Fin</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="tarea" items="${tareas}">
                <tr>
                    <td>${tarea.id}</td>
                    <td>${tarea.descripcion}</td>
                    <td>${tarea.responsable}</td>
                    <td><fmt:formatDate value="${tarea.fechaInicio}" pattern="dd/MM/yyyy" /></td>
                    <td><fmt:formatDate value="${tarea.fechaFin}" pattern="dd/MM/yyyy" /></td>
                    <td>${tarea.estado}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/TareaServlet?action=edit&id=${tarea.id}&projectId=${projectId}">Editar</a>
                        <a href="${pageContext.request.contextPath}/TareaServlet?action=delete&id=${tarea.id}&projectId=${projectId}"
                           onclick="return confirm('¿Está seguro de eliminar esta tarea?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/views/tareas/agregarTarea.jsp?projectId=${projectId}">Agregar Tarea</a>
    <br>
    <a href="${pageContext.request.contextPath}/ProyectoServlet?action=list">Volver a Proyectos</a>
</body>
</html>
