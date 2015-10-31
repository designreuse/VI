
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
<script type="text/javascript" src="js/QR/qrcodelib.js"></script>
<script type="text/javascript" src="js/QR/webcodecamjs.js"></script>
<script src="./js/QR/scan.js"></script>
<script src="./js/moment.js"></script>

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
		<%@include file="teacherSideBar.jsp"%>

		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa fa-bars"></i> Pages
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="teacherProfile.jsp">Home</a></li>
						</ol>
					</div>
				</div>

				<!-- -------------------page start-------------------------->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">Attendance Taking</div>
							<div class="panel-body">
								<!-- 								<p id="demo"></p> -->

								<form action="manualAttendance.jsp" method="get">
									<input class="btn btn-default" type="submit"
										value="Manual Attendance Taking">
								</form>
								<hr>
								<hr>
								<div class ="row">
									<div class="col-lg-4"></div>
									<div class="col-lg-4">
								<canvas></canvas>
								</div>
								<div class="col-lg-4"></div>
								</div>
								<script type="text/javascript">
								scan();
								</script>
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