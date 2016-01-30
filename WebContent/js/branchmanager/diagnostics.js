$(document).ready(function() {
	var branchManagerId = localStorage.getItem('branchManagerId');
	if (branchManagerId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
//		checkbox();
//		qty();
	}
});

var STUDENT;

function submitDiagnostic(){
	//studentId, subjectName, resultValue
	var studentId = STUDENT.studentId;
	var subjectName = document.querySelector('input[name="course"]:checked').value;
	var resultValue = $("#results").val();
	var courseLevel = $("#courseLevel").val();
	
	var input = {};
	input.studentId = studentId;
	input.subjectName = subjectName;
	input.resultValue = resultValue;
	input.courseLevel = courseLevel;
	
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		// need to add in the servlet name 
		url : '../VI/CreateDiagnosticsServlet?input=' + inputStr, //this part sends to the servlet
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
				alert("Updated successfully");	
				window.location = "diagnosticSuccess.jsp";
			} else {
				$("#message").html(message);
			}
		}
	});
	
}
function checkbox(){
	$('input.enable_cb').change(function(){
		if ($(this).is(':checked')){ 
			$(this).next('div.enter_text').show();
		}else {
			$('div.enter_text').hide();
		}
	}).change();
}

function qty(){
	$('.qtyplus').on("click",function(e){
		// Stop acting like a button
		e.preventDefault();
		// Get the field name
		fieldName = $(this).attr('field');
		// Get its current value
		var currentVal = parseInt($('input[name='+fieldName+']').val());
		// If is not undefined
		if (!isNaN(currentVal)) {
			// Increment
			$('input[name='+fieldName+']').val(currentVal + 1);
		} else {
			// Otherwise put a 0 there
			$('input[name='+fieldName+']').val(0);
		}
	});
	// This button will decrement the value till 0

	$(".qtyminus").on("click",function(e) {
		// Stop acting like a button
		e.preventDefault();
		// Get the field name
		fieldName = $(this).attr('field');
		// Get its current value
		var currentVal = parseInt($('input[name='+fieldName+']').val());
		// If it isn't undefined or its greater than 0
		if (!isNaN(currentVal) && currentVal > 0) {
			// Decrement one
			$('input[name='+fieldName+']').val(currentVal - 1);
		} else {
			// Otherwise put a 0 there
			$('input[name='+fieldName+']').val(0);
		}
	});
}

function getStudentByNric(){
	var studentNric = $("#studentNric").val();
	var input = {};
	input.studentNric = studentNric;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/GetStudentByNric?input=' + inputStr, // this part sends to
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
				STUDENT = data.message;
				submitDiagnostic();
			} else {
				console.log(message);
			}
		}
	});
	
}


function getSchedules() {
	//TeacherStudentCourse
	var courseName = $("#selectCourse").val();
	for(var j = 0; j < COURSES.length; j++){
		//console.log(COURSES[j].name);
		if(courseName == COURSES[j].name){
			var courseId = COURSES[j].courseId;
			var input = {};
			input.courseId = courseId;
			input.teacherId = teacherId;
			var inputStr = JSON.stringify(input);
			inputStr = encodeURIComponent(inputStr);
		
			$.ajax({
				url : '../VI/GetSchedulesByCourseServlet?input=' + inputStr, // this part sends to
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
						// store scheduleIds
						var scheduleIds = [];
						
						for (var i = 0; i < data.message.length; i++) {
							var obj = data.message[i];
							
							scheduleIds.push(obj.scheduleId);
							//console.log(scheduleIds);
							localStorage["scheduleIds"] = JSON.stringify(scheduleIds);
						}
					} else {
						console.log(message);
					}
				}
			});
		}
	}
}