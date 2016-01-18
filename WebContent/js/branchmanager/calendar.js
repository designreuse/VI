$(document).ready(function() {
	getSchedules();
	calendarInitiate();
	schedulePanelView();
	$('#scheduleRange').daterangepicker({
		timePicker: true, 
		timePickerIncrement: 30, 
		format: 'MM/DD/YYYY h:mm A'
	});
	generateTeacherOption();
	populateNewSchedules();
	
});

function generateTeacherOption(){
	var branchId = Number(localStorage.getItem("branchId"));
	var $teacherDDL = $('#teacherDDL');
	$teacherDDL.html('');
	var input = {};
	input.branchId = branchId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetTeachersByBranchServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			$teacherDDL.html('<option id="-1">No teachers available</option>');
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				$teacherDDL.html('<option id="0">Select Teacher</option>');
				for (var t = 0; t < message.length; t++){
					var teacherId = message[t].teacherId;
					var teacherName = message[t].name;
					$teacherDDL.append('<option value=' + message[t].teacherId + '>' + teacherName + '</option>' );
				}
			} else{
				$teacherDDL.html('<option id="-1">No teachers available</option>');
			}
		}
	});
}

function generateCourseOption(elem){
	var teacherId = elem.selectedIndex;
	var $courseDDL = $('#courseDDL');
	$courseDDL.html('');
	var input = {};
	input.teacherId = teacherId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetCoursesByTeacherServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			$courseDDL.html('<option id="-1">No course available</option>');
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				console.log(message);
				$courseDDL.html('<option id="0">Select Teacher</option>');
//				for (var t = 0; t < message.length; t++){
//					var teacherId = message[t].teacherId;
//					var teacherName = message[t].name;
//					$courseDDL.append('<option value=' + message[t].teacherId + '>' + teacherName + '</option>' );
//				}
			} else{
				$teacherDDL.html('<option id="-1">No course available</option>');
			}
		}
	});
	
	document.getElementById('courseDDL').disabled = !elem.selectedIndex;
}
//
//function generateClassroomOption(){
//	var branchId = Number(localStorage.getItem("branchId"));
//	var $classselect = $('#classroom');
//	$classselect.html('');
//	var input = {};
//	input.branchId = branchId
//	var inputStr = JSON.stringify(input);
//	inputStr = encodeURIComponent(inputStr);
//	
//	$.ajax({
//		url : '../VI/GetClassroomsByBranchServlet?input=' + inputStr, //this part sends to the servlet
//		method : 'POST',
//		dataType : 'json',
//		error : function(err) {
//			$classselect.html('<option id="-1">none available</option>');
//		},
//		success : function(data) {
//			var status = data.status; 
//			var message = data.message;
//			
//			if (status == 1) {
////				console.log(JSON.stringify(message));
//				$classselect.html('<option id="0"></option>');
//				for (var c = 0; c < message.length; c++){
//					$classselect.append('<option value=' + message[c].classroomId + '>' + message[c].name + '</option>' );
//				}
//			} else{
//				$classselect.html('<option id="-1">none available</option>');
//			}
//		}
//	});
//}

