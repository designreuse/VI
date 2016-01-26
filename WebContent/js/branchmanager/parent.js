$(document).ready(function() {
	var branchManagerId = localStorage.getItem('branchManagerId');
	if (branchManagerId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
		getParentsByBranch(localStorage.getItem('branchId'));
	}
});

var PARENTS = {};
var PARENT = {};

window.onload = function () {
	document.getElementById("parentPassword").onchange = validatePassword;
	document.getElementById("verifyPassword").onchange = validatePassword;
}
function validatePassword(){
	var pass2=document.getElementById("verifyPassword").value;
	var pass1=document.getElementById("parentPassword").value;
	if(pass1!=pass2)
		document.getElementById("verifyPassword").setCustomValidity("Passwords Don't Match");
	else
		document.getElementById("verifyPassword").setCustomValidity('');	 
	//empty string means no validation error
}

function registerParent() {
	var parentName = $("#parentName").val();
	var contact = $("#contactNumber").val();
	var parentEmail = $("#parentEmail").val();
	var parentNric = $("#parentNric").val();
	var occupation = $("#occupation").val();
	var relationship = $("#relationship").val();
	var parentPassword = $("#parentPassword").val();

	var branchId = localStorage.getItem("branchId");
	var numBranchId = Number(branchId);
	console.log(branchId);

	var input = {};
	input.name = parentName;
	input.contact = contact;
	input.email = parentEmail;
	input.parentNric = parentNric;
	input.occupation = occupation;
	input.relationship = relationship;
	input.password = parentPassword;
	input.branchId = numBranchId;

	console.log(input);
	var inputStr = JSON.stringify(input);

	inputStr = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/RegisterParentServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		success : function(data) {
			console.log(data);
			var status = data.status; //shows the  success/failure of the servlet request
			var message = data.message;
			// if status == 1, it means that it is successful. else it will fail
			if (status == 1) {
				alert("Created successfully");	
				var parentMessage = message;
				localStorage.setItem("parentMessage", parentMessage);
				window.location = "parentSuccess.jsp";
			} else {
				$("#message").html(message);
			}
		},
		error : function(err) {
			console.log(err);
			$("#message").html("System has some error. Please try again.");
		}
	});
}


function getParentsByBranch(branchId) {
	$.fn.dataTable.ext.errMode = 'none';
	var input = {};
	input.branchId = Number(branchId);
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url: '../VI/GetParentsByBranchServlet?input=' + inputStr,
		method : "GET",
		dataType : "json",
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			console.log(data);
			var status = data.status;
			var parents = data.message;
			if(status == 1){
				PARENTS = parents;
				var html = '';
				for ( var o in parents) {
					var parent = parents[o];
					var parentId = parent.parentId;
					var date = parent.createDate;
					html += '\
					<tr id="parent-'+ parentId+ '">\
						<td id="parentId-'+ parentId +'">'+ parentId +'</td>\
						<td id="name-'+parentId+'">'+parent.name+'</td>\
						<td id="parentNric-'+parentId+'">'+parent.parentNric+'</td>\
						<td id="contact-'+parentId+'">'+parent.contact+'</td>\
						<td id="email-'+parentId+'">'+parent.email+'</td>\
						<td id="relationship-'+parentId+'">'+parent.relationship+'</td>\
						<td id="occupation-'+parentId+'">'+parent.occupation+'</td>\
						<td>'+date.replace("T", "  ")+'</td>\
						<td>\
							<button class="btn btn-sm btn-info btn-sm" onclick="launchEditParent('+parentId+')" title="Edit"><i class="glyphicon glyphicon-pencil"></i></button>\
							<button class="btn btn-sm btn-warning btn-sm" onclick="launchChangeParentPassword('+parentId+')" title="Change password"><i class="glyphicon glyphicon-cog"></i></button>\
							<button class="btn btn-sm btn-danger btn-sm" onclick="launchDeleteParent('+parentId+')" title="Delete"><i class="glyphicon glyphicon-trash"></i></button>\
						</td>\
					</tr>';
				}
				$("#parents").html(html);
				$("#parentTable").DataTable({
					destroy : true,
					searching : true,
					responsive : true
				});
			} else {
				console.log(message);
			}
		}
	})
	
