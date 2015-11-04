$(document).ready(function() {
	scheduleCall();
	schedulePanelView();
	generateTeacherOption();
	generateClassroomOption();
});

function generateTeacherOption(){
	var branchId = Number(localStorage.getItem("branchId"));
	var $select = $('#teachercourse');
	$select.html('');
	var input = {};
	input.branchId = branchId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetTeacherCoursesByBranchServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			 $select.html('<option id="-1">none available</option>');
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				 $select.html('<option id="0"></option>');
				for (var t = 0; t < message.length; t++){
					var selectText = message[t].teacher.name + ' : ' + message[t].course.name;
					$select.append('<option value=' + message[t].teacherCourseId + '>' + selectText + '</option>' );
				}
			} else{
				 $select.html('<option id="-1">none available</option>');
			}
		}
	});
}

function generateClassroomOption(){
	var branchId = Number(localStorage.getItem("branchId"));
	var $classselect = $('#classroom');
	$classselect.html('');
	var input = {};
	input.branchId = branchId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetClassroomsByBranchServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			$classselect.html('<option id="-1">none available</option>');
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
//				console.log(JSON.stringify(message));
				$classselect.html('<option id="0"></option>');
				for (var c = 0; c < message.length; c++){
					$classselect.append('<option value=' + message[c].classroomId + '>' + message[c].name + '</option>' );
				}
			} else{
				$classselect.html('<option id="-1">none available</option>');
			}
		}
	});
}

var schedules = [];
function scheduleCall(){
	var branchId = Number(localStorage.getItem("branchId"));
	var input = {};
	input.branchId = branchId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/GetSchedulesByBranchServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			// you have to do a for loop to get all the data.'
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				for (var i = 0; i < message.length; i++){

					var id = message[i].id;
					var title = message[i].title;
					var allDay = message[i].allDay;
					var classroomId = message[i].classroom.classroomId;
					var roomCap = message[i].classroom.roomCapacity;
					var desc = message[i].description;
					var teacherCourseId = message[i].teacherCourse.teacherCourseId;
					
					var sdt = moment(message[i].start, "YYYY-MM-DD HH:mm:ss");
					var edt = moment(message[i].end, "YYYY-MM-DD HH:mm:ss");
					
					var dow = [];
					var ranges = [];
					var startTime = sdt.format('HH:mm');
					var endTime = edt.format('HH:mm')
					var startDate = sdt.format('YYYY-MM-DD');
					var endDate = edt.format('YYYY-MM-DD');
					dow.push(sdt.format('E'));
					
					var scheduleStr = {
						title: title,
						id: id,
						start: startTime,
						end: endTime,
						dow: dow,
						ranges: [{
							start: sdt,
							end: edt
						}],
						desc: desc,
						classroomId: classroomId,
						roomCap: roomCap,
						teacherCourseId: teacherCourseId
					};
					schedules.push(scheduleStr);
				}
				calendarInitiate(schedules);
			} else {
				console.log("try again");
			}
		}
	});
}


