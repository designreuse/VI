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
														<label for="scheduleName">Schedule Name</label>
														<input class="form-control input-sm" id="scheduleName"
														name="scheduleName" type="email" placeholder="Schedule Name" required />
													</div>
													
													<div class="form-group">
														<label for="scheduleDesc">Schedule Description</label>
														<input class="form-control input-sm" id="scheduleDesc"
														name="scheduleDesc" type="email" placeholder="Schedule Description" required />
													</div>	
													
													<div class="form-group">
														<div class="col-lg-6">
															<label for="scheduleStartDate">Schedule Start Date</label>											
															<input class="form-control input-sm" id="scheduleStartDate"
															name="scheduleStartDate" type="date" placeholder="Start Date" required />
														</div>
														
														<div class="col-lg-6">
															<label for="scheduleStartTime">Schedule Start Time</label>	
															<input class="form-control input-sm" id="scheduleStartTime"
															name="scheduleStartTime" type="time" placeholder="Start Time" required />
														</div>
													</div>
													
													<div class="form-group">
														<div class="col-lg-6">
															<label for="scheduleEndDate">Schedule End Date</label>	
															<input class="form-control input-sm" id="scheduleEndDate"
															name="scheduleEndDate" type="date" placeholder="End Date" required />
														</div>
														<div class="col-lg-6">
															<label for="scheduleEndTime">Schedule End Time</label>	
															<input class="form-control input-sm" id="scheduleEndTime"
															name="scheduleEndTime" type="time" placeholder="End Time" required />
														</div>
													</div>
													
													<div class="form-group">
														<label for="teachercourse">Select Teacher</label>
														<select class="form-control input-sm" id="teachercourse">
														</select>
													</div>
													
													<div class="form-group">
														<label for="teachercourse">Select Classroom</label>
														<select class="form-control input-sm" id="classroom">
														</select>
													</div>
													
													<input class="btn btn-primary btn-block submit"
														type="button" value="Create Schedule"
														onclick="createSchedule();">	
											</form>
											</div>
										</div>
									</div>
								</div>
								
								<!-- for search of subjects -->
								<div class="box-group" id="accordion">
									<div class="panel box box-primary">
										<div class="box-header with-border">
											<h4 class="box-title">
												<a data-toggle="collapse" data-parent="#accordion"
													href="#collapseOne"> Filter Search by Subjects </a>
											</h4>
										</div>

										<div id="collapseOne" class="panel-collapse collapse">
											<div class="box-body">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-search"></i></span> <input type="email"
														class="form-control" placeholder="Subjects">
												</div><br>
												
												<input class="btn btn-primary submit"
														type="button" value="Search"
														>
											</div>
											
										</div>
									</div>
								</div>
								
								<!-- for search of students -->
								<div class="box-group" id="accordion">
									<div class="panel box box-primary">
										<div class="box-header with-border">
											<h4 class="box-title">
												<a data-toggle="collapse" data-parent="#accordion"
													href="#collapseTwo"> Filter Search by Students </a>
											</h4>
										</div>

										<div id="collapseTwo" class="panel-collapse collapse">
											<div class="box-body">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-search"></i></span> <input type="email"
														class="form-control" placeholder="Students">
												</div><br>
												
												<input class="btn btn-primary submit"
														type="button" value="Search"
														>
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

  <!-- Main Footer -->s
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
