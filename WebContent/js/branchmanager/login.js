//$(document).ready(function() {
//	loginBranchManager();
//	loginTeacher();
//});

function loginBranchManager() {
	// console.log("enter");
	var unsuccessful;
	var email = $("#email").val();
	var password = $("#password").val();
	var input = {};
	input.email = email;
	input.password = password;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);							
	$.ajax({
		url : '../VI/LoginBranchManagerServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {

			unsuccessful = err;
		},
		success : function(data) {
			console.log(data);
			var status = data.status; //shows the  success/failure of the servlet request
			var message = data.message;
			// if status == 1, it means that it is successful. else it will fail
			if (status == 1) {
				var branchManager = message;
				//temporary
//				localStorage.setItem("teacherCourseId", 1);
//				localStorage.setItem("planStartDate", "2015-10-06T17:50:18.000Z");
				
				localStorage.setItem("branchId", branchManager.branch.branchId);
				localStorage.setItem("branchManagerId", branchManager.branchManagerId);
				window.location = "adminMain.jsp";
				console.log(data.message)
				
			} else{
				loginTeacher();
			}
//			if(unsuccessful != true){
//				loginTeacher();
//			}else {
//				$("#message").html("Invalid Email/Password");
//			}
		}
	});
}

function loginTeacher() {
	// console.log("enter");
	var email = $("#email").val();
	var password = $("#password").val();
	var input = {};
	input.email = email;
	input.password = password;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);							
	$.ajax({
		url : '../VI/LoginTeacherServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			$("#message").html("system failed to login in");
		},
		success : function(data) {
			//console.log(data);
			var status = data.status; //shows the  success/failure of the servlet request
			var message = data.message;
			// if status == 1, it means that it is successful. else it will fail
			if (status == 1) {
				//store teacherId and courseIds 
				localStorage.setItem("teacherId", message.teacherId);				
				window.location = "teacherProfile.jsp";
			} else {
				$("#message").html("Invalid Email/Password, Account does not exist.");
			}
		}
	});
}