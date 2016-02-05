$(document).ready(function() {
	var teacherId = localStorage.getItem('teacherId');
	if (teacherId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
		populateScheduleEvents();
		 localStorage.removeItem("studentList");
	}
});
 
function populateScheduleEvents(){
//	var teacherId = 2; -- for local testing only--
	var teacherId = Number(localStorage.getItem("teacherId"));
	var $coursesDDL = $('#coursesDDL');
	$coursesDDL.html('');
	var input = {};
	input.teacherId = teacherId;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
	url : '../VI/GetScheduleEventsByTeacherServlet?input=' + inputStr, //this part sends to the servlet
	method : 'POST',
	dataType : 'json',
	error : function(err) {
		console.log(err);
		$coursesDDL.html('<option id="-1">No Courses Available</option>');
	},
	success : function(data) {
		var status = data.status; 
		var message = data.message;
		
		if (status == 1) {	
			localStorage.setItem("scheduleEvents", JSON.stringify(message));
			$coursesDDL.html('<option id="-1">---- Select Course -----</option>');
			for (var t = 0; t < message.length; t++){
				var planDate = moment(message[t].planStartDate);
				if (planDate <= moment() && planDate >= moment().subtract(1, "w")){
					var selectText = message[t].schedule.description + " (" + planDate.format("L") +")";
					$coursesDDL.append('<option value=' + t + '>' + selectText + '</option>' );
				}
			}	
		} else{
			$coursesDDL.html('<option id="-1">No Courses Available</option>');
		}
	}
});	
} 

function populateStudents(){
	$(".collapse").show();
	var courseDDL = document.getElementById("coursesDDL");
	var id = Number(courseDDL.options[courseDDL.selectedIndex].value);
	var description = courseDDL.options[courseDDL.selectedIndex].text
	var scheduleEvents = JSON.parse(localStorage.getItem("scheduleEvents"));
	localStorage.setItem("courseId", JSON.stringify(scheduleEvents[id].schedule.course.courseId));
	localStorage.setItem("description", description);
	var attendance = scheduleEvents[id].attendances;
	
	
	$('#dynamicStudentList').html("");
	
	if (attendance.length >= 0){
		localStorage.setItem("studentList", JSON.stringify(attendance));
		for (var a = 0; a < attendance.length; a++){
			var i = attendance[a].student.studentId;
			var name = attendance[a].student.name;
			
			$("#dynamicStudentList").append("<div class='box-group header"+ i +"' id='accordion'>");
			$(".header"+ i ).append("<div class='panel box box-solid box-success headerpanel"+ i +"'>");
			$(".headerpanel" + i).append("<div class='box-header with-border headerwords" + i + "'>");
			$(".headerwords" + i).append("<h4 class='box-title tog" + i + "'>");
			$(".tog" + i).append("<a data-toggle='collapse' data-parent='#accordion' href='#addSched" + i + "'>" + name + " </a>");
			
			//accordion body
			$(".header"+ i).append("<div id='addSched" + i + "' class='panel-collapse collapse'>");
			$("#addSched" + i).append("<div class='box-body boxbody" + i + "'>");
			$(".boxbody" + i ).append("<div class='row' id = 'grading" + i + "'>");
			$(".boxbody" + i ).append("<div class='row' id = 'feedback" + i + "'>");
			
			$("#grading" + i ).append("<div class='col-md-4' id='colCourseBookLevel" + i + "'>");
			$("#colCourseBookLevel" + i ).append("<div class='box box-success' id='boxCourseBookLevel" + i + "'>");
			$("#boxCourseBookLevel" + i ).append("<div class='box-body' id='bodyCourseBookLevel" + i + "'>");
			$("#bodyCourseBookLevel" + i ).append("<p align='center'>Course and booklet level");
			$("#bodyCourseBookLevel" + i ).append("<input class='form-control input-sm' id='courseLevel" + i + "' placeholder='Input Course Level' required />");
			$("#bodyCourseBookLevel" + i ).append("<input class='form-control input-sm' id='bookLevel" + i + "' placeholder='Input Book Level' required />");
			
			$("#grading" + i ).append("<div class='col-md-4' id='colResult" + i + "'>");
			$("#colResult" + i ).append("<div class='box box-success' id='boxResult" + i + "'>");
			$("#boxResult" + i ).append("<div class='box-body' id='bodyResult" + i + "'>");
			$("#bodyResult" + i ).append("<p align='center'>Result for this booklet");
			$("#bodyResult" + i ).append("<input class='form-control input-sm' id='result" + i + "' placeholder='Input Result. If not completed, put N/A' required />");
			
			$("#grading" + i ).append("<div class='col-md-4' id='colPointAmt" + i + "'>");
			$("#colPointAmt" + i ).append("<div class='box box-success' id='boxPointAmt" + i + "'>");
			$("#boxPointAmt" + i ).append("<div class='box-body' id='bodyPointAmt" + i + "'>");
			$("#bodyPointAmt" + i ).append("<p align='center'>Total amount of points");
			$("#bodyPointAmt" + i ).append("<input class='form-control input-sm' id='pointAmt" + i + "' placeholder='Input Point Amount' required />");
			
			$("#feedback" + i ).append("<div class='col-md-12' id='colFeedback" + i + "'>");
			$("#colFeedback" + i ).append("<div class='' id='boxFeedback" + i + "'>");
			$("#boxFeedback" + i ).append("<div class='box-body' id='bodyFeedback" + i + "'>");
			$("#bodyFeedback" + i ).append("<p>How was the performance of the child today?</p>");
			$("#bodyFeedback" + i ).append("<input class='form-control' id='feedbackOne" + i + "' placeholder='Input Feedback' required />");
			$("#bodyFeedback" + i ).append("<br>");
			$("#bodyFeedback" + i ).append("<p>How was their behavior today?</p>");
			$("#bodyFeedback" + i ).append("<input class='form-control' id='feedbackTwo" + i + "' placeholder='Input Feedback' required />");
		}
	} else {
		$("#dynamicStudentList").append("<h4>No students available</h4>");
	}

}

