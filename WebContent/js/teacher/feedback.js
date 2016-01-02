 $(document).ready(function() {
	 populateCourseDDL();
 
 });

 
function populateCourseDDL(){
	var teacherId = 2;
	var $select = $('#coursesDDL');
	$select.html('');
	var input = {};
	input.teacherId = teacherId;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
	url : '../VI/GetCoursesByTeacherServlet?input=' + inputStr, //this part sends to the servlet
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
			$select.html('<option id="0">---- Select Course -----</option>');
			for (var t = 0; t < message.length; t++){
				var selectText = message[t].name;
				$select.append('<option value=' + message[t].courseId + '>' + selectText + '</option>' );
			}
		} else{
			$select.html('<option id="-1">none available</option>');
		}
	}
});	
} 

function populateStudents(){
	$(".collapse").collapse('toggle');
	
	var teacherId = 1;
	var input = {};
	input.branchId = teacherId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
	url : '../VI/GetParentsByBranchServlet?input=' + inputStr, //this part sends to the servlet
	method : 'POST',
	dataType : 'json',
	error : function(err) {
		console.log(err);
	},
	success : function(data) {
		var status = data.status; 
		var message = data.message;
		
		console.log(message);
			
		if (status == 1) {	
			
			for (var i = 0; i < message.length; i++){
				//accordion appending
				$("#dynamicStudentList").append("<div class='box-group header"+ i +"' id='accordion'>");
				$(".header"+ i ).append("<div class='panel box box-solid box-success headerpanel"+ i +"'>");
				$(".headerpanel" + i).append("<div class='box-header with-border headerwords" + i + "'>");
				$(".headerwords" + i).append("<h4 class='box-title tog" + i + "'>");
				$(".tog" + i).append("<a data-toggle='collapse' data-parent='#accordion' href='#addSched" + i + "'>" + message[i].name + " </a>");
				
				//accordion body
				$(".header"+ i).append("<div id='addSched" + i + "' class='panel-collapse collapse'>");
				$("#addSched" + i).append("<div class='box-body boxbody" + i + "'>");
				$(".boxbody" + i ).append("<div class='row' id = 'row" + i + "'>");
				
				$("#row" + i ).append("<div class='col-md-3' id='colCourseBookLevel" + i + "'>");
				$("#colCourseBookLevel" + i ).append("<div class='box box-solid box-success' id='boxCourseBookLevel" + i + "'>");
				$("#boxCourseBookLevel" + i ).append("<div class='box-body' id='bodyCourseBookLevel" + i + "'>");
				$("#bodyCourseBookLevel" + i ).append("<p>Course Book Level</p>");
				
				
				$("#row" + i ).append("<div class='col-md-3' id='colResult" + i + "'>");
				$("#colResult" + i ).append("<div class='box box-solid box-success' id='boxResult" + i + "'>");
				$("#boxResult" + i ).append("<div class='box-body' id='bodyResult" + i + "'>");
				$("#bodyResult" + i ).append("<p>Results</p>");
				
				$("#row" + i ).append("<div class='col-md-3' id='colPointAmt" + i + "'>");
				$("#colPointAmt" + i ).append("<div class='box box-solid box-success' id='boxPointAmt" + i + "'>");
				$("#boxPointAmt" + i ).append("<div class='box-body' id='bodyPointAmt" + i + "'>");
				$("#bodyPointAmt" + i ).append("<p>Point Amount</p>");
				
				$("#row" + i ).append("<div class='col-md-3' id='colFeedback" + i + "'>");
				$("#colFeedback" + i ).append("<div class='box box-solid box-success' id='boxFeedback" + i + "'>");
				$("#boxFeedback" + i ).append("<div class='box-body' id='bodyFeedback" + i + "'>");
				$("#bodyFeedback" + i ).append("<p>Feedback</p>");

			}
		
		} else{
			console.log("Nil")
		}
	}
});	
	
}
