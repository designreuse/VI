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
			defaultDate: date,
			eventLimit: true, // allow "more" link when too many events
			events: schedJSON,
			eventRender: function(event, element, view){
		        console.log(event.start.format());
		        return (event.ranges.filter(function(range){
		            return (event.start.isBefore(range.end) &&
		                    event.end.isAfter(range.start));
		        }).length)>0;
		    },
		});
}