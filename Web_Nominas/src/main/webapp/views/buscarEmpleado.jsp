<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buscar Salario por DNI</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
    <h2>Buscar Salario por DNI</h2>
    
    <!-- Formulario para buscar el salario por DNI -->
    <form action="empleados" method="get">
        <label for="dni">DNI del empleado:</label>
        <input type="text" id="dni" name="dni" required>
        <input type="hidden" name="opcion" value="buscarSalario">
        <button type="submit">Buscar Salario</button>
    </form>


        <c:if test="${salario != null && not empty dni && salario != 0}">
            <h3>El salario del empleado es: ${salario}</h3>
        </c:if>

        <!-- Mostrar mensaje de error si no se encontró el salario o hubo algún problema -->
        <c:if test="${not empty error && not empty dni}">
            <p style="color: red;">${error}</p>
        </c:if>

    <!-- Enlace para regresar al index -->
    <br>
    <a href="javascript:history. back()">Volver</a>
</body>
</html>


