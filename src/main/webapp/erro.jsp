<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Google font-->
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">


<title>Erro - Projeto JSP</title>

<style type="text/css">
body {
	font-family: 'Poppins', sans-serif;
}
</style>
</head>
<body>
	<h3>Ops... Algo deu errado :/ </h3>
	<% 
		out.print(request.getAttribute("msg"));
	%>
</body>
</html>