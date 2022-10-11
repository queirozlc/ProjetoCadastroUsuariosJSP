<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<style>

.form-container {
	display: flex;
	align-items: center;
	column-gap: 2.5rem;
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
														<h5>Relatório</h5>
													</div>
													<div class="card-block">
														<h4 class="sub-title">Gerar relatório de usuário.</h4>
														
														<!-- Form starts -->
														<form class="form-material form-container" method="get"
															action="<%=request.getContextPath()%>/ServletRelatorioController"
															id="formRelatorio"> 
															
															<input type="hidden" id="acaoRelatorioImprimirTipo" name="acao" value="">
										
															<div class="form-group form-default">
																<input type="text" name="dataInicial" id="dataInicial" class="form-control" 
																 onkeypress="$(this).mask('00/00/0000')" value="${dataInicial}"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">Data inicial:</label>
															</div>
															
															<div class="form-group form-default">
																<input type="text" name="dataFinal" id="dataFinal" class="form-control" 
																 onkeypress="$(this).mask('00/00/0000')" value="${dataFinal}"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">Data final:</label>
															</div>

															<button type="button" onclick="imprimirHTML();" class="btn btn-primary mb-2">Imprimir Relatório</button>
															<button type="button" onclick="imprimirPdf();" class="btn btn-primary mb-2">Imprimir PDF</button>
														
														</form> <!-- Form Ends -->
														
														<!-- Table starts -->
														<div class="table-wrapper">
															<table class="table" id="tabelaresultadosview">
																<thead>
																	<tr>
																		<th scope="col">ID</th>
																		<th scope="col">Nome</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${listaUser}" var="listUser">
																		<tr>
																			<td><c:out value="${listUser.id}"></c:out></td>
																			<td><c:out value="${listUser.nome}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
															<!-- Table end -->

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

function imprimirPdf(){
	document.getElementById("acaoRelatorioImprimirTipo").value = 'imprimirRelatorioPDF';
	$("#formRelatorio").submit();
}

function imprimirHTML(){
	
	document.getElementById("acaoRelatorioImprimirTipo").value = 'imprimirRelatorioUser';
	$("#formRelatorio").submit();
}


$(function() {
	  
	  $("#dataInicial").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
});

$(function() {
	  
	  $("#dataFinal").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
});
</script>
</body>

</html>
