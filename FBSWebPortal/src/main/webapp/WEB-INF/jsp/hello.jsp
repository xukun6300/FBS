<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
</style>

<script>
var jsBaseURL = '${pageContext.request.contextPath}';
$(document).ready(function() {
		$('#publishDateFrom').myDatePicker('#publishDateFromBtn');
});

</script>
</head>
<body>

<form:form method="POST" commandName="command" action="add.action">
<form:errors path="*" cssClass="alert alert-error" element="div" />
		<netui:dateinput id="publishDateFrom" path="publishDateFrom" maxlength="10" cssClass="input-medium" readonly="true" placeholder="DD/MM/YYYY" />
		<button id="publishDateFromBtn" type="button" class="btn">
			<i class="icon-calendar"></i>
		</button>
  name:<form:input path="name"/><br>
  password:<form:input path="password"/>
  <button type="submit" id="btnSubmit">submit</button>
</form:form> 


	<%-- 	<netui:grid queryFormName="command" action="hello.action">
			<netui:gridPaging tableId="tblIntraAnnouncement"/>
					<table id="tblIntraAnnouncement"
						class="table ftable table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th class="nowrap" >No</th>
								<th class="nowrap" style="cursor: pointer;"><netui:gridSorting displayName="frontier.common.announcement.ui.label.title" name="id" /></th>
								<th class="nowrap"><netui:gridSorting displayName="frontier.common.announcement.ui.label.title" name="name" /></th>
							    <th class="nowrap"><netui:gridSorting displayName="frontier.common.announcement.ui.label.title" name="password"/></th>			
							    <th class="nowrap" >Delete</th>
							</tr>
						</thead>
						<tbody>
							<netui:gridRows>
								<tr>
				                    <td><netui:gridCurrentRowNum /></td>
									<td ><netui:gridRowElement name="id" /></td>
									<td class="nowrap"><netui:gridRowElement name="name"/> </td>	
									<td class="nowrap"><netui:gridRowElement name="password"/> </td>		
									<td><netui:deleteTag  name="id" keyName="id" uri="hello.action?"/></td>
								</tr>
							</netui:gridRows>
						</tbody>
					</table>
				</netui:grid> --%>
</body>
</html>