$(document).ready(function() {
	branchCourseCall();
//	calendarInitiate()
});

function branchCourseCall(){
	var branchId = Number(localStorage.getItem("branchId"));
	var input = {};
	input.branchId = branchId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/GetBranchCoursesByBranchServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			// you have to do a for loop to get all the data.'
			console.log(data);
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				for (var i = 0; i < message.length; i++){
					var obj = message[i];
					var id = obj.course.courseId;
					scheduleServletCall(id);
				}		
			} else {
				console.log("try agin");
			}
		}
	});
}

function scheduleServletCall(id){
	var courseId = Number(id);
	var input = {};
	input.courseId = courseId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetSchedulesByCourse?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				console.log(JSON.stringify(message));
				//use for loop to create new json object and send it back to previous call.
			} else {
				console.log("try agin");
			}
		}
	});
	
}

function calendarInitiate(){
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
			defaultDate: date,
			eventLimit: true, // allow "more" link when too many events
			events: [
				{
					title: 'SP Tourism: PM Shift',
                    start: new Date(y, m, 17, 12, 00),
                    end: new Date(y, m, 17, 18, 00),
                    allDay: false
				}
			]

		});
}