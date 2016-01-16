//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
 $(document).ready(function() {
    	getTeacher();
    });
//}

function getTeacher() {
	
	var teacherId = Number(localStorage.getItem("teacherId"));
	var input = {};
	input.teacherId = teacherId;
	var inputStr = JSON.stringify(input);
	var i = encodeURIComponent(inputStr);
	
	
	$.ajax({
		url: '../VI/GetTeacherById?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			var status = data.status; //shows the  success/failure of the servlet request
			if (status == 1) {
				var name = data.message.name;
				var address = data.message.address;
				var contact = data.message.contact;
				document.getElementById('name').innerHTML = name;
				document.getElementById('address').innerHTML = address;
				document.getElementById('phone').innerHTML = contact;
				
			} else {
				console.log(message);
			}
		}
	});
}

