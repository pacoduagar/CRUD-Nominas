<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Buscar y Actualizar Empleado</title>
<link rel="stylesheet" href="styles/style.css">
</head>
<body>
	<h2>Buscar y Actualizar Empleado</h2>

	<!-- Formulario para buscar el empleado -->
	<form action="empleados" method="get">
		<label for="dni">DNI del empleado:</label> 
		<input type="text" id="dni" name="dni" required> 
		<input type="hidden" name="opcion" value="actualizarEmpleado"> 
		<button type="submit">Buscar Empleado</button>
	</form>

	<!-- Mostrar mensajes de error -->
		<c:if test="${not empty error && dni != null}">
			<p style="color: red;">${error}</p>
		</c:if>
	<!-- Mostrar empleado y formulario de actualización si existe -->
	<c:if test="${empleado != null}">
		<h3>Datos del Empleado</h3>
		<form action="empleados" method="post">
			<input type="hidden" name="dni" value="${empleado.dni}" />
			<div>
				<label>Nombre:</label> <input type="text" name="nombre"
					value="${empleado.nombre}" required />
			</div>
			<div>
				<label>Sexo:</label> <select name="sexo" required>
					<option value="M"
						<c:if test="${empleado.sexo == 'M'}">selected</c:if>>Masculino</option>
					<option value="F"
						<c:if test="${empleado.sexo == 'F'}">selected</c:if>>Femenino</option>
				</select>
			</div>
			<div>
				<label>Categoría:</label> <input type="number" name="categoria"
					value="${empleado.categoria}" required />
			</div>
			<div>
				<label>Años:</label> <input type="number" name="anyos"
					value="${empleado.anyos}" required />
			</div>
			<input type="hidden" name="opcion" value="actualizarEmpleado" />
			<button type="submit">Actualizar Empleado</button>
		</form>
	</c:if>

	<!-- Enlace para regresar al index -->
	<br>
	<a href="index.jsp">Volver al inicio</a>
</body>
</html>
