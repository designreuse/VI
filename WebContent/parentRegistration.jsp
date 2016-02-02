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
				<h1>New Parent Registration</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<form class="form-validate form-horizontal" id="register_form"
							method="post" action="parentSuccess.jsp"
							onsubmit="registerParent();return false;">
							<div>
								<font color="red" id="message"></font>
							</div>

							<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Account Details</h4>
								</div>
								<div class="box-body">
									<div class="form-group">
										<label for="pEmail" class="control-label col-lg-2">Email<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="parentEmail" name="email"
												type="email"
												pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"
												title="Invalid Email" required />
										</div>
									</div>
									<div class="form-group ">
										<label for="pPassword" class="control-label col-lg-2">Password
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="parentPassword"
												type="password" name="password" required />
										</div>
									</div>
									<div class="form-group ">
										<label for="pVerifyPassword" class="control-label col-lg-2">Verify
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
									<h4 class="box-title">Parent Details</h4>
								</div>
								<div class="box-body">
									<div class="form-group ">
										<label for="PName" class="control-label col-lg-2">Name
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="parentName" type="text"
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
												required />
										</div>
									</div>
									<div class="form-group ">
										<label for="pRelationship" class="control-label col-lg-2">Relationship
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<select class="form-control" name="relationship" id="relationship">
												<option value="Mother">Mother</option>
												<option value="Father">Father</option>
												<option value="Guardian">Guardian</option>
												<option value="Sibling">Sibling</option>
											</select>
										</div>
									</div>
									<!-- added in relationship -->

									<div class="form-group ">
										<label for="pOccupation" class="control-label col-lg-2">Occupation
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" type="text" name="pOccupation"
												id="occupation" required/>
										</div>
									</div>
									<!-- added in occupation -->

									<div class="form-group ">
										<label for="contactNumber" class="control-label col-lg-2">Mobile
											Phone <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input type='text' class='form-control' id="contactNumber" pattern="[0-9]{8}"
												title="Singapore local numbers only, eg.xxxx xxxx" required placeholder='parent contact'>
										</div>
									</div>
									
									<!--  removed address -->
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
	
<script src="./js/branchmanager/parent.js"></script>

</body>
</html>
