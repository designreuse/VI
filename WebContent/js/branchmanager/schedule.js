$(document).ready(function() {
	var branchManagerId = localStorage.getItem('branchManagerId');
	if (branchManagerId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
		getSchedulesByCourse();
	}
});

var SCHEDULES = [];
var SCHEDULE;

function getSchedulesByCourse() {
	var courseId = localStorage.getItem('courseId');
	var input = {};
	input.courseId = Number(courseId);
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/GetSchedulesByCourseServlet?input=' + inputStr, // this part sends to
		// the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			;
			var status = data.status; // shows the success/failure of the
			// servlet request
			if (status == 1) {
				for (var i = 0; i < data.message.length; i++) {
					var obj = data.message[i];
					SCHEDULES.push(obj);
				}
				displaySchedules();
			} else {
				console.log(message);
			}
		}
	});
}

function displaySchedules() {
	var select = document.getElementById("selectSchedule");
	
	var options = [];
	for(var j = 0; j < SCHEDULES.length; j++){
		var schedule = SCHEDULES[j];
		options.push(schedule);
	}

	
	for(var i = 0; i < options.length; i++) {
	    var opt = options[i].scheduleStartDate;
	    var el = document.createElement("option");
	    el.textContent = opt;
	    el.value = opt;
	    select.appendChild(el);
	}
}

function createAttendancesForStudentBySchedule(){
	var scheduleStartDate = $("#selectSchedule").val();
	for(var j = 0; j < SCHEDULES.length; j++){
		if(scheduleStartDate == SCHEDULES[j].scheduleStartDate){
			SCHEDULE = SCHEDULES[j];
			var scheduleId = SCHEDULES[j].scheduleId;
			var studentId = localStorage.getItem('studentId');
			var input = {};
			input.studentId = Number(studentId);
			input.scheduleId = scheduleId;
			var inputStr = JSON.stringify(input);
			inputStr = encodeURIComponent(inputStr);
		
			$.ajax({
				url : '../VI/CreateAttendancesForStudentByScheduleServlet?input=' + inputStr, // this part sends to
				// the servlet
				method : 'POST',
				dataType : 'json',
				error : function(err) {
					console.log(err);
				},
				success : function(data) {
					;
					var status = data.status; // shows the success/failure of the
					// servlet request
					if (status == 1) {
						createTeacherStudentCourse();
						alert("Assigned successfully");	
						
					} else {
						console.log(message);
					}
				}
			});
		}
	}
}

function createTeacherStudentCourse(){
	var studentId = localStorage.getItem('studentId');
	var courseId = localStorage.getItem('courseId');
	var courseLevel = localStorage.getItem('courseLevel');
	console.log(SCHEDULES);
	var teacherId = SCHEDULE.teacher.teacherId;
	
	var input = {};
	input.studentId = Number(studentId);
	input.courseId = Number(courseId);
	input.courseLevel = Number(courseLevel);
	input.teacherId = Number(teacherId);
	
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/CreateTeacherStudentCourseServlet?input=' + inputStr, //this part sends to the servlet
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
				console.log("done");
			} else {
				$("#message").html(message);
			}
		}
	});
}