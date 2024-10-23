<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Filtrar Empleados</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
    <h2>Filtrar Empleados</h2>

    <!-- Formulario de Filtrado -->
    <form action="empleados" method="get">
        <input type="hidden" name="opcion" value="filtrar">

        <div>
            <label for="sexo">Sexo:</label>
            <select id="sexo" name="sexo">
                <option value="">--Selecciona--</option>
                <option value="M" ${param.sexo == 'M' ? 'selected' : ''}>Masculino</option>
                <option value="F" ${param.sexo == 'F' ? 'selected' : ''}>Femenino</option>
            </select>
        </div>

        <div>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="${param.nombre}">
        </div>

        <div>
            <label for="categoria">Categoría:</label>
            <input type="number" id="categoria" name="categoria" value="${param.categoria}">
        </div>
	<br>
        <div>
            <label for="anyos">Años:</label>
            <input type="number" id="anyos" name="anyos" value="${param.anyos}">
        </div>
	<br>
        <button type="submit">Aplicar Filtros</button>
    </form>

    <!-- Botón para Eliminar Filtros -->
    <form action="empleados" method="get" style="margin-top: 10px;">
        <input type="hidden" name="opcion" value="filtrar">
        <button type="submit">Eliminar Filtros</button>
    </form>
	
	<br>
    <!-- Mostrar Lista de Empleados Filtrados -->
    <h3>Resultados del Filtrado:</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>DNI</th>
                <th>Sexo</th>
                <th>Categoría</th>
                <th>Años</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="empleado" items="${empleados}">
                <tr>
                    <td>${empleado.nombre}</td>
                    <td>${empleado.dni}</td>
                    <td>${empleado.sexo}</td>
                    <td>${empleado.categoria}</td>
                    <td>${empleado.anyos}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Enlace para regresar al index -->
    <br>
    <a href="index.jsp">Volver al inicio</a>
</body>
</html>

