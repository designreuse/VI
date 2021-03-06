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
				<h1>New Teacher Registration</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<form class="form-validate form-horizontal" id="feedback_form"
							method="post" onsubmit="registerTeacher();return false;">
							<div>
								<font color="red" id="message"></font>
							</div>
							<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Account Details</h4>
								</div>
								<div class="box-body">

									<div class="form-group ">
										<label for="tEmail" class="control-label col-lg-2">Email<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="teacherEmail" name="email"
												type="email"
												pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"
												title="Invalid Email" required />
										</div>
									</div>

									<div class="form-group ">
										<label for="tPassword" class="control-label col-lg-2">Password
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="teacherPassword"
												type="password" name="password" required />
										</div>
									</div>
									<div class="form-group ">
										<label for="tVerifyPassword" class="control-label col-lg-2">Verify
											Password <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="verifyPassword"
												type="password" name="verifyPassword" required />
										</div>
									</div>

								</div>
							</div>

							<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Teacher Details</h4>
								</div>
								<div class="box-body">
									<div class="form-group ">
										<label for="tName" class="control-label col-lg-2">Name<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="teacherName" type="text"
												name="tName" pattern="^([a-zA-Z\s]{1,255})$"
												title="Letters only" required />
										</div>
									</div>
									<div class="form-group ">
										<label for="tNric" class="control-label col-lg-2">NRIC
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="teacherNric" name="sNric"
												type="text" pattern="^[SFTG]\d{7}[A-Z]$" title="NRIC only"
												required />
										</div>
									</div>
									<div class="form-group ">
										<label for="contactNumber" class="control-label col-lg-2">ContactNumber
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="contactNumber" name="contact"
												type="text" required />
										</div>
									</div>
									<div class="form-group ">
										<label for="address" class="control-label col-lg-2">Address
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<textarea class="form-control" rows="4" id="teacherAddress"
												name="address" required></textarea>
										</div>
									</div>
								</div>
							</div>

							<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Education Details</h4>
								</div>
								<div class="box-body">
									<div class="form-group ">
										<label for="school" class="control-label col-lg-2">School<span
											class="required">*</span></label>
										<div class="col-lg-10">
											<input class="form-control" id="teacherSchool" name="school"
												pattern="^([a-zA-Z\s]{1,255})$" title="Letters only"
												required>
										</div>
									</div>
									<div class="form-group ">
										<label for="qualification" class="control-label col-lg-2">Qualification
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="qualification"
												name="qualification" type="text"
												pattern="^([a-zA-Z\s]{1,255})$" title="Letters only"
												required />
										</div>
									</div>
									<div class="form-group ">
										<label for="year" class="control-label col-lg-2">Year
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="year" name="year" type="text"
												pattern="^\d{4}$" title="Year only" required />
										</div>
									</div>

								</div>
							</div>

							<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Courses</h4>
								</div>
								<div class="box-body">
									<div class="form-group ">
										<label for="school" class="control-label col-lg-2">Course(s)<span
											class="required">*</span></label>
										<div class="col-lg-10">

											<!-- 										<input type="checkbox" id="course" class="enable_cb" -->
											<!-- 													name="course1"">Math -->
											<!-- 										<input type="checkbox" id="course" class="enable_cb" -->
											<!-- 													name="course2">English -->
											<!-- 										<input type="checkbox" id="course" class="enable_cb" -->
											<!-- 													name="course3" >Korean -->

											<input type="checkbox" name="course" value="2" id="course">
											Math <input type="checkbox" name="course" value="1" id="course">
											English <input type="checkbox" name="course" value="3" id="course">
											Korean<br>
										</div>
									</div>
									<div class="form-group">
										<div class="col-lg-offset-2 col-lg-10">
											<button class="btn btn-primary" type="submit" id="submit">Register</button>
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
	
	<script src="./js/branchmanager/teacher.js"></script>
</body>
</html>
