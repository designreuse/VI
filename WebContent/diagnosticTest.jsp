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

<!-- -----------------------DataTables CSS---------------------------------------- -->
<link
	href="js/datatables/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet">
<!-- ----------------------DataTables Responsive CSS---------------------- -->
<link
	href="js/datatables/datatables-responsive/css/dataTables.responsive.css"
	rel="stylesheet">

<!-- ------------------------ Add Points CSS -------------------------------- -->
<link href="css/addPoints.css" rel="stylesheet">

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
				<h1>Diagnostic Test</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<form class="form-validate form-horizontal myform"
							id="register_form" method="post" action="createSchedule.jsp"
							onsubmit="getStudentByNric();return false;">

							<div>
								<font color="red" id="message"></font>
							</div>

							<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Add Diagnostic Test Results</h4>
								</div>
								<div class="box-body">
									<div class="form-group">
										<label for="sName" class="control-label col-lg-2">Name<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="studentName" type="text"
												name="sName" pattern="^([a-zA-Z\s]{1,255})$"
												title="Letters only" required />
										</div>
									</div>
									<div class="form-group">
										<label for="sNric" class="control-label col-lg-2">NRIC<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="studentNric" type="text"
												name="sName" pattern="^[SFTG]\d{7}[A-Z]$" title="NRIC only"
												required />
										</div>
									</div>
									<div class="form-group ">
										<label for="coursesEnrolled" class="control-label col-lg-2">Course
											Taken<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<div id="course_points">
												<input type="radio" id="math" class="enable_cb"
													name="course" value="Math">Math <input type="radio"
													id="english" class="enable_cb" name="course"
													value="English">English <input type="radio"
													id="korean" class="enable_cb" name="course" value="Korean">Korean
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="results" class="control-label col-lg-2">Results<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="results" type="text"
												name="sResults" pattern="^[0-9]+$" title="Numbers only"
												required />
										</div>
									</div>

									<div class="form-group">
										<label for="courseLevel" class="control-label col-lg-2">Course Level<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="courseLevel" type="text"
												name="sCourseLevel" pattern="^[0-9]+$" title="Numbers only"
												required />
										</div>
									</div>
									<div class="form-group">
										<div class="col-lg-offset-2 col-lg-10">
											<button class="btn btn-primary" type="submit">Register</button>
										</div>
									</div>

								</div>

							</div>
						</form>
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

	<!-- Bootstrap 3.3.5 -->
	<script src="bootstrap/js/bootstrap.min.js"></script>

	<!-- jQuery UI 1.11.4 -->
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

	<!-- Slimscroll -->
	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>

	<!-- FastClick -->
	<script src="plugins/fastclick/fastclick.js"></script>

	<!-- AdminLTE App -->
	<script src="dist/js/app.min.js"></script>

	<!-- AdminLTE for demo purposes -->
	<script src="dist/js/demo.js"></script>

	<script src="./js/branchmanager/diagnostics.js"></script>

</body>
</html>
