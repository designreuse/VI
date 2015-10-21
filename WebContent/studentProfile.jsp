
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="img/favicon.png">

<title>Explore and Learn Pte Ltd - Main Page</title>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->

<!-- ----------------------Bootstrap CSS & Theme----------------------------- -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.css" rel="stylesheet">

<!-- ---------------------External CSS: Fonts and Icons ----------------------- -->
<!-- Fonts and Icons -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />

<!-- -------------------External CSS: Custom styles----------------------------- -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />

<!-- -----------------------DataTables CSS---------------------------------------- -->
<link
	href="js/datatables/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet">
<!-- ----------------------DataTables Responsive CSS---------------------- -->
<link
	href="js/datatables/datatables-responsive/css/dataTables.responsive.css"
	rel="stylesheet">

<!-- -------------------------------javascripts----------------------- -->
<script type="text/javascript" src="./js/jQuery-2.1.4.min.js"></script>
<script src="./js/student/student.js"></script>


</head>

<body>
	<!-- -----------------javascripts------------------ -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script
		src="js/datatables/datatables/media/js/jquery.dataTables.min.js"></script>

	<!-- ------------------nice scroll----------------------- -->
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>


	<!-- -------custome script for all page----------- -->
	<script src="js/scripts.js"></script>

	<!-- --------DataTables JavaScript----------- -->
	<script
		src="./js/datatables/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="./js/datatables/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<script
		src="./js/datatables/datatables-responsive/js/dataTables.responsive.js"></script>

	<!-- --------DataTables JavaScript----------- -->
	<script src="./js/bootbox.min.js"></script>

	<!------- container section start------------ -->
	<section id="container" class="">

		<!--------- including navBar & sideBar------------ -->
		<%@include file="navBar.jsp"%>
		<%@include file="sideBar.jsp"%>


		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa fa-bars"></i> Pages
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a
								href="partTimeCalOverview.jsp">Home</a></li>
							<li><i class="icon_contacts_alt"></i><a
								href="parentProfile.jsp">View Profile</a></li>
						</ol>
					</div>
				</div>

				<!-- -------------------page start-------------------------->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">User Profile</div>
							<div class="panel-body">
								<div class="panel panel-info">

									<!--  start of displaying teacher panel -- -->
									<div class="panel-heading" id="name"></div>

									<div class="panel-body">
										<div class="row">
											<div class="col-md-3 col-lg-3" align="center">
												<img alt="User Pic" src="./img/profile-avatar.jpg"
													class="img-circle img-responsive">
											</div>
											<div class="col-md-6 col-lg-6">
												<table class="table table-user-information">
													<tbody>
														<tr>
															<td><strong>Home Address:</strong></td>
															<td id="address"></td>
														</tr>
														<tr>
															<td><strong>Phone Number:</strong></td>
															<td id="phone"></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


				<!-- /.panel-heading -->
				<!-- --------------------page end-------------------------------->

			</section>
		</section>
		<!--main content end-->
	</section>
	<!-- container section end -->
</body>
</html>
