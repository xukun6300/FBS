<%@ page isErrorPage="true" import="java.io.*"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html> 
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Error</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <c:import url="include.jsp"></c:import>
</head>
<body>

	<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
	<c:set var="currentDate" value="<%=new java.util.Date()%>"/>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="block-center span12">
				<div class="alert alert-error">
					<span class="label label-important"> 
						<div>
							An error has occurred at <c:out value="${requestScope.moduleWebContext}${requestScope.methodName}"/> on 
							<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${currentDate}" />.
							<div/> 
							Please take a screen shot of this message and contact the system administrator.
						</div>
					</span>
				</div>
			</div>
		</div>
	</div>

</body>
</html>