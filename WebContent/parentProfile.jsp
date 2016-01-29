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
  
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  
  <!-- Skin Designs-->
  <link rel="stylesheet" href="dist/css/skins/skin-yellow.min.css">
  
	<link href="css/fullcalendar.min.css" rel="stylesheet">
	<link href='css/fullcalendar.print.css' rel='stylesheet' media='print' />

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
</head>

<body class="hold-transition skin-yellow sidebar-mini">
<div class="wrapper">
	<!-- including navBar and sideBar -->
	<%@include file="parentNavBar.jsp"%>
	<%@include file="parentSideBar.jsp"%>
  
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
	<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Your Profile
			</h1>
		</section>

    <!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-header">
							<h2 class="box-title" id="name">User Profile</h2>
							<button type="button" class="btn btn-default btn-warning btn-flat pull-right" onclick="launchEditParent()" title="Edit Parent Profile">
								<i class="glyphicon glyphicon-pencil"></i>Edit</button>
     						</div>
						<div class="box-body">
							<div class="row">
								<div class="col-md-3 col-lg-3" align="center">
									<img alt="User Pic" src="dist/img/user5-128x128.jpg" class="img-circle img-responsive">
								</div>
								
								<div class="col-md-6 col-lg-6">
									<table class="table table-user-information">
										<tbody>
											<tr>
												<td><strong>Email:</strong></td>
												<td id="email"></td>
											</tr>
											<tr>
												<td><strong>Phone Number:</strong></td>
												<td id="contact"></td>
											</tr>
											<tr>
												<td><strong>NRIC:</strong></td>
												<td id="nric"></td>
											</tr>
											<tr>
												<td><strong>Occupation:</strong></td>
												<td id="occupation"></td>
											</tr>
											<tr>
												<td><strong>Kinship:</strong></td>
												<td id=relationship></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- end of displaying parents panel -->
		</section>
		
		<!-- edit parent form -->
		<div id='editParent' class='modal fade' tabindex="-1" role='dialog'
		 aria-labelledby='myModalLabel' aria-hidden='true'>
			<div class='modal-dialog'>
				<div class='modal-content'>
					<div class='modal-header'>
						<button type='button' class='close' data-dismiss='modal'
							aria-label='Close'>
							<span aria-hidden='true'>&times;</span>
						</button>
						<h4 class='modal-title' id='myModalLabel'>Edit Parent</h4>
					</div>
					<div class='modal-body'>
						<form id='editParentForm' class='form-validate form-horizontal'>
							<div class='form-group'>
								<label for='nameEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>Name
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<input type='text' class='form-control' id='nameEdit'
										pattern="^([a-zA-Z\s]{1,255})$" title="Letters only" 
										required placeholder='parent name'>
								</div>
							</div>
							<div class='form-group'>
								<label for='emailEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>Email
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<input type='email' class='form-control' id='emailEdit'
										pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"
										title="Invalid Email" required placeholder='parent email'>
								</div>
							</div>
							<div class='form-group'>
								<label for='contactEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>Contact Number
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<input type='text' class='form-control' id='contactEdit' pattern="^[+]?\d*$"
										title="Numbers only" required placeholder='parent contact'>
								</div>
							</div>
							<div class='form-group'>
								<label for='parentNricEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>NRIC
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<input type='text' class='form-control' id='parentNricEdit' pattern="^[SFTG]\d{7}[A-Z]$"
									title="NRIC only" required placeholder='parent nric'>
								</div>
							</div>
							<div class='form-group'>
								<label for='occupationEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>Occupation
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<input type='text' class='form-control' id='occupationEdit' pattern="^([a-zA-Z\s]{1,255})$"
									title="Letters only" required placeholder='parent occupation'>
								</div>
							</div>
							<div class='form-group'>
								<label for='relationshipEdit' class='col-sm-3 col-md-3 col-lg-3 control-label'>Kinship
									<span class="required">*</span>
								</label>
								<div class='col-sm-8 col-md-8 col-lh-8'>
									<select class='form-control' name="relationship" id="relationshipEdit">
										<option value="mother">Mother</option>
										<option value="father">Father</option>
										<option value="guardian">Guardian</option>
										<option value="sibling">Sibling</option>
									</select>
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
    	<strong>Copyright &copy; 2015 <a href="exploreandlearn.com">Explore And Learn</a>.</strong> All rights reserved.
    </div>
    <!-- Default to the left -->
    Helping students help themselves.
  </footer>

</div>
<!-- ./wrapper -->

<!-- -----------------REQUIRED JS SCRIPTS--------------- -->
<!-- jQuery 2.1.4 -->
<script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>

<script src="js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/datatables/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="./js/datatables/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<script src="./js/datatables/datatables-responsive/js/dataTables.responsive.js"></script>

<!-- Slimscroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>

<!-- FastClick -->
<script src="plugins/fastclick/fastclick.js"></script>

<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>

<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>

<script src="./js/bootbox.min.js"></script>

<script src="./js/parent/setting.js"></script>
</body>
</html>
