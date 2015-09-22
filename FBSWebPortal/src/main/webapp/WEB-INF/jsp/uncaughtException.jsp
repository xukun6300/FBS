<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Internal Server Error</title>
</head>
<body>
	<div class="alert alert-danger" role="alert">
		<h1>Internal Server Error</h1>
		<p>Sorry, there is a problem with the page you are trying to reach and it cannot be displayed.</p>
		<p>Go to <a href="<c:url value='/authentication/showLogin.action'/>">Home</a> page</p>
	</div>
</body>
</html>
