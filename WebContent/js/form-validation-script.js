 $().ready(function() {
	$('#register_form').validate({
		rules : {
			parentName : {
				required : true,
				minlength : 2,
				maxlength : 20,
				lettersonly : true
			},
			parentNric : {
				required : true,
				minlength : 9,
				maxlength : 9,
				nowhitespace : true
			},
			contactNumber : {
				required : true,
				minlength : 10,
				maxlength : 13,
				digits : true
			},
			parentAddress : {
				required : true,
				minlength : 10,
			}
		},
		messages : {
			parentName : {
				required : "Please enter your name",
				minlength : "Name should be more than 2 characters",
				maxlength : "Name should be less than 20 characters",
				lettersonly : "Name should contain only letters"
			},
			parentNric : {
				required : "Please enter your NRIC",
				minlength : "NRIC should be more than 9 characters",
				maxlength : "NRIC should be less than 9 characters",
				nowhitespace : "NRIC should not have any spaces"
			},
			contactNumber : {
				required : "Please enter your mobile number",
				minlength : "Mobile number should be more than 10 characters",
				maxlength : "Mobile number should be less than 13 characters",
				digits : "Mobile number should contain only digits"
			},
			parentAddress : {
				required : "Please enter your address",
				minlength : "Address should be more than 10 characters",
			}
		},
	});
});