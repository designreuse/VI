$(document).ready(function() {
	scan();
});

//var STUDENTID;

function scan(){
	var txt = "innerText" in HTMLElement.prototype ? "innerText"
			: "textContent";
	var arg = {
		resultFunction : function(resText,lastImageSrc) {
			var aChild = document.createElement('li');
			aChild[txt] = resText;
			var qrStudentId = resText;
			updateQRAttendance(qrStudentId);
//			STUDENTID = qrStudentId;
//			var studentName = getStudentById(qrStudentId);

		}
	};
	new WebCodeCamJS("canvas").init(arg).play();
}

function getStudentById(studentId) {
	var input = {}
	input.studentId = Number(studentId);

	var inputStr = JSON.stringify(input);

	inputStr = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/GetStudentById?input=' + inputStr, // this part sends to
														// the servlet
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
			// if status == 1, it means that it is successful. else it will fail
			if (status == 1) {
				// console.log(message);
				console.log(message.name);
				updateQRAttendance(studentId);
			} else {
				$("#message").html("Something's wrong, please try again!");
			}
		}
	});
}

function updateQRAttendance(studentId){
	var attendanceStatus = 1;
	var miliseconds = new Date();
	var actualStartDate = moment().format();
	var input = {}
	input.studentId = Number(studentId);
	input.scheduleId = 1;
	input.attendanceStatus = Number(attendanceStatus);
	input.actualStartDate = actualStartDate;
	
	var inputStr = JSON.stringify(input);
	
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/UpdateAttendanceWithQRServlet?input=' + inputStr, 
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$("#message").html("System has some error. Please try again.");
		},
		success : function(data) {
			console.log(data);
			var status = data.status; //shows the  success/failure of the servlet request
			var message = data.message;
			console.log(data);
			console.log(message);
			// if status == 1, it means that it is successful. else it will fail
			if (status == 1) {
				bootbox.alert("Attendance taken! Welcome to class, " + message.student.name, function() {
					});
				//call the send email servlet to send the email
				console.log("here!!!");
				var studentName = message.student.name;
				var parentName = message.student.parent.name;
				var email = message.student.parent.email;
				
				var input = {};
				input.studentName = studentName;
				input.parentName = parentName;
				input.email = email;
				
				var inputMsg = JSON.stringify(input);
				console.log(inputMsg);
				
				$.ajax({
					url : '../VI/system/SendEmailServlet?input=' + inputMsg, 
					method : 'POST',
					dataType : 'json',
					error : function(err) {
						console.log(err);
						//$("#message").html(err);
					},
					success : function(data) {
						console.log(data);
						var status = data.status; //shows the  success/failure of the servlet request
						var message = data.message;
						console.log("email sent");
					}
				});
			} else {
				$("#message").html("Something's wrong, please try again!");
			}
		}
	});
}