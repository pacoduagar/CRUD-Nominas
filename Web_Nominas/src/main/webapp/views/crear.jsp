<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Añadir Nuevo Empleado</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
    <h2>Añadir Nuevo Empleado</h2>

    <!-- Formulario para crear un nuevo empleado -->
    <form action="empleados" method="post">
        <input type="hidden" name="opcion" value="crear">

        <div>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
        </div>

        <div>
            <label for="dni">DNI:</label>
            <input type="text" id="dni" name="dni" required>
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
            <label for="categoria">Categoría:</label>
            <input type="number" id="categoria" name="categoria" min="1" max="10" required>
        </div>

        <div>
            <label for="anyos">Años:</label>
            <input type="number" id="anyos" name="anyos" min="0" required>
        </div>

        <button type="submit" name="opcion" value="listar">Añadir Empleado</button>
    </form>

    <!-- Mostrar mensajes de error si existen -->
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <!-- Enlace para regresar al index -->
    <br>
    <a href="index.jsp">Volver al inicio</a>
</body>
</html>
