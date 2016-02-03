
<!-- Main Header -->
<header class="main-header">

	<!-- Logo -->
	<a href="parentMain.jsp" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>E</b>&L</span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>Explore</b>&Learn</span>
	</a>

	<!-- Header Navbar -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>
		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- User Account Menu -->
				<li class="dropdown user user-menu"><a href="parentProfile.jsp"
					class="btn btn-warning btn-flat">View Your Profile</a></li>
			</ul>
		</div>
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown user user-menu">
					<!-- Menu Toggle Button --> <a href="landingPage.jsp"
					class="btn btn-warning btn-flat" onclick="logout();">Sign out</a>
				</li>
			</ul>
		</div>
	</nav>
</header>
<!-- Previous Code  -->
<!--       Navbar Right Menu
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
        

          User Account Menu
          <li class="dropdown user user-menu">
            Menu Toggle Button
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              The user image in the navbar
              <img src="dist/img/user5-128x128.jpg" class="user-image" alt="User Image">
              hidden-xs hides the username on small devices so only the image appears.
              <span class="hidden-xs" id="navName"></span>
            </a>
            <ul class="dropdown-menu">
              The user image in the menu
              <li class="user-header">
                <img src="dist/img/user5-128x128.jpg" class="img-circle" alt="User Image">

                <p id="parentName">
                <small id="parentEmail"></small></p>
              </li>
              Menu Footer
              <li class="user-footer">
              	<div class="pull-left">
                  <a href="parentProfile.jsp" class="btn btn-warning btn-flat">View Details</a>
                </div>
                <div class="pull-right">
                  <a href="landingPage.jsp" class="btn btn-default btn-flat" onclick="logout();">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div> -->

<script>
	var parent = JSON.parse(localStorage.getItem("parent"));
	document.getElementById("parentName").innerHTML = parent.name;
	document.getElementById("navName").innerHTML = parent.name;
	document.getElementById("parentEmail").innerHTML = parent.email;
</script>