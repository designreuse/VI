$(document).ready(function() {
	scheduleCall();
});

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
			console.log(data);
			var status = data.status; 
			var message = data.message;
			
			if (status == 1) {
				for (var i = 0; i < message.length; i++){
					var id = message[i].id;
					var start = message[i].start;
					var title = message[i].title;
					var end = message[i].end;
					var allDay = message[i].allDay;
					
					var dt = moment(start, "YYYY-MM-DD HH:mm:ss");
					console.log(dt.format('dddd'));
					
					//if-else for calendar schedules!
					
					var scheduleStr = '{"id": ' + id + ', "title": "' + title + '", "start": "' + start + '", "end": "' + end + '", "allDay": false }';
					var schedJSON = JSON.parse(scheduleStr);
					schedules.push(schedJSON);
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
			defaultDate: date,
			eventLimit: true, // allow "more" link when too many events
			events: schedJSON
		});
}