//var schedules = [];
//function scheduleCall(){
//	var branchId = Number(localStorage.getItem("branchId"));
//	var input = {};
//	input.branchId = branchId
//	var inputStr = JSON.stringify(input);
//	inputStr = encodeURIComponent(inputStr);
//	$.ajax({
//		url : '../VI/GetSchedulesByBranchServlet?input=' + inputStr, //this part sends to the servlet
//		method : 'POST',
//		dataType : 'json',
//		error : function(err) {
//			console.log(err);
//		},
//		success : function(data) {
//			// you have to do a for loop to get all the data.'
//			var status = data.status; 
//			var message = data.message;
//			
//			if (status == 1) {
//				for (var i = 0; i < message.length; i++){
//
//					var id = message[i].id;
//					var title = message[i].title;
//					var allDay = message[i].allDay;
//					var classroomId = message[i].classroom.classroomId;
//					var roomCap = message[i].classroom.roomCapacity;
//					var desc = message[i].description;
//					var teacherCourseId = message[i].teacherCourse.teacherCourseId;
//					
//					var sdt = moment(message[i].start, "YYYY-MM-DD HH:mm:ss");
//					var edt = moment(message[i].end, "YYYY-MM-DD HH:mm:ss");
//					
//					var dow = [];
//					var ranges = [];
//					var startTime = sdt.format('HH:mm');
//					var endTime = edt.format('HH:mm')
//					var startDate = sdt.format('YYYY-MM-DD');
//					var endDate = edt.format('YYYY-MM-DD');
//					dow.push(sdt.format('E'));
//					
//					var scheduleStr = {
//						title: title,
//						id: id,
//						start: startTime,
//						end: endTime,
//						dow: dow,
//						ranges: [{
//							start: sdt,
//							end: edt
//						}],
//						desc: desc,
//						classroomId: classroomId,
//						roomCap: roomCap,
//						teacherCourseId: teacherCourseId
//					};
//					schedules.push(scheduleStr);
//				}
//				calendarInitiate(schedules);
//			} else {
//				console.log("try again");
//			}
//		}
//	});
//}


