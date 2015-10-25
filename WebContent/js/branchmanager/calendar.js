$(document).ready(function() {
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
				},
				{
					title: 'SP Tourism: PM Shift',
                    start: new Date(y, m, 24, 12, 00),
                    end: new Date(y, m, 24, 18, 00),
                    allDay: false
				} ,
				{
					title: 'SP Tourism: PM Shift',
                    start: new Date(y, m, 25, 8, 00),
                    end: new Date(y, m, 25, 13, 00),
                    allDay: false
				} ,
				{
					title: 'SP Tourism: AM Shift',
                    start: new Date(y, m, 31, 8, 00),
                    end: new Date(y, m, 31, 13, 00),
                    allDay: false
				}
			]

		});
		
});