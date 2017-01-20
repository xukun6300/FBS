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
    <c:if test="${command.crudResponse.moreQueryResults.successMsg}">
       <div class="alert alert-success alert-custom" id="saveSuccessMsg">
         <button data-dismiss="alert" class="close" type="button">×</button>
         New Budgeting for Financial Year <strong>${command.crudResponse.moreQueryResults.financialYear}</strong> Saved Successfully      
       </div>
    </c:if>
    <c:if test="${command.crudResponse.moreQueryResults.errorMsg}">
       <div class="alert alert-danger alert-custom" id="saveErrorMsg">
         <button data-dismiss="alert" class="close" type="button">×</button>
         System Error Happens When Save New Budgeting Configuration
       </div>
    </c:if>
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
	          <label class="control-label" for="budgetStartDate"><spring:message code="fbs.common.budgetconfig.ui.label.budgeting.date.start" /></label>
			  <div class="controls">
				 <netui:dateinput id="budgetStartDate" path="budgetStartDate" cssClass="input-medium" maxlength="10" placeholder="dd/MM/yyyy" />
				 <button id="budgetStartDateBtn" type="button" class="btn">
					 <i class=" icon-calendar"></i>
				 </button>
				 <form:errors path="budgetStartDate" cssClass="mandatory" element="div"/>				 
			  </div>
	      </div>
	    
	     <div class="control-group inline-control required">
	          <label class="control-label" for="budgetCutOffDate"><spring:message code="fbs.common.budgetconfig.ui.label.budgeting.date.cutoff" /></label>
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
			  <form:errors path="budgetForFY" cssClass="mandatory" element="div"/>
	      </div>
	      
	       <div style="margin-left:285px">	
			 <button id="btnSave" name="btnSave" class="bt bt1" type="submit">Save</button>
			 <button id="btnReset" name="btnReset" class="bt bt1" type="reset">Reset</button>
		   </div>
	    </div>
	</form:form>
	
</fieldset>

<script type="text/javascript">

	function toggleBudgetDateByFY(budgetStartDt){
		 var currentFy=new Date().getFullYear();
		 var nextFy = currentFy+1;
	     	     
		 if($("input[name=budgetForFY][value="+currentFy+"]").is(":checked")){
			 $('#budgetStartDate').budgetConfigDatePicker('#budgetStartDateBtn', new Date(currentFy, 7, 1), new Date());
			 $('#budgetCutOffDate').budgetConfigDatePicker('#budgetCutOffDateBtn', new Date(currentFy, 7, 1),budgetStartDt);	
		 }else if($("input[name=budgetForFY][value="+nextFy+"]").is(":checked")){
			 $('#budgetStartDate').budgetConfigDatePicker('#budgetStartDateBtn', new Date(nextFy, 7, 1), new Date());
			 $('#budgetCutOffDate').budgetConfigDatePicker('#budgetCutOffDateBtn', new Date(nextFy, 7, 1), budgetStartDt);	
		 } 
	 } 
	
	 $(document).ready(function(){
		 toggleBudgetDateByFY(new Date());
		 
		 $("input[name=budgetForFY]").change(function(){
			 $('#budgetStartDate').val("");
			 $('#budgetCutOffDate').val("");
			 toggleBudgetDateByFY(new Date());
		 });		
		 
		 $("#btnReset").click(function(event){
			 event.preventDefault();
		     $(this).closest('form').get(0).reset();
			 toggleBudgetDateByFY(new Date());
		 });
		 
		 
		 $("#budgetStartDate").change(function(){
			 var budgetStartDtStr = $('#budgetStartDate').val();	     
		     var startDate = budgetStartDtStr.split("/"); 
		     var budgetStartDt = new Date(startDate[2],startDate[1]-1,startDate[0]);
		     toggleBudgetDateByFY(budgetStartDt);
		     
		     //clear cut-off date if user change start date to some value greater that cut-off date
		     if($('#budgetCutOffDate').val()){
		    	 var budgetCutOffDtStr = $('#budgetCutOffDate').val();	     
			     var cutOffDate = budgetCutOffDtStr.split("/"); 
			     var budgetCutOffDt = new Date(cutOffDate[2],cutOffDate[1]-1,cutOffDate[0]);
			     
			     if(budgetStartDt>budgetCutOffDt){
			    	 $('#budgetCutOffDate').val("");
			     }
		     }
		 });
		 
	 }); 
	 
</script>











