$(document).ready(function() {
//	getFeedback();
	//Line chart
	
	// LINE CHART
    var line = new Morris.Line({
      element: 'line-chart',
      resize: true,
      data: [
        {y: '2011 Q1', item1: 100},
        {y: '2011 Q2', item1: 98},
        {y: '2011 Q3', item1: 100},
        {y: '2011 Q4', item1: 120},
        {y: '2012 Q1', item1: 150}
      ],
      xkey: 'y',
      ykeys: ['item1'],
      labels: ['Item 1'],
      lineColors: ['#3c8dbc'],
      hideHover: 'auto'
    });
    
});

function getFeedback(){
	
	var storedIds = JSON.parse(localStorage["studentIds"]);
	//console.log(storedIds[0]);
	
	for(var j = 0; j < storedIds.length; j++){
	var studentId = Number(storedIds[j]);
	var input = {};
	input.studentId = studentId;
	var inputStr = JSON.stringify(input);
	var i = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/GetFeedbacksAndResultsByStudentServlet?input=' + inputStr, // this part sends to
		// the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			;
			var feedbackStatus = data.teacherFeedbacks.status; // to modify for both status
			var resultsStatus = data.results.status;
			// servlet request
			if (feedbackStatus == 1 && resultsStatus == 1) {
				//length of for loop -- different lengths for teacherFeedbacks and results
				//display NIL if no feedback
				for (var i = 0; i < data.teacherFeedbacks.message.length; i++) {
					var feedbackTable = document.getElementById("test");
					
					var name = data.teacherFeedbacks.message[i].teacherStudentCourse.student.name;
					var feedback = data.teacherFeedbacks.message[i].content;
					//console.log(data.results.message[i].resultValue);
					var result = data.results.message[i].resultValue;
					var courseLevel = data.results.message[i].courseLevel;
					var bookletLevel = data.results.message[i].bookletLevel;
					
					var table = document.createElement('TABLE');
				    table.className = "table table-information";
				    var tableBody = document.createElement('TBODY');
				    
				    var nameRow = document.createElement('TR');
				    var nameHeader = document.createElement('TD');
				    var nameValue = document.createElement('TD');
				    
				    var feedbackRow = document.createElement('TR');
				    var feedbackHeader = document.createElement('TD');
				    var feedbackValue = document.createElement('TD');
				    
				    var resultRow = document.createElement('TR');
				    var resultHeader = document.createElement('TD');
				    var resultValue = document.createElement('TD');
				    
				    var courseLevelRow = document.createElement('TR');
				    var courseLevelHeader = document.createElement('TD');
				    var courseLevelValue = document.createElement('TD');
				    
				    var bookletLevelRow = document.createElement('TR');
				    var bookletLevelHeader = document.createElement('TD');
				    var bookletLevelValue = document.createElement('TD');
				    
				    var bold = document.createElement("b");
				    bold.appendChild(document.createTextNode("Name"));
				    
				    nameHeader.appendChild(bold);
				    nameValue.appendChild(document.createTextNode(name));
				    
				    nameRow.appendChild(nameHeader);
				    nameRow.appendChild(nameValue);
				    
				    feedbackHeader.appendChild(document.createTextNode("Feedback"));
				    feedbackValue.appendChild(document.createTextNode(feedback));
				    feedbackRow.appendChild(feedbackHeader);
				    feedbackRow.appendChild(feedbackValue);
				    
				    resultHeader.appendChild(document.createTextNode("Result"));
				    resultValue.appendChild(document.createTextNode(result));
				    resultRow.appendChild(resultHeader);
				    resultRow.appendChild(resultValue);
				    
				    courseLevelHeader.appendChild(document.createTextNode("Course Level"));
				    courseLevelValue.appendChild(document.createTextNode(courseLevel));
				    courseLevelRow.appendChild(courseLevelHeader);
				    courseLevelRow.appendChild(courseLevelValue);
				    
				    bookletLevelHeader.appendChild(document.createTextNode("Booklet Level"));
				    bookletLevelValue.appendChild(document.createTextNode(bookletLevel));
				    bookletLevelRow.appendChild(bookletLevelHeader);
				    bookletLevelRow.appendChild(bookletLevelValue);
				    
				    tableBody.appendChild(nameRow);
				    tableBody.appendChild(feedbackRow);
				    tableBody.appendChild(resultRow);
				    tableBody.appendChild(courseLevelRow);
				    tableBody.appendChild(bookletLevelRow);
				    
				    table.appendChild(tableBody);
				    
				   feedbackTable.appendChild(table);
					
				}
			
			} else {
				//console.log(message);
			}
		}
	});
	}
}


