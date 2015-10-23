//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
 $(document).ready(function() {
    	getStudents();
    });
//}

function getStudents() {
	$.fn.dataTable.ext.errMode = 'none';
	//var planStartDate = moment(localStorage.getItem("planStartDate"), "DD/MM/YYYY");
	var planStartDate = localStorage.getItem("planStartDate");
	var teacherCourseId = Number(localStorage.getItem("teacherCourseId"));
	
	var input = {};
	input.teacherCourseId = teacherCourseId;
	input.planStartDate = planStartDate;
	
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	var table =  $('#attendanceTable').on( 'error.dt', function ( e, settings, techNote, message ) {
	        console.log( 'An error has been reported by DataTables: ', message );
	    }).DataTable({
	    	ajax:{
				 url: '../VI/GetScheduleByTeacherCourseAndPlanStartDateServlet?input=' + inputStr,
//				 dataSrc: 'message'
				 dataSrc: function (json) {
					 var attendances = json.message.attendances;
					 console.log(attendances);
					 var return_data = new Array();
					 for(var i=0;i< attendances.length; i++){
						 return_data.push({
							 'studentName': attendances[i].student.name,
							 'studentNric': attendances[i].student.studentNric
						 })
					 }
					 return return_data;
				 }
			 },
			 columns: [   
				// {"data": 'teacherCourse.teacher.email'},
				//ajax - need a for loop to iterate through
				 {"data": 'studentName'},
				 {"data": 'studentNric'},
				 {"data": null, "defaultContent":'<input type="checkbox" name="attendance" value="attendance">'}
				 
				]
	    });
}

function submit(){
	var attendance = document.getElementsByName("attendance");
	
	num =0
	for (i = 0; i < attendance.length; i++) {
		if (attendance[i].type == "checkbox" && attendance[i].checked == true) {
			num ++;
		}
	}
	console.log(num);
	bootbox.confirm({
		title: "Confirm submission",
		message: "Are you sure you want to submit attendance? ",
		callback: function(result){
			if (result){
				bootbox.confirm({
					title: "Confirmat submission",
					message: "Attendance updated",
					callback: function(){
							updateAttendance();
							window.location = 'adminMain.jsp';
							//localStorage.setItem("status", "pending");
						}
					});
				
				}
				
			},
		onEscape: function(){}
	});
}

function updateAttendance(){
	var attendanceId = row.data().attendanceId;
	var miliseconds = new Date();
	var actualStartDate = miliseconds.toUTCString();
	
	var input = {}
	input.attendanceId = Number(attendanceId);
	input.actualStartDate = actualStartDate;
	
	var inputStr = JSON.stringify(input);
	
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/UpdateAttendanceServlet?input=' + inputStr, //need to implement
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$("#message").html("System has some error. Please try again.");
		},
		success : function(data) {
			console.log(data);
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				bootbox.alert("Update is successful!")
				table.ajax.reload();
				
			} else {
				$("#message").html("Something's wrong, please try again!");
			}
		}
	});

}

function getStudentById(studentId){
	var input = {}
	input.studentId = Number(studentId);
	
	var inputStr = JSON.stringify(input);
	
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetStudentById?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$("#message").html("System has some error. Please try again.");
		},
		success : function(data) {
			console.log(data);
			var status = data.status; //shows the  success/failure of the servlet request
			var message = data.message;
			// if status == 1, it means that it is successful. else it will fail
			if (status == 1) {
				console.log(message.name);
			} else {
				$("#message").html("Something's wrong, please try again!");
			}
		}
	});
}

function deselectAll() {
	var x = document.getElementsByName("attendance");
	var i;
	for (i = 0; i < x.length; i++) {
		if (x[i].type == "checkbox") {
			x[i].checked = false;
	     }
	}
}

function selectAll() {
	var x = document.getElementsByName("attendance");
	var i;
	for (i = 0; i < x.length; i++) {
			x[i].checked =true;
	}

}