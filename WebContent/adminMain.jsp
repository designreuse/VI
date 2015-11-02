<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="img/favicon.png">

<title>Explore and Learn Pte Ltd - Main Page</title>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->

<!-- ----------------------Bootstrap CSS & Theme----------------------------- -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.css" rel="stylesheet">

<!-- ---------------------External CSS: Fonts and Icons ----------------------- -->
<!-- Fonts and Icons -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />

<!-- -------------------External CSS: Custom styles----------------------------- -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />

<!-- ----------------------FullCalender's CSS----------------------------- -->
<link href="css/fullcalendar.min.css" rel="stylesheet">
<link href='css/fullcalendar.print.css' rel='stylesheet' media='print' />

<!-- -----------------javascripts------------------ -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
	
<!-- ---------------------nice scroll----------- -->
<script src="js/jquery.scrollTo.min.js"></script>
<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	
<!-- ------------------custome script for all page------------------->
<script src="js/scripts.js"></script>
	
<!-- -------------------------------fullcalendar javascripts----------------------- -->
<script src='./js/fullcalendar/lib/moment.min.js'></script>
<script src="./js/fullcalendar/fullcalendar.min.js"></script>
	
<!-- -------------------------------fullcalendar javascripts----------------------- -->
<script src="./js/bootbox.min.js"></script>	
</head>

<body>
	<script src="./js/branchmanager/calendar.js"></script>

	<!------- container section start------------ -->
	<section id="container" class="">
	
		<!--------- including navBar & sideBar------------ -->
		<%@include file="navBar.jsp"%>
		<%@include file="sideBar.jsp"%>
		
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa fa-bars"></i> Pages
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="adminMain.jsp">Branch Schedule</a></li>
						</ol>
					</div>
				</div>
				
				<!-- -------------------page start-------------------------->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">Branch Schedule</div>
							<div class="panel-body">

								<div class="row">
									<div class="col-lg-10">
										<div id='calendar'></div>
									</div>
									<div class="col-lg-2">
										<div class="panel panel-default">
											<div class="panel-heading">Action Items</div>
											<div class="panel-body">
												<button type="button" class="btn btn-primary view"
													data-toggle="collapse" data-target="#addSchedule">
													Add Schedule</button>
											</div>
										</div>
									</div>
								</div>
								
								<hr></hr>
								
								<div class="row">
									<div class="col-lg-12">
										<div class="collapse" id="addSchedule">
											<div class="panel panel-info">
												<div class="panel-heading">Create Schedule</div>
												<div class="panel-body">

													<div class="row">
														<div class="col-lg-12">
															<form>
																<div class="form-group">
																	<label for="scheduleName" class="control-label col-lg-2">Schedule Name</label>
																	
																	<div class="col-lg-10">
																		<input class="form-control" id="scheduleName" name="scheduleName"
																			type="email" required />
																	</div>
																</div>
																
																<div class="form-group">
																	<label for="scheduleDesc" class="control-label col-lg-2">Description</label>
																	
																	<div class="col-lg-10">
																		<input class="form-control" id="scheduleDesc" name="scheduleDesc"
																			type="email" required />
																	</div>
																</div>
																
																<div class="form-group">
																	<label for="scheduleStartDate" class="control-label col-lg-2">Start Date</label>
																	
																	<div class="col-lg-10">
																		<input class="form-control" id="scheduleStartDate" name="scheduleStartDate"
																			type="date" required />
																	</div>
																</div>
																
																<div class="form-group">
																	<label for="scheduleStartTime" class="control-label col-lg-2">Start Time
																	</label>
																	
																	<div class="col-lg-10">
																		<input class="form-control" id="scheduleStartTime" name="scheduleStartTime"
																			type="time" required />
																	</div>
																</div>
																
																<div class="form-group">
																	<label for="scheduleEndDate" class="control-label col-lg-2">End Date
																	</label>
																	
																	<div class="col-lg-10">
																		<input class="form-control" id="scheduleEndDate" name="scheduleEndDate"
																			type="date" required />
																	</div>
																</div>
																
																<div class="form-group">
																	<label for="scheduleStartDate" class="control-label col-lg-2">End Time
																	</label>
																	
																	<div class="col-lg-10">
																		<input class="form-control" id="scheduleEndTime" name="scheduleEndTime"
																			type="time" required />
																	</div>
																</div>
																
																<div class="form-group">
																	<label for="teacherSelect" class="control-label col-lg-2">Teacher
																	</label>
																	
																	<div class="col-lg-10">
																		 <select class="form-control m-bot15" id="teachercourse">
                                         								 </select>
																	</div>
																</div>
																
																<div class="form-group">
																	<label for="classroomSelect" class="control-label col-lg-2">Classroom
																	</label>
																	
																	<div class="col-lg-10">
																		 <select class="form-control m-bot15" id="classroom">
                                         								 </select>
																	</div>
																</div>
																
																<div class="row">
																	<div class="col-lg-10"></div>
																	<div class="col-lg-2">
																		<input class="btn btn-primary btn-lg btn-block submit" type="button" value="Create Schedule" onclick="createSchedule();">
																	</div>
																</div>

															</form>
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
				</div>



				<!-- /.panel-heading -->
				<!-- --------------------page end-------------------------------->
				
			</section>
		</section>
		<!--main content end-->
	</section>
	<!-- container section end -->
</body>
</html>
