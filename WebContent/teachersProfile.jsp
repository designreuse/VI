<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Explore and Learn | Admin Portal</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	
	<!-- Bootstrap 3.3.5 -->
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	
	<!-- Font Awesome -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	
	<!-- Ionicons -->
	<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	
	<!-- datatables css -->
	<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.10/css/dataTables.bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.0.0/css/responsive.bootstrap.min.css">
	
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
			<h1>
				Teacher List
			</h1>
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
							<div class="row">
								<div class="col-md-12">
									<table id="teacherTable" class="table table-striped table-bordered dt-responsive nowrap" width="100%">
										<thead>
											<tr>
												<th>Teacher ID</th>
												<th>Name</th>
												<th>Contact</th>
												<th>Email</th>
												<th>Address</th>
												<th>Nric</th>
												<th>Qualification</th>
												<th class="all">Options</th>
											</tr>
										</thead>
										<tbody id="teachers">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		
		<!-- edit teacher form -->
		<div id='editTeacher' class='modal fade' tabindex="-1" role='dialog'
		 aria-labelledby='myModalLabel' aria-hidden='true'>
			<div class='modal-dialog'>
				<div class='modal-content'>
					<div class='modal-header'>
						<button type='button' class='close' data-dismiss='modal'
							aria-label='Close'>
							<span aria-hidden='true'>&times;</span>
						</button>
						<h4 class='modal-title' id='myModalLabel'>Edit Teacher</h4>
					</div>
					<div class='modal-body'>
						<form id='editTeacherForm' class='form-validate form-horizontal'>
							<div class='form-group'>
								<label for='nameEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>Name
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<input type='text' class='form-control' id='nameEdit' pattern="^([a-zA-Z\s]{1,255})$"
										title="Letters only" required placeholder='teacher name'>
								</div>
							</div>
							<div class='form-group'>
								<label for='contactEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>Contact Number
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<input type='text' class='form-control' id='contactEdit' pattern="^[+]?\d*$"
										title="Numbers only" required placeholder='teacher contact'>
								</div>
							</div>
							<div class='form-group'>
								<label for='emailEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>Email
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<input type='email' class='form-control' id='emailEdit'
										pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"
										title="Invalid Email" required placeholder='teacher email'>
								</div>
							</div>
							<div class='form-group'>
								<label for='addressEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>Address
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<input type='text' class='form-control' id='addressEdit'
									title="Letters only" required placeholder='teacher address'>
								</div>
							</div>
							<div class='form-group'>
								<label for='teacherNricEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>NRIC
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<input type='text' class='form-control' id='teacherNricEdit' pattern="^[SFTG]\d{7}[A-Z]$"
									title="NRIC only" required placeholder='teacher nric'>
								</div>
							</div>
							<div class='form-group'>
								<label for='qualificationEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>Qualification
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<input type='text' class='form-control' id='qualificationEdit'
									title="Letters only" required placeholder='teacher qualification'>
								</div>
							</div>
							<!--  upload picture -->
<!-- 							<div class="form-group col-md-6"> -->
<!-- 								<label for="imageEdit" -->
<!-- 									class="col-sm-3 col-md-3 col-lg-3 control-label">dd -->
<!-- 									(Image)</label> -->
<!-- 								<div class="col-sm-6 col-md-6 col-lg-6"> -->
<!-- 									<label for="imageEditUpload"> <img id="imageEditImg" -->
<!-- 										class="img-responsive img-rounded clickable" alt="image" -->
<!-- 										src="/KP/assets/img/default.jpg"> -->
<!-- 									</label> -->
<!-- 									<div> -->
<!-- 										<small>dsds</small> <small>(Click picture to upload)</small> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<input type="hidden" id="imageEdit" -->
<!-- 									value="/KP/assets/img/default.jpg" class='form-control sr-only'> -->
<!-- 							</div> -->
							<div class="modal-footer">
								<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-warning">Save</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</div>

  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="pull-right hidden-xs">
      Anything you want
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2015 <a href="#">Company</a>.</strong> All rights reserved.
  </footer>

</div>
<!-- ./wrapper -->

<!-- -----------------REQUIRED JS SCRIPTS--------------- -->
<!-- jQuery 2.1.4 -->
<script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>

<script src="bootstrap/js/bootstrap.min.js"></script>

<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.0.0/js/dataTables.responsive.js"></script>
<script src="https://cdn.datatables.net/responsive/2.0.0/js/responsive.bootstrap.min.js"></script>


<!-- Slimscroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>

<!-- FastClick -->
<script src="plugins/fastclick/fastclick.js"></script>

<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>

<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>

<script src="./js/bootbox.min.js"></script>

<script src="./js/branchmanager/teacher.js"></script>
</body>
</html>
