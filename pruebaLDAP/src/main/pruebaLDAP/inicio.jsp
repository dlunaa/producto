<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Login JSP</h1>
	<br>
	<form action="HolaServlet" method="post">
		<label>Nombre</label>
		<input type="text" name="nombre"> 
		<label>Contraseña</label>
		<input type="text" name="contrasena"> 
		<button type="submit">Enviar</button>
	
	</form>

</body>
</html>