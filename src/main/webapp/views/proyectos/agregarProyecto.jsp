<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Proyecto</title>
    <!-- Puedes incluir hojas de estilo o frameworks (Bootstrap, jQuery UI) para mejorar la apariencia del calendario -->
</head>
<body>
    <h1>Agregar Proyecto</h1>
    <form action="${pageContext.request.contextPath}/ProyectoServlet" method="post">
        <input type="hidden" name="action" value="create">
        
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>
        <br><br>
        
        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" required></textarea>
        <br><br>
        
        <label for="fechaInicio">Fecha de Inicio:</label>
        <!-- Input type date mostrará un calendario -->
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
        
        <input type="submit" value="Crear Proyecto">
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/ProyectoServlet?action=list">Volver a la lista de proyectos</a>
</body>
</html>
