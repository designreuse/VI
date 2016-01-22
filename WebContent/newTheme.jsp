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
  <link rel="stylesheet" href="dist/css/skins/skin-green.min.css">
  
	<link href="css/fullcalendar.min.css" rel="stylesheet">
	<link href='css/fullcalendar.print.css' rel='stylesheet' media='print' />

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body class="hold-transition skin-green sidebar-mini">
<div class="wrapper">
	<!-- including navBar and sideBar -->
	<%@include file="teacherNavBar.jsp"%>
	<%@include file="teacherSideBar.jsp"%>
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Student Grading
      </h1>
    </section>

    <!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="box box-success">
							<div class="box-body">
								<div class="col-md-12">
								
									<div class='box-group' id='accordion'>
									<div class='panel box box-success'>
										<div class='box-header with-border'>
											<h4 class='box-title'>
												<a data-toggle='collapse' data-parent='#accordion'
													href='#addSched'> Add Schedule </a>
											</h4>
										</div>

										<div id='addSched' class='panel-collapse collapse'>
											<div class='box-body'>
												<div class='form-group'>
													<label for='scheduleName'>Schedule Name</label>
													<input class='form-control input-sm' id='scheduleName'
														name='scheduleName' type='text' placeholder="Schedule Name" required />
												</div>
													
												<div class="form-group">
													<label for="scheduleDesc">Schedule Description</label>
													<input class="form-control input-sm" id="scheduleDesc"
														name="scheduleDesc" type="text" placeholder="Schedule Description" required />
												</div>	
											
											</div>
										</div>
									</div>
								</div>
								
								<!-- for search of subjects -->
								<div class="box-group" id="accordion">
									<div class="panel box box-success">
										<div class="box-header with-border">
											<h4 class="box-title">
												<a data-toggle="collapse" data-parent="#accordion"
													href="#collapseOne"> Filter Search by Students </a>
											</h4>
										</div>

										<div id="collapseOne" class="panel-collapse collapse">
											<div class="box-body">
												<div class="form-group">
													<label for="scheduleName">Schedule Name</label>
													<input class="form-control input-sm" id="scheduleName1"
														name="scheduleName1" type="text" placeholder="Schedule Name" required />
												</div>
													
												<div class="form-group">
													<label for="scheduleDesc">Schedule Description</label>
													<input class="form-control input-sm" id="scheduleDesc1"
														name="scheduleDesc1" type="text" placeholder="Schedule Description" required />
												</div>	
												
											</div>
											
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<input class="btn btn-primary submit"
					type="button" value="Search" onclick="searchByCourse();">
					
				</div>
			</div>

				<!-- end of displaying parents panel -->
			</section>

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

<script src="./js/branchmanager/calendar.js"></script>
</body>
</html>