$(".collapse").show();
if (status == 1) {
	$('#dynamicStudentList').html("");
	for (var a = 0; a < message.length; a++){
		
		var i = message[a].studentId;
		var name = message[a].name;
		
		$("#dynamicStudentList").append("<div class='box-group header"+ i +"' id='accordion'>");
		$(".header"+ i ).append("<div class='panel box box-solid box-warning headerpanel"+ i +"'>");
		$(".headerpanel" + i).append("<div class='box-header with-border headerwords" + i + "'>");
		$(".headerwords" + i).append("<h4 class='box-title tog" + i + "'>");
		$(".tog" + i).append("<a data-toggle='collapse' data-parent='#accordion' href='#addSched" + i + "'>" + name + " </a>");
		
		//accordion body
		$(".header"+ i).append("<div id='addSched" + i + "' class='panel-collapse collapse'>");
		$("#addSched" + i).append("<div class='box-body boxbody" + i + "'>");
		$(".boxbody" + i ).append("<div class='row' id = 'row" + i + "'>");
		
		$("#row" + i ).append("<div class='col-md-3' id='colCourseBookLevel" + i + "'>");
		$("#colCourseBookLevel" + i ).append("<div class='box box-solid box-warning' id='boxCourseBookLevel" + i + "'>");
		$("#boxCourseBookLevel" + i ).append("<div class='box-body' id='bodyCourseBookLevel" + i + "'>");
		$("#bodyCourseBookLevel" + i ).append("<input class='form-control input-sm' id='courseLevel" + i + "' placeholder='Input Course Level' required />");
		$("#bodyCourseBookLevel" + i ).append("<input class='form-control input-sm' id='bookLevel" + i + "' placeholder='Input Book Level' required />");
		
		$("#row" + i ).append("<div class='col-md-3' id='colResult" + i + "'>");
		$("#colResult" + i ).append("<div class='box box-solid box-warning' id='boxResult" + i + "'>");
		$("#boxResult" + i ).append("<div class='box-body' id='bodyResult" + i + "'>");
		$("#bodyResult" + i ).append("<input class='form-control input-sm' id='result" + i + "' placeholder='Input Result' required />");
		
		
		$("#row" + i ).append("<div class='col-md-3' id='colPointAmt" + i + "'>");
		$("#colPointAmt" + i ).append("<div class='box box-solid box-warning' id='boxPointAmt" + i + "'>");
		$("#boxPointAmt" + i ).append("<div class='box-body' id='bodyPointAmt" + i + "'>");
		$("#bodyPointAmt" + i ).append("<input class='form-control input-sm' id='pointAmt" + i + "' placeholder='Input Point Amount' required />");
		
		$("#row" + i ).append("<div class='col-md-3' id='colFeedback" + i + "'>");
		$("#colFeedback" + i ).append("<div class='box box-solid box-warning' id='boxFeedback" + i + "'>");
		$("#boxFeedback" + i ).append("<div class='box-body' id='bodyFeedback" + i + "'>");
		$("#bodyFeedback" + i ).append("<input class='form-control input-sm' id='feedback" + i + "' placeholder='Input Feedback' required />");
	}
	
	localStorage.setItem("studentList", JSON.stringify(message));

} else{
	console.log("Nil")
}