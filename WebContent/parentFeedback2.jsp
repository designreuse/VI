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
	<section class="content-header">
      <h1>
        Children Feedback
        <small>(Version A)</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="parentFeedback"><i class="fa fa-dashboard"></i> Return to Version A</a></li>
      </ol>
    </section>
    <!-- Main content -->
			<section class="content">
				<br>
				<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-3">
					<div class="box box-warning">
			            <div class="box-body box-profile">
			              <img class="profile-user-img img-responsive img-circle" src="dist/img/avatar2.png" alt="User profile picture">
			
			              <h3 class="profile-username text-center">Carrie</h3>
			
			              <p class="text-muted text-center">Current Points: 250</p>
			
			              <ul class="list-group list-group-unbordered">
			                <li class="list-group-item">
			                  <b>Subject</b> <a class="pull-right">Mathematics</a>
			                </li>
			                <li class="list-group-item">
			                  <b>Current Course Level</b> <a class="pull-right">13</a>
			                </li>
			                <li class="list-group-item">
			                  <b>Current Booklet Level</b> <a class="pull-right">20</a>
			                </li>
			              </ul>
			            </div>
			         </div>
			         </div>
				
				
					<div class="col-md-7">
						<div class="box box-warning">
						<div class="box-body">
							<div class="row">
									<div class="col-lg-4 col-xs-6">
										<div class="small-box bg-green">
											<div class="inner">
												<h4><strong>Latest Feedback on 22/1/2015</strong><br></h4>
												<p>Course level: 13<br>Booklet Level: 20</p>
											</div>
											<div class="icon"><i class="ion ion-stats-bars"></i></div>
											<a data-toggle="collapse" class="small-box-footer" href="#latest">More info <i class="fa fa-arrow-circle-right"></i></a></div>
										</div>
										
										<div class="col-lg-4 col-xs-6">
											<div class="small-box bg-yellow">
												<div class="inner">
													<h4><strong>Feedback on 15/01/2015</strong></h4>
													<p>Course level: 13<br>Booklet Level: 19 3</p>
												</div>
												<div class="icon">
													<i class="ion ion-stats-bars"></i>
												</div>
												<a data-toggle="collapse" class="small-box-footer" href="#latest2">More info <i class="fa fa-arrow-circle-right"></i></a>
											</div>
										</div>
										

										<div class="col-lg-4 col-xs-6">
											<!-- small box -->
											<div class="small-box bg-red">
												<div class="inner">
													<h4><strong>Feedback on 10/1/2016</strong></h4>
													<p>Course level: 13<br>Booklet lvl: 18</p>
												</div>
												<div class="icon">
													<i class="ion ion-stats-bars"></i>
												</div>
												<a data-toggle="collapse" class="small-box-footer" href="#latest3">More info <i class="fa fa-arrow-circle-right"></i></a>
											</div>
										</div>
										
									</div>
								</div>
							</div>
							
						<div class="row">
						<div class="col-lg-12">
							<div id = "latest" class = "panel-collapse collapse">
								<div class="panel box box-warning">
								<div class="box-header with-border" id="name"><h3 class="box-title">Latest Feedback</h3></div>
									<div class="box-body">
										<div class="row">
											<div class="col-lg-12">	
										<dl class="dl">
							                <dt>Date</dt>
							                <dd>22/01/2016</dd><br>
							                
							                <dt>Total booklet score</dt>
							                <dd>39 out of 40</dd><br>
							                
							                <dt>Total points</dt>
							                <dd>250</dd><br>
							                
							                <dt>Feedback</dt>
							                <dd>A great improvement of what she has done for her work today! 
							                </dd>
							              </dl>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>	
						</div>
						
						<div class="row">
						<div class="col-lg-12">
							<div id = "latest2" class = "panel-collapse collapse">
								<div class="panel box box-warning">
								<div class="box-header with-border" id="name"><h3 class="box-title">Latest Feedback</h3></div>
									<div class="box-body">
										<div class="row">
											<div class="col-lg-12">	
												<dl class="dl">
							                <dt>Date</dt>
							                <dd>15/01/2016</dd><br>
							                
							                <dt>Total booklet score</dt>
							                <dd>25 out of 40</dd><br>
							                
							                <dt>Total points</dt>
							                <dd>225</dd><br>
							                
							                <dt>Feedback</dt>
							                <dd>Talkative and distracted easily. Needs more focus, Carrie! 
							                </dd>
							              </dl>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>	
						</div>		
						
						<div class="row">
						<div class="col-lg-12">
							<div id = "latest3" class = "panel-collapse collapse">
								<div class="panel box box-warning">
								<div class="box-header with-border" id="name"><h3 class="box-title">Latest Feedback</h3></div>
									<div class="box-body">
										<div class="row">
											<div class="col-lg-12">	
											<dl class="dl">
							                <dt>Date</dt>
							                <dd>10/01/2016</dd><br>
							                
							                <dt>Total booklet score</dt>
							                <dd>25 out of 40</dd><br>
							                
							                <dt>Total points</dt>
							                <dd>220</dd><br>
							                
							                <dt>Feedback</dt>
							                <dd>Talkative and distracted easily. Needs stronger concepts in fractions!
							                </dd>
							              </dl>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>	
						</div>	
					</div>
					
				<div class="col-md-1"></div>
				</div>


				<!-- end of displaying parents panel -->
			</section>

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

<script src="./js/parent/parent.js"></script>
</body>
</html>
