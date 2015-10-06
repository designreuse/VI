//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
    $(document).ready(function() {
    	//getTeachers();
    });
//}

var TEACHERS = [];

function registerTeacher() {
	var teacherEmail = $("#teacherEmail").val();
	var teacherPassword = $("#teacherPassword").val();
	var teacherName = $("#teacherName").val();
	var teacherNric = $("#teacherNric").val();
	var contactNumber = $("#contactNumber").val();
	var teacherAddress = $("#teacherAddress").val();
	var teacherSchool = $("#teacherSchool").val() + ", ";
	var teacherQualification = $("#qualification").val() + ", ";;
	var year = $("#year").val();
	
	var qualification = teacherSchool.concat(teacherQualification, year);
	console.log(qualification);
	
	var branchId = localStorage.getItem("branchId");
	
	var input = {};
	input.email = teacherEmail;
	input.password = teacherPassword;
	input.name = teacherName;
	input.contact = contactNumber;
	input.address = teacherAddress;
	input.teacherNric = teacherNric;
	input.qualification = qualification;
	
	input.branchId = branchId;
	
	var inputStr = JSON.stringify(input);
	console.log(inputStr);
	
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/RegisterTeacherServlet?input=' + inputStr, //this part sends to the servlet
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
				alert("Created successfully");	
			} else {
				$("#message").html("Something's wrong, please try again!");
			}
		}
	});
}

function getTeachers() {
	$.ajax({
		url : '../VI/GetAllTeachersServlet?input={}',
		method : 'GET',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			if (data.status == 1){
				loadDataTable(data);
			}
			else{
				console.log(data.message);
			}
		}
	});		
}

function loadDataTable(data){
//	console.log(JSON.stringify(data));
//	console.log(JSON.stringify(data.status));
//	console.log(JSON.stringify(data.message));
	if (data.status == 1){
		console.log(JSON.stringify(data));
		$('#parentTable').DataTable({
			ajax:{
				url: '../VI/GetAllTeachersServlet?input={}',
				dataSrc: 'message'
			},
			processing: true,
			columns: [
				{"data": "teacherId"},
				{"data": "remark"},
				{"data": 'name'},
				{"data": 'contact'},
				{"data": 'email'},
				{"data": 'address'},
				{"data": 'createDate'},
				{"data": "objStatus"}
				]
		});

	}
	else{
		console.log("failure!");
	}
}

