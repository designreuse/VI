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
				<h1>Marketing Campaign</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<form class="form-validate form-horizontal myform"
							id="register_form" method="post"
							onsubmit="getLatLong(createMarketingCampaign);return false;">

							<div>
								<font color="red" id="message"></font>
							</div>

							<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Create Marketing Campaign</h4>
								</div>
								<div class="box-body">
									<div class="form-group">
										<label for="sName" class="control-label col-lg-2">Name<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="name" type="text"
												name="sName" required />
										</div>
									</div>
									<div class="form-group ">
										<label for="startDate" class="control-label col-lg-2">Start
											Date <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<div id="course_points">
												<input class="form-control pull-right input-sm"
													id="startDate" name="startDate" type="text"
													placeholder="dd/mm/yyyy" required />
											</div>
										</div>
									</div>


									<div class="form-group">
										<label for="endDate" class="control-label col-lg-2">End
											Date<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control pull-right input-sm" id="endDate"
												name="endDate" type="text" placeholder="dd/mm/yyyy" required />
										</div>
									</div>

									<div class="form-group">
										<label for="type" class="control-label col-lg-2">Type<span
											class="required">*</span>
										</label>
										<div class="col-lg-10">
											<select class="form-control" id="type" name="type" required>
												<option value="truckShow">Truck Show</option>
												<option value="email">Email</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label for="address" class="control-label col-lg-2">Address
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<textarea class="form-control " rows="4" id="address"
												name="address"/></textarea>
										</div>
									</div>


									<div class="form-group">
										<label for="postalCode" class="control-label col-lg-2">Postal
											Code<span class="required"></span>
										</label>
										<div class="col-lg-10">
											<input class="form-control " id="postalCode" type="text"
												name="postalCode" pattern="^[0-9]+$" title="Numbers only" />
										</div>
									</div>

									<div class="form-group">
										<div class="col-lg-offset-2 col-lg-10">
											<button class="btn btn-primary" type="submit">Submit</button>
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

	<!-- date-range-picker -->
	<script src="./js/fullcalendar/lib/moment.min.js"></script>
	<script src="plugins/daterangepicker/daterangepicker.js"></script>

	<!-- Slimscroll -->
	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>

	<!-- FastClick -->
	<script src="plugins/fastclick/fastclick.js"></script>

	<!-- AdminLTE App -->
	<script src="dist/js/app.min.js"></script>

	<!-- AdminLTE for demo purposes -->
	<script src="dist/js/demo.js"></script>

	<script src="./js/branchmanager/campaign.js"></script>

</body>
</html>