//function calendarInitiate(schedJSON){
//		$('#calendar').fullCalendar({
//			header: {
//				left: 'prev,next today',
//				center: 'title',
//				right: 'month,agendaWeek,agendaDay'
//			},
//			contentHeight: 500,
//			defaultDate: moment(),
//			eventLimit: true, // allow "more" link when too many events
//			events: schedJSON,
//			eventRender: function(event, element, view){
//				var content = '<b>Description</b>: ' + event.desc + '<br> <b>Classroom Id</b>: ' + event.classroomId  + '<br><b>Room Capacity</b>: ' + event.roomCap + '<br><br> <b>Click on me to edit</b>';
//				 $(element).tooltip({title: content, html: true, container: 'body'}); 
//		        return (event.ranges.filter(function(range){
//		            return (event.start.isBefore(range.end) &&
//		                    event.end.isAfter(range.start));
//		        }).length)>0;
//		    },
//		    eventClick: function(calEvent, jsEvent, view) {
//		    	bootbox.dialog({
//		    		title: "Edit Schedule for " + calEvent.title,
//		    		message: '<div class = "row">' +
//			    			'<label class="col-md-2 control-label" for="schedDesc"><b>Schedule Name</b></label> ' +
//							'<div class="col-md-10"> ' +
//								'<input id="schedName" name="schedName" type="text" class="form-control input-md" value= "' + calEvent.title + '"> ' +
//							'</div> ' +
//						'</div>' +
//					
//					'<div class = "row">' +
//						'<label class="col-md-2 control-label" for="schedDesc"><b>Schedule Description<b></label> ' +
//						'<div class="col-md-10"> ' +
//							'<input id="schedDesc" name="schedDesc" type="text" class="form-control input-md" value= "' + calEvent.desc + '"> ' +
//						'</div> ' +
//					'</div>' +
//						
//					'<div class = "row">' +
//						'<label class="col-md-2 control-label" for="startDate"><b>Schedule Start Date<b></label> ' +
//						'<div class="col-md-10"> ' +
//							'<input id="startDate" name="startDate" type="date" class="form-control input-md" value= "' + calEvent.ranges[0].start.format("YYYY-MM-DD") + '"> ' +
//						'</div> ' +
//					'</div>' + 
//					
//					'<div class = "row">' +
//						'<label class="col-md-2 control-label" for="startTime"><b> Schedule Start Time<b></label> ' +
//						'<div class="col-md-10"> ' +
//							'<input id="startTime" name="startTime" type="time" class="form-control input-md" value= "' + calEvent.start.format("HH:mm") + '"> ' +
//						'</div> ' +
//					'</div>' + 
//					
//					'<div class = "row">' +
//						'<label class="col-md-2 control-label" for="endDate"><b> Schedule End Date<b></label> ' +
//						'<div class="col-md-10"> ' +
//							'<input id="endDate" name="endDate" type="date" class="form-control input-md" value= "' + calEvent.ranges[0].end.format("YYYY-MM-DD") + '"> ' +
//						'</div> ' +
//					'</div>' +
//					
//					'<div class = "row">' +
//						'<label class="col-md-2 control-label" for="endTime"><b> Schedule End Time<b></label> ' +
//						'<div class="col-md-10"> ' +
//							'<input id="endTime" name="endTime" type="time" class="form-control input-md" value= "' + calEvent.end.format("HH:mm") + '"> ' +
//						'</div> ' +
//					'</div>',
//								
//				onEscape: function() {},
//	    		buttons: {
//    				main: {
//    				      label: "Delete",
//    				      className: "btn-primary",
//    				      callback: function() {
//    				        bootbox.dialog({
//    				        	title: "Delete Schedule",
//    				        	message: "Are you sure you want to delete <b>" + calEvent.title + "</b>?",
//    				        	onEscape: function() {},
//    				        	buttons: {
//    				        		main: {
//    				        			label: "Ok",
//    				        			className: "btn-primary",
//    				        			callback: function(){
//    				        				var id = Number(calEvent.id);
//    				        				var input = {};
//    				    					input.scheduleId = id;
//    				    					var inputStr = JSON.stringify(input);
//    				    					inputStr = encodeURIComponent(inputStr);
//    				        				
//    				        				$.ajax({
//    				    						url : '../VI/DeleteScheduleServlet?input=' + inputStr, //this part sends to the servlet
//    				    						method : 'POST',
//    				    						dataType : 'json',
//    				    						error : function(err) {
//    				    							console.log(err);
//    				    						},
//    				    						success : function(data) {
//    				    							console.log(data);
//    				    							var status = data.status; 
//    				    							var message = data.message;
//    				    							
//    				    							if (status == 1) {
//    				    								window.location.reload();
//    				    							} else {
//    				    								console.log("error");
//    				    							}
//    				    						}
//    				    					});
//    				        			}
//    				        		}
//    				        	}
//    				        });
//    				      }
//    					},
//	    			
//	    			success:{
//	    				label: "Save",
//	    				className: "btn-success",
//	    				
//	    				callback: function(){
//	    					var id = Number(calEvent.id);
//	    					var updatedName = $("#schedName").val();
//	    					var updatedDesc = $("#schedDesc").val();
//	    					var updatedSD = $("#startDate").val();
//	    					var updatedST = $("#startTime").val();
//	    					var updatedED = $("#endDate").val();
//	    					var updatedET = $("#endTime").val();
//	    					var teacherCourseId = calEvent.teacherCourseId;
//	    					var classroomId = calEvent.classroomId;
//	    					
//	    					var ustart = updatedSD + " " + updatedST;
//	    					var uend = updatedED + " " + updatedET;
//	    					
//	    					var input = {};
//	    					input.scheduleId = id;
//	    					input.name = updatedName;
//	    					input.description = updatedDesc;
//	    					input.planStartDate = moment(ustart).format();
//	    					input.planEndDate = moment(uend).format();
//	    					input.teacherCourseId = Number(teacherCourseId);
//	    					input.classroomId = Number(classroomId);
//	    					
//	    					var inputStr = JSON.stringify(input);
//	    					inputStr = encodeURIComponent(inputStr);
//	    					
//	    					$.ajax({
//	    						url : '../VI/UpdateScheduleServlet?input=' + inputStr, //this part sends to the servlet
//	    						method : 'POST',
//	    						dataType : 'json',
//	    						error : function(err) {
//	    							console.log(err);
//	    						},
//	    						success : function(data) {
//	    							console.log(data);
//	    							var status = data.status; 
//	    							var message = data.message;
//	    							
//	    							if (status == 1) {
//	    								console.log(message);
//	    								window.location.reload();
//	    							} else {
//	    								$("#message").html("Something's wrong, please try again!");
//	    							}
//	    						}
//	    					});
//	    					
//	    					}
//	    				}
//	    			}
//		    	});
//		    }
//		});
//}

