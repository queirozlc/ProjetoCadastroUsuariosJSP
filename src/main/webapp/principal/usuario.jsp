<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<style>
.sucess-msg {
	margin-top: 15px;
}

.table-wrapper {
	height: 300px;
	overflow: scroll;
}



.uploader-container {
	display: flex;
	align-items: center;
	justify-content: center;
	column-gap: 1.5rem;
}
</style>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="preloader.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">


			<!-- NavBar starts -->
			<jsp:include page="navbar.jsp"></jsp:include>
			<!-- Navbar Ends -->

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<!-- Side bar starts -->
					<jsp:include page="sidebar.jsp"></jsp:include>
					<!-- Sidebar ends -->
					<div class="pcoded-content">

						<!-- Page-header start -->
						<jsp:include page="pageheader.jsp"></jsp:include>
						<!-- Page-header end -->

						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-header">
														<h5>Cadastro de Usuários</h5>
													</div>
													<div class="card-block">
														<h4 class="sub-title">Cadastro e Consulta de
															Usuários.</h4>
														<form class="form-material" method="post"
															enctype="multipart/form-data"
															action="<%=request.getContextPath()%>/ServletCadastrarUsuario"
															id="formUser">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" required readonly="readonly"
																	value="${modelLogin.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID:</label>
															</div>

															<div class="form-group form-default">
																<input type="text" name="nome" id="nome"
																	class="form-control" required
																	value="${modelLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome:</label>
															</div>
															
															<div class="form-group form-default">
																<input type="text" name="login" id="login"
																	class="form-control" required
																	value="${modelLogin.login}"> <span
																	class="form-bar"></span> <label class="float-label">Login:</label>
															</div>
															
															<div class="form-group form-default">
																<input type="email" name="email" id="email"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.email}"> <span
																	class="form-bar"></span> <label class="float-label">E-mail:</label>
															</div>
															
															<div class="form-group form-default">
																<input type="text" name="dataNascimento" id="dataNascimento"
																	class="form-control" required onkeypress="$(this).mask('00/00/0000')"
																	value="${modelLogin.dataNascimento}"> <span
																	class="form-bar"></span> <label class="float-label">Data de Nascimento:</label>
															</div>
															
															<div class="form-group form-default">
																<input type="text" name="salario" id="salario"
																	value="${modelLogin.salario }" class="form-control" required> <span
																	class="form-bar"></span> <label class="float-label">Salário:</label>
															</div>

															<div class="form-group form-default">
																<input type="text" name="cep" id="cep"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.cep}" onblur="pesquisaCep();" >
																<span class="form-bar"></span> <label
																	class="float-label">Cep:</label>
															</div>

															<div class="form-group form-default">
																<input type="text" name="logradouro" id="logradouro"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.logradouro}"> <span
																	class="form-bar"></span> <label class="float-label">Rua:</label>
															</div>

															<div class="form-group form-default">
																<input type="text" name="numero" id="numero"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.numero}"> <span
																	class="form-bar"></span> <label class="float-label">Número:</label>
															</div>

															<div class="form-group form-default">
																<input type="text" name="bairro" id="bairro"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.bairro}"> <span
																	class="form-bar"></span> <label class="float-label">Bairro:</label>
															</div>

															<div class="form-group form-default">
																<input type="text" name="localidade" id="localidade"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.localidade}"> <span
																	class="form-bar"></span> <label class="float-label">Cidade:</label>
															</div>

															<div class="form-group form-default">
																<input type="text" name="uf" id="uf"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.uf}"> <span
																	class="form-bar"></span> <label class="float-label">Estado:</label>
															</div>



															<div class="form-group form-default">
																<select class="form-control"
																	aria-label="Default select example" name="perfil">
																	<option disabled="disabled" selected="selected">SELECIONE
																		UMA OPÇÃO</option>
																	<option value="ADMINISTRADOR"
																		<%ModelLogin model = (ModelLogin) request.getAttribute("modelLogin");

