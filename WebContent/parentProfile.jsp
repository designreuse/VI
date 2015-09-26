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
<link href="css/jquery.dynatable.css" rel="stylesheet" />

<!-- -------------------------------javascripts----------------------- -->
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="./js/pages/parent.js"></script>
	
</head>

<body>
<!-- -----------------javascripts------------------ -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.dynatable.js"></script>
	<script src="js/datatables/datatables/media/js/jquery.dataTables.min.js"></script>
	<!-- nice scroll -->
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<!--custome script for all page-->
	<script src="js/scripts.js"></script>

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
							<li><i class="fa fa-home"></i><a href="adminMain.jsp">Home</a></li>
						</ol>
					</div>
				</div>
				
				<!-- -------------------page start-------------------------->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
						<div class="panel-heading">View All Parents</div>
							<div class="panel-body">
								<table id="parentTable" class="table table-bordered">
									<thead>
										<tr>
											<th>Parent ID</th>
											<th>Remark</th>
											<th>Name</th>
											<th>Contact</th>
											<th>Email</th>
											<th>Address</th>
											<th>Create Date</th>
											<th>ObjStatus</th>
										</tr>
									</thead>
<!-- 									<tbody id='parents'> -->
<!-- 										<tr class="prototype"> -->
<!-- 											<td><label for="parentId"></label></td> -->
<!-- 											<td><label for="name"></label></td> -->
<!-- 											<td><label for="contact"></label></td> -->
<!-- 											<td><label for="email"></label></td> -->
<!-- 											<td><label for="address"></label></td> -->
<!-- 											<td><label for="createDate"></label></td> -->
<!-- 											<td><label for="options">pls work!</label></td> -->
<!-- 										</tr> -->
<!-- 									</tbody> -->
								</table>
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
