$(document).ready(function() {
	getChildren();
	viewFeedback();
});

var COURSES;

function getChildren(){
	var parent = JSON.parse(localStorage.getItem("parent"));
	var parentId = Number(parent.parentId);
	var input = {};
	input.parentId = parentId;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetResultsByParentServlet?input=' + inputStr,
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			var status = data.status; 
			var message = data.message;
			
			if(status == 1){
				localStorage.setItem("childrenResults", JSON.stringify(message));
				for (var s = 0; s < message.length; s++){
					var name = message[s].student.name;
					html = '<div class="col-md-5">'	 +
						'<div class="box box-warning">'+
						'<div class="box-body box-profile">' +
						'<img class="profile-user-img img-responsive img-circle" src="dist/img/avatar3.png" alt="User profile picture">' + 
							'<h3 class="profile-username text-center">'+ message[s].student.name + '</h3>' + 
							'<p class="text-muted text-center"><b>Subjects taking</b>: English & Mathematics </p>' +
							'<ul class="list-group list-group-unbordered">' +
							'<li class="list-group-item">'+
							'<button type="button" class="btn btn-block bg-orange" onclick="redirectFeedback('+ s +')">View All Feedbacks of '+ message[s].student.name +'</button> '+
							' </li>'+
							'</ui>'+
							'</div>'+
							'</div>';
					$("#children").append(html);
				}
			} else {
				console.log(message);
			}
		}
	});
}

function redirectFeedback(studentId){
	localStorage.setItem("selectedFeedback", studentId);
	window.location = "parentFeedbackB2.jsp";
}

function viewFeedback(){
	var childrenList = JSON.parse(localStorage.getItem("childrenResults"));
	var selectedId = Number(localStorage.getItem("selectedFeedback"));
//	console.log(childrenList)
	var selectedChild = childrenList[selectedId].student;
	var selectedCourses = selectedChild.courses;
	COURSES = selectedCourses;
	console.log(COURSES);
	$("#childrenName").append("View All Feedbacks of " + selectedChild.name);

//	for (var tabs = 0; tabs < 4; tabs++){
//		$("#tab_" + tabs).append("<div class='row' id='subrow" + tabs + "'>");
//		$("#subrow" + tabs).append("<div class='col-md-12' id='subcol" + tabs + "'>");
//	}
//	
	var courseHtml = '';
	for (var c = 0; c < selectedCourses.length; c++){
		var courseName = selectedCourses[c].course.name;
		var courseId = selectedCourses[c].course.courseId;
		courseHtml += '<button type="button" class="btn bg-orange" onclick="loadCourseResult('+courseId+')">'+courseName+'</button>';
	}
	
	$("#courses").html(courseHtml);
}

function loadCourseResult(id){
	var course = {};
	for(var tsc in COURSES){
		var tempTsc = COURSES[tsc];
		if(tempTsc.course.courseId == id){
			course = tempTsc;
//			console.log(course);
			break;
		}
	}
	
	var results = course.results;
	var feedbackContents = '';
	var feedBackHeader = '';
	if(results.length != 0){
		for(var r = 0; r < results.length; r++){
			var feedbacks = results[r].teacherFeedbacks;
			var teacherFeedbacks = '';
			if(feedbacks.length != 0){
				for(var fb in feedbacks){
//					console.log(feedbacks[fb].content);
					teacherFeedbacks += '<dd>'+feedbacks[fb].content+'</dd>';
				}
			}
			
			feedBackHeader += '<li><a href="#tab_'+r+'" data-toggle="tab" id="tab'+r+'">Feedback on '+results[r].createDate+'</a></li>'
			feedbackContents += '<div class="tab-pane" id="tab_'+r+'">\
						            <div class="row">\
										<div class="col-md-5">\
											<div class="small-box bg-green">\
												<div class="inner">\
													<h4>Results for</h4>\
													<p>Course Level: '+results[r].courseLevel+'<br>\
														Booklet Level: '+results[r].bookletLevel+'</p>\
												</div>\
												<div class="icon">\
													<i class="ion ion-stats-bars"></i>\
												</div>\
												<div class="small-box-footer"> <i class="fa fa-arrow-circle-right"></i></div>\
											</div>\
										</div>\
										<div class="col-md-7">\
											<dl class="dl">\
												<dt>Teacher</dt>\
								                <dd>'+course.teacher.name+'</dd><br>\
								                <dt>Date</dt>\
								                <dd>'+results[r].createDate+'</dd><br>\
								                <dt>Total booklet score</dt>\
								                <dd>'+results[r].resultValue+'</dd><br>\
								                <dt>Total points</dt>\
								                <dd>'+results[r].pointamount+'</dd><br>\
								                <dt>Feedbacks</dt>\
								                '+ teacherFeedbacks +'\
								              </dl>\
										</div>\
									</div>\
							  </div>';
			
		}
	} else {
		feedBackHeader = '<li class="active"><a href="#tab_0" data-toggle="tab" id="tab0">Latest Feedback</a></li>';
		feedbackContents = '<div class="tab-pane active" id="tab_0">Sorry, there is no result been given yet.</div>';
	}
	
	$("#feedbackHeaders").html(feedBackHeader);
	$("#feedbackContents").html(feedbackContents);
}


