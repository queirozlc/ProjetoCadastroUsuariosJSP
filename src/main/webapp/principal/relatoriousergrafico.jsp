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
															
															<input type="hidden" id="acaoRelatorioImprimirTipo" name="acao" value="imprimirRelatorioUser">
										
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

															<button type="button" class="btn btn-primary mb-2" onclick="gerarGrafico();">Gerar Gráfico</button>
														</form> <!-- Form Ends -->
														
														<div>
															<div>
																<canvas id="myChart"></canvas>
															</div>
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

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script type="text/javascript">

var myChart = new Chart(document.getElementById('myChart'));
	
	
	function gerarGrafico() {
		
		var urlAction = document.getElementById('formRelatorio').action;
		var dataInicial = document.getElementById('dataInicial').value;
		var dataFinal = document.getElementById('dataFinal').value;
		
		$.ajax({

			method : 'get',
			url : urlAction,
			data : 'dataInicial=' + dataInicial + '&dataFinal=' + dataFinal + '&acao=graficoSalario',
			success : function(response) {
				
				var json = JSON.parse(response);
				
				myChart.destroy();
				
				myChart = new Chart(
					document.getElementById('myChart'), 
					{
						type : 'line',
						data : {
							labels : json.listaPerfil,
							datasets : [ {
								label : 'Média Salarial',
								backgroundColor : 'rgb(255, 99, 132)',
								borderColor : 'rgb(255, 99, 132)',
								data : json.listaMediaSalarial,
						} ]
					},
					options : {}
				});
			}

		}).fail(
				function(xhr, status, errorThrown) {
					alert('Erro ao buscar os dados para o gráfico: '
							+ xhr.responseText);
				});

	}

	$(function() {

		$("#dataInicial").datepicker(
				{
					dateFormat : 'dd/mm/yy',
					dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta',
							'Quinta', 'Sexta', 'Sábado' ],
					dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D' ],
					dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex',
							'Sáb', 'Dom' ],
					monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril',
							'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro',
							'Outubro', 'Novembro', 'Dezembro' ],
					monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai',
							'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
					nextText : 'Próximo',
					prevText : 'Anterior'
				});
	});

	$(function() {

		$("#dataFinal").datepicker(
				{
					dateFormat : 'dd/mm/yy',
					dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta',
							'Quinta', 'Sexta', 'Sábado' ],
					dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D' ],
					dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex',
							'Sáb', 'Dom' ],
					monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril',
							'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro',
							'Outubro', 'Novembro', 'Dezembro' ],
					monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai',
							'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
					nextText : 'Próximo',
					prevText : 'Anterior'
				});
	});
</script>
</body>

</html>
