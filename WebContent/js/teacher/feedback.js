 $(document).ready(function() {
	 populateCourseDDL();
	 localStorage.removeItem("studentList");
 });

 
function populateCourseDDL(){
	var teacherId = 1;
	var $select = $('#coursesDDL');
	$select.html('');
	var input = {};
	input.teacherId = teacherId;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
	url : '../VI/GetSchedulesByTeacherServlet?input=' + inputStr, //this part sends to the servlet
	method : 'POST',
	dataType : 'json',
	error : function(err) {
		console.log(err);
		 $select.html('<option id="-1">none available</option>');
	},
	success : function(data) {
		var status = data.status; 
		var message = data.message;
		
		if (status == 1) {	
			console.log(message);
			$select.html('<option id="0">---- Select Course -----</option>');
			for (var t = 0; t < message.length; t++){
				var day = moment(message[t].scheduleStartDate).format('dddd')
				var selectText = message[t].name + " (" + day + ")";
				$select.append('<option value=' + message[t].course.courseId + '>' + selectText + '</option>' );
			}	
		} else{
			$select.html('<option id="-1">none available</option>');
		}
	}
});	
} 

function populateStudents(){
	var courseDDL = document.getElementById("coursesDDL");
	var courseId = courseDDL.options[courseDDL.selectedIndex].value;
	var teacherId = 1;
	var input = {};
	input.teacherId = teacherId;
	input.courseId = Number(courseId);
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	localStorage.setItem("courseId", courseId);
	localStorage.setItem ("teacherId", teacherId);
	
	$.ajax({
	url : '../VI/GetStudentsByTeacherAndCourseServlet?input=' + inputStr, //this part sends to the servlet
	method : 'POST',
	dataType : 'json',
	error : function(err) {
		console.log(err);
	},
	success : function(data) {
		var status = data.status; 
		var message = data.message;
		
		$(".collapse").show();
			
		if (status == 1) {	
			$('#dynamicStudentList').html("");
			for (var a = 0; a < message.length; a++){
				
				var i = message[a].studentId;
				var name = message[a].name;
				
				$("#dynamicStudentList").append("<div class='box-group header"+ i +"' id='accordion'>");
				$(".header"+ i ).append("<div class='panel box box-solid box-success headerpanel"+ i +"'>");
				$(".headerpanel" + i).append("<div class='box-header with-border headerwords" + i + "'>");
				$(".headerwords" + i).append("<h4 class='box-title tog" + i + "'>");
				$(".tog" + i).append("<a data-toggle='collapse' data-parent='#accordion' href='#addSched" + i + "'>" + name + " </a>");
				
				//accordion body
				$(".header"+ i).append("<div id='addSched" + i + "' class='panel-collapse collapse'>");
				$("#addSched" + i).append("<div class='box-body boxbody" + i + "'>");
				$(".boxbody" + i ).append("<div class='row' id = 'row" + i + "'>");
				
				$("#row" + i ).append("<div class='col-md-3' id='colCourseBookLevel" + i + "'>");
				$("#colCourseBookLevel" + i ).append("<div class='box box-solid box-success' id='boxCourseBookLevel" + i + "'>");
				$("#boxCourseBookLevel" + i ).append("<div class='box-body' id='bodyCourseBookLevel" + i + "'>");
				$("#bodyCourseBookLevel" + i ).append("<input class='form-control input-sm' id='courseLevel" + i + "' placeholder='Input Course Level' required />");
				$("#bodyCourseBookLevel" + i ).append("<input class='form-control input-sm' id='bookLevel" + i + "' placeholder='Input Book Level' required />");
				
				$("#row" + i ).append("<div class='col-md-3' id='colResult" + i + "'>");
				$("#colResult" + i ).append("<div class='box box-solid box-success' id='boxResult" + i + "'>");
				$("#boxResult" + i ).append("<div class='box-body' id='bodyResult" + i + "'>");
				$("#bodyResult" + i ).append("<input class='form-control input-sm' id='result" + i + "' placeholder='Input Result' required />");
				
				
				$("#row" + i ).append("<div class='col-md-3' id='colPointAmt" + i + "'>");
				$("#colPointAmt" + i ).append("<div class='box box-solid box-success' id='boxPointAmt" + i + "'>");
				$("#boxPointAmt" + i ).append("<div class='box-body' id='bodyPointAmt" + i + "'>");
				$("#bodyPointAmt" + i ).append("<input class='form-control input-sm' id='pointAmt" + i + "' placeholder='Input Point Amount' required />");
				
				$("#row" + i ).append("<div class='col-md-3' id='colFeedback" + i + "'>");
				$("#colFeedback" + i ).append("<div class='box box-solid box-success' id='boxFeedback" + i + "'>");
				$("#boxFeedback" + i ).append("<div class='box-body' id='bodyFeedback" + i + "'>");
				$("#bodyFeedback" + i ).append("<input class='form-control input-sm' id='feedback" + i + "' placeholder='Input Feedback' required />");
			}
			
			localStorage.setItem("studentList", JSON.stringify(message));
		
		} else{
			console.log("Nil")
		}
	}
});	
	
}

function submitFeedback(){
	var studentList = JSON.parse(localStorage.getItem("studentList"));
	console.log(studentList);
	var feedbacks = [];
	var input = {};
	
	for (var sId = 0; sId < studentList.length; sId++){
		var id = studentList[sId].studentId;
		var courseLevel = $("#courseLevel" + id).val();
		var bookletLevel = $("#bookLevel" + id).val();
		var resultMarks = $("#result" + id).val();
		var pointAmt = $("#pointAmt" + id).val();
		var feedback = $("#feedback" + id).val();
		
		var result = {
			"studentId": Number(id),
			"content": $("#feedback" + id).val(),
			"courseLevel": Number($("#courseLevel" + id).val()),
			"bookletLevel": Number($("#bookLevel" + id).val()),
			"pointsValue": Number($("#pointAmt" + id).val()),
			"resultValue": $("#result" + id).val()
		};
		
		feedbacks.push(result);
		console.log(JSON.stringify(feedbacks));
	}
	
	input.feedbacks = feedbacks;
	input.teacherId = Number(localStorage.getItem("teacherId"));
	input.courseId = Number(localStorage.getItem("courseId"));
	
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
			
			if (status == 1) {	
				console.log(message);
				alert("Created successfully");	
			} else{
				console.log(message);
			}
		}
	});	
}

