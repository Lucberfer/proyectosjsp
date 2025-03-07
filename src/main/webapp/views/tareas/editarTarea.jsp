<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Tarea</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/Style.css">
</head>
<body>
    <h1>Editar Tarea</h1>
    <!-- Form to update an existing task -->
    <form action="${pageContext.request.contextPath}/TareaServlet" method="post">
        <!-- Hidden fields to specify the update action and task ID -->
        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="id" value="${tarea.id}" />
        
        <!-- Task description input -->
        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" name="descripcion" value="${tarea.descripcion}" required />
        <br/><br/>
        
        <!-- Responsible person input -->
        <label for="responsable">Responsable:</label>
        <input type="text" id="responsable" name="responsable" value="${tarea.responsable}" required />
        <br/><br/>
        
        <!-- Start date input with a date picker -->
        <label for="fechaInicio">Fecha de inicio:</label>
        <input type="date" id="fechaInicio" name="fechaInicio" 
               value="<fmt:formatDate value='${tarea.fechaInicio}' pattern='yyyy-MM-dd'/>" required />
        <br/><br/>
        
        <!-- End date input with a date picker -->
        <label for="fechaFin">Fecha de fin:</label>
        <input type="date" id="fechaFin" name="fechaFin" 
               value="<fmt:formatDate value='${tarea.fechaFin}' pattern='yyyy-MM-dd'/>" required />
        <br/><br/>
        
        <!-- Status dropdown -->
        <label for="estado">Estado:</label>
        <select id="estado" name="estado" required>
            <option value="sin iniciar" <c:if test="${tarea.estado eq 'sin iniciar'}">selected</c:if>>Not started</option>
            <option value="en proceso" <c:if test="${tarea.estado eq 'en proceso'}">selected</c:if>>In process</option>
            <option value="finalizado" <c:if test="${tarea.estado eq 'finalizado'}">selected</c:if>>Completed</option>
        </select>
        <br/><br/>
        
        <!-- Project dropdown: iterate over all available projects -->
        <label for="projectId">Projecto:</label>
        <select id="projectId" name="projectId" required>
            <c:forEach var="proyecto" items="${proyectos}">
                <option value="${proyecto.id}" <c:if test="${proyecto.id eq tarea.proyecto.id}">selected</c:if>>
                    ${proyecto.nombre}
                </option>
            </c:forEach>
        </select>
        <br/><br/>
        
        <!-- Submit button -->
        <input type="submit" value="Update Task" />
    </form>
    <br/>
    <!-- Link to return to the task list -->
    <a href="${pageContext.request.contextPath}/TareaServlet?action=list">Vuelta a la Lista de Tareas</a>
</body>
</html>
