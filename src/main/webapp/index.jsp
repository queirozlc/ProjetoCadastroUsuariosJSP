<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Curso JSP</title>

<style type="text/css">
	
	body {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		row-gap: 1.5rem;
	}
	
	.title {
		margin-top: 150px;
	}
	
	form {
		max-width: 1024px;
	}
	
</style>
</head>
<body>
	<h3 class="title">Entrar no Sistema</h3>

	<form action= "<%= request.getContextPath() %>/LoginServlet" method="post" class="row g-3 needs-validation" novalidate>
	<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
	<div class="mb-3">
			<label class="form-label">Login: </label> 
			<input name="login" type="text" class="form-control" required> 
			<div class="invalid-feedback">
      			Informe o Login !
    		</div>
	</div>
	<div class="mb-3">
		<label class="form-label">Senha: </label> 
		<input name="senha" type="password" class="form-control" required>
		<div class="invalid-feedback">
      		Informe a senha !
    	</div>
	</div>
		<button type="submit" class="btn btn-primary">Login</button>
	</form>
	
	<h5>${msg}</h5>
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
	
	<script type="text/javascript">
	(() => {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  const forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.from(forms).forEach(form => {
		    form.addEventListener('submit', event => {
		      if (!form.checkValidity()) {
		        event.preventDefault()
		        event.stopPropagation()
		      }

		      form.classList.add('was-validated')
		    }, false)
		  })
		})()
	</script>
</body>
</html>