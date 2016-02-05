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
      <h1 id="childrenName"></h1>
      <small>Monthly Feedback</small>
    </section>
    <!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10" id=>
						<button type="button" class="btn bg-orange" onclick="">English</button>
					</div>
					
				</div>
				<div class="row">
					<div class="col-md-1"></div>			
					<div class="col-md-10" id="populateFeedback">
						<div class="nav-tabs-custom">
							<ul class="nav nav-tabs">
				              <li class="active"><a href="#tab_0" data-toggle="tab" id="latest">Latest Feedback</a></li>
				              <li><a href="#tab_1" data-toggle="tab" id="second">Feedback on 15/01/2016</a></li>
				              <li><a href="#tab_2" data-toggle="tab" id="third">Feedback on 10/01/2016</a></li>
				              <li><a href="#tab_3" data-toggle="tab" id="forth">Feedback on 10/01/2016</a></li>
				            </ul>
				            
			            <div class="tab-content">
			              <div class="tab-pane active" id="tab_0">

							</div>
			              

			              <div class="tab-pane" id="tab_1">
			                <div class="row">
									<div class="col-md-5">
											<div class="small-box bg-green">
												<div class="inner">
													<h4>Results for</h4>
													<p>Course Level: 13<br>
														Booklet Level: 19</p>
												</div>
												<div class="icon">
													<i class="ion ion-stats-bars"></i>
												</div>
												<div class="small-box-footer"> <i class="fa fa-arrow-circle-right"></i></div>
											</div>
									</div>
									
									<div class="col-md-7">
										<dl class="dl">
										
											<dt>Teacher</dt>
							                <dd>Richard</dd><br>
										
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

			              <div class="tab-pane" id="tab_2">
			                <div class="row" id = "feedbackThree">
									<div class="col-md-5">
											<div class="small-box bg-green">
												<div class="inner">
													<h4>Results for</h4>
													<p>Course Level: 13<br>
														Booklet Level: 18</p>
												</div>
												<div class="icon">
													<i class="ion ion-stats-bars"></i>
												</div>
												<div class="small-box-footer"> <i class="fa fa-arrow-circle-right"></i></div>
											</div>
									</div>
									
									<div class="col-md-7">
										<dl class="dl">
										
											<dt>Teacher</dt>
							                <dd>Richard</dd><br>
											
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
			              
			              <div class="tab-pane" id="tab_3">
			                <div class="row" id="feedbackFour">
									<div class="col-md-5">
											<div class="small-box bg-green">
												<div class="inner">
													<h4>Results for</h4>
													<p>Course Level: 13<br>
														Booklet Level: 19</p>
												</div>
												<div class="icon">
													<i class="ion ion-stats-bars"></i>
												</div>
												<div class="small-box-footer"> <i class="fa fa-arrow-circle-right"></i></div>
											</div>
									</div>
									
									<div class="col-md-7">
										<dl class="dl">
										
											<dt>Teacher</dt>
							                <dd>Richard</dd><br>
										
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

<script src="./js/parent/feedback.js"></script>
</body>
</html>
