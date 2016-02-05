$(document).ready(function() {
	var branchManagerId = localStorage.getItem('branchManagerId');
	if (branchManagerId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
		$('#submit').click(function() {
		      checked = $("input[type=checkbox]:checked").length;

		      if(!checked) {
		        alert("You must check at least one checkbox.");
		        return false;
		      }

		    });
		getTeachersByBranch(localStorage.getItem('branchId'));
		// checkbox();
	}
});


window.onload = function () {
	document.getElementById("teacherPassword").onchange = validatePassword;
	document.getElementById("verifyPassword").onchange = validatePassword;
}
function validatePassword(){
	var pass2=document.getElementById("verifyPassword").value;
	var pass1=document.getElementById("teacherPassword").value;
	if(pass1!=pass2)
		document.getElementById("verifyPassword").setCustomValidity("Passwords Don't Match");
	else
		document.getElementById("verifyPassword").setCustomValidity('');	 
	//empty string means no validation error
}

var TEACHERS = {};
var TEACHER	= {};	

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
//	console.log(branchId);
//	console.log(numBranchId);
	
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
	//await servlet update
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
				
				//courseIds
				var arrCB = $("input[name='course']:checked").map(function(){
					 return $(this).val();
				}).get();

				var courses = JSON.stringify(arrCB);
				console.log(courseId);
				
				$.ajax({
					url : '../VI/CreateTeacherCoursesServlet?input=' + inputStr, //this part sends to the servlet
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
							console.log("teacherCourse created");
						} else {
							$("#message").html(message);
						}
					}
				});
				
				alert("Created successfully");	
				window.location = "teacherSuccess.jsp";
			} else {
				$("#message").html(message);
			}
		}
	});
}

function getTeachersByBranch(branchId){
	$.fn.dataTable.ext.errMode = 'none';
	var input = {};
	input.branchId = Number(branchId);
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url: '../VI/GetTeachersByBranchServlet?input=' + inputStr,
		method : "GET",
		dataType : "json",
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			console.log(data);
			var status = data.status;
			var teachers = data.message;
			if(status == 1){
				TEACHERS = teachers;
				var html = '';
				for ( var o in teachers) {
					var teacher = teachers[o];
					var teacherId = teacher.teacherId;
					var date = teacher.createDate;
					html += '\
					<tr id="teacher-'+ teacherId+ '">\
						<td id="teacherId-'+ teacherId +'">'+ teacherId +'</td>\
						<td id="name-'+teacherId+'">'+teacher.name+'</td>\
						<td id="contact-'+teacherId+'">'+teacher.contact+'</td>\
						<td id="email-'+teacherId+'">'+teacher.email+'</td>\
						<td id="address-'+teacherId+'">'+teacher.address+'</td>\
						<td id="teacherNric-'+teacherId+'">'+teacher.teacherNric+'</td>\
						<td id="qualification-'+teacherId+'">'+teacher.qualification+'</td>\
						<td>\
							<button class="btn btn-sm btn-info btn-sm" onclick="launchEditTeacher('+teacherId+')" title="Edit"><i class="glyphicon glyphicon-pencil"></i></button>\
							<button class="btn btn-sm btn-warning btn-sm" onclick="launchChangeTeacherPassword('+teacherId+')" title="Change password"><i class="glyphicon glyphicon-cog"></i></button>\
							<button class="btn btn-sm btn-danger btn-sm" onclick="launchDeleteTeacher('+teacherId+')" title="Delete"><i class="glyphicon glyphicon-trash"></i></button>\
						</td>\
					</tr>';
				}
				$("#teachers").html(html);
				$("#teacherTable").DataTable({
					destroy : true,
					searching : true,
					responsive : {
						details: false
					}
				});
			} else {
				console.log(message);
			}
		}
	})