//	var table =  $('#parentTable').on( 'error.dt', function ( e, settings, techNote, message ) {
//		console.log( 'An error has been reported by DataTables: ', message );
//	}).DataTable({
//		ajax:{
//			url: '../VI/GetParentsByBranchServlet?input=' + inputStr,
//			dataSrc: 'message'
//		},
//		columns: [
//		          {"data": "parentId"},
//		          {"data": 'name'},
//		          {"data": 'parentNric'},
//		          {"data": 'contact'},
//		          {"data": 'email'},
//		          {"data": 'relationship'},
//		          {"data": 'occupation'},
//		          {"data": 'createDate'},
//		          {"data": null, "defaultContent":'<button class="btn btn-sm btn-warning fa" onclick="launchEditParent();" title="Edit"><i class="fa fa-pencil-square-o"></i></button><button class="btn btn-sm btn-danger fa" onclick="deleteParent();" title="Delete"><i class="fa fa-trash-o"></i></button>'}
//		          ],
//		destroy : true,
//		searching : true,
//		responsive : true
//	});
}

function launchEditParent(id) {
	$('#parentTable tbody').off('click').on( 'click', 'button', function () {
		var parent = {};
		for(var p in PARENTS){
			var tempPar = PARENTS[p];
			if(tempPar.parentId == id){
				parent = tempPar;
				break;
			}
		}
		$("#editParentForm").attr('onsubmit','editParent(' + parent.parentId + '); return false;');
		$("#nameEdit").val(parent.name);
		$("#emailEdit").val(parent.email);
		$("#contactEdit").val(parent.contact);
		$("#parentNricEdit").val(parent.parentNric);
//		$("#imageEditImg").attr('src', image);
		$("#occupationEdit").val(parent.occupation);
		$("#realtionshipEdit").val(parent.relationship);
		
		$("#editParent").modal("show");
	});	
}

function editParent(id) {
//	var table = $('#parentTable').DataTable();
	$("#messageEdit").html('');
	$(".my-loading").removeClass('sr-only');

	var updatedName = $("#nameEdit").val();
	var updatedEmail = $("#emailEdit").val();
	var updatedContact = $("#contactEdit").val();
	var updatedNric = $("#parentNricEdit").val();
	var updatedOccupation = $("#occupationEdit").val();
	var updatedRelationship = $("#relationshipEdit").val();
	
	var input = {}
	input.parentId = Number(id);
	input.name = updatedName;
	input.email = updatedEmail;
	input.contact = updatedContact;
	input.parentNric = updatedNric;
	input.occupation = updatedOccupation;
	input.relationship = updatedRelationship;
//	console.log(input);
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : "../VI/UpdateParentServlet?input=" + inputStr,
		method : "POST",
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			console.log(data);
			var status = data.status;
			var parent = data.message;
			var parentId = parent.parentId;
			if (status == 1) {
				$("#name-"+parentId).html(parent.name);
				$("#email"+parentId).html(parent.email);
				$("#contact"+parentId).html(parent.contact);
				$("#nric"+parentId).html(parent.parentNric);
				$("#occupation"+parentId).html(parent.occupation);
				$("#relationship"+parentId).html(parent.relationship);
				for(var p in PARENTS){
					var tempPar = PARENTS[p];
					if(tempPar.parentId == id){
						PARENTS[p] = parent;
						break;
					}
				}
				PARENT = parent;
				//the next line does not work
//				table.ajax.reload();
				$('#editParent').modal("hide");
			} else {
				$("#messageEdit").html(message);
			}
			$(".my-loading").addClass('sr-only');
		}
	});
}

