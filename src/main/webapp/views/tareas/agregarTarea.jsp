<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Tarea</title>
</head>
<body>
    <h1>Agregar Tarea</h1>
    <form action="${pageContext.request.contextPath}/TareaServlet" method="post">
        <input type="hidden" name="action" value="create">
        <!-- Se asume que se recibe el projectId para vincular la tarea al proyecto -->
        <input type="hidden" name="projectId" value="${param.projectId}">
        
        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" name="descripcion" required>
        <br><br>
        
        <label for="responsable">Responsable:</label>
        <input type="text" id="responsable" name="responsable" required>
        <br><br>
        
        <label for="fechaInicio">Fecha de Inicio:</label>
        <input type="date" id="fechaInicio" name="fechaInicio" required>
        <br><br>
        
        <label for="fechaFin">Fecha de Fin:</label>
        <input type="date" id="fechaFin" name="fechaFin" required>
        <br><br>
        
        <label for="estado">Estado:</label>
        <select id="estado" name="estado" required>
            <option value="sin iniciar">Sin iniciar</option>
            <option value="en proceso">En proceso</option>
            <option value="finalizado">Finalizado</option>
        </select>
        <br><br>
        
        <input type="submit" value="Crear Tarea">
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/TareaServlet?action=list&projectId=${param.projectId}">Volver a la lista de tareas</a>
</body>
</html>
