<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<fieldset>
    <c:if test="${command.crudResponse.moreQueryResults.successMsg}">
       <div class="alert alert-success" id="deleteSuccessMsg">
         <button data-dismiss="alert" class="close" type="button">×</button>
         You have successfully deleted code value <strong>(${command.crudResponse.moreQueryResults.deletedAccount.accountCode})${command.crudResponse.moreQueryResults.deletedAccount.accountDesc}</strong>       
       </div>
    </c:if>
	<legend class="section">Account Management</legend>
	<form:form method="POST" commandName="command" action="searchAccount.action" class="clearfix" id="accountForm">
		<div class="clearfix">
			<table class="ftable">
			    <tr>
                    <!-- Account code field -->
					<td style="width: 200px;">
						<div class="pull-left inline-control">
							<label class="control-label" for="accountCode"><spring:message code="fbs.common.account.ui.label.account.search.code" /></label>
						</div>
					</td>
					<td style="width: 50px;">
						
					</td>
					<td style="width:250px;">
						<div class="controls">
							<form:input path="accountCode" class="input-large" maxlength="100"/>					
						</div>
			        </td>
			        <!-- Account code field -->
			        
			        <!-- Account desc field -->
			        <td style="width: 200px;">
						<div class="pull-left inline-control">
							<label class="control-label" for="accountDesc"><spring:message code="fbs.common.account.ui.label.account.search.desc" /></label>
						</div>
					</td>					
					<td style="width: 50px;">
						<div class="pull-left inline-control">
							<label class="control-label" for="accountCode"><spring:message code="fbs.common.ui.label.search.contains" /></label>
						</div>
					</td>
					<td style="width:250px;">
						<div class="controls">
							<form:input path="accountDesc" class="input-large" maxlength="100"/>					
						</div>
			        </td>
					<!-- Account desc field -->
				  </tr>
				  
				  <tr>
                    <!-- req form field -->
					<td style="width: 200px;">
						<div class="pull-left inline-control">
							<label class="control-label" for="requisitionForm"><spring:message code="fbs.common.account.ui.label.account.search.need.requisition.form" /></label>
						</div>
					</td>
					<td style="width: 50px;">
						
					</td>
					<td style="width:250px;">
						<div class="controls">
							<form:select path="requisitionForm" class="input-large">
							    <form:option value="" label="Please Select"/>
							    <form:option value="true" label="Yes"/>
							    <form:option value="false" label="No"/>
							</form:select>
						</div>
			        </td>
			        <!-- req form field -->
			        
			        <!-- spend period field -->
			       <td style="width: 200px;">
						<div class="pull-left inline-control">
							<label class="control-label" for="spendPeriod"><spring:message code="fbs.common.account.ui.label.account.search.spend.period" /></label>
						</div>
					</td>
					<td style="width: 50px;">
						
					</td>
					<td style="width:250px;">
						<div class="controls">
							<form:input path="spendPeriod" class="input-large" maxlength="100"/>			
							<form:errors path="spendPeriod"	cssClass="mandatory" element="div"/>	
						</div>
			        </td>
					<!-- spend period field -->
				  </tr>
				  
				  <tr>
					  <td style="width: 200px;">
							<div class="pull-left inline-control">
								<label class="control-label" for="financialYear"><spring:message code="fbs.common.account.ui.label.account.search.fy" /></label>
							</div>
						</td>
						<td style="width: 50px;">
							
						</td>
						<td style="width:250px;">
							<div class="controls">
								<form:select path="financialYear" class="input-large">
								    <form:options items="${financialYears}"/>								   
								</form:select>		
							</div>
				        </td>
				  </tr>
				  
				  <tr>
				     <td colspan="6">
				        <button id="btnSearch" name="btnSearch" class="bt bt-pane b1" type="submit">Search</button>
				     </td>
				  </tr>
			</table>
		</div>
	</form:form>
	
	 <netui:grid queryFormName="command" action="searchAccount.action">
        <netui:gridPaging tableId="accountsTable"/>
        <table id="accountsTable" class="table ftable table-bordered table-hover table-condensed">
           <thead>
              <tr>
                 <th><spring:message code="fbs.common.ui.label.pagination.sn"/></th>
                 <th class="nowrap"><netui:gridSorting displayName="fbs.common.account.ui.label.account.code" name="accountCode"/></th>
                 <th class="nowrap"><netui:gridSorting displayName="fbs.common.account.ui.label.account.desc" name="accountDesc"/></th>                     
                 <th class="nowrap"><netui:gridSorting displayName="fbs.common.account.ui.label.account.need.requisition.form" name="requisitionForm"/></th>   <!-- name is property of Account.java -->
                 <th class="nowrap"><netui:gridSorting displayName="fbs.common.account.ui.label.account.spend.period.short" name="spendPeriod"/></th>
                 <th class="nowrap"><netui:gridSorting displayName="fbs.common.account.ui.label.account.fy" name="financialYear"/></th>   
                 <th class="nowrap"><netui:gridSorting displayName="fbs.common.ui.label.create.on" name="createon"/></th>
                 <th class="nowrap"><spring:message code="fbs.common.ui.label.delete"/></th>                    
              </tr>
           </thead>
           <tbody>
              <netui:gridRows>
                 <tr>
                    <td><netui:gridCurrentRowNum/></td>
                    <td><a href='showAccountDetails.action?id=<netui:gridRowElement name="id"/>'><netui:gridRowElement name="accountCode"/></a></td>
                    <td><netui:gridRowElement name="accountDesc"/></td>                       
                    <td><netui:gridRowElement name="requisitionForm"/></td> 
                    <td><netui:gridRowElement name="spendPeriod"/></td>
                    <td><netui:gridRowElement name="financialYear"/></td>    
                    <td><netui:gridRowElement name="createon" format="dd MMM yyyy"/></td>    
                    <td>
                      <button type="button" class="btn-icon btn-danger" title="Delete" name="deleteAccount" account-id="<netui:gridRowElement name="id"/>">
						  <i class="icon-remove icon-white"></i>
					  </button>		
                    </td>               
                 </tr>
              </netui:gridRows>
           </tbody>
        </table>
     </netui:grid>

