<!-- Left side column. contains the logo and sidebar -->
 <aside class="main-sidebar">
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Bukit Batok Branch</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li class="header">Navigation</li>
        <!-- Optionally, you can add icons to the links -->
        <li class="active"><a href="adminMain.jsp"><i class="fa fa-link"></i> <span>Dashboard</span></a></li>
     
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Registration</span> <i class="fa fa-angle-left pull-right"></i></a>
          <ul class="treeview-menu">
            <li><a href="parentRegistration.jsp">New Parent</a></li>
            <li><a href="studentRegistration.jsp">New Student</a></li>
            <li><a href="teacherRegistration.jsp">New Teacher</a></li>
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Database</span> <i class="fa fa-angle-left pull-right"></i></a>
          <ul class="treeview-menu">
            <li><a href="parentsProfile.jsp">View All Parents</a></li>
            <li><a href="studentsProfile.jsp">View All Students</a></li>
             <li><a href="teachersProfile.jsp">View All Teachers</a></li>
          </ul>
        </li>
        
        <li class="treeview">
        <a href="diagnosticTest.jsp"><i class="fa fa-link"></i> <span>Diagnostic Results</span></a>
        </li>
        
        <li class="treeview">
        <a href="generateQR.jsp"><i class="fa fa-link"></i> <span>Generate QR Code</span></a>
        </li>
        
      </ul>
      
    </section>
</aside>