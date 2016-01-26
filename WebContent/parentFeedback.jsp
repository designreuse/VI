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

<!-- Morris chart -->
<link rel="stylesheet" href="plugins/morris/morris.css">

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
					Children Feedback<small>(Version A)</small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="collapse">
						<div class="col-md-12">
							<div class="box">
								<div class="box-body">
									<div class="col-md-12 " id="dynamicStudentList"></div>
									<!-- Small boxes (Stat box) -->
									<div class="row">
										<div class="col-lg-4 col-xs-6">
											<!-- small box -->
											<div class="small-box bg-green">
												<div class="inner">
													<h3>90<!-- <sup style="font-size: 20px">%</sup> --></h3>
													<p>Course lvl: 1</p>
													<p>Booklet lvl: 1</p>
												</div>
												<div class="icon">
													<i class="ion ion-stats-bars"></i>
												</div>
												<a href="#" class="small-box-footer">More info <i
													class="fa fa-arrow-circle-right"></i></a>
											</div>
										</div>
										<!-- ./col -->
										<div class="col-lg-4 col-xs-6">
											<!-- small box -->
											<div class="small-box bg-yellow">
												<div class="inner">
													<h3>70</h3>
													<p>Course lvl: 1</p>
													<p>Booklet lvl: 2</p>
												</div>
												<div class="icon">
													<i class="ion ion-stats-bars"></i>
												</div>
												<a href="#" class="small-box-footer">More info <i
													class="fa fa-arrow-circle-right"></i></a>
											</div>
										</div>
										<!-- ./col -->
										<div class="col-lg-4 col-xs-6">
											<!-- small box -->
											<div class="small-box bg-red">
												<div class="inner">
													<h3>30</h3>
					
													<p>Course lvl: 1</p>
													<p>Booklet lvl: 3</p>
												</div>
												<div class="icon">
													<i class="ion ion-stats-bars"></i>
												</div>
												<a href="#" class="small-box-footer">More info <i
													class="fa fa-arrow-circle-right"></i></a>
											</div>
										</div>
										<!-- ./col -->
									</div>
									<!-- /.row -->
									<!-- LINE CHART -->
							          <div class="box box-info">
							            <div class="box-header with-border">
							              <h3 class="box-title">Line Chart</h3>
							
							              <div class="box-tools pull-right">
							                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
							                </button>
							                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
							              </div>
							            </div>
							            <div class="box-body chart-responsive">
							              <div class="chart" id="line-chart" style="height: 300px;"></div>
							            </div>
							            <!-- /.box-body -->
							          </div>
							          <!-- /.box -->
									</div>
									<!-- /.box -->
									<!-- for the accordion -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>

			<section class="content">
				
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

	<script src="js/jquery.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script
		src="js/datatables/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="./js/datatables/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<script
		src="./js/datatables/datatables-responsive/js/dataTables.responsive.js"></script>

	<!-- Slimscroll -->
	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>

	<!-- FastClick -->
	<script src="plugins/fastclick/fastclick.js"></script>
	
	<!-- Morris.js charts -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<script src="plugins/morris/morris.min.js"></script>
	
	<!-- AdminLTE App -->
	<script src="dist/js/app.min.js"></script>

	<!-- AdminLTE for demo purposes -->
	<script src="dist/js/demo.js"></script>

	<script src="./js/bootbox.min.js"></script>

	<script src="./js/parent/feedback.js"></script>
</body>
</html>
