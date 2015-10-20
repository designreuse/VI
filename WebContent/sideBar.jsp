<script src="./js/branchmanager/logout.js"></script>
<aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu">
			<li><a class="" href="adminMain.jsp"> <i
					class="icon_house_alt"></i> <span>Dashboard</span>
			</a></li>

			<!-- 			<li class="active"><a class="" href="scheduleOverview.jsp"> <i -->
			<!-- 					class="icon_house_alt"></i> <span>Schedule</span> -->
			<!-- 			</a></li> -->

			<li class="sub-menu"><a href="javascript:;" class=""> <i
					class="icon_document_alt"></i> <span>Parent</span> <span
					class="menu-arrow arrow_carrot-right"></span>
				</a>
				<ul class="sub">
					<li><a class="" href="parentRegistration.jsp">Registration</a></li>
					<li><a class="" href="parentsProfile.jsp">Parents List</a></li>
				</ul>
			</li>
			
			<li class="sub-menu"><a href="javascript:;" class=""> <i
					class="icon_document_alt"></i> <span>Student</span> <span
					class="menu-arrow arrow_carrot-right"></span>
				</a>
				<ul class="sub">
					<li><a class="" href="studentRegistration.jsp">Registration</a></li>
					<li><a class="" href="studentsProfile.jsp">Students List</a></li>
				</ul>
			</li>
				
			<li class="sub-menu"><a href="javascript:;" class=""> <i
					class="icon_document_alt"></i> <span>Teacher</span> <span
					class="menu-arrow arrow_carrot-right"></span>
				</a>
				<ul class="sub">
					<li><a class="" href="teacherRegistration.jsp">Registration</a></li>
					<li><a class="" href="teachersProfile.jsp">Teachers List</a></li>
				</ul>
			</li>
			
			<li><a class="" href="QR.jsp"> <i
					class="icon_check"></i> <span>Attendance Taking</span>
			</a></li>
			
			<li><a class="" href="index.jsp" onclick="logout();"> <i
					class="fa fa-times-circle-o"></i> <span>Log Out</span>
			</a></li>


		</ul>
		<!-- sidebar menu end-->
	</div>
</aside>
<!--sidebar end-->