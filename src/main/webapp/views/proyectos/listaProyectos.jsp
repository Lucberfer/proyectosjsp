<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Proyectos</title>
    <!-- Puedes agregar tus hojas de estilo CSS aquí -->
</head>
<body>
    <h1>Lista de Proyectos</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Fecha Inicio</th>
                <th>Fecha Fin</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="proyecto" items="${proyectos}">
                <tr>
                    <td>${proyecto.id}</td>
                    <td>${proyecto.nombre}</td>
                    <td>${proyecto.descripcion}</td>
                    <td><fmt:formatDate value="${proyecto.fechaInicio}" pattern="dd/MM/yyyy" /></td>
                    <td><fmt:formatDate value="${proyecto.fechaFin}" pattern="dd/MM/yyyy" /></td>
                    <td>${proyecto.estado}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/ProyectoServlet?action=edit&id=${proyecto.id}">Editar</a>
                        <a href="${pageContext.request.contextPath}/ProyectoServlet?action=delete&id=${proyecto.id}"
                           onclick="return confirm('¿Está seguro de eliminar este proyecto?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/views/proyectos/agregarProyecto.jsp">Agregar Proyecto</a>
    <br>
    <a href="${pageContext.request.contextPath}/views/admin/admin.jsp">Volver al Panel</a>
</body>
</html>
