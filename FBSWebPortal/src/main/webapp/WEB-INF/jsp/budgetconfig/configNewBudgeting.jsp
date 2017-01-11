<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="conextPath" value="${pageContext.request.contextPath}"/>

<fieldset>
	<legend class="section">Configure New Budgeting For Next FY</legend>
	
	 <netui:grid queryFormName="command" action="showConfigNewBudgeting.action">
       <table class="table ftable table-bordered table-hover table-condensed">
         <thead>
           <tr>
              <th><spring:message code="fbs.common.account.ui.label.budget.config.fy"/></th>
              <th><spring:message code="fbs.common.account.ui.label.budget.config.budgeting.start.date"/></th>
              <th><spring:message code="fbs.common.account.ui.label.budget.config.budgeting.end.date"/></th>
           </tr>
         </thead>
         <tbody>
              <netui:gridRows>
                 <tr>
                    <td><netui:gridRowElement name="budgetConfigFY"/></td>
                    <td><netui:gridRowElement name="budgetingStartDt" format="dd MMM yyyy"/></td>
                    <td><netui:gridRowElement name="budgetingEndDt" format="dd MMM yyyy"/></td>
                 </tr>
              </netui:gridRows>
         </tbody>
       </table>
	 </netui:grid>
	
	
	<form:form method="POST" commandName="command" action="saveNewBudgeting.action" class="clearfix" id="newBudgetingForm">
	
	</form:form>
	
</fieldset>