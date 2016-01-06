$(document).ready(function() {
	getFeedback();
});
// }

function getFeedback(){
	
	var storedIds = JSON.parse(localStorage["studentIds"]);
	//console.log(storedIds[0]);
	
	var studentId = Number(storedIds[0]);
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
				var name = data.message[0].teacherStudentCourse.student.name;
				var feedback = data.message[0].content;
				document.getElementById('name').innerHTML = name;
				document.getElementById('feedback').innerHTML = feedback;

			} else {
				console.log(message);
			}
		}
	});

}