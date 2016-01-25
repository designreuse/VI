$(document).ready(function() {
	var branchManagerId = localStorage.getItem('branchManagerId');
	if (branchManagerId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
		
	}
});

var STUDENT;

function getStudent(){
	var studentName = $("#studentName").val();
	var studentNric = $("#studentNric").val();

	var input = {};

	input.name = studentName;
	input.studentNric = studentNric;

	var inputStr = JSON.stringify(input);

	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/GetStudentByNricServlet?input=' + inputStr,
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$("#message").html("System has some error. Please try again.");
		},
		success : function(data) {
			console.log(data);
			var status = data.status; // shows the success/failure of the servlet request
			var message = data.message;
			// if status == 1, it means that it is successful, else it will fail.
			if (status == 1) {
				STUDENT = message;
				console.log(STUDENT + " pre");
				generateQR();
			} else {
				$("#message").html(message);
			}
		}
	});
}

function generateQR() {
	console.log(STUDENT + " post");
	var studentId = message.studentId;
	
	var input = {};

	input.studentId = studentId;

	var inputStr = JSON.stringify(input);

	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/system/GenerateQRCodeServlet?input=' + inputStr,
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$("#message").html("System has some error. Please try again.");
		},
		success : function(data) {
			console.log(data);
			var status = data.status; // shows the success/failure of the servlet request
			var message = data.message;
			// if status == 1, it means that it is successful, else it will fail.
			if (status == 1) {
				alert("Generated successfully");
			} else {
				$("#message").html(message);
			}
		}
	});
}
