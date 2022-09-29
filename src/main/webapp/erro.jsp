<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Erro - Projeto JSP</title>
</head>
<body>
	<h3>Ops... Algo deu errado :/ </h3>
	<% 
		out.print(request.getAttribute("msg"));
	%>
</body>
</html>