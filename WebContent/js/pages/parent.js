//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
    $(document).ready(function() {
    	getParents();
    });
//}

var PARENTS = [];

function registerParent() {
	var parentEmail = $("#parentEmail").val();
	var parentPassword = $("#parentPassword").val();
	var parentName = $("#parentName").val();
	var contactNumber = $("#contactNumber").val();
	var parentAddress = $("#parentAddress").val();

	var input = {};
	input.email = parentEmail;
	input.password = parentPassword;
	input.name = parentName;
	input.contact = contactNumber;
	input.address = parentAddress;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url : '../VI/RegisterParentServlet?input=' + inputStr, //this part sends to the servlet
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
				var parentMessage = message;
				localStorage.setItem("parentMessage", parentMessage);
				window.location = "parentSuccess.jsp";
			} else {
				$("#message").html("Something's wrong, please try again!");
			}
		}
	});
}

function getParents() {
//    var input = {};
//    input.branchId = Number(branchId);
//    var inputStr = JSON.stringify(input);
//    inputStr = encodeURIComponent(inputStr);
	
    $.ajax({
        url: '../VI/GetAllParentsServlet?input={}',
        method: 'GET',
        dataType: 'json',
        error: function(err) {
            console.log(err);
        },
        success: function(data) {
            console.log(data);
            var status = data.status;
            var message = data.message;
            if (status == 1) {
                var parents = message;
                //console.log(JSON.stringify(parents))
                PARENTS = parents;
                var html = '';
                for (var p in parents) {
                    var parent = parents[p];

                    var parentId = parent.parentId;
                    var name = parent.name;
                    var contact = parent.contact;
                    var email = parent.email;
                    var address = parent.address;
                    var date = parent.createDate;
//                    var status = parent.objStatus;
//                    var objStatus = '';
//                    switch (status) {
//                        case 0:
//                            objStatus = "Non-Active";
//                            break;
//                        case 1:
//                            objStatus = "Active";
//                            break;
//                        default:
//                            objStatus = "Undefined";
//                            break;
//                    }

                    html += '\
					<tr id="parent-' + parentId + '">\
						<td id="parentId-' + parentId + '">' + parentId + '</td>\
						<td id="name-' + parentId + '">' + name + '</td>\
						<td id="contact-' + parentId + '">' + contact + '</td>\
						<td id="email-' + parentId + '">' + email + '</td>\
						<td id="address-' + parentId + '">' + address + '</td>\
						<td>' + date.replace("T", "  ") + '</td>\
						<td>\
							<button class="btn btn-sm btn-success fa fa-file-powerpoint-o" onclick="launchViewChildren(' + parentId + ')" title="Children"></button>\
							<button class="btn btn-sm btn-warning fa fa-pencil" onclick="launchEditParent(' + parentId + ')" title="Edit"></button>\
							<button class="btn btn-sm btn-info fa fa-eraser" onclick="launchChangeParentPassword(' + parentId + ')" title="Change Password"></button>\
							<button class="btn btn-sm btn-danger fa fa-trash-o" onclick="launchDeleteParent(' + parentId + ')" title="Delete"></button>\
						</td>\
					</tr>';
                }

                $("#parents").html(html);

                $("#parentTable").DataTable({
                    destroy: true,
                    searching: true,
                    responsive: true
                });
            } else {
                console.log(message);
            }
        }
    });
}