<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="conextPath" value="${pageContext.request.contextPath}"/>

<fieldset>
		
<form:form method="POST" commandName="command" action="savePlanBudget.action" class="clearfix form-horizontal" id="planBudgetForm">
	<legend class="section">Plan Budget For <c:out value="${command.budgetForFY}"/></legend>
	   <div class="clearfix">
	       <div class="control-group inline-control">
	          <label class="control-label"><spring:message code="fbs.common.budgeting.ui.label.budget.planning.period" /></label>
	          <joda:format value="${command.budgetStartDate}" pattern="dd MMM yyyy"/> to 
	          <joda:format value="${command.budgetCutOffDate}" pattern="dd MMM yyyy"/>
	      </div>
	   
	   </div>
	</form:form>
	
</fieldset>