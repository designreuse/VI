$(document).ready(function() {
	getFeedback();
});
// }

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