//function getFeedback(){
//	
//	var storedIds = JSON.parse(localStorage["studentIds"]);
//	//console.log(storedIds[0]);
//	
//	for(var j = 0; j < storedIds.length; j++){
//	var studentId = Number(storedIds[j]);
//	var input = {};
//	input.studentId = studentId;
//	var inputStr = JSON.stringify(input);
//	var i = encodeURIComponent(inputStr);
//
//	$.ajax({
//		url : '../VI/GetFeedbacksAndResultsByStudentServlet?input=' + inputStr, // this part sends to
//		// the servlet
//		method : 'POST',
//		dataType : 'json',
//		error : function(err) {
//			console.log(err);
//		},
//		success : function(data) {
//			;
//			var feedbackStatus = data.teacherFeedbacks.status; // to modify for both status
//			var resultsStatus = data.results.status;
//			// servlet request
//			if (feedbackStatus == 1 && resultsStatus == 1) {
//				//length of for loop -- different lengths for teacherFeedbacks and results
//				//display NIL if no feedback
//				for (var i = 0; i < data.teacherFeedbacks.message.length; i++) {
//					var feedbackTable = document.getElementById("test");
//					
//					var name = data.teacherFeedbacks.message[i].teacherStudentCourse.student.name;
//					var feedback = data.teacherFeedbacks.message[i].content;
//					//console.log(data.results.message[i].resultValue);
//					var result = data.results.message[i].resultValue;
//					var courseLevel = data.results.message[i].courseLevel;
//					var bookletLevel = data.results.message[i].bookletLevel;
//					
//					var table = document.createElement('TABLE');
//				    table.className = "table table-information";
//				    var tableBody = document.createElement('TBODY');
//				    
//				    var nameRow = document.createElement('TR');
//				    var nameHeader = document.createElement('TD');
//				    var nameValue = document.createElement('TD');
//				    
//				    var feedbackRow = document.createElement('TR');
//				    var feedbackHeader = document.createElement('TD');
//				    var feedbackValue = document.createElement('TD');
//				    
//				    var resultRow = document.createElement('TR');
//				    var resultHeader = document.createElement('TD');
//				    var resultValue = document.createElement('TD');
//				    
//				    var courseLevelRow = document.createElement('TR');
//				    var courseLevelHeader = document.createElement('TD');
//				    var courseLevelValue = document.createElement('TD');
//				    
//				    var bookletLevelRow = document.createElement('TR');
//				    var bookletLevelHeader = document.createElement('TD');
//				    var bookletLevelValue = document.createElement('TD');
//				    
//				    var bold = document.createElement("b");
//				    bold.appendChild(document.createTextNode("Name"));
//				    
//				    nameHeader.appendChild(bold);
//				    nameValue.appendChild(document.createTextNode(name));
//				    
//				    nameRow.appendChild(nameHeader);
//				    nameRow.appendChild(nameValue);
//				    
//				    feedbackHeader.appendChild(document.createTextNode("Feedback"));
//				    feedbackValue.appendChild(document.createTextNode(feedback));
//				    feedbackRow.appendChild(feedbackHeader);
//				    feedbackRow.appendChild(feedbackValue);
//				    
//				    resultHeader.appendChild(document.createTextNode("Result"));
//				    resultValue.appendChild(document.createTextNode(result));
//				    resultRow.appendChild(resultHeader);
//				    resultRow.appendChild(resultValue);
//				    
//				    courseLevelHeader.appendChild(document.createTextNode("Course Level"));
//				    courseLevelValue.appendChild(document.createTextNode(courseLevel));
//				    courseLevelRow.appendChild(courseLevelHeader);
//				    courseLevelRow.appendChild(courseLevelValue);
//				    
//				    bookletLevelHeader.appendChild(document.createTextNode("Booklet Level"));
//				    bookletLevelValue.appendChild(document.createTextNode(bookletLevel));
//				    bookletLevelRow.appendChild(bookletLevelHeader);
//				    bookletLevelRow.appendChild(bookletLevelValue);
//				    
//				    tableBody.appendChild(nameRow);
//				    tableBody.appendChild(feedbackRow);
//				    tableBody.appendChild(resultRow);
//				    tableBody.appendChild(courseLevelRow);
//				    tableBody.appendChild(bookletLevelRow);
//				    
//				    table.appendChild(tableBody);
//				    
//				   feedbackTable.appendChild(table);
//					
//				}
//			
//			} else {
//				//console.log(message);
//			}
//		}
//	});
//	}
//}


