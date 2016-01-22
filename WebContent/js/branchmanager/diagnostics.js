$(document).ready(function(){
	checkbox();
	qty();
});

function submitDiagnostic(){
	var studentName = $("studentName").val();
	var studentNric = $("studentNric").val();
	var coursesEnrolled = [];
	$("#enable_cb input:checked").each(function(){
		coursesEnrolled.push(this.name);
	});
	console.log(coursesEnrolled);
	
	var input = {};
	// need to find all the inputs needed 
	// MISSING HERE
	
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		// need to add in the servlet name 
		url : '../VI/GetStudentById?input=' + inputStr, //this part sends to the servlet
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
				var diagnosticMessage = message;
				localStorage.setItem("diagnosticMessage", diagnosticMessage);
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
