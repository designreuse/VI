//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
    $(document).ready(function() {
    	getStudentsByBranch(localStorage.getItem('branchId'));
    });
//}

function registerStudent() {
	// Parent's details 
	jQuery('#parentNric').on('input', function() {
		var pNric = $("#parentNric").val();
	    console.log(pNric);
	});
	
	var parentName = $("#parentName").val();
	var parentNric = $("#parentNric").val();
	
	// student details
	var taken = $("#diagnostic").val();
	var studentName = $("#studentName").val();
	var studentNric = $("#studentNric").val();
	var gender = $("#gender").val();
	var birthDate = $("#birthDate").val();
	var homeContact = $("#homeContact").val();
	var emergencyContact = $("#emergencyContact").val();
	console.log(emergencyContact);
	var studentAddress = $("#studentAddress").val();
	var postalCode = $("#postalCode").val();
	
	// student education details 
	var schoolName = $("#schoolName").val();
	var schoolLevel = $("#schoolLevel").val()
	
	var branchId = localStorage.getItem("branchId");
	console.log(branchId);
	console.log(taken);
	
	var input = {};
	
	input.parentNric = parentNric;
	
	input.name = studentName;
	input.studentNric = studentNric;
	input.gender = gender;
	input.birthDate = birthDate;
	input.homeContact = homeContact;
	input.EmergencyContact = emergencyContact;
	input.postalCode = postalCode;
	input.address = studentAddress;
	
	input.schoolName = schoolName;
	input.schoolLevel = schoolLevel;
	
	input.takenDiagnostic = Number(taken);
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
			// if status == 1, it means that it is successful, else it will fail.
			if (status == 1) {
				alert("Created successfully");
				
				var scheduleId = Number(1);
				var studentId = Number(message.studentId);
				var input = {};
				input.scheduleId = scheduleId;
				input.studentId = studentId;
				var inputMsg = JSON.stringify(input);
				
				$.ajax({
					url : '../VI/CreateAttendanceServlet?input=' + inputMsg, 
					method : 'POST',
					dataType : 'json',
					error : function(err) {
						console.log(err);
						//$("#message").html(err);
					},
					success : function(data) {
						console.log(data);
						var status = data.status; //shows the  success/failure of the servlet request
						var message = data.message;
						console.log("attendance created");
					}
				});
				
				
				window.location = "studentSuccess.jsp";
			} else {
				$("#message").html(message);
			}
		}
	});
}

function getStudentsByBranch(branchId) {
	$.fn.dataTable.ext.errMode = 'none';
	var input = {};
	input.branchId = Number(branchId);
	var inputStr = JSON.stringify(input);
	console.log(inputStr);
	inputStr = encodeURIComponent(inputStr);
	var table =  $('#studentTable').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    }).DataTable({
    	ajax:{
			 url: '../VI/GetStudentsByBranchServlet?input='+ inputStr,
			 dataSrc: 'message'
		 },
		 columns: [
			 {"data": "studentId"},
			 {"data": 'name'},
			 {"data": 'gender'},
			 {"data": 'studentNric'},
			 {"data": 'birthDate'},
			 {"data": 'homeContact'},
			 {"data": 'EmergencyContact'},
			 {"data": 'address'},
			 {"data": 'postalCode'},
			 {"data": 'schoolName'},
			 {"data": 'schoolLevel'},
			 {"data": 'takenDiagnostic'},
			 {"data": 'points'},
			 {"data": 'createDate'},
			 {"data": null, "defaultContent":'<button class="btn btn-sm btn-warning fa" onclick="editStudent();" title="Edit"><i class="fa fa-pencil-square-o"></i></button><button class="btn btn-sm btn-danger fa" onclick="deleteStudent();" title="Delete"><i class="fa fa-trash-o"></i></button>'}
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
			'<input id="name" name="name" type="text" class="form-control input-md" value= ' + row.data().name + '> ' +
			'</div> ' +
			'<label class="col-md-2 control-label" for="nric">NRIC</label> ' +
			'<div class="col-md-10"> ' +
				'<input id="nric" name="nric" type="text" class="form-control input-md" value= ' + row.data().studentNric + '> ' +
			'</div> ' +
			'<label class="col-md-2 control-label" for="contact">Contact</label> ' +
			'<div class="col-md-10"> ' +
			'<input id="contact" name="contact" type="text" class="form-control input-md" value= ' + row.data().contact + '> ' +
			'</div> ' +
			'<label class="col-md-2 control-label" for="address">Address</label> ' +
			'<div class="col-md-10"> ' +
			'<input id="address" name="address" type="text" class="form-control input-md" value= ' + row.data().address + '> ' +
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
    					var updatedNric = $("#nric").val();
    					var updatedContact = $("#contact").val();
    					var updatedAddress = $("#address").val();
    					var updatedEmail = $("#email").val();
    					
    					var input = {}
    					input.studentId = Number(studentId);
    					input.name = updatedName;
    					input.studentNric = updatedNric;
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

function deleteStudent(){
	var table = $('#studentTable').DataTable();
	$('#studentTable tbody').off('click').on( 'click', 'button', function () {
		var tr = $(this).closest('tr');
        var row = table.row( tr );
       
        bootbox.dialog({
        	title: "Delete Student",
    		message: '<div class="row">  ' +
						'<div class="col-md-12"> ' +
							'<form class="form-horizontal" method="post"> ' +
								'<div class="form-group"> ' +
									'<label class="col-md-1 control-label"></label> ' +
									'<div>Please enter the student name to confirm deletion.</div></br>'+
									'<div><font color="red" id="deleteMessage"></font></div>'+
									'<label class="col-md-2 control-label" for="name">Name</label> ' +
									'<div class="col-md-10"> ' +
										'<input id="deleteName" name="deleteName" type="text" class="form-control input-md" placeholder= ' + row.data().name + '> ' +
									'</div> ' +
								'</div> ' +
							'</form> ' +
						'</div> ' +
					'</div>',
    		onEscape: function() {},
    		buttons: {
    			success:{
    				label: "Delete!",
    				className: "btn-danger",
    				
    				callback: function(){
    					var studentId = row.data().studentId;
    					var studentName = row.data().name;
    					var checkName = $("#deleteName").val();
    					
    					if(studentName == checkName){
    						var input = {}
        					input.studentId = Number(studentId);
        					var inputStr = JSON.stringify(input);
        					inputStr = encodeURIComponent(inputStr);
        					$.ajax({
        						url : '../VI/DeleteStudentServlet?input=' + inputStr, //this part sends to the servlet
        						method : 'POST',
        						dataType : 'json',
        						error : function(err) {
        							console.log(err);
        							$("#deleteMessage").html("System has some error. Please try again.");
        						},
        						success : function(data) {
        							console.log(data);
        							var status = data.status; 
        							var message = data.message;
        							
        							if (status == 1) {
        								bootbox.alert("Student has been successfully deleted!")
        								table.ajax.reload();
        								
        							} else {
        								$("#deleteMessage").html("Something's wrong, please try again!");
        							}
        						}
        					});
    					} else {
    						$("#deleteMessage").html("The entered name does not match, please try again.");
    					}
    				}    		
    			}
    		}	
    	}); 
	});
}
