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
  
  <!-- daterange picker -->
  <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker-bs3.css">
  
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
        Admin Page
        <small>Welcome to the admin page</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
        <li class="active">Here</li>
      </ol>
    </section>

    <!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-8">
						<div class="box box-primary">
							<div class="box-body">
								<div id="calendar"></div>
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<div class="row">
							<div class = "col-md-12">							
								<div class="box box-solid">
									<div class="box-header with-border">
										<h4 class="box-title">Schedule Details</h4>
									</div>
									<div class="box-body">
										<div class="row">
											<div class="col-md-12" id="details">
												Select any schedule at the side to view details
											</div>
										</div>
									</div>
								</div>
								
								<div class="box box-solid">
								<div class="box-header with-border">
									<h4 class="box-title">Action Items</h4>
								</div>
								
								<div class="box-body">
								
								<!-- for adding of schedule -->
									<div class="box-group" id="accordion">
										<div class="panel box box-primary">
											<div class="box-header with-border">
												<h4 class="box-title">
													<a data-toggle="collapse" data-parent="#accordion"
														href="#addSched"> Add Schedule </a>
												</h4>
											</div>
	
											<div id="addSched" class="panel-collapse collapse">
												<div class="box-body">
													<form>
														<div class="form-group">
															<label for="scheduleName">Name</label>
															<input class="form-control input-sm" id="scheduleName"
															name="scheduleName" type="text" placeholder="Schedule Name" required />
														</div>
														
														<div class="form-group">
															<label for="scheduleDesc">Description</label>
															<input class="form-control input-sm" id="scheduleDesc"
															name="scheduleDesc" type="text" placeholder="Schedule Description" required />
														</div>	
														
														<div class="form-group">
															<label for="scheduleStartDate">Schedule Date Range</label>											
															<input class="form-control pull-right input-sm" id="scheduleRange"
																name="scheduleRange" type="text" placeholder="Start Date" required />
														</div>
														
														
														<div class="form-group">
															<label for="teachercourse">Select Teacher</label>
															<select class="form-control input-sm" id="teacherDDL" onChange="generateCourseOption(this);">
															</select>
														</div>
														
														<div class="form-group">
															<label for="teachercourse">Select Course</label>
															<select class="form-control input-sm" id="courseDDL" disabled="disabled">
															</select>
														</div>
														
														<input class="btn btn-primary btn-block submit"
															type="button" value="Create Schedule"
															onclick="testing();">	
												</form>
												</div>
											</div>
										</div>
									</div>
								
								<!-- for search of teacher -->
									<div class="box-group" id="accordion">
										<div class="panel box box-primary">
											<div class="box-header with-border">
												<h4 class="box-title">
													<a data-toggle="collapse" data-parent="#accordion"
														href="#collapseThree"> Search Teacher's Schedule </a>
												</h4>
											</div>
	
											<div id="collapseThree" class="panel-collapse collapse">
												<div class="box-body">
													<div class="input-group">
														<span class="input-group-addon"><i
															class="fa fa-search"></i></span> <input type="email"
															class="form-control" placeholder="Teacher Name">
													</div><br>
													
													<input class="btn btn-primary submit"
															type="button" value="Search">
												</div>
												
											</div>
										</div>
									</div>
								
								<!-- for rescheduling students-->
									<div class="box-group" id="accordion">
										<div class="panel box box-primary">
											<div class="box-header with-border">
												<h4 class="box-title">
													<a data-toggle="collapse" data-parent="#accordion"
														href="#collapseTwo"> Reschedule Student </a>
												</h4>
											</div>
	
											<div id="collapseTwo" class="panel-collapse collapse">
												<div class="box-body">
													<div class="row">
														<div class="col-md-12">
														
															<div class="form-group">
																<div class="input-group">
																	<span class="input-group-addon"><i class="fa fa-male"></i></span> 
																	<input type="text" class="form-control" placeholder="Student Name" id="reschedStudentName">
																</div>
															</div>
															
															<div class="form-group">
																<div class="input-group">
																	<span class="input-group-addon"><i class="fa fa-male"></i></span> 
																	<input type="text" class="form-control" placeholder="Student NRIC" onChange="getReschedulingStudentId(this.value);" id="reschedStudentNric">
																</div>
															</div>
															
															<div class="form-group">
																<div class="input-group">
																	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
																	<select class="form-control input-sm" id="studentCourseDDl" disabled="disabled" onChange="getCourseValue()"></select>
																</div>
															</div>
														
															<div class="form-group">
																<div class="input-group">
																	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
																	<select class="form-control input-sm" id="attendanceDDL" disabled="disabled"></select>
																</div>
															</div>
															
															
															<div class="form-group">
																<input class="btn btn-primary submit" type="button" onclick="redirectReschedule()" value="Search">
															</div>
														</div>
													</div>
												</div>
												
											</div>
										</div>
									</div>

								<!-- for changing student's class -->
									<div class="box-group" id="accordion">
										<div class="panel box box-primary">
											<div class="box-header with-border">
												<h4 class="box-title">
													<a data-toggle="collapse" data-parent="#accordion"
														href="#collapseFour"> Search Student's Schedules</a>
												</h4>
											</div>
	
											<div id="collapseFour" class="panel-collapse collapse">
												<div class="box-body">
													<div class="row"><div class="col-md-12">
														<div class="input-group">
															<span class="input-group-addon"><i class="fa fa-search"></i></span> 
															<input type="email" class="form-control" placeholder="Student NRIC" id="NRIC">
														</div>
													</div></div>
													<br>
													
													<div class="row"><div class="col-md-12">
														<input class="btn btn-primary submit" type="button" value="Search" onclick="searchStudent();">
													</div></div>
												</div>
												
											</div>
										</div>
									</div>								
								</div>
								
								</div>	
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

<script src="./js/branchmanager/calendar.js"></script>  

</body>
</html>
