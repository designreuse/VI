<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Explore and Learn | Parents Portal Login</title>
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
  <link rel="stylesheet" href="dist/css/skins/skin-green.min.css">
  
	<link href="css/fullcalendar.min.css" rel="stylesheet">
	<link href='css/fullcalendar.print.css' rel='stylesheet' media='print' />

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body class="hold-transition login-page">
<div class="login-box">
	<div class="login-logo">
		<b>Parents</b>Portal</a>
	</div>
	
	<div class="login-box-body">
		<p class="login-box-msg">Sign in to start your session</p>
		
		<form action="parentMain.jsp" method="post" onsubmit="login();return false;">
			<div class="form-group has-feedback">
				<input type="email" class="form-control" placeholder="Email" id="email">
				<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
			</div>
			
      		<div class="form-group has-feedback">
		       <input type="password" class="form-control" placeholder="Password" id="password">
		       <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      		</div>
      		
	      <div class="row">	        
	        <div class="col-xs-4">
	          <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
	        </div>
	        
	       	<div class="col-xs-4 text-center">
	          <p>------ OR -------</p>
	        </div>
	        
	       <div class="col-xs-4">
	          <a href="landingPage.jsp" class="btn btn-facebook btn-flat">Go Back</a>
	        </div>

	      </div>
    </form>

  </div>
  <!-- /.login-box-body -->
</div>

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

<script src="./js/parent/login.js"></script>
</body>
</html>



