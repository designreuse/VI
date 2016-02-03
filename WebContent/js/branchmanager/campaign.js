$(document).ready(function() {
	var branchManagerId = localStorage.getItem('branchManagerId');
	if (branchManagerId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
		$('#startDate').daterangepicker({
			singleDatePicker: true,
			format : 'DD/MM/YYYY'
		});
		$('#endDate').daterangepicker({
			singleDatePicker: true,
			format : 'DD/MM/YYYY'
		});
	}
});

function createMarketingCampaign(lati,longi){
	var branchId = localStorage.getItem('branchId');
	var name = $("#name").val();
	var startDate = $("#startDate").val(); 
	var endDate = $("#endDate").val();
	var campaignType = $("#type").val();
	var address = $("#address").val();
	var postalCode = $("#postalCode").val();
		
	var input = {};
	input.branchId = Number(branchId);
	input.name = name;
	input.startDate = moment((startDate), "DD/MM/YYYY");
	input.endDate = moment((endDate), "DD/MM/YYYY");
	input.campaignType = campaignType;
	input.address = address;
	input.postalCode = postalCode;
	input.latitude = lati;
	input.longitude = longi;
	
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);

	$.ajax({
		url : '../VI/CreateCampaignServlet?input=' + inputStr, // this part sends to
		// the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			var status = data.status; // shows the success/failure of the
			// servlet request
			if (status == 1) {
				//TBC
				alert("Created successfully");

			} else {
				console.log(message);
			}
		}
	});
	
}

function getLatLong(callback){
	var address = $("#address").val();
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
