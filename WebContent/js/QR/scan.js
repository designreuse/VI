$(document).ready(function() {
	scan();
});

function scan(){
	var txt = "innerText" in HTMLElement.prototype ? "innerText"
			: "textContent";
	var arg = {
		resultFunction : function(resText,lastImageSrc) {
			var aChild = document.createElement('li');
			aChild[txt] = resText;
			var qrStudentId = resText;

			var studentName = getStudentById(qrStudentId);

			//call update attendance servlet?

			//document.getElementById("demo").innerHTML = studentName;
			//document.querySelector('body').appendChild(aChild);

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
	input.attendanceStatus = Number(attendanceStatus);
	input.actualStartDate = actualStartDate;
	
	var inputStr = JSON.stringify(input);
	
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/UpdateAttendanceServlet?input=' + inputStr, 
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
			// if status == 1, it means that it is successful. else it will fail
			if (status == 1) {
				bootbox.alert("Attendance taken! Welcome to class, " + message.name, function() {
					});
			} else {
				$("#message").html("Something's wrong, please try again!");
			}
		}
	});
}