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

<!-- -----------------------DataTables CSS---------------------------------------- -->
<link
	href="js/datatables/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet">
<!-- ----------------------DataTables Responsive CSS---------------------- -->
<link
	href="js/datatables/datatables-responsive/css/dataTables.responsive.css"
	rel="stylesheet">

<!-- ------------------- External CSS: Fonts and Icons ----------------------- -->
<!-- Fonts and Icons -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />

<!-- ------------------- External CSS: Custom styles----------------------------- -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />

</head>

<body>
	<!-- ----------------------JS section------------------------- -->
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/pages/parent.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="assets/jquery-knob/js/jquery.knob.js"></script>

	<!-- DataTables JavaScript -->
	<script
		src="./js/datatables/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="./js/datatables/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<script
		src="./js/datatables/datatables-responsive/js/dataTables.responsive.js"></script>

	<!-- Custom JavaScript -->
	

	<script src="js/scripts.js"></script>
	<!------- container section start------------ -->
	<section id="container" class="">
		<!--------- including navBar & sideBar------------ -->
		<%@include file="navBar.jsp"%>
		<%@include file="sideBar.jsp"%>

		<!-- ----------------main content start-------------------->
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa fa-bars"></i> Pages
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="adminMain.jsp">Home</a></li>
							<li><i class="icon_document_alt"></i>Parent</li>
							<li><i class="fa fa-files-o"></i>Parents Profile</li>
						</ol>
					</div>
				</div>
				
				<!-- -------------------------page start-------------------------->
				<div id="page-wrapper">
					<!-- /.row -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">View All Parents</div>
								<!-- /.panel-heading -->
								<div class="panel-body">
									<div class="dataTable_wrapper">
										<table class="table table-striped table-bordered table-hover"
											id="parentTable">
											<thead>
												<tr>
													<th>Parent ID</th>
													<th>Name</th>
													<th>Contact</th>
													<th>Email</th>
													<th>Address</th>
													<th>Join Date</th>
													<th>Options</th>
												</tr>
											</thead>
											<tbody id='parents'>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!-- /.row -->
					</div>
				</div>
				<!-- /#wrapper -->

				<!-- page end-->
			</section>
		</section>
		<!--main content end-->
	</section>
	<!-- container section end -->
</body>
</html>
