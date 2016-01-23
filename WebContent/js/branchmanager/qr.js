$(document).ready(function() {
	var branchManagerId = localStorage.getItem('branchManagerId');
	if (branchManagerId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
		getStudentsByBranch(localStorage.getItem('branchId'));
	}
});

function generateQR() {
	var studentName = $("#studentName").val();
	var studentNric = $("#studentNric").val();

	var input = {};

	input.name = studentName;
	input.studentNric = studentNric;

	var inputStr = JSON.stringify(input);

	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/GenerateQRCodeServlet?input=' + inputStr,
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$("#message").html("System has some error. Please try again.");
		},
		success : function(data) {
			console.log(data);
			var status = data.status; // shows the success/failure of the
			// servlet request
			var message = data.message;
			// if status == 1, it means that it is successful, else it will fail.
			if (status == 1) {
				alert("Created successfully");

				window.location = "studentSuccess.jsp";
			} else {
				$("#message").html(message);
			}
		}
	});
}
