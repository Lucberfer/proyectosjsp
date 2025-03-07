<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Tarea</title>
</head>
<body>
    <h1>Editar Tarea</h1>
    <form action="${pageContext.request.contextPath}/TareaServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${tarea.id}">
        <!-- Se asume que el projectId se pasa por parámetro -->
        <input type="hidden" name="projectId" value="${param.projectId}">
        
        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" name="descripcion" value="${tarea.descripcion}" required>
        <br><br>
        
        <label for="responsable">Responsable:</label>
        <input type="text" id="responsable" name="responsable" value="${tarea.responsable}" required>
        <br><br>
        
        <label for="fechaInicio">Fecha de Inicio:</label>
        <input type="date" id="fechaInicio" name="fechaInicio" 
               value="<fmt:formatDate value='${tarea.fechaInicio}' pattern='yyyy-MM-dd' />" required>
        <br><br>
        
        <label for="fechaFin">Fecha de Fin:</label>
        <input type="date" id="fechaFin" name="fechaFin" 
               value="<fmt:formatDate value='${tarea.fechaFin}' pattern='yyyy-MM-dd' />" required>
        <br><br>
        
        <label for="estado">Estado:</label>
        <select id="estado" name="estado" required>
            <option value="sin iniciar" <c:if test="${tarea.estado == 'sin iniciar'}">selected</c:if>>Sin iniciar</option>
            <option value="en proceso" <c:if test="${tarea.estado == 'en proceso'}">selected</c:if>>En proceso</option>
            <option value="finalizado" <c:if test="${tarea.estado == 'finalizado'}">selected</c:if>>Finalizado</option>
        </select>
        <br><br>
        
        <input type="submit" value="Actualizar Tarea">
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/TareaServlet?action=list&projectId=${param.projectId}">Volver a la lista de tareas</a>
</body>
</html>
