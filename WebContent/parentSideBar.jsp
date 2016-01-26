<!-- Left side column. contains the logo and sidebar -->
 <aside class="main-sidebar">
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="dist/img/user5-128x128.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p id="sidebarName"></p>
          <p id="sidebarEmail"></p>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li class="header"></li>
        <li class="active"><a href="parentMain.jsp"><i class="fa fa-link"></i> <span>Home</span></a></li>
        <li class="active"><a href="parentStudent.jsp"><i class="fa fa-link"></i> <span>Children</span></a></li>
        <li class="active"><a href="parentFeedback.jsp"><i class="fa fa-link"></i> <span>Feedback Version1</span></a></li>
        <li class="active"><a href="parentFeedbackB.jsp"><i class="fa fa-link"></i> <span>Feedback Version2</span></a></li>
      </ul>
      
    </section>
</aside>

  <script>
	var parent = JSON.parse(localStorage.getItem("parent"));
	document.getElementById("sidebarName").innerHTML = parent.name;
	document.getElementById("sidebarEmail").innerHTML = parent.email;
</script>