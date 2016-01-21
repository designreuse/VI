//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
$(document).ready(function() {
	displayParent();
});
// }
var PARENT = {};

function displayParent() {
	var parent = JSON.parse(localStorage.getItem("parent"));
	//console.log(parent);
	if (parent != null || parent != "") {
		PARENT = parent;
		$("#name").html(parent.name);
		$("#email").html(parent.email);
		$("#contact").html(parent.contact);
		$("#nric").html(parent.parentNric);
		$("#occupation").html(parent.occupation);
		$("#relationship").html(parent.relationship);
	} else {
		console.log(parent);
	}
}

function launchEditParent() {
	$("#editParentForm").attr('onsubmit',
			'editParent(' + PARENT.parentId + '); return false;');
	$("#nameEdit").val(PARENT.name);
	$("#emailEdit").val(PARENT.email);
	$("#contactEdit").val(PARENT.contact);
	$("#parentNricEdit").val(PARENT.parentNric);
//	$("#imageEditImg").attr('src', image);
	$("#occupationEdit").val(PARENT.occupation);
	$("#realtionshipEdit").val(PARENT.relationship);
	
	$("#editParent").modal("show");
}

function editParent(id) {
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
	
	console.log(input);
	var inputStr = JSON.stringify(input);
	console.log(input);
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
			if (status == 1) {
				$("#name").html(parent.name);
				$("#email").html(parent.email);
				$("#contact").html(parent.contact);
				$("#nric").html(parent.parentNric);
				$("#occupation").html(parent.occupation);
				$("#relationship").html(parent.relationship);
				PARENT = parent;
				$('#editParent').modal("hide");
			} else {
				$("#messageEdit").html(message);
			}
			$(".my-loading").addClass('sr-only');
		}
	});
}