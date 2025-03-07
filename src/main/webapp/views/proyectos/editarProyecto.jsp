<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Proyecto</title>
    <!-- Agrega tus hojas de estilo CSS aquí si lo deseas -->
</head>
<body>
    <h1>Editar Proyecto</h1>
    <form action="${pageContext.request.contextPath}/ProyectoServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${proyecto.id}">
        
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="${proyecto.nombre}" required>
        <br><br>
        
        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" required>${proyecto.descripcion}</textarea>
        <br><br>
        
        <label for="fechaInicio">Fecha de Inicio (dd/MM/yyyy):</label>
        <input type="text" id="fechaInicio" name="fechaInicio" value="<fmt:formatDate value='${proyecto.fechaInicio}' pattern='dd/MM/yyyy'/>" required>
        <br><br>
        
        <label for="fechaFin">Fecha de Fin (dd/MM/yyyy):</label>
        <input type="text" id="fechaFin" name="fechaFin" value="<fmt:formatDate value='${proyecto.fechaFin}' pattern='dd/MM/yyyy'/>" required>
        <br><br>
        
        <label for="estado">Estado:</label>
        <input type="text" id="estado" name="estado" value="${proyecto.estado}" required>
        <br><br>
        
        <input type="submit" value="Actualizar Proyecto">
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/ProyectoServlet?action=list">Volver a la lista de proyectos</a>
</body>
</html>
