<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear Producto</title>
<link rel="stylesheet" href="style/styles.css">

</head>
<body>
	<h1>Crear Producto</h1>
	<h2><a href="index.jsp">Volver al Index</a></h2>
	<form action="productos" method="post">
		<input type="hidden" name="opcion" value="guardar"> �
		<input type="hidden" name="sourcePage" value="crear.jsp">�
		<table border="1">
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" size="50"></td>
			</tr>
			<tr>
				<td>Cantidad:</td>
				<td><input type="text" name="cantidad" size="50"></td>
			</tr>
			<tr>
				<td>Precio:</td>
				<td><input type="text" name="precio" size="50"></td>
			</tr>
		</table>
		��<input type="submit" value="Guardar"> �
	</form>
	<h2>
	<%
	String mensaje = (String) request.getAttribute("message");
	%>
	<%
	if (mensaje != null) {
	%>
	<%=mensaje%>
	<%
	}
	%>
	</h2>
</body>
</html>