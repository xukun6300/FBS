<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="conextPath" value="${pageContext.request.contextPath}"/>
<style type="text/css">
.acct-title{
    margin-left: 25px;
    width: 30%;
    text-align:left;
}

.icon-right{
    float:right;
}

.acct-table{
    margin:0%;
    width:100%;
}

.acct-table th{
    font-size:13px;
}

</style>
<script type="text/javascript" src="<spring:url value="/static/internal/js/budgeting/budgeting.js" />"></script>

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

	      <c:forEach items="${command.accounts}" var="account" varStatus="index">	        
	        <p>
				<button class="btn btn-primary acct-title" data-toggle="collapse" href="#${account.accountCode}" aria-expanded="false" 
				aria-controls="collapseExample">${account.accountDesc} <i class="icon-minus icon-white icon-right"></i></button>
			</p>
			<!-- in means open by default -->
			<div class="collapse in" id="${account.accountCode}">
				<div class="card card-block">
				  <div class="alert alert-success alert-dismissible" id="alert_save_success_${account.accountCode}" role="alert" style="display:none;"> 
				    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button> 
				    <div class="alert-messgae"></div>Line Item Saved Successfully.
				  </div>
				  <div class="alert alert-success alert-dismissible" id="alert_delete_success_${account.accountCode}" role="alert" style="display:none;"> 
				    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button> 
				    <div class="alert-messgae"></div>Line Item Deleted Successfully.
				  </div>
				  <div class="alert alert-danger alert-dismissible" id="alert_danger_${account.accountCode}" role="alert" style="display:none;"> 
				    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
				    <div class="alert-messgae"></div>
				  </div>
				   <table class="table ftable table-bordered table-hover table-condensed acct-table" account-id="${account.id}" id="acctTb_${account.accountCode}" index="${index.index}"
				   column-size="${fn:length(account.acctStructures)}">
					   <thead>
	                      <tr>
	                        <c:forEach items="${account.acctStructures}" var="acctColumn">
	                           <th width="${acctColumn.columnSize}" column-type="${acctColumn.columnType}">${acctColumn.columnName}</th>                            
	                        </c:forEach>
	                        <th width="100" column-name="forcast">Forecast</th>
	                        <th width="100" column-name="currApproved">Current FY Approved</th>
	                        <th width="100" column-name="currSpent">Current FY Spent</th>
	                        <th width="100" column-name="putupby">Put Up By</th> 
	                        <th width="100" column-name="approveStatus">Approval Status</th>  
	                        <th width="160" column-name="action">Action</th>                   
	                      </tr>
	                    </thead>
	                    <tbody>
	                        <c:forEach items="${account.lineItems}" var="lineitem">
	                          <tr lineitem-id="${lineitem.id}">
	                            <c:forEach items="${account.acctStructures}" varStatus="lineitemIndex"> 
	                               <c:set var="lineitemColumn" value="column${lineitemIndex.index+1}"/>
	                               <td>
	                               <label>${lineitem[lineitemColumn]}</label>
	                               <input type="text" style="display:none;">
	                               </td>                            
	                            </c:forEach> 
	                            <td></td>
		                        <td></td>
		                        <td></td>
		                        <td></td>
		                        <td></td>
		                        <td>		                          
		                          <button type="button" class="btn-icon btn-success save-lineitem" title="Save" style="display:none;"><i class="icon-check icon-white"></i></button>
		                          <button style="" type="button" class="btn-icon btn-inverse edit-lineitem" title="Update" > <i class="icon-edit icon-white"></i></button>&nbsp;
		                          <button style="" type="button" class="btn-icon btn-danger delete-lineitem" title="Delete" ><i class="icon-remove icon-white"></i></button>
		                        </td>
	                            </tr>
	                                                 
	                        </c:forEach>
	                        
	                    </tbody>
				   </table>
				   <div>
						<button type="button" class="btn btn-primary" id="addNewRow_${account.accountCode}"><i class="icon-plus icon-white"></i> Add Row</button>						
				   </div>
				</div>
			</div>
			<br>
			<br>
	      </c:forEach>

			

		</div>
	</form:form>
	
</fieldset>

<div id="deleteLineitemDialog" style="width: 630px; height: 180px;" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="Delete Lineitem" aria-hidden="true">
	<div class="modal-header">
		<a class="close" data-dismiss="modal">×</a>
		<h3 style="width:330px;font-family:Arial;font-size:16px;font-weight:bold;font-style:normal;text-decoration:none;color:#EB9B07;">Delete Lineitem</h3>
	</div>
	<div class="modal-body" style="max-height:800px;width:600px;height:60px;">	
		Are you sure you want to delete lineitem?
	</div>
	<div class="modal-footer" style="width:600px;height:30px;">
		<a class="bt bt-pane b1" id="deleteLineitemDialogDeleteBtn">Delete</a>
		<a class="bt" id="deleteLineitemDialogCloseBtn">Close</a>
	</div>
</div> 