//function editParent(){
//	var table = $('#parentTable').DataTable();
//	$('#parentTable tbody').off('click').on( 'click', 'button', function () {
//		var tr = $(this).closest('tr');
//		var row = table.row( tr );
//
//		bootbox.dialog({
//			title: "Edit Details",
//			message: '<div class="row">  ' +
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
//			'<input id="nric" name="nric" type="text" class="form-control input-md" value= ' + row.data().parentNric + '> ' +
//			'</div> ' +
//			'<label class="col-md-2 control-label" for="contact">Contact</label> ' +
//			'<div class="col-md-10"> ' +
//			'<input id="contact" name="contact" type="text" class="form-control input-md" value= ' + row.data().contact + '> ' +
//			'</div> ' +
//			'<label class="col-md-2 control-label" for="email">Email</label> ' +
//			'<div class="col-md-10"> ' +
//			'<input id="email" name="email" type="text" class="form-control input-md" value= ' + row.data().email + '> ' +
//			'</div> ' +
//			'<label class="col-md-2 control-label" for="relationship">Relationship</label> ' +
//			'<div class="col-md-10"> ' +
//			'<input id="relationship" name="relationship" type="text" class="form-control input-md" value= ' + row.data().relationship + '> ' +
//			'</div> ' +
//			'<label class="col-md-2 control-label" for="occupation">Occupation</label> ' +
//			'<div class="col-md-10"> ' +
//			'<input id="occupation" name="occupation" type="text" class="form-control input-md" value= ' + row.data().occupation + '> ' +
//			'</div> ' +
//			'</div> ' +
//			'</form> ' +
//			'</div> ' +
//			'</div>',
//			onEscape: function() {},
//			buttons: {
//				success:{
//					label: "Save!",
//					className: "btn-success",
//
//					callback: function(){
//						var parentId = row.data().parentId;
//						var updatedName = $("#name").val();
//						var updatedNric = $("#nric").val();
//						var updatedContact = $("#contact").val();
//						var updatedRelationship = $("#relationship").val();
//						var updatedOccupation = $("#occupation").val();
//						var updatedEmail = $("#email").val();
//
//						var input = {}
//						input.parentId = Number(parentId);
//						input.name = updatedName;
//						input.parentNric = updatedNric;
//						input.contact = updatedContact;
//						input.relationship = updatedRelationship;
//						input.email = updatedEmail;
//						var inputStr = JSON.stringify(input);
//
//						inputStr = encodeURIComponent(inputStr);
//						$.ajax({
//							url : '../VI/UpdateParentServlet?input=' + inputStr, //this part sends to the servlet
//							method : 'POST',
//							dataType : 'json',
//							error : function(err) {
//								console.log(err);
//								$("#message").html("System has some error. Please try again.");
//							},
//							success : function(data) {
//								console.log(data);
//								var status = data.status; 
//								var message = data.message;
//
//								if (status == 1) {
//									bootbox.alert("Update is successful!")
//									table.ajax.reload();
//
//								} else {
//									$("#message").html("Something's wrong, please try again!");
//								}
//							}
//						});
//					}
//
//				}
//			}	
//		}); 
//	});
//}

function deleteParent(){
	var table = $('#parentTable').DataTable();
	$('#parentTable tbody').off('click').on( 'click', 'button', function () {
		var tr = $(this).closest('tr');
		var row = table.row( tr );

		bootbox.dialog({
			title: "Delete Parent",
			message: '<div class="row">  ' +
			'<div class="col-md-12"> ' +
			'<form class="form-horizontal" method="post"> ' +
			'<div class="form-group"> ' +
			'<label class="col-md-1 control-label"></label> ' +
			'<div>Please enter the parent name to confirm delete</div></br>'+
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
						var parentId = row.data().parentId;
						var parentName = row.data().name;
						var checkName = $("#deleteName").val();

						if(parentName == checkName){
							var input = {}
							input.parentId = Number(parentId);
							var inputStr = JSON.stringify(input);
							inputStr = encodeURIComponent(inputStr);
							$.ajax({
								url : '../VI/DeleteParentServlet?input=' + inputStr, //this part sends to the servlet
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
							return false;
						}
					}    		
				}
			}	
		}); 
	});
}