function calendarInitiate(schedJSON){
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			contentHeight: 500,
			defaultDate: moment(),
			eventLimit: true, // allow "more" link when too many events
			events: schedJSON,
			eventRender: function(event, element, view){
				var content = '<b>Description</b>: ' + event.desc + '<br> <b>Classroom Id</b>: ' + event.classroomId  + '<br><b>Room Capacity</b>: ' + event.roomCap + '<br><br> <b>Click on me to edit</b>';
				 $(element).tooltip({title: content, html: true, container: 'body'}); 
		        return (event.ranges.filter(function(range){
		            return (event.start.isBefore(range.end) &&
		                    event.end.isAfter(range.start));
		        }).length)>0;
		    },
		    eventClick: function(calEvent, jsEvent, view) {
		    	bootbox.dialog({
		    		title: "Edit Schedule for " + calEvent.title,
		    		message: '<div class = "row">' +
			    			'<label class="col-md-2 control-label" for="schedDesc"><b>Schedule Name</b></label> ' +
							'<div class="col-md-10"> ' +
								'<input id="schedName" name="schedName" type="text" class="form-control input-md" value= "' + calEvent.title + '"> ' +
							'</div> ' +
						'</div>' +
					
					'<div class = "row">' +
						'<label class="col-md-2 control-label" for="schedDesc"><b>Schedule Description<b></label> ' +
						'<div class="col-md-10"> ' +
							'<input id="schedDesc" name="schedDesc" type="text" class="form-control input-md" value= "' + calEvent.desc + '"> ' +
						'</div> ' +
					'</div>' +
						
					'<div class = "row">' +
						'<label class="col-md-2 control-label" for="startDate"><b>Schedule Start Date<b></label> ' +
						'<div class="col-md-10"> ' +
							'<input id="startDate" name="startDate" type="date" class="form-control input-md" value= "' + calEvent.ranges[0].start.format("YYYY-MM-DD") + '"> ' +
						'</div> ' +
					'</div>' + 
					
					'<div class = "row">' +
						'<label class="col-md-2 control-label" for="startTime"><b> Schedule Start Time<b></label> ' +
						'<div class="col-md-10"> ' +
							'<input id="startTime" name="startTime" type="time" class="form-control input-md" value= "' + calEvent.start.format("HH:mm") + '"> ' +
						'</div> ' +
					'</div>' + 
					
					'<div class = "row">' +
						'<label class="col-md-2 control-label" for="endDate"><b> Schedule End Date<b></label> ' +
						'<div class="col-md-10"> ' +
							'<input id="endDate" name="endDate" type="date" class="form-control input-md" value= "' + calEvent.ranges[0].end.format("YYYY-MM-DD") + '"> ' +
						'</div> ' +
					'</div>' +
					
					'<div class = "row">' +
						'<label class="col-md-2 control-label" for="endTime"><b> Schedule End Time<b></label> ' +
						'<div class="col-md-10"> ' +
							'<input id="endTime" name="endTime" type="time" class="form-control input-md" value= "' + calEvent.end.format("HH:mm") + '"> ' +
						'</div> ' +
					'</div>',
								
				onEscape: function() {},
	    		buttons: {
	    			success:{
	    				label: "Save",
	    				className: "btn-success",
	    				
	    				callback: function(){
	    					var id = Number(calEvent.id);
	    					var updatedName = $("#schedName").val();
	    					var updatedDesc = $("#schedDesc").val();
	    					var updatedSD = $("#startDate").val();
	    					var updatedST = $("#startTime").val();
	    					var updatedED = $("#endDate").val();
	    					var updatedET = $("#endTime").val();
	    					var teacherCourseId = calEvent.teacherCourseId;
	    					var classroomId = calEvent.classroomId;
	    					
	    					var ustart = updatedSD + " " + updatedST;
	    					var uend = updatedED + " " + updatedET;
	    					
	    					var input = {};
	    					input.scheduleId = id;
	    					input.name = updatedName;
	    					input.description = updatedDesc;
	    					input.planStartDate = moment(ustart).format();
	    					input.planEndDate = moment(uend).format();
	    					input.teacherCourseId = Number(teacherCourseId);
	    					input.classroomId = Number(classroomId);
	    					
	    					var inputStr = JSON.stringify(input);
	    					inputStr = encodeURIComponent(inputStr);
	    					
	    					$.ajax({
	    						url : '../VI/UpdateScheduleServlet?input=' + inputStr, //this part sends to the servlet
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
	    								
	    							} else {
	    								$("#message").html("Something's wrong, please try again!");
	    							}
	    						}
	    					});
	    					
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
			var status = data.status; //shows the  success/failure of the servlet request
			var message = data.message;
			
			if (status == 1){
				window.location.reload();
			}
		}
		
	});
//	}
	
//	console.log(scheduleName);
//	console.log(scheduleDesc);
//	console.log(scheduleStartDate);
//	console.log(scheduleStartTime);
//	console.log(scheduleEndDate);
//	console.log(scheduleEndTime);
//	console.log(teacherCourseId);
//	console.log(classroomId)
}