var schedules = []; 
function getSchedules(){
	var branchId = 1;
//	var branchId = Number(localStorage.getItem("branchId"));
	var input = {};
	input.branchId = branchId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetScheduleEventsByBranchServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			 console.log(err);
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				 for (var e = 0; e < message.length; e++){
					 var scheduleEventId = message[e].scheduleEventId;
					 var name = message[e].schedule.name;
					 var description = message[e].schedule.description;
					 var start = moment(message[e].planStartDate, "YYYY-MM-DD HH:mm:ss");
					 var end = moment(message[e].planEndDate, "YYYY-MM-DD HH:mm:ss");
					 
					 var scheduleStr = {
							 id: scheduleEventId,
							 title: name,
							 start: start,
							 end: end,
							 allDay: false
							};
					 
					 schedules.push(scheduleStr);
				 }
				localStorage.setItem("scheduleMsg", JSON.stringify(message));
				localStorage.setItem("schedules", JSON.stringify(schedules));
				 
			} else{
				console.log(message);
			}
		}
	});
}

function calendarInitiate(){
	var scheduleEvents = JSON.parse(localStorage.getItem("schedules"));
	var message = JSON.parse(localStorage.getItem("scheduleMsg"));
	
	
	console.log(message);
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		defaultDate: moment(),
		eventLimit: true, // allow "more" link when too many events
		events: scheduleEvents,
		 eventClick: function(calEvent, jsEvent, view) {
			 bootbox.dialog({
				 title: "Example",
				 message: function (message){
					 return JSON.stringify(message)
				 },
				 onEscape: function() {},
				 buttons:{
					main: {
						label: "Okay",
						className:"btn-primary",
						callback: function(){
							console.log("clicked on okay.");
						}
					}
				 }
			 });
		 }
	});
}


function schedulePanelView(){
	$(".view").click(function() {
		$(".collapse").collapse('toggle');
	});
}

function searchStudent(){
	var studentNRIC = $("#NRIC").val();
	var input = {};
	input.studentNric = studentNRIC;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetStudentByNricServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				retrieveStudentSchedule(message.studentId);	
			} else{
				$attendanceDDL.html('<option id="-1">No dates available</option>');
			}
						
		}
	});
};

function retrieveStudentSchedule(id){
	var input = {};
	input.studentId = id;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetScheduleEventsByStudentServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
//				console.log(message);
//				var startDate = "";
//				var courseName = "";
//				for (var a = 0; a < message.length; a++){
//					startDate = moment(message[a].planStartDate);
//					courseName = message[a].schedule.course.name
//					
//					console.log(startDate);
//					console.log(courseName);
//					
////					var bbMsg = "<dl><dt>Course</dt><dd>" + courseName + "</dd> <dt>Date</dt><dd>" + startDate + "</dd>"
//					
////					if (startDate >= moment() && message[a].schedule.course.courseId === courseId){
////						var attendanceId = a;
////						$attendanceDDL.append('<option value=' + attendanceId + '>' + message[a].planStartDate + '</option>' );
////					}
//				}
				
				bootbox.dialog({
					 title: "View Student Schedule", 
					 message: "<dl><dt>Course</dt><dd>Mathematics</dd> <dt>2016-01-29 15:00</dt><dd></dd>",
					 onEscape: function() {},
					 buttons:{
						main: {
							label: "Okay",
							className:"btn-primary",
							callback: function(){
								console.log("clicked on okay.");
							}
						}
					 }
				 });
				
			} else{
				console.log(message);
			}
		}
	});
	

}

function getStudentId(studentNRIC){
	var $attendanceDDL = $('#attendanceDDL');
	$attendanceDDL.html('');
	var input = {};
	input.studentNric = studentNRIC
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetStudentByNricServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$attendanceDDL.html('<option id="-1">No dates available</option>');
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				localStorage.setItem("studentIdResched", message.studentId);
				localStorage.setItem("studentNRICResched", studentNRIC);
				retrieveCourse(message.studentId);
					
			} else{
				$attendanceDDL.html('<option id="-1">No dates available</option>');
			}
						
		}
	});
}

