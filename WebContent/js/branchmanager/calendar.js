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
				localStorage.setItem("schedules", JSON.stringify(schedules));
				 
			} else{
				console.log(message);
			}
		}
	});
}

function calendarInitiate(){
	var ob = JSON.parse(localStorage.getItem("schedules"));
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		defaultDate: moment(),
		eventLimit: true, // allow "more" link when too many events
		events: ob,
		 eventClick: function(calEvent, jsEvent, view) {
			 bootbox.dialog({
				 title: "Example",
				 message: "display current details " + calEvent.title,
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
				retrieveAttendances(message.studentId);
			} else{
				$attendanceDDL.html('<option id="-1">No dates available</option>');
			}
						
		}
	});
}

function retrieveAttendances(studentId){
	var $attendanceDDL = $('#attendanceDDL');
	$attendanceDDL.html('');
	var input = {};
	input.studentId = studentId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	//error at retrieveAttendances
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
				console.log(message);
				document.getElementById('attendanceDDL').disabled = !studentId;
				$attendanceDDL.html('<option id="0">Select Date to change</option>');
//				for (var t = 0; t < message.length; t++){
//					var teacherId = message[t].teacherId;
//					var teacherName = message[t].name;
//					$courseDDL.append('<option value=' + message[t].teacherId + '>' + teacherName + '</option>' );
//				}
			} else{
				$attendanceDDL.html('<option id="-1">No dates available</option>');
			}
		}
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