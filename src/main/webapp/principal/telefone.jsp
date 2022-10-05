<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<style>
.success-msg {
	margin-top: 1.5rem;
}

.table-wrapper {
	height: 300px;
	overflow: scroll;
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
														<h5>Telefone</h5>
													</div>
													<div class="card-block">
														<h4 class="sub-title">Consulta e Cadastro de Telefones do Usuário</h4>
														
														<form class="form-material" method="post" action="<%=request.getContextPath()%>/ServletTelefone" id="formTelefone">
															<!-- Id usuario pai -->
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" required readonly="readonly"
																	value="${modelLogin.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID User:</label>
															</div>
															
															<!-- Nome do Usuário Pai -->
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required readonly="readonly"
																	value="${modelLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome Usuário:</label>
															</div>
															
															<!-- Numero do telefone -->
															<div class="form-group form-default">
																<input type="text" name="numero" id="numero"
																	class="form-control" required onkeypress="$(this).mask('(00) 0000-00009')"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">Número do Telefone:</label>
															</div>
															
															<!-- Cadastrar Telefone -->
															<button class="btn btn-success waves-effect waves-light">Salvar Telefone</button>
														</form>
														<h5 class="success-msg" id="msg">${msg}</h5>
														
														<!-- Listando Telefones do Usuario-->
														<div class="table-wrapper">
															<table class="table" id="tabelaresultadosview">
																<thead>
																	<tr>
																		<th scope="col">ID</th>
																		<th scope="col">Número</th>
																		<th scope="col">Excluir</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${listaTelefones}" var="listaTelefones">
																		<tr>
																			<td><c:out value="${listaTelefones.id}"></c:out></td>
																			<td><c:out value="${listaTelefones.numero}"></c:out></td>
																			<td><a
																				class="btn btn-primary btn-outline-secondary"
																				href="<%= request.getContextPath() %>/ServletTelefone?acao=excluir&id=${listaTelefones.id}&userpai=${modelLogin.id}">Excluir</a>
																			</td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</div>
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
	
	<script type="text/javascript">
	
	
	
	
	</script>
</body>

</html>