if (model != null && model.getPerfil().equals("ADMINISTRADOR")) {
	out.println(" ");
	out.print("selected=\"selected\"");
	out.println(" ");
}%>>
																		Administrador</option>
																	<option value="FUNCIONARIO"
																		<%model = (ModelLogin) request.getAttribute("modelLogin");

if (model != null && model.getPerfil().equals("FUNCIONARIO")) {
	out.println(" ");
	out.print("selected=\"selected\"");
	out.println(" ");
}%>>Funcionário</option>
																	<option value="DIRETOR"
																		<%model = (ModelLogin) request.getAttribute("modelLogin");

if (model != null && model.getPerfil().equals("DIRETOR")) {
	out.println(" ");
	out.print("selected=\"selected\"");
	out.println(" ");
}%>>Diretor</option>
																</select> <span class="form-bar"></span> <label
																	class="float-label">Perfil:</label>
															</div>


															<div class="form-group form-default">
																<div class="form-check-inline">
																	<label class="form-check-label"> <input
																		type="radio" class="form-check-input" name="sexo"
																		value="MASCULINO"
																		<%model = (ModelLogin) request.getAttribute("modelLogin");

if (model != null && model.getSexo().equals("MASCULINO")) {
	out.println(" ");
	out.print("checked=\"checked\"");
	out.println(" ");
}%>>
																		Masculino
																	</label>
																</div>
																<div class="form-check-inline">
																	<label class="form-check-label"> <input
																		type="radio" class="form-check-input" name="sexo"
																		value="FEMININO"
																		<%model = (ModelLogin) request.getAttribute("modelLogin");

