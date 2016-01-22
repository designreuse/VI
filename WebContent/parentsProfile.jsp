<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Explore and Learn | Admin Portal</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.10/css/dataTables.bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.0.0/css/responsive.bootstrap.min.css">


<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

<!-- Theme style -->
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">

<!-- Skin Designs-->
<link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">

<link href="css/fullcalendar.min.css" rel="stylesheet">
<link href='css/fullcalendar.print.css' rel='stylesheet' media='print' />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- including navBar and sideBar -->
		<%@include file="navBar.jsp"%>
		<%@include file="sideBar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Parents List</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="box">
							<div class="box-header">
								<h3 class="box-title">Bukit Batok Branch</h3>
							</div>

							<div class="box-body">
								<table id="parentTable"
									class="table table-striped table-bordered dt-responsive nowrap"
									width="100%">
									<thead>
										<tr>
											<th>Parent ID</th>
											<th>Name</th>
											<th>NRIC</th>
											<th>Contact</th>
											<th>Email</th>
											<th>Relationship</th>
											<th>Occupation</th>
											<th>Create Date</th>
											<th>Options</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>

		</div>

		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<!-- To the right -->
			<div class="pull-right hidden-xs">Anything you want</div>
			<!-- Default to the left -->
			<strong>Copyright &copy; 2015 <a href="#">Company</a>.
			</strong> All rights reserved.
		</footer>

	</div>
	<!-- ./wrapper -->

	<!-- -----------------REQUIRED JS SCRIPTS--------------- -->
	<!-- jQuery 2.1.4 -->
	<script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>

	<script src="bootstrap/js/bootstrap.min.js"></script>

	<script src="plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.0.0/js/dataTables.responsive.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.0.0/js/responsive.bootstrap.min.js"></script>

	<!-- Slimscroll -->
	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>

	<!-- FastClick -->
	<script src="plugins/fastclick/fastclick.js"></script>

	<!-- AdminLTE App -->
	<script src="dist/js/app.min.js"></script>

	<!-- AdminLTE for demo purposes -->
	<script src="dist/js/demo.js"></script>

	<script src="./js/bootbox.min.js"></script>

<script src="./js/branchmanager/parent.js"></script>

</body>
</html>
