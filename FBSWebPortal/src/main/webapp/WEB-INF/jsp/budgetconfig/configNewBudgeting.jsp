<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="conextPath" value="${pageContext.request.contextPath}"/>

<style>
.form-horizontal .control-label {
    width: 260px;
}

.form-horizontal .controls {
    margin-left: 265px;
}

/*to hide date picker left and right navi arrow*/
.ui-datepicker-prev {
    display:none;
}

.ui-datepicker-next {
    display:none;
}
</style>

<fieldset>
	<legend class="section">Configure New Budgeting For Next FY</legend>
	
	 <netui:grid queryFormName="command" action="showConfigNewBudgeting.action">
       <table class="table ftable table-bordered table-hover table-condensed">
         <thead>
           <tr>
              <th><spring:message code="fbs.common.budgetconfig.ui.label.fy"/></th>
              <th><spring:message code="fbs.common.budgetconfig.ui.label.budgeting.start.date"/></th>
              <th><spring:message code="fbs.common.budgetconfig.ui.label.budgeting.end.date"/></th>
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
	<br><br>
	<hr>
	
	<form:form method="POST" commandName="command" action="saveNewBudgeting.action" class="clearfix form-horizontal" id="newBudgetingForm">
	   <div class="clearfix">
	      <div class="control-group inline-control required">
	          <label class="control-label" for="budgetStartDate"><spring:message code="fbs.common.budgetconfig.ui.label.budgeting.start.date" /></label>
			  <div class="controls">
				 <netui:dateinput id="budgetStartDate" path="budgetStartDate" cssClass="input-medium" maxlength="10" placeholder="dd/MM/yyyy" />
				 <button id="budgetStartDateBtn" type="button" class="btn">
					 <i class=" icon-calendar"></i>
				 </button>
				 <form:errors path="budgetStartDate" cssClass="mandatory" element="div"/>				 
			  </div>
	      </div>
	    
	     <div class="control-group inline-control required">
	          <label class="control-label" for="budgetCutOffDate"><spring:message code="fbs.common.budgetconfig.ui.label.budgeting.cutoff.date" /></label>
			  <div class="controls">
				 <netui:dateinput id="budgetCutOffDate" path="budgetCutOffDate" cssClass="input-medium" maxlength="10" placeholder="dd/MM/yyyy" />
				 <button id="budgetCutOffDateBtn" type="button" class="btn">
					 <i class=" icon-calendar"></i>
				 </button>				 
				 <form:errors path="budgetCutOffDate" cssClass="mandatory" element="div"/>
			  </div>
	      </div>
	      
	      <div class="control-group inline-control">
	          <label class="control-label" for="endDate"><spring:message code="fbs.common.budgetconfig.ui.label.budget.for.fy" /></label>
			  <div class="controls control-radio-checkbox">			  
			     <form:radiobuttons path="budgetForFY" items="${budgetForFYs}"></form:radiobuttons>
			  </div>
	      </div>
	      
	       <div style="margin-left:285px">	
			 <button id="btnSave" name="btnSave" class="bt bt1" type="submit">Save</button>
			 <button id="btnReset" name="btnReset" class="bt bt1" type="reset">Reset</button>
		   </div>
	    </div>
	</form:form>
	
</fieldset>

<script type="text/javascript">
	$('#budgetStartDate').myDatePicker('#budgetStartDateBtn');
	//$('#budgetCutOffDate').myDatePicker('#budgetCutOffDateBtn');	
	 var today=new Date();
	 $('#budgetCutOffDate').fixedYearDatePicker('#budgetCutOffDateBtn', new Date(today.getFullYear()+2, 7, 1));	
	/* $(document).ready(function () {
	    var today=new Date();
	    $("#budgetCutOffDate").datepicker({
	        dateFormat: 'dd-mm-yy',
	        changeMonth: true,
	        changeYear: false
	    }).datepicker("setDate", new Date(today.getFullYear()+2, today.getMonth(), today.getDate())); }); */
</script>











