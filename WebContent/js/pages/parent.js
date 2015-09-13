function registerParent() {
	var parentEmail = $("#parentEmail").val();
	var parentPassword = $("#parentPassword").val();
	var parentName = $("#parentName").val();
	var contactNumber = $("#contactNumber").val();
	var parentAddress = $("#parentAddress").val();

	var input = {};
	input.email = parentEmail;
	input.password = parentPassword;
	input.name = parentName;
	input.contact = contactNumber;
	input.address = parentAddress;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/RegisterParentServlet?input=' + inputStr, //this part sends to the servlet
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
				var parentMessage = message;
				localStorage.setItem("parentMessage", parentMessage);
				window.location = "parentSuccess.jsp";
			} else {
				$("#message").html("Something's wrong, please try again!");
			}
		}
	});
}