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

<!-- daterange picker -->
<link rel="stylesheet"
	href="plugins/daterangepicker/daterangepicker-bs3.css">

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
				<h1>New Student Registration</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<form class="form-validate form-horizontal" id="register_form"
							method="post" onsubmit="registerStudent();return false;">
							<div>
								<font color="red" id="message"></font>
							</div>
							<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Parent Details</h4>
								</div>
								<div class="box-body">
									<div class="form-group ">
										<label for="pName" class="control-label col-lg-2">Name<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="parentName" type="text"
												name="pName" pattern="^([a-zA-Z\s]{1,255})$"
												title="Letters only" required />
										</div>
									</div>
									<div class="form-group ">
										<label for="pNric" class="control-label col-lg-2">NRIC
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="parentNric" name="pNric"
												type="text" pattern="^[SFTG]\d{7}[A-Z]$" title="NRIC only"
												required onChange="valid(this.form)" />
										</div>
									</div>
								</div>
							</div>
							<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Student Details</h4>
								</div>
								<div class="box-body">
									<div class="form-group ">
										<label for="coursesEnrolled" class="control-label col-lg-2">Courses
											Enrolled<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input type="checkbox" id="course" name="course" value="Math" required>Math 
											<input type="checkbox" id="course" name="course" value="English">English 
											<input type="checkbox" id="course" name="course" value="Korean">Korean
										</div>
									</div>
									<div class="form-group ">
										<label for="gender" class="control-label col-lg-2">Taken
											Diagnostic<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input type="radio" id="diagnostic" name="diagnostic"
												value="0" required>No <input type="radio"
												id="diagnostic" name="diagnostic" value="1">Yes
										</div>
									</div>

									<div class="form-group ">
										<label for="sName" class="control-label col-lg-2">Name<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="studentName" type="text"
												name="sName" pattern="^([a-zA-Z\s]{1,255})$"
												title="Letters only" required />
										</div>
									</div>
									<div class="form-group ">
										<label for="sName" class="control-label col-lg-2">NRIC<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="studentNric" type="text"
												name="sName" pattern="^[SFTG]\d{7}[A-Z]$" title="NRIC only"
												required />
										</div>
									</div>
									<!-- add in gender -->
									<div class="form-group ">
										<label for="gender" class="control-label col-lg-2">Gender<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input type="radio" id="gender" name="gender" value="male"
												required>Male <input type="radio" id="gender"
												name="gender" value="female">Female
										</div>
									</div>
									<div class="form-group">
										<label for="birthdate" class="control-label col-lg-2">Birth
											Date <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<!-- <input class="form-control" id="birthDate" name="birthdate"
												type="date" pattern="/^(\d{1,2})\/(\d{1,2})\/(\d{4})$/"
											
												place="Birthdate in the format dd/mm/yyyy" required /> -->
											<input class="form-control pull-right input-sm"
												id="birthDate" name="birthDate" type="text"
												placeholder="dd/mm/yyyy" required />
										</div>
									</div>

									<div class="form-group ">
										<label for="homeContact" class="control-label col-lg-2">Home
											Contact <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="homeContact"
												name="homeContact" type="text" pattern="[0-9]{8}" required />
										</div>
									</div>
									<div class="form-group ">
										<label for="emergencyContact" class="control-label col-lg-2">Emergency
											Contact <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="emergencyContact"
												name="emergencyContact" type="text" pattern="[0-9]{8}"
												required />
										</div>
									</div>
									<div class="form-group ">
										<label for="address" class="control-label col-lg-2">Address
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<textarea class="form-control" rows="4" id="studentAddress"
												name="address" required></textarea>
										</div>
									</div>
									<div class="form-group ">
										<label for="postal" class="control-label col-lg-2">Postal
											Code <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="postalCode" name="postalCode"
												type="text" pattern="[0-9]{6}" required />
										</div>
									</div>
								</div>
							</div>
							<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Student Education Details</h4>
								</div>
								<div class="box-body">
									<div class="form-group ">
										<label for="sName" class="control-label col-lg-2">School
											Name <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="schoolName" name="schoolName"
												type="text" required />
										</div>
									</div>
								</div>
								<div class="box-body">
									<div class="form-group ">
										<label for="level" class="control-label col-lg-2">School
											Level<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<!-- 											<input class="form-control" id="schoolLevel"
												name="schoolLevel" type="text" required /> -->

											<select class="form-control" id="schoolLevel"
												name="schoolLevel" required>
												<option value="Nursery">Nursery</option>
												<option value="Kindergarten 1">Kindergarten 1</option>
												<option value="Kindergarten 2">Kindergarten 2</option>
												<option value="Primary 1">Primary 1</option>
												<option value="Primary 2">Primary 2</option>
												<option value="Primary 3">Primary 3</option>
												<option value="Primary 4">Primary 4</option>
												<option value="Primary 5">Primary 5</option>
												<option value="Primary 6">Primary 6</option>
											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Marketing Details</h4>
								</div>
								<div class="box-body">
									<div class="form-group ">
										<label for="level" class="control-label col-lg-2">Marketing Campaign
										</label>
										<div class="col-lg-10">

											<select class="form-control" id="campaign" name="campaign">
											</select>
										</div>
									</div>
									<div class="form-group">
										<div class="col-lg-offset-2 col-lg-10">
											<button class="btn btn-primary" type="submit">Save</button>
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

	<!-- fullCalender.io -->
	<script src="./js/fullcalendar/lib/moment.min.js"></script>
	<script src="./js/fullcalendar/fullcalendar.min.js"></script>

	<!-- date-range-picker -->
	<script src="plugins/daterangepicker/daterangepicker.js"></script>

	<!-- Slimscroll -->
	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>

	<!-- FastClick -->
	<script src="plugins/fastclick/fastclick.js"></script>

	<!-- AdminLTE App -->
	<script src="dist/js/app.min.js"></script>

	<!-- AdminLTE for demo purposes -->
	<script src="dist/js/demo.js"></script>
	
	<script src="./js/bootbox.min.js"></script>	
	
	<script src=".js/form-validation-script.js"></script>

	<script src="./js/branchmanager/student.js"></script>
</body>
</html>
