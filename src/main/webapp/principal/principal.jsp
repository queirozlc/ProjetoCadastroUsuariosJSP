<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

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
</body>

</html>
