<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Demo</title>
    </head>
    <body>
    	<h3>Simple initalization with default settings</h3>
    	<p id="demo"></p>
        <hr>
        <canvas></canvas>
        <hr>
        <ul></ul>
        <script type="text/javascript" src="js/QR/qrcodelib.js"></script>
        <script type="text/javascript" src="js/QR/webcodecamjs.js"></script>
        <script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script type="text/javascript" src="js/branchmanager/student.js"></script>
        <script type="text/javascript">
        scanning();
//         	var txt = "innerText" in HTMLElement.prototype ? "innerText" : "textContent";
//         	var studentName ="";
//             var arg = {
//                 resultFunction: function(resText, lastImageSrc) {
//                 	var aChild = document.createElement('li');
//                 	aChild[txt] = resText;
//                 	var qrStudentId = resText;
                	
//                 	var studentName = getStudentById(qrStudentId);
//                 	console.log(studentName);
//                 	//document.getElementById("demo").innerHTML = studentName;
//                     //document.querySelector('body').appendChild(aChild);
                    
//                 }
//             };
//             new WebCodeCamJS("canvas").init(arg).play();
        </script>
    </body>
</html>