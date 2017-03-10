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
	        <label class="control-label control-label-long"><spring:message code="fbs.common.budgeting.ui.label.budget.planning.period" /></label></td>
	        <div>
			      <joda:format value="${command.budgetStartDate}" pattern="dd MMM yyyy"/>&nbsp;&nbsp;to&nbsp;&nbsp; 
		          <joda:format value="${command.budgetCutOffDate}" pattern="dd MMM yyyy"/>
			</div>
	      </div>

			<p>
				<a class="btn btn-primary" data-toggle="collapse"
					href="#collapseExample" aria-expanded="false"
					aria-controls="collapseExample"> Link with href </a>

			</p>
			<div class="collapse" id="collapseExample">
  <div class="card card-block">
    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
  </div>
</div>

		</div>
	</form:form>
	
</fieldset>