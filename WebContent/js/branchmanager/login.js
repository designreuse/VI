function login() {
	// console.log("enter");
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
			console.log(err);
			$("#message").html("system failed to login in");
		},
		success : function(data) {
			console.log(data);
			var status = data.status; //shows the  success/failure of the servlet request
			var message = data.message;
			// if status == 1, it means that it is successful. else it will fail
			if (status == 1) {
				var branchManager = message;
				//console.log(branchManager);
				localStorage.setItem("courseId", 1);
				
				localStorage.setItem("branchId", branchManager.branch.branchId);
				localStorage.setItem("branchManagerId", branchManager.branchManagerId);
				window.location = "studentRegistration.jsp";
			} else {
				$("#message").html("Invalid Email/Password");
			}
		}
	});
}