function retrieveCourse(studentId){
	var $studentCourseDDL = $('#studentCourseDDl');
	$studentCourseDDL.html('');
	var input = {};
	input.studentId = studentId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	//error at retrieveAttendances
	$.ajax({
		url : '../VI/GetCoursesByStudentServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$studentCourseDDL.html('<option id="-1">No courses available</option>');
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				document.getElementById('studentCourseDDl').disabled = !studentId;
				$studentCourseDDL.html('<option id="0">Select Course</option>');
				
				for (var a = 0; a < message.length; a++){
					var courseId = message[a].courseId;
					var courseName = message[a].name
					$studentCourseDDL.append('<option value=' + courseId + '>' + courseName + '</option>' );
				}
				
			} else{
				$studentCourseDDL.html('<option id="-1">No dates available</option>');
			}
		}
	});
}

function getCourseValue(){
	var course = document.getElementById("studentCourseDDl");
	var cId = Number(course.options[course.selectedIndex].value);
	var courseName = course.options[course.selectedIndex].text;
	var sId = Number(localStorage.getItem("studentIdResched"));
	
	console.log("value of course: " + cId)
	
	localStorage.setItem("rescheduleCourse", cId);
	localStorage.setItem("rescheduleCourseName", courseName);
	retrieveAttendances(sId, cId);
}

function retrieveAttendances(studentId, courseId){
	console.log(courseId);
	
	var $attendanceDDL = $('#attendanceDDL');
	$attendanceDDL.html('');
	var input = {};
	input.studentId = studentId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	//error at retrieveAttendances. need to ask song rui to fix
	
	$.ajax({
		url : '../VI/GetScheduleEventsByStudentServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$attendanceDDL.html('<option id="-1">No dates available</option>');
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				document.getElementById('attendanceDDL').disabled = !studentId;
				$attendanceDDL.html('<option id="0">Select Date to change</option>');
				
				console.log(message);
				for (var a = 0; a < message.length; a++){
					var startDate = moment(message[a].planStartDate);
					
					if (startDate >= moment() && message[a].schedule.course.courseId === courseId){
						var attendanceId = a;
						$attendanceDDL.append('<option value=' + attendanceId + '>' + message[a].planStartDate + '</option>' );
					}
				}
				
			} else{
				$attendanceDDL.html('<option id="-1">No dates available</option>');
			}
		}
	});
	
}

function tryrandom(){	
	var studentName = $("#reschedStudentName").val();
	localStorage.setItem("studentReschedName", studentName);
	
	var selectedAttendance = document.getElementById("attendanceDDL");
	var selectedAttendanceId = Number(selectedAttendance.options[selectedAttendance.selectedIndex].value);
	var selectedAttendanceDate = selectedAttendance.options[selectedAttendance.selectedIndex].text;
	
	localStorage.setItem("reschedAId", selectedAttendanceId);
	localStorage.setItem("reschedADate", selectedAttendanceDate);

// ---------------- to retrieve your data --------------------------	
//	console.log(localStorage.getItem("studentReschedName"));
//	console.log(localStorage.getItem("rescheduleCourse"));
//	console.log(localStorage.getItem("rescheduleCourseName"));
//	console.log(localStorage.getItem("studentIdResched"));
//	console.log(localStorage.getItem("studentNRICResched"));
//	console.log(localStorage.getItem("reschedAId"));
//	console.log(localStorage.getItem("reschedADate"));

	window.location = "rescheduleStudent.jsp";
}