//$(".collapse").show();
//if (status == 1) {
//	$('#dynamicStudentList').html("");
//	for (var a = 0; a < message.length; a++){
//		
//		var i = message[a].studentId;
//		var name = message[a].name;
//		
//		$("#dynamicStudentList").append("<div class='box-group header"+ i +"' id='accordion'>");
//		$(".header"+ i ).append("<div class='panel box box-solid box-warning headerpanel"+ i +"'>");
//		$(".headerpanel" + i).append("<div class='box-header with-border headerwords" + i + "'>");
//		$(".headerwords" + i).append("<h4 class='box-title tog" + i + "'>");
//		$(".tog" + i).append("<a data-toggle='collapse' data-parent='#accordion' href='#addSched" + i + "'>" + name + " </a>");
//		
//		//accordion body
//		$(".header"+ i).append("<div id='addSched" + i + "' class='panel-collapse collapse'>");
//		$("#addSched" + i).append("<div class='box-body boxbody" + i + "'>");
//		$(".boxbody" + i ).append("<div class='row' id = 'row" + i + "'>");
//		
//		$("#row" + i ).append("<div class='col-md-3' id='colCourseBookLevel" + i + "'>");
//		$("#colCourseBookLevel" + i ).append("<div class='box box-solid box-warning' id='boxCourseBookLevel" + i + "'>");
//		$("#boxCourseBookLevel" + i ).append("<div class='box-body' id='bodyCourseBookLevel" + i + "'>");
//		$("#bodyCourseBookLevel" + i ).append("<input class='form-control input-sm' id='courseLevel" + i + "' placeholder='Input Course Level' required />");
//		$("#bodyCourseBookLevel" + i ).append("<input class='form-control input-sm' id='bookLevel" + i + "' placeholder='Input Book Level' required />");
//		
//		$("#row" + i ).append("<div class='col-md-3' id='colResult" + i + "'>");
//		$("#colResult" + i ).append("<div class='box box-solid box-warning' id='boxResult" + i + "'>");
//		$("#boxResult" + i ).append("<div class='box-body' id='bodyResult" + i + "'>");
//		$("#bodyResult" + i ).append("<input class='form-control input-sm' id='result" + i + "' placeholder='Input Result' required />");
//		
//		
//		$("#row" + i ).append("<div class='col-md-3' id='colPointAmt" + i + "'>");
//		$("#colPointAmt" + i ).append("<div class='box box-solid box-warning' id='boxPointAmt" + i + "'>");
//		$("#boxPointAmt" + i ).append("<div class='box-body' id='bodyPointAmt" + i + "'>");
//		$("#bodyPointAmt" + i ).append("<input class='form-control input-sm' id='pointAmt" + i + "' placeholder='Input Point Amount' required />");
//		
//		$("#row" + i ).append("<div class='col-md-3' id='colFeedback" + i + "'>");
//		$("#colFeedback" + i ).append("<div class='box box-solid box-warning' id='boxFeedback" + i + "'>");
//		$("#boxFeedback" + i ).append("<div class='box-body' id='bodyFeedback" + i + "'>");
//		$("#bodyFeedback" + i ).append("<input class='form-control input-sm' id='feedback" + i + "' placeholder='Input Feedback' required />");
//	}
//	
//	localStorage.setItem("studentList", JSON.stringify(message));
//
//} else{
//	console.log("Nil")
//}