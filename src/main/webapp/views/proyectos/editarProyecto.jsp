<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar Proyecto</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/Style.css">
    </head>
    <body>
        <h1>Editar Proyecto</h1>
        <!-- Form for editing an existing project -->
        <form action="${pageContext.request.contextPath}/ProyectoServlet" method="post">
            <!-- Hidden fields to specify the update action and project id -->
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${proyecto.id}">

            <!-- Project name input -->
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="${proyecto.nombre}" required>
            <br/><br/>

            <!-- Project description input -->
            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" required>${proyecto.descripcion}</textarea>
            <br/><br/>

            <!-- Start date input with a date picker -->
            <label for="fechaInicio">Fecha de inicio:</label>
            <input type="date" id="fechaInicio" name="fechaInicio" 
                   value="<fmt:formatDate value='${proyecto.fechaInicio}' pattern='yyyy-MM-dd'/>" required>
            <br/><br/>

            <!-- End date input with a date picker -->
            <label for="fechaFin">Fecha fin:</label>
            <input type="date" id="fechaFin" name="fechaFin" 
                   value="<fmt:formatDate value='${proyecto.fechaFin}' pattern='yyyy-MM-dd'/>" required>
            <br/><br/>

            <!-- Status dropdown -->
            <label for="estado">Estado:</label>
            <select id="estado" name="estado" required>
                <option value="sin iniciar" <c:if test="${proyecto.estado eq 'sin iniciar'}">selected</c:if>>Sin iniciar</option>
                <option value="en proceso" <c:if test="${proyecto.estado eq 'en proceso'}">selected</c:if>>En proceso</option>
                <option value="finalizado" <c:if test="${proyecto.estado eq 'finalizado'}">selected</c:if>>Completado</option>
            </select>
            <br/><br/>

            <!-- Submit button -->
            <input type="submit" value="Actualizar proyecto">
        </form>
        <br/>
        <!-- Link to return to the project list -->
        <a href="${pageContext.request.contextPath}/ProyectoServlet?action=list">Volver a Lista de Proyectos</a>
    </body>
</html>
