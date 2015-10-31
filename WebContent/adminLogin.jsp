<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta name="keyword" content="">
<link rel="shortcut icon" href="img/favicon.png">

<title>Explore and Learn Pte Ltd</title>

<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.css" rel="stylesheet" />
<!-- Custom styles -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />

<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="./js/branchmanager/login.js"></script>
<!-- <script src="./js/parent/login.js"></script> -->
<!-- <script src="./js/teacher/login.js"></script> -->
<!-- <script src="./js/student/login.js"></script> -->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-img3-body">
	<div class="container">
		<form class="login-form" action="studentRegistration.jsp" method="post"
			onsubmit="loginBranchManager();return false;">
			<div class="login-wrap">
				<p class="login-img">
					<i class="icon_lock_alt"></i>
				</p>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="text" class="form-control" placeholder="Email"
						id="email">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="password" class="form-control" placeholder="Password"
						id="password">
				</div>
				<button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>

				<div>
					<font color="red" id="message"></font>
				</div>
			</div>
		</form>



	</div>
</body>
</html>
