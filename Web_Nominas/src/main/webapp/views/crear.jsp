<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>A�adir Nuevo Empleado</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
    <h2>A�adir Nuevo Empleado</h2>
	
	<c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>
	
    <!-- Formulario para crear un nuevo empleado -->
    <form action="empleados" method="post">
        <input type="hidden" name="opcion" value="crear">

        <div>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
        </div>

        <div>
            <label for="dni">DNI:</label>
            <input type="text" id="dni" name="dni" pattern="[0-9]{8}[A-Za-z]{1}" 
         maxlength="9" required>
        </div>

        <div>
            <label for="sexo">Sexo:</label>
            <select id="sexo" name="sexo" required>
                <option value="">--Selecciona--</option>
                <option value="M">Masculino</option>
                <option value="F">Femenino</option>
            </select>
        </div>

        <div>
            <label for="categoria">Categor�a:</label>
            <input type="number" id="categoria" name="categoria" min="1" max="10" required>
        </div>

        <div>
            <label for="anyos">A�os:</label>
            <input type="number" id="anyos" name="anyos" min="0" required>
        </div>

        <button type="submit" >A�adir Empleado</button>
    </form>

    <!-- Mostrar mensajes de error si existen -->
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <!-- Enlace para regresar al index -->
    <br>
    <a href="javascript:history. back()">Volver</a>
</body>
</html>