</fieldset>

<div id="deleteAccountDialog" style="width: 630px; height: 180px;" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="Delete Account" aria-hidden="true">
	<div class="modal-header">
		<a class="close" data-dismiss="modal">×</a>
		<h3 style="width:330px;font-family:Arial;font-size:16px;font-weight:bold;font-style:normal;text-decoration:none;color:#EB9B07;">Delete Account</h3>
	</div>
	<div class="modal-body" style="max-height:800px;width:600px;height:60px;">	
		Are you sure you want to delete account: <label id="deleteAcctName" class="bold" style="display:inline"></label>
	</div>
	<div class="modal-footer" style="width:600px;height:30px;">
		<a class="bt bt-pane b1" id="deleteAccountDialogDeleteBtn">Delete</a>
		<a class="bt" id="deleteAccountDialogCloseBtn">Close</a>
	</div>
</div> 

<script type="text/javascript">
$(document).ready(function(){
	$("[name='deleteAccount']").click(function(){
		$('#deleteAccountDialog').attr('account-id',$(this).attr('account-id'));		
		$('#deleteAcctName').text('('+$(this).closest('tr').find('td:nth-child(2)').text()+')'+$(this).closest('tr').find('td:nth-child(3)').text());
		$('#deleteAccountDialog').modal({
	        keyboard: false ,
	    	backdrop: true,	    	
	    }) ;
	});
	
	$("#deleteAccountDialogCloseBtn").click(function(){
		$('#deleteAccountDialog').modal('hide');
	});
	
	$("#deleteAccountDialogDeleteBtn").click(function(){
		$("#accountForm").attr('action','deleteAccount.action?id='+$('#deleteAccountDialog').attr('account-id'));
		$("#accountForm").submit();
	});
});


</script>