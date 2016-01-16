$(document).ready(function() {
	scan();
	displayCourses();
	 $("#selectCourse").change(function () {
			getSchedules();
	    });
});

function scan(){
	var txt = "innerText" in HTMLElement.prototype ? "innerText"
			: "textContent";
	var arg = {
		resultFunction : function(resText,lastImageSrc) {
			var aChild = document.createElement('li');
			aChild[txt] = resText;
			var qrStudentId = resText;
			updateQRAttendance(qrStudentId);
		}
	};
	new WebCodeCamJS("canvas").init(arg).play();
}

function getStudentById(studentId) {
	var input = {}
	input.studentId = Number(studentId);

	var inputStr = JSON.stringify(input);

	inputStr = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/GetStudentById?input=' + inputStr, // this part sends to
														// the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$("#message").html("System has some error. Please try again.");
		},
		success : function(data) {
			console.log(data);
			var status = data.status; // shows the success/failure of the
										// servlet request
			var message = data.message;
			// if status == 1, it means that it is successful. else it will fail
			if (status == 1) {
				// console.log(message);
				console.log(message.name);
				updateQRAttendance(studentId);
			} else {
				$("#message").html("Something's wrong, please try again!");
			}
		}
	});
}

function updateQRAttendance(studentId){	
	var scheduleEventId = $("#selectScheduleEvent").val();
	var attendanceStatus = 1;
	var miliseconds = new Date();
	var actualStartDate = moment().format();
	var input = {}
	input.scheduleEventId = Number(scheduleEventId);
	input.studentId = Number(studentId);
	input.attendanceStatus = Number(attendanceStatus);
	input.actualStartDate = actualStartDate;
	
	var inputStr = JSON.stringify(input);
	
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/UpdateAttendanceWithQRServlet?input=' + inputStr, 
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
			console.log(data);
			console.log(message);
			// if status == 1, it means that it is successful. else it will fail
			if (status == 1) {
				bootbox.alert("Attendance taken! Welcome to class, " + message.student.name, function() {
					});
				//call the send email servlet to send the email
				console.log("here!!!");
				var studentName = message.student.name;
				var parentName = message.student.parent.name;
				var email = message.student.parent.email;
				
				var input = {};
				input.studentName = studentName;
				input.parentName = parentName;
				input.email = email;
				
				var inputMsg = JSON.stringify(input);
				console.log(inputMsg);
				
				$.ajax({
					url : '../VI/system/SendEmailServlet?input=' + inputMsg, 
					method : 'POST',
					dataType : 'json',
					error : function(err) {
						console.log(err);
						//$("#message").html(err);
					},
					success : function(data) {
						console.log(data);
						var status = data.status; //shows the  success/failure of the servlet request
						var message = data.message;
						console.log("email sent");
					}
				});
			} else {
				$("#message").html("Something's wrong, please try again!");
			}
		}
	});
}

function displayCourses() {
	var select = document.getElementById("selectCourse");
	
	var storedIds = JSON.parse(localStorage["courseIds"]);
	var options = [];
	for(var j = 0; j < storedIds.length; j++){
		var courseId = Number(storedIds[j]);
		options.push(courseId);
	}
	

	for(var i = 0; i < options.length; i++) {
	    var opt = options[i];
	    var el = document.createElement("option");
	    el.textContent = opt;
	    el.value = opt;
	    select.appendChild(el);
	}
}

function getSchedules() {
	//scheduleid - courseid, teacherid (GetSchedulesByTeacherAndCourseServlet)
	var courseId = Number($("#selectCourse").val());
	var teacherId = Number(localStorage.getItem("teacherId"));
	var input = {};
	input.courseId = courseId;
	input.teacherId = teacherId;
	var inputStr = JSON.stringify(input);
	var i = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/GetSchedulesByTeacherAndCourseServlet?input=' + inputStr, // this part sends to
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
				// store scheduleIds
				var scheduleIds = [];
				
				for (var i = 0; i < data.message.length; i++) {
					var obj = data.message[i];
					
					scheduleIds.push(obj.scheduleId);
					console.log(scheduleIds);
					localStorage["scheduleIds"] = JSON.stringify(scheduleIds);
					getScheduleEvents();
				}
			} else {
				console.log(message);
			}
		}
	});
}

function displayScheduleEvents() {
	var select = document.getElementById("selectScheduleEvent");
	var storedIds = JSON.parse(localStorage["scheduleEventIds"]);
	var options = [];
	for(var j = 0; j < storedIds.length; j++){
		var scheduleEventId = Number(storedIds[j]);
		options.push(scheduleEventId);
	}
	

	for(var i = 0; i < options.length; i++) {
	    var opt = options[i];
	    var el = document.createElement("option");
	    el.textContent = opt;
	    el.value = opt;
	    select.appendChild(el);
	}
}

function getScheduleEvents() {
	//scheduleeventid - scheduleid (GetScheduleEventsBySchedule)
	//i have scheduleIds not scheduleId
	var storedIds = JSON.parse(localStorage["scheduleIds"]);
	for(var j = 0; j < storedIds.length; j++){
		var scheduleId = Number(storedIds[j]);
	}
	var input = {};
	input.scheduleId = scheduleId;
	var inputStr = JSON.stringify(input);
	var i = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/GetScheduleEventsBySchedule?input=' + inputStr, // this part sends to
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
				// store scheduleEventIds
				var scheduleEventIds = [];
				
				for (var i = 0; i < data.message.length; i++) {
					var obj = data.message[i];
					
					scheduleEventIds.push(obj.scheduleEventIds);
					console.log(scheduleEventIds);
					localStorage["scheduleEventIds"] = JSON.stringify(scheduleEventIds);
					displayScheduleEvents();
				}
			} else {
				console.log(message);
			}
		}
	});
}