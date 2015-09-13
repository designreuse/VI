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

</head>

<body>

	<!-- javascripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
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
							<i class="fa fa fa-bars"></i> Parents Registration
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="adminMain.jsp">Home</a></li>
							<li><i class="icon_document_alt"></i>Parent</li>
							<li><i class="fa fa-files-o"></i>New Parent Registration</li>
						</ol>
					</div>
				</div>
				<!-- page start-->
				Parent's Registration Successful!
				<!-- page end-->
			</section>
		</section>
		<!--main content end-->
	</section>
	<!-- container section end -->
</body>
</html>
