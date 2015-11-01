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
					$select.append('<option id="' + message[t].teacherCourseId + '">' + selectText + '</option>' );
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
				console.log(JSON.stringify(message));
				
				$classselect.html('<option id="0"></option>');
				for (var c = 0; c < message.length; c++){
					$classselect.append('<option id="' + message[c].classroomId + '">' + message[c].name + '</option>' );
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
						}]
					};
					schedules.push(scheduleStr);
				}
				calendarInitiate(schedules);
			} else {
				console.log("try agin");
			}
		}
	});
}


function calendarInitiate(schedJSON){
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			contentHeight: 450,
			defaultDate: date,
			eventLimit: true, // allow "more" link when too many events
			events: schedJSON,
			eventRender: function(event, element, view){
		        return (event.ranges.filter(function(range){
		            return (event.start.isBefore(range.end) &&
		                    event.end.isAfter(range.start));
		        }).length)>0;
		    },
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
//	var teacherCourseId = $("#teacher").val();
//	var classroomId = $("#classroom")val();
	
	console.log(scheduleName);
	console.log(scheduleDesc);
	console.log(scheduleStartDate);
	console.log(scheduleStartTime);
	console.log(scheduleEndDate);
	console.log(scheduleEndTime);
}