<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta name="keyword" content="">
<link rel="shortcut icon" href="img/favicon.png">

<title>Explore and Learn - Parent Login</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.css" rel="stylesheet">
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />
<link href="css/parent-login.css" rel="stylesheet" />

<script type="text/javascript" src="./js/jQuery-2.1.4.min.js"></script>
<script src="./js/parent/login.js"></script>
</head>

<body>
<div class="container">
    <div class="row"> 
        <div class="col-md-12">
            
            <div class="wrap">
				<div class = "row"> 
					<p class="form-title">
					<img src="./img/parentLg.jpg" alt="login" class="img-circle"><br><br>
					Sign In
					</p>
				</div>
                
                <div>
					<font color="red" id="message"></font>
				</div>
                    
                <form class="login" action="parentProfile.jsp" method="post" onsubmit="login();">
                <input type="text" placeholder="Username" id="email"/>
                <input type="password" placeholder="Password" id="password"/>
                <input type="submit" value="Sign In" class="btn btn-success btn-sm" />
              
              
                <div class="remember-forgot">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" />
                                    Remember Me
                                </label>
                            </div>
                        </div>
                        <div class="col-md-6 forgot-pass-content">
                            <a href="javascript:void(0)" class="forgot-pass">Forgot Password</a>
                        </div>
                    </div>
                </div>
                
                </form>
            </div>
        </div>
    </div>
</div>


</body>
</html>