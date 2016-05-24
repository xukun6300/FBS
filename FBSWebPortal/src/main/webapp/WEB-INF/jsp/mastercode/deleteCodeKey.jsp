<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<fieldset>
	<legend class="section">Delete Code Key</legend>
	<form:form method="POST" commandName="command" action="deleteCodeKey.action" class="clearfix form-horizontal">
		<div class="clearfix">
			<div class="control-group">
				<label class="control-label bold"><spring:message code="fbs.common.codemaintenance.ui.label.code.key" /></label>
				<div class="controls">
					<label>${command.codeKey}</label>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label bold"><spring:message code="fbs.common.codemaintenance.ui.label.code.key" /></label>
				<div class="controls">
					<label>${command.remarks}</label>
				</div>
			</div>
			
			<div style="margin-left:25px">
			    <button id="btnDelete" name="btnDelete" class="bt" type="submit" >Delete</button>
				<button id="btnBack" name="btnBack" class="bt" onClick="javascript:location.href='searchCategoryType.action'" type="button" >Back</button>
			</div>
		</div>
	</form:form>
</fieldset>