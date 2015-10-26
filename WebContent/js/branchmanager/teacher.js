//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
    $(document).ready(function() {
    	getTeachers();
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
	
	var branchId = localStorage.getItem("branchId");
	var numBranchId = Number(branchId);
	console.log(branchId);
	console.log(numBranchId);
	
	var input = {};
	input.email = teacherEmail;
	input.password = teacherPassword;
	input.name = teacherName;
	input.contact = contactNumber;
	input.address = teacherAddress;
	input.teacherNric = teacherNric;
	input.qualification = qualification;
	
	input.branchId = numBranchId;
	
	var inputStr = JSON.stringify(input);
	//console.log(inputStr);
	
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
$.fn.dataTable.ext.errMode = 'none';
	
	var table =  $('#teacherTable').on( 'error.dt', function ( e, settings, techNote, message ) {
	        console.log( 'An error has been reported by DataTables: ', message );
	    }).DataTable({
	    	ajax:{
				 url: '../VI/GetAllTeachersServlet?input={}',
				 dataSrc: 'message'
			 },
			 columns: [
				 {"data": "teacherId"},
				 {"data": 'name'},
				 {"data": 'contact'},
				 {"data": 'email'},
				 {"data": 'address'},
				 {"data": 'teacherNric'},
				 {"data": 'qualification'},
				 {"data": 'createDate'},
				 {"data": null, "defaultContent":'<button class="btn btn-sm btn-success fa fa-file-powerpoint-o" onclick="editTeacher();" title=""></button>'}
				]
	    });	
}

function editTeacher(){
	var table = $('#teacherTable').DataTable();
	$('#teacherTable tbody').off('click').on( 'click', 'button', function () {
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
    					var teacherId = row.data().teacherId;
    					var updatedName = $("#name").val();
    					var updatedContact = $("#contact").val();
    					var updatedAddress = $("#address").val();
    					var updatedEmail = $("#email").val();
    					
    					var input = {}
    					input.teacherId = Number(teacherId);
    					input.name = updatedName;
    					input.contact = updatedContact;
    					input.address = updatedAddress;
    					input.email = updatedEmail;
    					var inputStr = JSON.stringify(input);
    					
    					inputStr = encodeURIComponent(inputStr);
    					$.ajax({
    						url : '../VI/UpdateTeacherServlet?input=' + inputStr, //this part sends to the servlet
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