function populateNewSchedules(){
	document.getElementById("studentName").innerHTML = localStorage.getItem("studentReschedName");
	document.getElementById("studentNRIC").innerHTML = localStorage.getItem("studentNRICResched");
	document.getElementById("courseSelected").innerHTML = localStorage.getItem("rescheduleCourseName");
	var selectedDate =  moment(localStorage.getItem("reschedADate"));
	document.getElementById("dateSelected").innerHTML = selectedDate;
	
	$.fn.dataTable.ext.errMode = 'none';
//	var branchId = Number(localStorage.getItem("branchId"));
	var courseId = Number(localStorage.getItem("rescheduleCourse"));
	var input = {};
	input.courseId = courseId;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	var table = $('#scheduleTable')
			.on('error.dt', function(e, settings, techNote, message) {
					console.log('An error has been reported by DataTables: ',message);
			}).DataTable(
					{
					ajax : {
							url : '../VI/GetScheduleEventsByCourseServlet?input='+ inputStr,
							// dataSrc: 'message'
							dataSrc : function(json) {
								var message = json.message;
								console.log(message);
								
								var return_data = new Array();
								
								for (var i = 0; i < message.length; i++) {
									var planM = moment(message[i].planStartDate);
									var plandateM = moment(planM, "DD-MM-YYYY");
									
									if (message[i].planStartDate > localStorage.getItem("reschedADate")){
										var sched = {	
												'scheduleId':message[i].scheduleEventId,
												'courseName':message[i].schedule.name,
												'scheduleDate': moment(message[i].planStartDate).format("dddd, MMMM Do YYYY, h:mm a"),
												'teacherName':message[i].schedule.teacher.name,
												'button' : "<button class='btn btn-sm btn-success' onclick='reschedStudent()'>Reschedule</button>"
												
										}
										return_data.push(sched)
								}
								}
								return return_data;
							}
						},
						columns : [ 
						            {"data" : 'scheduleId'}, 
						            {"data" : 'courseName'}, 
						            {"data" : 'scheduleDate'}, 
						            {"data" : 'teacherName'}, 
						            {"data" : 'button'}
						]
					});
	
}

function reschedStudent(){
	var table = $('#scheduleTable').DataTable();
	$('#scheduleTable tbody').off('click').on( 'click', 'button', function () {
		var tr = $(this).closest('tr');
		var row = table.row( tr );

		bootbox.dialog({
			title: "Confirm reschedule",
			message: "Do you want to reschedule " + localStorage.getItem("studentReschedName") + "'s schedule to <u>" + row.data().scheduleDate + "</u>?",
			onEscape: function() {},
			buttons: {
				success:{
					label: "Save!",
					className: "btn-success",

					callback: function(){
    					var input = {};
    					input.scheduleEventId = Number(row.data().scheduleId);
    					input.attendanceId = 9; //change this please!!!!! when servlet is updated
    					var inputStr = JSON.stringify(input);
    					inputStr = encodeURIComponent(inputStr);
    					
    					$.ajax({
    						url : '../VI/ChangeScheduleEventInAttendanceServlet?input=' + inputStr, //this part sends to the servlet
    						method : 'POST',
    						dataType : 'json',
    						error : function(err) {
    							console.log(err);
    						},
    						success : function(data) {
    							console.log(data);
    							var status = data.status; 
    							var message = data.message;
    							
    							if (status == 1) {
    								console.log(message);
    								bootbox.dialog({
    									title: "Reschedule confirmed!",
    									message: "Updated!"
    									
    								})
    								window.location = "adminMain.jsp";
    							} else {
    								$("#message").html("Something's wrong, please try again!");
    							}
    						}
    					});
					}
				}
			}	
		}); 
	});
}

function createSchedule(){
	var scheduleName = $("#scheduleName").val();
	var scheduleDesc = $("#scheduleDesc").val();
	var scheduleStartDate = $("#scheduleStartDate").val();
	var scheduleStartTime = $("#scheduleStartTime").val();
	var scheduleEndDate = $("#scheduleEndDate").val();
	var scheduleEndTime = $("#scheduleEndTime").val();
	var teacher = document.getElementById("teachercourse");
	var classroom = document.getElementById("classroom");
	var teacherCourseId = teacher.options[teacher.selectedIndex].value;
	var classroomId = classroom.options[classroom.selectedIndex].value;
	
	var start = scheduleStartDate + " " + scheduleStartTime;
	var end = scheduleEndDate + " " + scheduleEndTime;
		
	var input = {};
	input.name = scheduleName;
	input.description = scheduleDesc;
	input.planStartDate = moment(start).format();
	input.planEndDate = moment(end).format();
	input.teacherCourseId = Number(teacherCourseId);
	input.classroomId = Number(classroomId);
	
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/CreateScheduleServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$("#message").html("System has some error. Please try again.");
		},
		success : function(data) {
			console.log(data);
			var status = data.status; //shows the success/failure of the servlet request
			var message = data.message;
			
			if (status == 1){
				window.location.reload();
			}
		}
		
	});
	
}