//	var table =  $('#teacherTable').on( 'error.dt', function ( e, settings, techNote, message ) {
//	        console.log( 'An error has been reported by DataTables: ', message );
//	    }).DataTable({
//	    	ajax:{
//				 url: '../VI/GetTeachersByBranchServlet?input='+ inputStr,
//				 dataSrc: 'message'
//			 },
//			 columns: [
//				 {"data": "teacherId"},
//				 {"data": 'name'},
//				 {"data": 'contact'},
//				 {"data": 'email'},
//				 {"data": 'address'},
//				 {"data": 'teacherNric'},
//				 {"data": 'qualification'},
//				 {"data": null, "defaultContent":'<button class="btn btn-sm btn-warning fa" onclick="editTeacher();" title="Edit"><i class="fa fa-pencil-square-o"></i></button><button class="btn btn-sm btn-danger fa" onclick="deleteTeacher();" title="Delete"><i class="fa fa-trash-o"></i></button>'}
//				]
//	    });	
}

function launchEditTeacher(id) {
	$('#teacherTable tbody').off('click').on( 'click', 'button', function () {
		var teacher = {};
		for(var t in TEACHERS){
			var tempTeacher = TEACHERS[t];
			if(tempTeacher.teacherId == id){
				teacher = tempTeacher;
				break;
			}
		}
		$("#editTeacherForm").attr('onsubmit','editTeacher(' + teacher.teacherId + '); return false;');
		$("#nameEdit").val(teacher.name);
		$("#contactEdit").val(teacher.contact);
		$("#emailEdit").val(teacher.email);
		$("#addressEdit").val(teacher.address);
		$("#teacherNricEdit").val(teacher.teacherNric);
		$("#qualificationEdit").val(teacher.qualification);
//		$("#imageEditImg").attr('src', image);
		$("#editTeacher").modal("show");
	});	
}

function editTeacher(id) {
//	var table = $('#teacherTable').DataTable();
	$("#messageEdit").html('');
	$(".my-loading").removeClass('sr-only');
	
	var input = {}
	input.teacherId = Number(id);
	input.name = $("#nameEdit").val();
	input.contact = $("#contactEdit").val();
	input.email = $("#emailEdit").val();
	input.address = $("#addressEdit").val();
	input.teacherNric = $("#teacherNricEdit").val();
	input.qualification = $("#qualificationEdit").val();
	
//	console.log(input);
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : "../VI/UpdateTeacherServlet?input=" + inputStr,
		method : "POST",
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			console.log(data);
			var status = data.status;
			var teacher = data.message;
			var teacherId = teacher.teacherId;
			if (status == 1) {
				$("#name-"+teacherId).html(teacher.name);
				$("#contact"+teacherId).html(teacher.contact);
				$("#email"+teacherId).html(teacher.email);
				$("#address"+teacherId).html(teacher.address);
				$("#nric"+teacherId).html(teacher.teacherNric);
				$("#qualification"+teacherId).html(teacher.qualification);
				for(var t in TEACHERS){
					var tempTeacher = TEACHERS[t];
					if(tempTeacher.teacherId == id){
						TEACHERS[t] = tempTeacher;
						break;
					}
				}
				TEACHER = teacher;
				//the next line does not work
//				table.ajax.reload();
				$('#editTeacher').modal("hide");
			} else {
				$("#messageEdit").html(message);
			}
			$(".my-loading").addClass('sr-only');
		}
	});
}


