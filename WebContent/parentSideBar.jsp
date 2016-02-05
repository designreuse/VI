
<!-- Left side column. contains the logo and sidebar -->
 <aside class="main-sidebar">
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="dist/img/user.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p id="sidebarName"></p>
          <p id="sidebarEmail"></p>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li class="header"></li>
        <li class="treeview"><a href="parentMain.jsp"><i class="fa fa-link"></i> <span>Home</span></a></li>
        <li class="treeview"><a href="parentStudent.jsp"><i class="fa fa-link"></i> <span>Children</span></a></li>
        <li class="treeview"><a href="parentFeedbackB.jsp"><i class="fa fa-link"></i> <span>Report Cards</span></a></li>
      </ul>
      
    </section>
</aside>

  <script>
	var parent = JSON.parse(localStorage.getItem("parent"));
	document.getElementById("sidebarName").innerHTML = parent.name;
	document.getElementById("sidebarEmail").innerHTML = parent.email;
</script>