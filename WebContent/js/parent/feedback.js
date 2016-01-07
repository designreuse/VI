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
		url : '../VI/GetFeedbacksByStudentServlet?input=' + inputStr, // this part sends to
		// the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			;
			var status = data.status; // shows the success/failure of the
			// servlet request
			if (status == 1) {
				for (var i = 0; i < data.message.length; i++) {
					var feedbackTable = document.getElementById("test");
					
					var name = data.message[i].teacherStudentCourse.student.name;
					var feedback = data.message[i].content;
	//				document.getElementById('name').innerHTML = name;
	//				document.getElementById('feedback').innerHTML = feedback;
	//				
					var table = document.createElement('TABLE');
				    table.className = "table table-information";
				    var tableBody = document.createElement('TBODY');
				    
				    var nameRow = document.createElement('TR');
				    var nameHeader = document.createElement('TD');
				    var nameValue = document.createElement('TD');
				    
				    var feedbackRow = document.createElement('TR');
				    var feedbackHeader = document.createElement('TD');
				    var feedbackValue = document.createElement('TD');
				    
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
				    
				    tableBody.appendChild(nameRow);
				    tableBody.appendChild(feedbackRow);
				    
				    table.appendChild(tableBody);
				    
				   feedbackTable.appendChild(table);
					
				}
			
			} else {
				console.log(message);
			}
		}
	});
	}
}