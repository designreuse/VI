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

<!-- DataTables CSS -->
<link
	href="js/datatables/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link
	href="js/datatables/datatables-responsive/css/dataTables.responsive.css"
	rel="stylesheet">

<!-- ---------------------External CSS: Fonts and Icons ----------------------- -->
<!-- Fonts and Icons -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />

<!-- -------------------External CSS: Custom styles----------------------------- -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />

</head>

<body>

	<!-- ----------------------JS section------------------------- -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="assets/jquery-knob/js/jquery.knob.js"></script>

	<!-- DataTables JavaScript -->
	<script
		src="js/datatables/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="js/datatables/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<script
		src="js/datatables/datatables-responsive/js/dataTables.responsive.js"></script>

	<!-- Custom JavaScript -->
	<script src="js/pages/parent.js"></script>

	<script src="js/scripts.js"></script>

	<!-- 	<script> -->
	// knob // $(".knob").knob();
	<!-- 	</script> -->

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
							<li><i class="icon_document_alt"></i>Parent</li>
							<li><i class="fa fa-files-o"></i>Parents Profile</li>
						</ol>
					</div>
				</div>
				<!-- page start-->
				<div id="page-wrapper">
					<!-- /.row -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">View All Parents</div>
								<!-- /.panel-heading -->
								<div class="panel-body">
									<div class="dataTable_wrapper">
										<table class="table table-striped table-bordered table-hover"
											id="parentTable">
											<thead>
												<tr>
													<th>Parent ID</th>
													<th>Name</th>
													<th>contact</th>
													<th>email</th>
													<th>address</th>
													<th>Join Date</th>
													<th>Options</th>
												</tr>
											</thead>
											<tbody id='parents'>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>
							</div>
						</div>
						<!-- /.row -->
					</div>
					<!-- /#page-wrapper -->

<!-- 					Create parent -->
<!-- 					<div class="modal fade" id="createParent" tabindex="-1" -->
<!-- 						role="dialog" aria-labelledby="exampleModalLabel"> -->
<!-- 						<div class="modal-dialog" role="document"> -->
<!-- 							<div class="modal-content"> -->
<!-- 								<div class="modal-header"> -->
<!-- 									<button type="button" class="close" data-dismiss="modal" -->
<!-- 										aria-label="Close"> -->
<!-- 										<span aria-hidden="true">&times;</span> -->
<!-- 									</button> -->
<!-- 									<h4 class="modal-title" id="exampleModalLabel">New Parent</h4> -->

<!-- 								</div> -->
<!-- 								<div class="modal-body"> -->
<!-- 									<form class="form-horizontal" id="createParentForm" -->
<!-- 										onsubmit="createParent();return false;"> -->
<!-- 										<div class="form-group"> -->
<!-- 											<label for="name" class="col-sm-3 control-label">ParentName</label> -->
<!-- 											<div class="col-sm-9"> -->
<!-- 												<input type="text" class="form-control" id="name" -->
<!-- 													placeholder="Parent Name"> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<div class="form-group"> -->
<!-- 											<label for="name" class="col-sm-3 control-label">UserName</label> -->
<!-- 											<div class="col-sm-9"> -->
<!-- 												<input type="text" class="form-control" id="username" -->
<!-- 													placeholder="Parent Name"> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<div class="form-group"> -->
<!-- 											<label for="password" class="col-sm-3 control-label">Password</label> -->
<!-- 											<div class="col-sm-9"> -->
<!-- 												<input type="password" class="form-control" id="password" -->
<!-- 													placeholder="Password"> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<div class="form-group"> -->
<!-- 											<label for="password2" class="col-sm-3 control-label">Confirm -->
<!-- 												Password</label> -->
<!-- 											<div class="col-sm-9"> -->
<!-- 												<input type="password" class="form-control" id="password2" -->
<!-- 													placeholder="Confirm Password" -->
<!-- 													oninput="passwordCheck(100);"> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<div class="modal-footer"> -->
<!-- 											<font color="red" id="message"></font> <img -->
<!-- 												src="img/gif/loader.gif" -->
<!-- 												class="my-loading sr-only"> -->
<!-- 											<button type="button" class="btn btn-default" -->
<!-- 												data-dismiss="modal">Close</button> -->
<!-- 											<button type="submit" class="btn btn-primary">Add -->
<!-- 												New Parent</button> -->
<!-- 										</div> -->
<!-- 									</form> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->

					<!-- Edit parent form section -->
					<div class="modal fade" id="editParent" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Edit Parent</h4>
								</div>
								<div class="modal-body">
									<form id="editParentForm" class="form-horizontal">
										<div class="form-group">
											<label for="name" class="col-sm-3 control-label">ParentName</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="nameEdit"
													placeholder="Parent Name">
											</div>
										</div>
										<div class="form-group">
											<label for="name" class="col-sm-3 control-label">UserName</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="usernameEdit"
													placeholder="Parent Name">
											</div>
										</div>
										<div class="modal-footer">
											<font color="red" id="messageEdit"></font> <img
												src="img/gif/loader.gif"
												class="my-loading sr-only">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
											<button type="submit" class="btn btn-warning">Save</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>

					<div id="changeParentPassword" class="modal fade bs-example-modal"
						tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">Change Password</h4>
								</div>
								<div class="modal-body">
									<form class="form-horizontal" id="changeParentPasswordForm"
										onsubmit="changeParentPassword(0); return false;">
										<div class="form-group">
											<label for="passwordEdit" class="col-sm-2 control-label">Password</label>
											<div class="col-sm-10">
												<input type="password" class="form-control"
													id="passwordEdit" placeholder="Password" required>
											</div>
										</div>
										<div class="form-group">
											<label for="password2Edit" class="col-sm-2 control-label">Confirm
												Password</label>
											<div class="col-sm-10">
												<input type="password" class="form-control"
													id="password2Edit" placeholder="Confirm Password" required
													oninput="passwordEditCheck(100);">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-8 col-xs-10">
												<button type="submit" class="btn btn-info">Change</button>
												<font color="red" id="messageChangePassword"></font> <img
													src="img/gif/loader.gif"
													class="my-loading sr-only">
											</div>
											<div class="col-xs-2">
												<button type="button" class="btn btn-default pull-right"
													data-dismiss="modal">Close</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div id="deleteParent" class="modal fade bs-example-modal-lg"
						tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
						aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">Delete Parent</h4>
								</div>
								<div class="modal-body">
									<form class="form-horizontal" id="deleteParentForm"
										onsubmit="deleteParent(0); return false;">
										<div class="form-group">
											<label for="nameDelete" class="col-sm-2 control-label">Name</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="nameDelete"
													placeholder="Please key in the employee name to confirm delete!"
													required>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-8 col-xs-10">
												<button type="submit" class="btn btn-danger">Delete</button>
												<font color="red" id="messageDeleteParent"></font> <img
													src="img/gif/loader.gif"
													class="my-loading sr-only">
											</div>
											<div class="col-xs-2">
												<button type="button" class="btn btn-default pull-right"
													data-dismiss="modal">Close</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /#wrapper -->

				<!-- page end-->
			</section>
		</section>
		<!--main content end-->
	</section>
	<!-- container section end -->
</body>
</html>
