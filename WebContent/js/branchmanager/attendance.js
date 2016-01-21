var adminId = localStorage.getItem('adminId');
if (adminId == null) {
    window.location.replace('adminLogin.jsp');;
} else {
$(document).ready(function() {
	displayCourses();
	 $("#selectCourse").change(function () {
			getSchedules();
	    });
	 $("#selectScheduleEvent").change(function () {
		 getStudents();
	    });
});
 }

function getStudents() {
	$.fn.dataTable.ext.errMode = 'none';
	var scheduleEventPlanStartDate = $("#selectScheduleEvent").val();
	for(var j = 0; j < SCHEDULEEVENTS.length; j++){
//		console.log(SCHEDULEEVENTS[j].name);
		if(scheduleEventPlanStartDate == SCHEDULEEVENTS[j].planStartDate){
			var scheduleEventId = SCHEDULEEVENTS[j].scheduleEventId;

			var input = {};
			input.scheduleEventId = Number(scheduleEventId);
		
			var inputStr = JSON.stringify(input);
			inputStr = encodeURIComponent(inputStr);
		
			var table = $('#attendanceTable')
					.on(
							'error.dt',
							function(e, settings, techNote, message) {
								console.log(
										'An error has been reported by DataTables: ',
										message);
							})
					.DataTable(
							{
								ajax : {
									url : '../VI/GetScheduleEventByIdServlet?input='
											+ inputStr,
									// dataSrc: 'message'
									dataSrc : function(json) {
										var attendances = json.message.attendances;
										console.log(attendances);
										var return_data = new Array();
										//attendance (not array)
										for (var i = 0; i < attendances.length; i++) {
											if (attendances[i].attendanceStatus == 0) {
												return_data
														.push({
															'attendanceId' : attendances[i].attendanceId,
															'studentName' : attendances[i].student.name,
															'studentNric' : attendances[i].student.studentNric,
															'attendanceStatus' : attendances[i].attendanceStatus,
															'button' : "<button class='btn btn-sm btn-success fa fa-check' onclick='getValue();'></button>"
														})
											} else {
												return_data
														.push({
															'attendanceId' : attendances[i].attendanceId,
															'studentName' : attendances[i].student.name,
															'studentNric' : attendances[i].student.studentNric,
															'attendanceStatus' : attendances[i].attendanceStatus,
															'button' : "present"
														})
											}
										}
										return return_data;
									}
								},
								columns : [ {
									"data" : 'attendanceId'
								}, {
									"data" : 'studentName'
								}, {
									"data" : 'studentNric'
								}, {
									"data" : 'attendanceStatus'
								}, {
									"data" : 'button'
								}
		
								]
							});
		}
	}
}

function getValue() {
	var table = $('#attendanceTable').DataTable();
	$('#attendanceTable tbody').off('click').on('click', 'button', function() {
		var attendanceStatus = 1;
		var input = {}
		var tr = $(this).closest('tr');
		var row = table.row(tr);
		var attendanceId = row.data().attendanceId;
		console.log(row.data().attendanceId);
		var miliseconds = new Date();
		var actualStartDate = moment().format();
		
		input.attendanceId = Number(attendanceId);
		input.attendanceStatus = Number(attendanceStatus);
		input.actualStartDate = actualStartDate;
		
		var inputStr = JSON.stringify(input);

		inputStr = encodeURIComponent(inputStr);

		console.log(inputStr);

		$.ajax({
			url : '../VI/UpdateAttendanceServlet?input=' + inputStr, 
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
					
					//call the send email servlet to send the email
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
	});
}

function submit() {
	bootbox.confirm({
		title : "Confirm submission",
		message : "Are you sure you want to submit attendance? ",
		callback : function(result) {
			if (result) {
				updateAttendance();
				// bootbox.confirm({
				// title: "Confirm submission",
				// message: "Attendance updated",
				// callback: function(){
				//							
				// //window.location = 'adminMain.jsp';
				// }
				// });
				//				
			}
		},
		onEscape : function() {
		}
	});
}

function updateAttendance() {
	// var rows_selected = [];
	var attendance = document.getElementsByName("attendance");
	var table = $('#attendanceTable').DataTable();
	$('#attendanceTable tbody').on(
			'click',
			'input[type="checkbox"]',
			function() {
				console.log("what?");
				var tr;
				var row;
				var attendanceId;
				var attendanceStatus;
				var actualStartDate;
				var attendancesArr = [];
				var input = {}
				console.log("hello");
				num = 0
				for (i = 0; i < attendance.length; i++) {
					if (attendance[i].type == "checkbox"
							&& attendance[i].checked == true) {
						num++;
						// rows_selected.push(attendance[i].value);

						tr = $(this).closest('tr');
						row = table.row(tr);
						console.log(row.data().attendanceId);
						attendanceId = row.data().attendanceId;
						attendanceStatus = row.data().attendanceStatus;

						var miliseconds = new Date();
						actualStartDate = miliseconds.toUTCString();

						// attendancesArr.attendances.push(Number(attendanceId),
						// attendanceStatus, actualStartDate);

						input.attendances = attendancesArr;
					}
				}
				// console.log(num);

				// {"scheduleId":1,"attendanceId":1,"studentId":5,"attendanceStatus":0,"actualStartDate":"2015-10-06T17:50:18.000Z"}

				// pause
				var inputStr = JSON.stringify(input);

				inputStr = encodeURIComponent(inputStr);

				console.log(inputStr);

				$.ajax({
					url : '../VI/UpdateAttendanceServlet?input=' + json, // need
																			// to
																			// implement
					method : 'POST',
					dataType : 'json',
					error : function(err) {
						console.log(err);
						$("#message").html(
								"System has some error. Please try again.");
					},
					success : function(data) {
						console.log(data);
						var status = data.status;
						var message = data.message;

						if (status == 1) {
							bootbox.alert("Update is successful!")
							table.ajax.reload();

						} else {
							$("#message").html(
									"Something's wrong, please try again!");
						}
					}
				});
			});
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