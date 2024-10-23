<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Empleados</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>

<h2>Lista de Empleados</h2>

<h2><a href="index.jsp">Volver al Index</a></h2>

<table border=1>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>DNI</th>
            <th>Sexo</th>
            <th>Categoría</th>
            <th>Años</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="empleado" items="${listaEmpleados}">
            <tr>
                <td>${empleado.id}</td>
                <td>${empleado.nombre}</td>
                <td>${empleado.dni}</td>
                <td>${empleado.sexo}</td>
                <td>${empleado.categoria}</td>
                <td>${empleado.anyos}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>