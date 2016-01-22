function logout() {
	localStorage.clear();
	localStorage.removeItem('branchManagerId');
	localStorage.removeItem('branchId');
	localStorage.removeItem('teacherId');
	localStorage.removeItem('courses');
	window.location.replace('landingPage.jsp');
}
