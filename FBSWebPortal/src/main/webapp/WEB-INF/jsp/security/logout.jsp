<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css"> -->


<%-- <link rel="stylesheet" href="<spring:url value="/static/external/jqueryui/css/ui-lightness/jquery-ui-1.10.3.css" />" />
<link rel="stylesheet" href="<spring:url value="/static/external/jqueryplugins/datetimepicker/css/jquery-ui-timepicker-addon.css" />" />
<link rel="stylesheet" href="<spring:url value="/static/external/bootstrap/css/bootstrap.css"/>"/>
<link rel="stylesheet" href="<spring:url value="/static/external/bootstrap/css/bootstrap-responsive.css"/>"/>
<link rel="stylesheet" href="<spring:url value="/static/internal/css/style.css"/>"/> 
<link rel="stylesheet" href="<spring:url value="/static/internal/css/basic.css"/>"/> --%>
<style type="text/css">
table.gridtable {
	font-family: arila,sans-serif;
	color:#333333;
	border-width: 1px;
	border-color: #DEDEDE;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #DEDEDE;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #DEDEDE;
	background-color: #ffffff;
}
</style>


<div align="center">
	<table cellpadding="0" cellsapcing="0" width="100%" border=0>
		<tr>
			<div class="success">Logged out successfully.</div>
		</tr>
	</table>
	<a href="<spring:url value="/authentication/showLogin.action" htmlEscape="true"/>"><h3>Go To Login</h3></a>
</div>
