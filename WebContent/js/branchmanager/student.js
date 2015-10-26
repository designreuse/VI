//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
    $(document).ready(function() {
    	getStudents();
    });
//}

var STUDENTS = [];

function registerStudent() {
	var studentEmail = $("#studentEmail").val();
	var studentPassword = $("#studentPassword").val();
	var studentName = $("#studentName").val();
	var studentNric = $("#studentNric").val();
	var contactNumber = $("#contactNumber").val();
	var studentAddress = $("#studentAddress").val();
	
	var parentNric = $("#parentNric").val();
	
	var branchId = localStorage.getItem("branchId");
	console.log(branchId);
	
	var input = {};
	input.email = studentEmail;
	input.password = studentPassword;
	input.name = studentName;
	input.contact = contactNumber;
	input.address = studentAddress;
	input.studentNric = studentNric;
	input.parentNric = parentNric;
	input.branchId = Number(branchId);
	var inputStr = JSON.stringify(input);
	
	
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/RegisterStudentServlet?input=' + inputStr, //this part sends to the servlet
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
				window.location = "studentSuccess.jsp";
			} else {
				$("#message").html("Something's wrong, please try again!");
			}
		}
	});
}

function getStudents() {
	$.fn.dataTable.ext.errMode = 'none';
	
	var table =  $('#studentTable').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    }).DataTable({
    	ajax:{
			 url: '../VI/GetAllStudentsServlet?input={}',
			 dataSrc: 'message'
		 },
		 columns: [
			 {"data": "studentId"},
			 {"data": 'name'},
			 {"data": 'studentNric'},
			 {"data": 'contact'},
			 {"data": 'email'},
			 {"data": 'address'},
			 {"data": 'points'},
			 {"data": 'createDate'},
			 {"data": null, "defaultContent":'<button class="btn btn-sm btn-success fa fa-file-powerpoint-o" onclick="editStudent();" title=""></button>'}
			]
    });
}

function editStudent(){
	var table = $('#studentTable').DataTable();
	$('#studentTable tbody').off('click').on( 'click', 'button', function () {
		var tr = $(this).closest('tr');
        var row = table.row( tr );
       
        bootbox.dialog({
        	title: "Edit Details",
    		message: '<div class="row">  ' +
			'<div class="col-md-12"> ' +
			'<form class="form-horizontal" method="post"> ' +
			'<div class="form-group"> ' +
			'<div><font color="red" id="updatedMessage"></font></div>'+
			'<label class="col-md-2 control-label" for="name">Name</label> ' +
			'<div class="col-md-10"> ' +
			'<input id="name" name="name" type="text" class="form-control input-md" placeholder= ' + row.data().name + '> ' +
			'</div> ' +
			'<label class="col-md-2 control-label" for="contact">Contact</label> ' +
			'<div class="col-md-10"> ' +
			'<input id="contact" name="contact" type="text" class="form-control input-md" placeholder= ' + row.data().contact + '> ' +
			'</div> ' +
			'<label class="col-md-2 control-label" for="email">Email</label> ' +
			'<div class="col-md-10"> ' +
			'<input id="email" name="email" type="text" class="form-control input-md" placeholder= ' + row.data().email + '> ' +
			'</div> ' +
			'<label class="col-md-2 control-label" for="address">Address</label> ' +
			'<div class="col-md-10"> ' +
			'<input id="address" name="address" type="text" class="form-control input-md" placeholder= ' + row.data().address + '> ' +
			'</div> ' +
			'</div> ' +
			'</form> ' +
			'</div> ' +
			'</div>',
    		onEscape: function() {},
    		buttons: {
    			success:{
    				label: "Save!",
    				className: "btn-success",
    				
    				callback: function(){
    					var studentId = row.data().studentId;
    					var updatedName = $("#name").val();
    					var updatedContact = $("#contact").val();
    					var updatedAddress = $("#address").val();
    					var updatedEmail = $("#email").val();
    					
    					var input = {}
    					input.studentId = Number(studentId);
    					input.name = updatedName;
    					input.contact = updatedContact;
    					input.address = updatedAddress;
    					input.email = updatedEmail;
    					var inputStr = JSON.stringify(input);
    					
    					inputStr = encodeURIComponent(inputStr);
    					$.ajax({
    						url : '../VI/UpdateStudentServlet?input=' + inputStr, //this part sends to the servlet
    						method : 'POST',
    						dataType : 'json',
    						error : function(err) {
    							console.log(err);
    							$("#message").html("System has some error. Please try again.");
    						},
    						success : function(data) {
    							console.log(data);
    							var status = data.status; 
    							var message = data.message;
    							
    							if (status == 1) {
    								bootbox.alert("Update is successful!")
    								table.ajax.reload();
    								
    							} else {
    								$("#message").html("Something's wrong, please try again!");
    							}
    						}
    					});
    				}
    		
    			}
    		}	
    	}); 
	});
}

