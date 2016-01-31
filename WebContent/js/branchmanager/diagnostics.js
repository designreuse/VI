$(document).ready(function() {
	var branchManagerId = localStorage.getItem('branchManagerId');
	if (branchManagerId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
		getAllCourses();
//		checkbox();
//		qty();
	}
});

var STUDENT;
var COURSES = [];
var COURSE;
var SCHEDULES = [];

function submitDiagnostic(){
	//studentId, subjectName, resultValue
	var studentId = STUDENT.studentId;
	localStorage.setItem("studentId", studentId);
	var subjectName = document.querySelector('input[name="course"]:checked').value;
	var courseLevel = $("#courseLevel").val();
	localStorage.setItem("courseLevel", courseLevel);
	for(var j = 0; j < COURSES.length; j++){
		if(subjectName == COURSES[j].name){
			var courseId = COURSES[j].courseId;
			localStorage.setItem("courseId", courseId);
			var resultValue = $("#results").val();
			var diagnostics = [{courseId:courseId, resultValue:resultValue}];
			
			var input = {};
			input.studentId = studentId;
			input.diagnostics = diagnostics;
			
			var inputStr = JSON.stringify(input);
			inputStr = encodeURIComponent(inputStr);
			$.ajax({
				url : '../VI/CreateDiagnosticsServlet?input=' + inputStr, //this part sends to the servlet
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
						window.location = "createSchedule.jsp";
					} else {
						$("#message").html(message);
					}
				}
			});
		}
	}
}
function checkbox(){
	$('input.enable_cb').change(function(){
		if ($(this).is(':checked')){ 
			$(this).next('div.enter_text').show();
		}else {
			$('div.enter_text').hide();
		}
	}).change();
}

function qty(){
	$('.qtyplus').on("click",function(e){
		// Stop acting like a button
		e.preventDefault();
		// Get the field name
		fieldName = $(this).attr('field');
		// Get its current value
		var currentVal = parseInt($('input[name='+fieldName+']').val());
		// If is not undefined
		if (!isNaN(currentVal)) {
			// Increment
			$('input[name='+fieldName+']').val(currentVal + 1);
		} else {
			// Otherwise put a 0 there
			$('input[name='+fieldName+']').val(0);
		}
	});
	// This button will decrement the value till 0

	$(".qtyminus").on("click",function(e) {
		// Stop acting like a button
		e.preventDefault();
		// Get the field name
		fieldName = $(this).attr('field');
		// Get its current value
		var currentVal = parseInt($('input[name='+fieldName+']').val());
		// If it isn't undefined or its greater than 0
		if (!isNaN(currentVal) && currentVal > 0) {
			// Decrement one
			$('input[name='+fieldName+']').val(currentVal - 1);
		} else {
			// Otherwise put a 0 there
			$('input[name='+fieldName+']').val(0);
		}
	});
}

function getStudentByNric(){
	var studentNric = $("#studentNric").val();
	var input = {};
	input.studentNric = studentNric;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/GetStudentByNricServlet?input=' + inputStr, // this part sends to
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
				STUDENT = data.message;
				submitDiagnostic();
			} else {
				console.log(message);
			}
		}
	});
	
}

function getSchedulesByCourse() {
	//courseId
	var courseId = COURSE.courseId;
	console.log(courseId);
	var input = {};
	input.courseId = courseId;
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
	var scheduleName = $("#selectSchedule").val();
	for(var j = 0; j < SCHEDULES.length; j++){
		if(scheduleName == SCHEDULES[j].name){
			var scheduleId = SCHEDULES[j].scheduleId;
			var studentId = STUDENT.studentId;
			var input = {};
			input.studentId = studentId;
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
						alert("Assigned successfully");	
						
					} else {
						console.log(message);
					}
				}
			});
		}
	}
}

function getAllCourses(){
	var inputStr = {};
	inputStr = JSON.stringify(inputStr);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/GetAllCoursesServlet?input=' + inputStr, // this part sends to
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
					obj = data.message[i];	
					COURSES.push(obj);
				}
			} else {
				console.log(message);
			}
		}
	});
}