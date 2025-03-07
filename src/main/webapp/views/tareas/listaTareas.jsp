<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Tareas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/Style.css"></head>
<body>
    <h1>Lista de Tareas</h1>
    <!-- Table to display tasks -->
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Descripción</th>
                <th>Responsable</th>
                <th>Fecha de Inicio</th>
                <th>Fecha de Finalización</th>
                <th>Estado</th>
                <th>Proyecto</th>
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
                    <td>${tarea.proyecto.nombre}</td>
                    <td>
                        <button href="${pageContext.request.contextPath}/TareaServlet?action=edit&id=${tarea.id}&projectId=${tarea.proyecto.id}">Editar</button>
                        |
                        <button href="${pageContext.request.contextPath}/TareaServlet?action=delete&id=${tarea.id}&projectId=${tarea.proyecto.id}" onclick="return confirm('Estas seguro de que quieres eliminar la Tarea?');">Eliminar</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br/>
    <!-- Link to add a new task -->
    <button href="${pageContext.request.contextPath}/TareaServlet?action=new">Añádir una nueva Tarea</button>
    <br/><br/>
    <!-- Link to return to the project list (or admin panel) -->
    <a href="${pageContext.request.contextPath}/ProyectoServlet?action=list">Vuelta a la Lista de Proyectos</a>
</body>
</html>
