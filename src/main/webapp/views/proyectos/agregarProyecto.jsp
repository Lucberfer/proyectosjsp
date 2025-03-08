<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Agregar Proyecto</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/Style.css">
    </head>
    <body>
        <h1>Añadir Nuevo Proyecto</h1>
        <!-- Form for creating a new project -->
        <form action="${pageContext.request.contextPath}/ProyectoServlet" method="post">
            <!-- Hidden field to specify the "create" action -->
            <input type="hidden" name="action" value="create">

            <!-- Project name input -->
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
            <br/><br/>

            <!-- Project description input -->
            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" required></textarea>
            <br/><br/>

            <!-- Start date input using a date picker -->
            <label for="fechaInicio">Fecha de inicio:</label>
            <input type="date" id="fechaInicio" name="fechaInicio" required>
            <br/><br/>

            <!-- End date input using a date picker -->
            <label for="fechaFin">Fecha de fin:</label>
            <input type="date" id="fechaFin" name="fechaFin" required>
            <br/><br/>

            <!-- Status dropdown -->
            <label for="estado">Estado:</label>
            <select id="estado" name="estado" required>
                <option value="sin iniciar">Sin iniciar</option>
                <option value="en proceso">En proceso</option>
                <option value="finalizado">Completado</option>
            </select>
            <br/><br/>

            <!-- Submit button -->
            <input type="submit" value="Crear Proyecto">
        </form>
        <br/>
        <!-- Link to return to the project list -->
        <a href="${pageContext.request.contextPath}/ProyectoServlet?action=list">Vuelta a lista de Proyectos</a>
    </body>
</html>