if (model != null && model.getSexo().equals("FEMININO")) {
	out.println(" ");
	out.print("checked=\"checked\"");
	out.println(" ");
}%>>
																		Feminino
																	</label>
																</div>

															</div>

															<div class="form-group form-default">
																<input type="password" name="senha" id="senha"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.senha}"> <span
																	class="form-bar"></span> <label class="float-label">Senha:</label>
															</div>

															<div
																class="form-group form-default input-group mb-4 mt-4">
																<div class="input-group-prepend uploader-container">
																	<c:if
																		test="${modelLogin.fotoUser != '' && modelLogin.fotoUser != null}">
																		<a
																			href="<%= request.getContextPath() %>/ServletCadastrarUsuario?acao=downloadFoto&id=${modelLogin.id}">
																			<img class="img-80 img-radius" alt="Upload Foto perfil"
																			src="${modelLogin.fotoUser }" id="fotoembase64">
																		</a>
																	</c:if>

																	<c:if
																		test="${modelLogin.fotoUser == '' || modelLogin.fotoUser == null}">

																		<img class="img-80 img-radius" alt="Upload Foto perfil"
																			src="<%=request.getContextPath()%>/assets/images/avatar-4.jpg"
																			id="fotoembase64">
																	</c:if>

																	<label for="fileFoto">Escolher Foto de Perfil</label> <input
																		type="file" accept="image/*" class="form-control-file"
																		onchange="visualizarImg('fotoembase64', 'fileFoto')"
																		style="cursor: pointer;" id="fileFoto" name="fileFoto">
																</div>
															</div>

															<!-- Novo -->
															<button type="button"
																class="btn btn-primary waves-effect waves-light"
																onclick="limparForm();">Novo Registro</button>

															<!-- Cadastrar -->
															<button class="btn btn-success waves-effect waves-light">Salvar
																Usuário</button>

															<!-- Excluir -->
															<button type="button"
																class="btn btn-info waves-effect waves-light"
																onclick="criarDeleteAjax();">Excluir Usuário</button>

															<!-- Consulta -->
															<button type="button" class="btn btn-primary"
																data-toggle="modal" data-target="#exampleModal">
																Consultar Cadastros</button>
																
															<!-- Consultar Telefone -->
															<c:if test="${modelLogin.id > 0}">
																<a href="<%= request.getContextPath() %>/ServletTelefone?idUser=${modelLogin.id}" 
																class="btn btn-success waves-effect waves-light">Consultar Telefone</a>
															</c:if>
														</form>

														<h5 class="sucess-msg" id="msg">${msg}</h5>

														<!-- Listando usuarios da tela -->
														<div class="table-wrapper">
															<table class="table" id="tabelaresultadosview">
																<thead>
																	<tr>
																		<th scope="col">ID</th>
																		<th scope="col">Nome</th>
																		<th scope="col">Email</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${listaUsers}" var="listUser">
																		<tr>
																			<td><c:out value="${listUser.id}"></c:out></td>
																			<td><c:out value="${listUser.nome}"></c:out></td>
																			<td><c:out value="${listUser.email}"></c:out></td>
																			<td><a
																				class="btn btn-primary btn-outline-secondary"
																				href="<%= request.getContextPath() %>/ServletCadastrarUsuario?acao=buscarEditar&id=${listUser.id}">Ver</a>
																			</td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</div>
														<!-- Pagination -->
														<nav aria-label="Page navigation example">
															<ul class="pagination">
															
																<%
																	int totalPagina = (int) request.getAttribute("totalPaginas");
																
																	for (int i = 0; i < totalPagina; i++){
																		String url = request.getContextPath() + 
																				"/ServletCadastrarUsuario?acao=paginar&pagina=" + (i * 5);
																		out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+
																				url+"\">"+
																				(i + 1)+"</a></li>");
																	}
																%>
															</ul>
														</nav>
													</div>
												</div>
											</div>
										</div>

									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- JS files -->
	<jsp:include page="scripts.jsp"></jsp:include>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisar
						Usuário</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<!--  Campo de pesquisa -->
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome"
							aria-label="Recipient's username" aria-describedby="basic-addon2"
							id="nomeBusca">
						<div class="input-group-append">
							<button class="btn btn-primary btn-outline-secondary"
								type="button" onclick="buscarUsuario();">Buscar</button>
						</div>
					</div>

					<!-- Retorno dos dados na tela -->
					<div class="table-wrapper">
						<table class="table" id="tabelaresultados">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Nome</th>
									<th scope="col">Ver Mais</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
					<span id="totalResultados"></span>
				</div>
				<div class="modal-footer">
					<nav aria-label="Page navigation example">
						<ul class="pagination" id="ulPaginacaoUser">
						</ul>
					</nav>

					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
		$("#salario").maskMoney({showSymbol:true, symbol:"R$ ", decimal:",", thousands:"."});
		
		const formatter = Intl.NumberFormat('pt-BR', {
			
			currency: 'BRL',
			minimumFractionDigits: 2
		});
		
		$("#salario").val(formatter.format($("#salario").val()));
		$("#salario").focus();
		
		var dataNascimento = $("#dataNascimento").val();
		
		var dateFormat = new Date(dataNascimento);
		
		$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR', {timeZone: 'UTC'}));
		
		$("#nome").focus();
	
		$( function() {
			  
			  $("#dataNascimento").datepicker({
				    dateFormat: 'dd/mm/yy',
				    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
				    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
				    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
				    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
				    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
				    nextText: 'Próximo',
				    prevText: 'Anterior'
				});
		} );
	
		$("#numero").keypress(function () {
			return /\d/.test(String.fromCharCode(event.keyCode));
		});
		
		$("#cep").keypress(function () {
			return /\d/.test(String.fromCharCode(event.keyCode));
		});
	
		function pesquisaCep() {

			let cep = $("#cep").val();

			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							
							$("#cep").val(dados.cep);
							$("#logradouro").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#localidade").val(dados.localidade);
							$("#uf").val(dados.uf);
						}

					});

		}

		function visualizarImg(elementImg, fileFoto) {

			let preview = document.getElementById(elementImg);
			let fileUser = document.getElementById(fileFoto).files[0];
			let reader = new FileReader();

			reader.onloadend = function() {
				preview.src = reader.result;
			};

			if (fileUser) {
				reader.readAsDataURL(fileUser);
			} else {
				preview.src = '';
			}
		}

		function verEditar(id) {

			let urlAction = document.getElementById('formUser').action;
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;

		}
		
		function buscaUserPagAjax(url){
			
			var urlAction = document.getElementById('formUser').action;
		    var nomeBusca = document.getElementById('nomeBusca').value;
		    
			$.ajax({

				method: "get",
			    url : urlAction,
			    data : url,
				success : function(response, textStatus, xhr) {

					var json = JSON.parse(response);

					$('#tabelaresultados > tbody > tr').remove();
					$("#ulPaginacaoUser > li").remove();

					for (p = 0; p < json.length; p++) {
						$('#tabelaresultados > tbody')
								.append(
										'<tr> <td>'
												+ json[p].id
												+ '</td> <td> '
												+ json[p].nome
												+ '</td> <td><button onclick="verEditar('
												+ json[p].id
												+ ');" type="button" class="btn btn-info">Ver</button></td></tr>');
					}
					
					document.getElementById('totalResultados').textContent = "Resultados: " + json.length;
					
					var totalPagina = xhr.getResponseHeader("totalPagina");
					
					for (var p = 0; p < totalPagina; p++) {
						var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina='+ p * 5;
						$("#ulPaginacaoUser").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>');
					}
				}	

			}).fail(
			function(xhr, status, errorThrown) {
				alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
			});
			
		}

		function buscarUsuario() {

			let nomeBusca = document.getElementById('nomeBusca').value;

			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') { /*Validando que tem que ter valor pra buscar no banco*/

				let urlAction = document.getElementById('formUser').action;

				$.ajax({

									method : "get",
									url : urlAction,
									data : "nomeBusca=" + nomeBusca
											+ '&acao=buscarUser',
									success : function(response, textStatus, xhr) {

										var json = JSON.parse(response);

										$('#tabelaresultados > tbody > tr').remove();
										$("#ulPaginacaoUser > li").remove();

										for (p = 0; p < json.length; p++) {
											$('#tabelaresultados > tbody')
													.append(
															'<tr> <td>'
																	+ json[p].id
																	+ '</td> <td> '
																	+ json[p].nome
																	+ '</td> <td><button onclick="verEditar('
																	+ json[p].id
																	+ ');" type="button" class="btn btn-info">Ver</button></td></tr>');
										}
										
										document.getElementById('totalResultados').textContent = "Resultados: " + json.length;
										
										var totalPagina = xhr.getResponseHeader("totalPagina");
										
										for (p = 0; p < totalPagina; p++) {
											var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina='+ (p * 5);
											$("#ulPaginacaoUser").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>');
										}
									}	

								}).fail(
								function(xhr, status, errorThrown) {
									alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
								});
			}

		}
		

		function criarDeleteAjax() {
			if (confirm('Deseja realmente deletar esse usuario?')) {

				let urlAction = document.getElementById('formUser').action;
				let idUser = document.getElementById('id').value;

				$.ajax({

					method : 'get',
					url : urlAction,
					data : 'id=' + idUser + '&acao=deleteajax',
					success : function(response) {

						limparForm();
						document.getElementById('msg').textContent = response;
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar Usuario por id: '
									+ xhr.responseText);
						});
			}
		}

		function criarDelete() {
			if (confirm("Deseja realmente deletar esse usuario?")) {
				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'delete';
				document.getElementById("formUser").submit();
			}
		}

		function limparForm() {

			let elementos = document.getElementById("formUser").elements;
			let msg = document.querySelector('.sucess-msg');
			for (i = 0; i < elementos.length; i++) {
				elementos[i].value = '';
			}

			msg.textContent = '';
		}
	</script>
</body>

</html>
