$(document).ready(function() {
	var teacherId = localStorage.getItem('teacherId');
	if (teacherId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
		scan();
		displayCourses();
		 $("#selectCourse").change(function () {
				getSchedules();
		    });
	}
});

//need to select before scanning
var COURSES;
var SCHEDULEEVENTS;

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
	var scheduleEventPlanStartDate = $("#selectScheduleEvent").val();
	for(var j = 0; j < SCHEDULEEVENTS.length; j++){
//		console.log(SCHEDULEEVENTS[j].name);
		if(scheduleEventPlanStartDate == SCHEDULEEVENTS[j].planStartDate){
			var scheduleEventId = SCHEDULEEVENTS[j].scheduleEventId;
	
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
						var status = data.status;
						var message = data.message;
						
						bootbox.alert("Attendance taken! Welcome to class, " + message.student.name, function() {
							});
						//call the send email servlet to send the email
//						console.log("here!!!");
						sendEmail(message);
						sendSMS(message);

					} else {
						$("#message").html("Something's wrong, please try again!");
					}
				}
			});
		}
	}
}

function displayCourses() {
	var select = document.getElementById("selectCourse");
	
	var storedCourses = JSON.parse(localStorage["courses"]);
	//console.log(storedCourses);
	var options = [];
	for(var j = 0; j < storedCourses.length; j++){
		var course = storedCourses[j];
		options.push(course);
	}
	COURSES = storedCourses;
	//console.log(COURSES);

	for(var i = 0; i < options.length; i++) {
	    var opt = options[i].name;
	    var el = document.createElement("option");
	    el.textContent = opt;
	    el.value = opt;
	    select.appendChild(el);
	}
}

function getSchedules() {
	//scheduleid - courseid, teacherid (GetSchedulesByTeacherAndCourseServlet)
	var courseName = $("#selectCourse").val();
	for(var j = 0; j < COURSES.length; j++){
		//console.log(COURSES[j].name);
		if(courseName == COURSES[j].name){
			var courseId = COURSES[j].courseId;
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
							localStorage.setItem("teacherName",obj.teacher.name);
							scheduleIds.push(obj.scheduleId);
							//console.log(scheduleIds);
							localStorage["scheduleIds"] = JSON.stringify(scheduleIds);
							getScheduleEvents();
						}
					} else {
						console.log(message);
					}
				}
			});
		}
	}
}

function displayScheduleEvents() {
	var select = document.getElementById("selectScheduleEvent");
	var storedEvents = JSON.parse(localStorage["scheduleEvents"]);
	var options = [];
	for(var j = 0; j < storedEvents.length; j++){
		//console.log(storedIds[j])
		var scheduleEvent = storedEvents[j];
		options.push(scheduleEvent);
	}
	
	SCHEDULEEVENTS = storedEvents;

	for(var i = 0; i < options.length; i++) {
	    var opt = options[i].planStartDate;
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
	
	var input = {};
	input.scheduleId = scheduleId;
	var inputStr = JSON.stringify(input);
	var i = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/GetScheduleEventsByScheduleServlet?input=' + inputStr, // this part sends to
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
				var scheduleEvents = [];
				
				for (var i = 0; i < data.message.length; i++) {
					var obj = data.message[i];
					
					scheduleEvents.push(obj);
					//console.log(obj.scheduleEventId);
					localStorage["scheduleEvents"] = JSON.stringify(scheduleEvents);	
				}
				displayScheduleEvents();
			} else {
				console.log(message);
			}
		}
	});
	}
}

function sendEmail(message){
	var studentName = message.student.name;
	var parentName = message.student.parent.name;
	var email = message.student.parent.email;
	var teacherName = localStorage.getItem("teacherName");
	
	var input = {};
	input.studentName = studentName;
	input.parentName = parentName;
	input.email = email;
	input.teacherName = teacherName;
	
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
}

function sendSMS(message){
	//appid=123&appsecret=e0b637e3-e218-4e9e- a52f-3967d9d2cb81&receivers= 6511111111%2C6522222222&content=This%20is%20a%20test%20message.&responseformat =JSON
	//http://www.smsdome.com/api/http/sendsms.aspx?appid=1408&appsecret=4c76978e-01c3-43b4-bc57-9d82681f1485&receivers=6591267284&content=%5BExplore%20And%20Learn%5D%20Attendance%20notification.%20Your%20child%20Amanda%20has%20attended%20class.&responseformat=JSON
	var appId = "appid=1408";
	var appSecret = "appsecret=4c76978e-01c3-43b4-bc57-9d82681f1485";
	var receivers = "receivers=65" + message.student.parent.contact;
	var studentName = message.student.name;
	var teacherName = localStorage.getItem("teacherName");
	//ADD IN TEACHER NAME
	var content = "content=[Explore And Learn] Attendance notification." + " Your child " + studentName + " has attended class. Best regards, " + teacherName + ".&responseformat=JSON";	
//	var content = "content=[Explore%20And%20Learn]%20Attendance%20notification." + "%20Your child%20" + studentName + "%20has attended class.";
	
	var input = appId + "&" + appSecret + "&" + receivers + "&" + content;
	var url = encodeURI(input);
//	console.log(url);
	
	var theUrl = "https://www.smsdome.com/api/http/sendsms.aspx?" + url;

//	var xhr = new XMLHttpRequest();
//	xhr.open("GET", theUrl, true);
//	xhr.onload = function (e) {
//	  if (xhr.readyState === 4) {
//	    if (xhr.status === 200) {
//	      console.log(xhr.responseText);
//	    } else {
//	      console.error(xhr.statusText);
//	    }
//	  }
//	};
//	xhr.onerror = function (e) {
//	  console.error(xhr.statusText);
//	};
//	xhr.send(null);

	
	$.ajax({
		url : 'https://www.smsdome.com/api/http/sendsms.aspx?' + url, 
		method : 'GET',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			var result = data.result;
			var content = data.content;
			var credit = data.creadit;
			var recivers = data.recivers;
			
			console.log(result.status);
			console.log("sms sent");
		}
	});
}