//function editTeacher(){
//	var table = $('#teacherTable').DataTable();
//	$('#teacherTable tbody').off('click').on( 'click', 'button', function () {
//		var tr = $(this).closest('tr');
//        var row = table.row( tr );
//       
//        bootbox.dialog({
//        	title: "Edit Details",
//    		message: '<div class="row">  ' +
//			'<div class="col-md-12"> ' +
//			'<form class="form-horizontal" method="post"> ' +
//			'<div class="form-group"> ' +
//			'<div><font color="red" id="updatedMessage"></font></div>'+
//			'<label class="col-md-2 control-label" for="name">Name</label> ' +
//			'<div class="col-md-10"> ' +
//			'<input id="name" name="name" type="text" class="form-control input-md" value= ' + row.data().name + '> ' +
//			'</div> ' +
//			'<label class="col-md-2 control-label" for="nric">NRIC</label> ' +
//			'<div class="col-md-10"> ' +
//				'<input id="nric" name="nric" type="text" class="form-control input-md" value= ' + row.data().teacherNric + '> ' +
//			'</div> ' +
//			'<label class="col-md-2 control-label" for="contact">Contact</label> ' +
//			'<div class="col-md-10"> ' +
//			'<input id="contact" name="contact" type="text" class="form-control input-md" value= ' + row.data().contact + '> ' +
//			'</div> ' +
//			'<label class="col-md-2 control-label" for="email">Email</label> ' +
//			'<div class="col-md-10"> ' +
//			'<input id="email" name="email" type="text" class="form-control input-md" value= ' + row.data().email + '> ' +
//			'</div> ' +
//			'<label class="col-md-2 control-label" for="address">Address</label> ' +
//			'<div class="col-md-10"> ' +
//			'<input id="address" name="address" type="text" class="form-control input-md" value= ' + row.data().address + '> ' +
//			'</div> ' +
//			'</div> ' +
//			'</form> ' +
//			'</div> ' +
//			'</div>',
//    		onEscape: function() {},
//    		buttons: {
//    			success:{
//    				label: "Save!",
//    				className: "btn-success",
//    				
//    				callback: function(){
//    					var teacherId = row.data().teacherId;
//    					var updatedName = $("#name").val();
//    					var updatedNric = $("#nric").val();
//    					var updatedContact = $("#contact").val();
//    					var updatedAddress = $("#address").val();
//    					var updatedEmail = $("#email").val();
//    					
//    					var input = {}
//    					input.teacherId = Number(teacherId);
//    					input.name = updatedName;
//    					input.teacherNric = updatedNric;
//    					input.contact = updatedContact;
//    					input.address = updatedAddress;
//    					input.email = updatedEmail;
//    					var inputStr = JSON.stringify(input);
//    					
//    					inputStr = encodeURIComponent(inputStr);
//    					$.ajax({
//    						url : '../VI/UpdateTeacherServlet?input=' + inputStr, //this part sends to the servlet
//    						method : 'POST',
//    						dataType : 'json',
//    						error : function(err) {
//    							console.log(err);
//    							$("#message").html("System has some error. Please try again.");
//    						},
//    						success : function(data) {
//    							console.log(data);
//    							var status = data.status; 
//    							var message = data.message;
//    							
//    							if (status == 1) {
//    								bootbox.alert("Update is successful!")
//    								table.ajax.reload();
//    								
//    							} else {
//    								$("#message").html("Something's wrong, please try again!");
//    							}
//    						}
//    					});
//    				}
//    		
//    			}
//    		}	
//    	}); 
//	});
//}

function deleteTeacher(){
	var table = $('#teacherTable').DataTable();
	$('#teacherTable tbody').off('click').on( 'click', 'button', function () {
		var tr = $(this).closest('tr');
        var row = table.row( tr );
       
        bootbox.dialog({
        	title: "Delete Teacher",
    		message: '<div class="row">  ' +
						'<div class="col-md-12"> ' +
							'<form class="form-horizontal" method="post"> ' +
								'<div class="form-group"> ' +
									'<label class="col-md-1 control-label"></label> ' +
									'<div>Please enter the teacher name to confirm delete</div></br>'+
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
    					var teacherId = row.data().teacherId;
    					var teacherName = row.data().name;
    					var checkName = $("#deleteName").val();
    					
    					if(teacherName == checkName){
    						var input = {}
        					input.teacherId = Number(teacherId);
        					var inputStr = JSON.stringify(input);
        					inputStr = encodeURIComponent(inputStr);
        					$.ajax({
        						url : '../VI/DeleteTeacherServlet?input=' + inputStr, //this part sends to the servlet
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
        								bootbox.alert("Delete is successful!")
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

//function checkbox(){
//	$('input.enable_cb').keyup(function(){
//		if ($(this).is(':checked')){ 
//			$('button[type="submit"]').prop("disabled, false");
//		}else {
//			$('input[type="submit"]').prop("disabled, true");
//		}
//	}).change();
//}

