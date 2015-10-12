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

<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="./js/branchmanager/parent.js"></script>

</head>

<body>
	<!-- -----------------javascripts------------------ -->
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

		<!-- ---------------------main content--------------------------->
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa fa-bars"></i> Pages
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="adminMain.jsp">Home</a></li>
							<li><i class="icon_document_alt"></i>Parent</li>
							<li><i class="fa fa-files-o"></i>New Parent Registration</li>
						</ol>
					</div>
				</div>
				<!-- page start-->
				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<div class="form">
								<form class="form-validate form-horizontal" id="feedback_form"
									method="post" action="parentSuccess.jsp" onsubmit="registerParent();return false;">
									<div>
										<font color="red" id="message"></font>
									</div>
									<header class="panel-heading"> Account Details </header>
									<div class="panel-body">
										<div class="form-group ">
											<label for="pEmail" class="control-label col-lg-2">Email<span
												class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control" id="parentEmail" name="email"
													type="email" required />
											</div>
										</div>
										<div class="form-group ">
											<label for="pPassword" class="control-label col-lg-2">Password
												<span class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control " id="parentPassword"
													type="password" name="password" required />
											</div>
										</div>
										<div class="form-group ">
											<label for="pVerifyPassword" class="control-label col-lg-2">Verify
												Password <span class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control " id="verifyPassword"
													type="password" name="verifyPassword" required />
											</div>
										</div>
									</div>

									<header class="panel-heading"> Parent Details </header>
									<div class="panel-body">
										<div class="form-group ">
											<label for="PName" class="control-label col-lg-2">Name</label>
											<div class="col-lg-10">
												<input class="form-control " id="parentName" type="text"
													name="pName" />
											</div>
										</div>
										<div class="form-group ">
											<label for="pNric" class="control-label col-lg-2">NRIC
												<span class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control" id="parentNric" name="pNric"
													type="text" required />
											</div>
										</div>
										<div class="form-group ">
											<label for="contactNumber" class="control-label col-lg-2">ContactNumber
												<span class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control" id="contactNumber"
													name="contact" type="text" required />
											</div>
										</div>
										<div class="form-group ">
											<label for="address" class="control-label col-lg-2">Address</label>
											<div class="col-lg-10">
												<textarea class="form-control" rows="4" id="parentAddress"
													name="address" required></textarea>
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<button class="btn btn-primary" type="submit">Register</button>
												<button class="btn btn-default pull-right" type="button">Back</button>
											</div>
										</div>
									</div>
								</form>
							</div>

						</section>
					</div>
				</div>
				<!-- page end-->
			</section>
		</section>
		<!--main content end-->
	</section>
	<!-- container section end -->
</body>
</html>
