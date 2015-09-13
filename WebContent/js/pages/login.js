function login() {
	// console.log("enter");
	var email = $("#email").val();
	console.log(email);
	var password = $("#password").val();
	console.log(password);
	var input = {};
	input.email = email;
	input.password = password;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/LoginAdminServlet?input=' + inputStr, //this part sends to the servlet
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
				var adminMessage = message;
				localStorage.setItem("adminMessage", adminMessage);
				window.location = "adminMain.jsp";
			} else {
				$("#message").html("Invalid Email/Password");
				console.log(message);
			}
		}
	});
}