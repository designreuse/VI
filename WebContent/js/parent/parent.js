//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
$(document).ready(function() {
	var PARENT = JSON.parse(localStorage.getItem("parent"));
	viewChildren(PARENT.parentId);
});
// }

var STUDENTS = {};
var STUDENT = {};

// toggle function
function viewChildren(id) {
	var input = {};
	input.parentId = Number(id);
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
//	console.log("enter method");
	$.ajax({
		url : '../VI/GetStudentsByParentServlet?input=' + inputStr,
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			var status = data.status; 
			var students = data.message;
			if(status == 1){
				STUDENTS = students;
				var html = '';
				for(var s in students){
					var student = students[s];
					var sId = student.studentId;
					html += '\
					<div class="row">\
						<div class="col-md-12">\
							<div class="box">\
								<div class="box-header">\
									<h2 class="box-title" id="name-'+sId+'">'+student.name+'</h2>\
									<button type="button" class="btn btn-default btn-warning btn-flat pull-right" onclick="launchEditStudent('+student.studentId+')" title="Edit Student Profile">\
										<i class="glyphicon glyphicon-pencil"></i>Edit</button>\
		     						</div>\
								<div class="box-body">\
									<div class="row">\
										<div class="col-md-3 col-lg-3" align="center">\
											<img alt="Student Pic" src="dist/img/avatar2.png" class="img-circle img-responsive">\
										</div>\
										<div class="col-md-6 col-lg-6">\
											<table class="table table-user-information">\
												<tbody>\
													<tr>\
														<td><strong>NRIC:</strong></td>\
														<td id="nric-'+sId+'">'+student.studentNric+'</td>\
													</tr>\
													<tr>\
														<td><strong>Home Contact:</strong></td>\
														<td id="homeContact-'+sId+'">'+student.homeContact+'</td>\
													</tr>\
													<tr>\
														<td><strong>Emergency Contact:</strong></td>\
														<td id="emergencyContact-'+sId+'">'+student.emergencyContact+'</td>\
													</tr>\
													<tr>\
														<td><strong>Address:</strong></td>\
														<td id="address-'+sId+'">'+student.address+'</td>\
													</tr>\
													<tr>\
														<td><strong>Postal Code:</strong></td>\
														<td id="postalCode-'+sId+'">'+student.postalCode+'</td>\
													</tr>\
													<tr>\
														<td><strong>School Name:</strong></td>\
														<td id="schoolName-'+sId+'">'+student.schoolName+'</td>\
													</tr>\
													<tr>\
														<td><strong>School Level:</strong></td>\
														<td id="schoolLevel-'+sId+'">'+student.schoolLevel+'</td>\
													</tr>\
													<tr>\
														<td><strong>Points:</strong></td>\
														<td id="points-'+sId+'">'+student.points+'</td>\
													</tr>\
												</tbody>\
											</table>\
										</div>\
									</div>\
								</div>\
							</div>\
						</div>\
					</div>';
				}
				$("#students").html(html);
			} else {
				console.log(message);
			}
		}
	});

}

function launchEditStudent(id) {
	var student = {};
	for(var s in STUDENTS){
		var tempStu = STUDENTS[s];
		if(tempStu.studentId == id){
			student = tempStu;
			STUDENT = tempStu;
			break;
		}
	}
	$("#editStudentForm").attr('onsubmit', 'editStudent(' +id+ '); return false;');
	$("#nameEdit").val(student.name);
	$("#nricEdit").val(student.studentNric);
	$("#homeContactEdit").val(student.homeContact);
	$("#emergencyContactEdit").val(student.emergencyContact);
	$("#addressEdit").val(student.address);
	$("#postalCodeEdit").val(student.postalCode);
//	$("#imageEditImg").attr('src', image);
	$("#schoolNameEdit").val(student.schoolName);
	$("#schoolLevelEdit").val(student.schoolLevel);
	
	//files not shown
//	$("#birthDate").val(student.birthday);
	
	$("#editStudent").modal("show");
}


function editStudent(id){
	var input = {}
	input.studentId = Number(id);
	input.name = $("#nameEdit").val();
	input.studentNric = $("#nricEdit").val();
	input.homeContact = $("#homeContactEdit").val();
	input.emergencyContact = $("#emergencyContactEdit").val();
	input.address = $("#addressEdit").val();
	input.postalCode = $("#postalCodeEdit").val();
//	$("#imageEditImg").attr('src', image);
	input.schoolName = $("#schoolNameEdit").val();
	input.schoolLevel = $("#schoolLevelEdit").val();
	//fields not been modified
	input.birthDate = STUDENT.birthDate;
	input.takenDiagnostic = STUDENT.takenDiagnostic;
	
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/UpdateStudentServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			console.log(data);
			var status = data.status; 
			var student = data.message;
			if (status == 1) {
				$("#name-" + id).html(student.name);
				$("#nric-" + id).html(student.studentNric);
				$("#homeContact-" + id).html(student.homeContact);
				$("#emergencyContact-" + id).html(student.emergencyContact);
//				$("#imageA-" + id).attr('herf', student.image);
//				$("#image-" + id).attr('src', student.image);
				$("#address-" + id).html(student.address);
				$("#postalCode-" + id).html(student.postalCode);
				$("#schoolName-" + id).html(student.schoolName);
				$("#schoolLevel-" + id).html(student.schoolLevel);
				for(var s in STUDENTS){
					var tempStudent = STUDENTS[s];
					if(tempStudent.studentId == id){
						STUDENTS[s] = student;
						break;
					}
				}
				$("#editStudent").modal("hide");
			} else {
				$("#message").html(data.message);
			}
		}
	});
}