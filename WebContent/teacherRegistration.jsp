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
<script src="./js/branchmanager/teacher.js"></script>

<!-- yl test -->
<!-- <script type="text/javascript"> -->
<!-- // var counter = 1; -->
<!-- // var limit = 3; -->
<!-- // function addInput(divName){ -->
<!-- //      if (counter == limit)  { -->
<!-- //           alert("You have reached the limit of adding " + counter + " inputs"); -->
<!-- //      } -->
<!-- //      else { -->
<!-- //           var newdiv = document.createElement('div'); -->
<!-- //           newdiv.innerHTML = "Entry " + (counter + 1) + "<br><div class='form-group '><label for='year' class='control-label col-lg-2'>Year<span class='required'>*</span></label><div class='col-lg-10'><input class='form-control' id='year' name='year'type='text' required /></div></div>"; -->
<!-- //           document.getElementById(divName).appendChild(newdiv); -->
<!-- //           counter++; -->
<!-- //      } -->
<!-- }</script> -->

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
							<li><i class="icon_document_alt"></i>Teacher</li>
							<li><i class="fa fa-files-o"></i>New Teacher Registration</li>
						</ol>
					</div>
				</div>
				<!-- page start-->
				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<div class="form">
								<form class="form-validate form-horizontal" id="feedback_form"
									method="post"
									onsubmit="registerTeacher();return false;">
									<div>
										<font color="red" id="message"></font>
									</div>
									<header class="panel-heading"> Account Details </header>
									<div class="panel-body">
										<div class="form-group ">
											<label for="tEmail" class="control-label col-lg-2">Email<span
												class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control" id="teacherEmail" name="email"
													type="email" required />
											</div>
										</div>
										<div class="form-group ">
											<label for="tPassword" class="control-label col-lg-2">Password
												<span class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control " id="teacherPassword"
													type="password" name="password" required />
											</div>
										</div>
										<div class="form-group ">
											<label for="tVerifyPassword" class="control-label col-lg-2">Verify
												Password <span class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control " id="verifyPassword"
													type="password" name="verifyPassword" required />
											</div>
										</div>
									</div>

									<header class="panel-heading"> Teacher Details </header>
									<div class="panel-body">
										<div class="form-group ">
											<label for="tName" class="control-label col-lg-2">Name<span class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control " id="teacherName" type="text"
													name="tName" pattern="^([a-zA-Z\s]{1,255})$" title="Letters only" required/>
											</div>
										</div>
										<div class="form-group ">
											<label for="tNric" class="control-label col-lg-2">NRIC
												<span class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control" id="teacherNric" name="sNric"
													type="text" pattern="^[SFTG]\d{7}[A-Z]$" title="NRIC only" required />
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
											<label for="address" class="control-label col-lg-2">Address
											<span class="required">*</span></label>
											<div class="col-lg-10">
												<textarea class="form-control" rows="4" id="teacherAddress"
													name="address" required></textarea>
											</div>
										</div>
									</div>
									<header class="panel-heading"> Education Details </header>
									<div class="panel-body">
										<div id="dynamicInput">

											<div class="form-group ">
												<label for="school" class="control-label col-lg-2">School<span
													class="required">*</span></label>
												<div class="col-lg-10">
													<input class="form-control" id="teacherSchool"
														name="school" pattern="[A-Za-z]" title="Letters only" required>
												</div>
											</div>
											<div class="form-group ">
												<label for="qualification" class="control-label col-lg-2">Qualification
													<span class="required">*</span>
												</label>
												<div class="col-lg-10">
													<input class="form-control" id="qualification"
														name="qualification" type="text" pattern="[A-Za-z]" title="Letters only" required />
												</div>
											</div>
											<div class="form-group ">
												<label for="year" class="control-label col-lg-2">Year
													<span class="required">*</span>
												</label>
												<div class="col-lg-10">
													<input class="form-control" id="year" name="year"
														type="text" required />
												</div>
											</div>


											<!-- 										<div class="form-group "> -->
											<!-- 												Entry 1<br> -->
											<!-- 												<input type="text" name="myInputs[]"> -->
											<!-- 											</div> -->
											<!-- 											<input type="button" value="Add another education" -->
											<!-- 												onClick="addInput('dynamicInput');"> -->
											<!-- 										</div> -->

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
