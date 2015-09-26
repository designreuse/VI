//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
    $(document).ready(function() {
    	getParents();
    });
//}

var PARENTS = [];

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

function getParents() {
	$.ajax({
		url : '../VI/GetAllParentsServlet?input={}',
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
				url: '../VI/GetAllParentsServlet?input={}',
				dataSrc: 'message'
			},
			processing: true,
			columns: [
				{"data": "parentId"},
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


//
//$('#parentTable').DataTable({
//"data": data,
//"columns": {
//	"message": "parentId",
//	"message": "name",
//	"message": "contact",
//	"message": "email",
//	"message": "address",
//	"message": "createDate",
//	"message": "parentId",
//}
//});
//}
//else{
//console.log(message);
//}
//}
//});

//$.ajax({
//	url : '../VI/GetAllParentsServlet?input={}',
//	method : 'GET',
//	dataType : 'json',
//	success : function(data) {
//		console.log(JSON.stringify(data));
//		console.log(data.status);
//		if (data.status == 1) {
//			$('#parentTable').dynatable({
//				dataset : {
//					records : data.message
//				}
//			});
//		} else {
//			console.log(status);
//		}
//	},
//	error : function(err) {
//		console.log(err);
//	}
//});