function submitFeedback(){
	var studentList = JSON.parse(localStorage.getItem("studentList"));
	var description = localStorage.getItem("description");
	var feedbacks = [];
	var input = {}; 
	
	for (var sId = 0; sId < studentList.length; sId++){
		var id = studentList[sId].student.studentId;
		var courseLevel = $("#courseLevel" + id).val();
		var bookletLevel = $("#bookLevel" + id).val();
		var resultMarks = $("#result" + id).val();
		var pointAmt = $("#pointAmt" + id).val();
		var feedbackOne = $("#feedbackOne" + id).val();
		var feedbackTwo = $("#feedbackTwo" + id).val();
		
		var result = {
			"studentId": Number(id),
			"content": $("#feedback" + id).val(),
			"courseLevel": Number($("#courseLevel" + id).val()),
			"bookletLevel": Number($("#bookLevel" + id).val()),
			"pointAmount": $("#pointAmt" + id).val(),
			"resultValue": $("#result" + id).val(),
			"contents": [feedbackOne, feedbackTwo]
		};
		
		feedbacks.push(result);
//		console.log(JSON.stringify(feedbacks));
	}
	
	input.feedbacks = feedbacks;
//	input.teacherId = 2; //-- for local testing only--
	input.teacherId = Number(localStorage.getItem("teacherId")); 
	input.courseId = Number(localStorage.getItem("courseId"));
	var inputStr = JSON.stringify(input);
	console.log(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GenerateResultServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
//			console.log(message);
//			if (status == 1) {	
				bootbox.dialog({
					message: "Feedback submitted for " + description + "!",
					title: "Feedback submission",
					buttons: {
						 main: {
						      label: "Ok",
						      className: "btn-primary",
						      callback: function() {
						    	  window.location = "teacherProfile.jsp";
						      }
						 }
					 }	
				});	
//			} else{
//				console.log(message);
//			}
		}
	});	
}

