//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
    $(document).ready(function() {
    	//getStudents();
    });
//}

var STUDENTS = [];

function getStudents() {
	$.ajax({
		url : '../VI/GetAllStudentsServlet?input={}',
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
				url: '../VI/GetAllStudentsServlet?input={}',
				dataSrc: 'message'
			},
			processing: true,
			columns: [
				{"data": "studentId"},
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

