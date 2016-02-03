$(document).ready(function() {
	var branchManagerId = localStorage.getItem('branchManagerId');
	if (branchManagerId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
		$('#birthDate').daterangepicker({
			singleDatePicker: true,
			format : 'DD/MM/YYYY'
		});
		getMarketingCampaigns();
		getStudentsByBranch(localStorage.getItem('branchId'));
		

	}
});

var STUDENTS = {};
var STUDENT	= {};
var CAMPAIGNS;

function registerStudent(lat,long) {
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
//	console.log(birthDate);
	var homeContact = $("#homeContact").val();
	var emergencyContact = $("#emergencyContact").val();
//	console.log(emergencyContact);
	var studentAddress = $("#studentAddress").val();
	var postalCode = $("#postalCode").val();

	// student education details
	var schoolName = $("#schoolName").val();
	var schoolLevel = $("#schoolLevel").val()

	//marketing
	var campaignName = $("#campaign").val();
	for(var j = 0; j < CAMPAIGNS.length; j++){
		if(campaignName == CAMPAIGNS[j].name){
			var campaignId = CAMPAIGNS[j].campaignId;
	
			var branchId = localStorage.getItem("branchId");
		//	console.log(branchId);
		//	console.log(taken);
		
			var input = {};
		
			input.parentNric = parentNric;
		
			input.name = studentName;
			input.studentNric = studentNric;
			input.gender = gender;
			input.birthDate = moment((birthDate), "DD/MM/YYYY");
			input.homeContact = homeContact;
			input.emergencyContact = emergencyContact;
			input.postalCode = postalCode;
			input.address = studentAddress;
		
			input.schoolName = schoolName;
			input.schoolLevel = schoolLevel;
		
			input.takenDiagnostic = Number(taken);
			input.branchId = Number(branchId);
			
			input.campaignId = Number(campaignId);
			
			input.latitude = lat;
			input.longitude = long;
		
			var inputStr = JSON.stringify(input);
		
			inputStr = encodeURIComponent(inputStr);
			$.ajax({
				url : '../VI/RegisterStudentServlet?input=' + inputStr, // this part
																		// sends to the
																		// servlet
				method : 'POST',
				dataType : 'json',
				error : function(err) {
					console.log(err);
					$("#message").html("System has some error. Please try again.");
				},
				success : function(data) {
					console.log(data);
					var status = data.status; // shows the success/failure of the
												// servlet request
					var message = data.message;
					// if status == 1, it means that it is successful, else it will
					// fail.
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
								// $("#message").html(err);
							},
							success : function(data) {
								console.log(data);
								var status = data.status; // shows the success/failure
															// of the servlet request
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
	}
}

//$.ajax({
//	url: '../VI/GetStudentsByBranchServlet?input=' + inputStr,
//	method : "GET",
//	dataType : "json",
//	error : function(err) {
//		console.log(err);
//	},
//	success : function(data) {
//		console.log(data);
//		var status = data.status;
//		var students = data.message;
//		if(status == 1){
//			STUDENTS = students;
//			var html = '';
//			for ( var o in students) {
//				var student = students[o];
//				var studentId = student.studentId;
//				var date = student.createDate;
//				html += '\
//				<tr id="student-'+ studentId+ '">\
//					<td id="studentId-'+ studentId +'">'+ studentId +'</td>\
//					<td id="name-'+studentId+'">'+student.name+'</td>\
//					<td id="gender-'+studentId+'">'+student.gender+'</td>\
//					<td id="studentNric-'+studentId+'">'+student.studentNric+'</td>\
//					<td id="birthDate-'+studentId+'">'+student.birthDate+'</td>\
//					<td id="homeContact-'+studentId+'">'+student.homeContact+'</td>\
//					<td id="emergencyContact-'+studentId+'">'+student.emergencyContact+'</td>\
//					<td id="address-'+studentId+'">'+student.address+'</td>\
//					<td id="postalCode-'+studentId+'">'+student.postalCode+'</td>\
//					<td id="schoolName-'+studentId+'">'+student.schoolName+'</td>\
//					<td id="schoolLevel-'+studentId+'">'+student.schoolLevel+'</td>\
//					<td id="takenDiagnostic-'+studentId+'">'+student.takenDiagonstic+'</td>\
//					<td id="points-'+studentId+'">'+student.points+'</td>\
//					<td>'+date.replace("T", "  ")+'</td>\
//					<td>\
//						<button class="btn btn-sm btn-info btn-sm" onclick="launchEditStudent('+studentId+')" title="Edit"><i class="glyphicon glyphicon-pencil"></i></button>\
//						<button class="btn btn-sm btn-danger btn-sm" onclick="launchDeleteStudent('+studentId+')" title="Delete"><i class="glyphicon glyphicon-trash"></i></button>\
//					</td>\
//				</tr>';
//			}
//			$("#students").html(html);
//			$("#studentTable").DataTable({
//				destroy : true,
//				searching : true,
//				responsive : true
//			});
//		} else {
//			console.log(message);
//		}
//	}
//})

function getStudentsByBranch(branchId) {
	$.fn.dataTable.ext.errMode = 'none';
	var input = {};
	input.branchId = Number(branchId);
	var inputStr = JSON.stringify(input);
	console.log(inputStr);
	inputStr = encodeURIComponent(inputStr);

	var table = $('#studentTable').on('error.dt',
					function(e, settings, techNote, message) {
						console.log('An error has been reported by DataTables: ', message);
					}).DataTable(
					{
					ajax : {
						url : '../VI/GetStudentsByBranchServlet?input=' + inputStr,
							dataSrc : 'message'
						},
						columns : [
								{"data" : "studentId"},
								{"data" : 'name'},
								{"data" : 'gender'},
								{"data" : 'studentNric'},
								{"data" : 'birthDate'},
								{"data" : 'homeContact'},
								{"data" : 'emergencyContact'},
//								{"data" : 'address'},
//								{"data" : 'postalCode'},
//								{"data" : 'schoolName'},
//								{"data" : 'schoolLevel'},
								{"data" : 'takenDiagnostic'},
								{"data" : 'points'},
								{"data" : null, "defaultContent" : '<button class="btn btn-sm btn-warning fa" onclick="launchEditStudent();" title="Edit"><i class="fa fa-pencil-square-o"></i></button><button class="btn btn-sm btn-danger fa" onclick="deleteStudent();" title="Delete"><i class="fa fa-trash-o"></i></button>'} 
								],
						destroy : true,
						searching : true,
						responsive: {
							details: false
						}
					});
}

//edited until here.
function launchEditStudent() {
	$('#studentTable tbody').off('click').on( 'click', 'button', function () {
		var student = {};
		for(var s in STUDENTS){
			var tempStudent = STUDENTS[s];
			if(tempStudent.studentId == id){
				student = tempStudent;
				break;
			}
		}
		$("#editStudentForm").attr('onsubmit','editStudent(' + student.studentId + '); return false;');
		$("#nameEdit").val(student.name);
		$("#genderEdit").val(student.gender);
		$("#studentNricEdit").val(student.studentNric);
		$("#birthDateEdit").val(student.contact);
		$("#homeContactEdit").val(student.homeContact);
		$("#emergencyContactEdit").val(student.emergencyContact);
		$("#addressEdit").val(student.address);
		$("#postalCodeEdit").val(student.postalCode);
		$("#schoolNameEdit").val(student.schoolName);
		$("#schoolLevelEdit").val(student.schoolLevel);
		$("#takenDiagnosticEdit").val(student.takenDiagnostic);
		$("#pointsEdit").val(student.points);
//		$("#imageEditImg").attr('src', image);
		
		$("#editStudent").modal("show");
	});	
}

function editStudent(id) {
//	var table = $('#studentTable').DataTable();
	$("#messageEdit").html('');
	$(".my-loading").removeClass('sr-only');
	
	var input = {}
	input.studentId = Number(id);
	input.name = $("#nameEdit").val();
	input.gender = $("#genderEdit").val();
	input.studentNric = $("#studentNricEdit").val();
	input.birthDate = $("#birthDateEdit").val();
	input.homeContact = $("#homeContactEdit").val();
	input.emergencyContact = $("#emergencyContactEdit").val();
	input.address = $("#addressEdit").val();
	input.postalCode = $("#postalCodeEdit").val();
	input.schoolName = $("#schoolNameEdit").val();
	input.schoolLevel = $("#schoolLevelEdit").val();
	input.takenDiagnostic = $("#takenDiagnosticEdit").val();
	input.points = $("#pointsEdit").val();
	
//	console.log(input);
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : "../VI/UpdateStudentServlet?input=" + inputStr,
		method : "POST",
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			console.log(data);
			var status = data.status;
			var student = data.message;
			var studentId = student.studentId;
			if (status == 1) {
				$("#name"+studentId).html(student.name);
				$("#gender"+studentId).html(student.gender);
				$("#studentNric"+studentId).html(student.studentNric);
				$("#birthDate"+studentId).html(student.contact);
				$("#homeContact"+studentId).html(student.homeContact);
				$("#emergencyContact"+studentId).html(student.emergencyContact);
				$("#address"+studentId).html(student.address);
				$("#postalCode"+studentId).html(student.postalCode);
				$("#schoolName"+studentId).html(student.schoolName);
				$("#schoolLevel"+studentId).html(student.schoolLevel);
				$("#takenDiagnostic"+studentId).html(student.takenDiagnostic);
				$("#points"+studentId).html(student.points);
				for(var s in STUDENTS){
					var tempStudent = STUDENTS[s];
					if(tempStudent.studentId == id){
						STUDENTS[s] = student;
						break;
					}
				}
				STUDENT = student;
				//the next line does not work
//				table.ajax.reload();
				$('#editStudent').modal("hide");
			} else {
				$("#messageEdit").html(message);
			}
			$(".my-loading").addClass('sr-only');
		}
	});
}


//function editStudent() {
//	var table = $('#studentTable').DataTable();
//	$('#studentTable tbody')
//			.off('click')
//			.on(
//					'click',
//					'button',
//					function() {
//						var tr = $(this).closest('tr');
//						var row = table.row(tr);
//
//						bootbox
//								.dialog({
//									title : "Edit Details",
//									message : '<div class="row">  '
//											+ '<div class="col-md-12"> '
//											+ '<form class="form-horizontal" method="post"> '
//											+ '<div class="form-group"> '
//											+ '<div><font color="red" id="updatedMessage"></font></div>'
//											+ '<label class="col-md-2 control-label" for="name">Name</label> '
//											+ '<div class="col-md-10"> '
//											+ '<input id="name" name="name" type="text" class="form-control input-md" value= '
//											+ row.data().name
//											+ '> '
//											+ '</div> '
//											+ '<label class="col-md-2 control-label" for="nric">NRIC</label> '
//											+ '<div class="col-md-10"> '
//											+ '<input id="nric" name="nric" type="text" class="form-control input-md" value= '
//											+ row.data().studentNric
//											+ '> '
//											+ '</div> '
//											+ '<label class="col-md-2 control-label" for="contact">Contact</label> '
//											+ '<div class="col-md-10"> '
//											+ '<input id="contact" name="contact" type="text" class="form-control input-md" value= '
//											+ row.data().contact
//											+ '> '
//											+ '</div> '
//											+ '<label class="col-md-2 control-label" for="address">Address</label> '
//											+ '<div class="col-md-10"> '
//											+ '<input id="address" name="address" type="text" class="form-control input-md" value= '
//											+ row.data().address
//											+ '> '
//											+ '</div> '
//											+ '</div> '
//											+ '</form> ' + '</div> ' + '</div>',
//									onEscape : function() {
//									},
//									buttons : {
//										success : {
//											label : "Save!",
//											className : "btn-success",
//
//											callback : function() {
//												var studentId = row.data().studentId;
//												var updatedName = $("#name")
//														.val();
//												var updatedNric = $("#nric")
//														.val();
//												var updatedContact = $(
//														"#contact").val();
//												var updatedAddress = $(
//														"#address").val();
//												var updatedEmail = $("#email")
//														.val();
//
//												var input = {}
//												input.studentId = Number(studentId);
//												input.name = updatedName;
//												input.studentNric = updatedNric;
//												input.contact = updatedContact;
//												input.address = updatedAddress;
//												input.email = updatedEmail;
//												var inputStr = JSON
//														.stringify(input);
//
//												inputStr = encodeURIComponent(inputStr);
//												$
//														.ajax({
//															url : '../VI/UpdateStudentServlet?input='
//																	+ inputStr, // this
//																				// part
//																				// sends
//																				// to
//																				// the
//																				// servlet
//															method : 'POST',
//															dataType : 'json',
//															error : function(
//																	err) {
//																console
//																		.log(err);
//																$("#message")
//																		.html(
//																				"System has some error. Please try again.");
//															},
//															success : function(
//																	data) {
//																console
//																		.log(data);
//																var status = data.status;
//																var message = data.message;
//
//																if (status == 1) {
//																	bootbox
//																			.alert("Update is successful!")
//																	table.ajax
//																			.reload();
//
//																} else {
//																	$(
//																			"#message")
//																			.html(
//																					"Something's wrong, please try again!");
//																}
//															}
//														});
//											}
//
//										}
//									}
//								});
//					});
//}

function deleteStudent() {
	var table = $('#studentTable').DataTable();
	$('#studentTable tbody')
			.off('click')
			.on(
					'click',
					'button',
					function() {
						var tr = $(this).closest('tr');
						var row = table.row(tr);

						bootbox
								.dialog({
									title : "Delete Student",
									message : '<div class="row">  '
											+ '<div class="col-md-12"> '
											+ '<form class="form-horizontal" method="post"> '
											+ '<div class="form-group"> '
											+ '<label class="col-md-1 control-label"></label> '
											+ '<div>Please enter the student name to confirm deletion.</div></br>'
											+ '<div><font color="red" id="deleteMessage"></font></div>'
											+ '<label class="col-md-2 control-label" for="name">Name</label> '
											+ '<div class="col-md-10"> '
											+ '<input id="deleteName" name="deleteName" type="text" class="form-control input-md" placeholder= '
											+ row.data().name + '> '
											+ '</div> ' + '</div> '
											+ '</form> ' + '</div> ' + '</div>',
									onEscape : function() {
									},
									buttons : {
										success : {
											label : "Delete!",
											className : "btn-danger",

											callback : function() {
												var studentId = row.data().studentId;
												var studentName = row.data().name;
												var checkName = $("#deleteName")
														.val();

												if (studentName == checkName) {
													var input = {}
													input.studentId = Number(studentId);
													var inputStr = JSON
															.stringify(input);
													inputStr = encodeURIComponent(inputStr);
													$
															.ajax({
																url : '../VI/DeleteStudentServlet?input='
																		+ inputStr, // this
																					// part
																					// sends
																					// to
																					// the
																					// servlet
																method : 'POST',
																dataType : 'json',
																error : function(
																		err) {
																	console
																			.log(err);
																	$(
																			"#deleteMessage")
																			.html(
																					"System has some error. Please try again.");
																},
																success : function(
																		data) {
																	console
																			.log(data);
																	var status = data.status;
																	var message = data.message;

																	if (status == 1) {
																		bootbox
																				.alert("Student has been successfully deleted!")
																		table.ajax
																				.reload();

																	} else {
																		$(
																				"#deleteMessage")
																				.html(
																						"Something's wrong, please try again!");
																	}
																}
															});
												} else {
													$("#deleteMessage")
															.html(
																	"The entered name does not match, please try again.");
												}
											}
										}
									}
								});
					});
}

function displayMarketingCampaigns() {
	var select = document.getElementById("campaign");
	var storedEvents = JSON.parse(localStorage["campaigns"]);
	var options = [];
	for(var j = 0; j < storedEvents.length; j++){
		//console.log(storedIds[j])
		var campaign = storedEvents[j];
		options.push(campaign);
	}
	
	CAMPAIGNS = storedEvents;

	for(var i = 0; i < options.length; i++) {
	    var opt = options[i].name;
	    var el = document.createElement("option");
	    el.textContent = opt;
	    el.value = opt;
	    select.appendChild(el);
	}
}

function getMarketingCampaigns() {
	var branchId = localStorage.getItem('branchManagerId');
	
	var input = {};
	input.branchId = Number(branchId);
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/GetCampaignsByBranchServlet?input=' + inputStr, // this part sends to
		// the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			;
			var status = data.status; // shows the success/failure of the
			// servlet request
			if (status == 1) {
				var campaigns = [];
				
				for (var i = 0; i < data.message.length; i++) {
					var obj = data.message[i];
					campaigns.push(obj);
					localStorage["campaigns"] = JSON.stringify(campaigns);	
					
				}
				displayMarketingCampaigns();
			} else {
				console.log(message);
			}
		}
	});
	
}

function getLatLong(callback){
	var address = $("#studentAddress").val();
	var replaced = address.split(' ').join('+');
	var geoAddress = "address=" + replaced;
	var key = "key=AIzaSyDPiRJIGTN8vggsL2yJErTwHrw6DcLM0kI";
	
	var input = geoAddress + "&" + key;
	
//	var inputStr = JSON.stringify(input);
//	inputStr = encodeURIComponent(inputStr);
	console.log(input);

	$.ajax({
		url : 'https://maps.googleapis.com/maps/api/geocode/json?' + input, // this part sends to
		// the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			//lat, long
			for (var i = 0; i < data.results.length; i++) {
				var lat = data.results[i].geometry.location.lat;
				var long = data.results[i].geometry.location.lng;
				console.log(data);
				callback(lat,long);
			}
		}
	});
}