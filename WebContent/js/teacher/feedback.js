 $(document).ready(function() {
	 populateStudents();
   });

function populateStudents(){
//	var branchId = 1;
//	var input = {};
//	input.branchId = branchId
//	var inputStr = JSON.stringify(input);
//	inputStr = encodeURIComponent(inputStr);
//		
//	$.ajax({
//		url : '../VI/GetBranchCoursesByBranchServlet?input=' + inputStr, //this part sends to the servlet
//		method : 'POST',
//		dataType : 'json',
//		error : function(err) {
//			console.log(err);
//		},
//		success : function(data) {
//			var status = data.status; 
//			var message = data.message;
//				
//			if (status == 1) {	
//				console.log(message);
//			} else{
//				console.log("Nil")
//			}
//		}
//	});	

	for (var i = 0; i < 5; i++){
		$("#dynamicStudentList").append("<div class='box-group header"+ i +"' id='accordion'>");
		$(".header"+ i ).append("<div class='panel box box-success headerpanel"+ i +"'>");
		$(".headerpanel" + i).append("<div class='box-header with-border headerwords" + i + "'>");
		$(".headerwords" + i).append("<h4 class='box-title tog" + i + "'>");
		$(".tog" + i).append("<a data-toggle='collapse' data-parent='#accordion' href='#addSched" + i + "'> Student" + i + " </a>");
		
		$(".header"+ i).append("<div id='addSched" + i + "' class='panel-collapse collapse'>");
		$("#addSched" + i).append("<div class='box-body content" + i + "'>");
		$(".content" + i ).append("<p>HELLO WORLD!" + i + " <p>");
	
	}
}
