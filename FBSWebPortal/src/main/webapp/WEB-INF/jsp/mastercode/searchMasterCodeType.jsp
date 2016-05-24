<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function(){
	

	$("[name='deleteCodeKey']").click(function(){
		$('#deleteCodeKeyDialog').attr('code-key-id',$(this).attr('code-key-id'));
		
		$('#deleteCodeKeyDialog').modal({
	        keyboard: false ,
	    	backdrop: true,	    	
	    }) ;
	});
	
	$("#deleteCodeKeyDialogCloseBtn").click(function(){
		$('#deleteCodeKeyDialog').modal('hide');
	});
	
	$("#deleteCodeKeyDialogDeleteBtn").click(function(){
		$("#codeKeyForm").attr('action','deleteCodeKey.action?id='+$('#deleteCodeKeyDialog').attr('code-key-id'));
		$("#codeKeyForm").submit();
	});
});
</script>

<fieldset>

 <form:form method="POST" commandName="command" action="searchCategoryType.action" class="clearfix form-horizontal" id="codeKeyForm">
    <c:if test="${command.crudResponse.moreQueryResults.successMsg}">
       <div class="alert alert-success" id="deleteSuccessMsg">
         <button data-dismiss="alert" class="close" type="button">×</button>
         You have successfully deleted code key <strong>${command.crudResponse.moreQueryResults.deletedCodeKey}</strong>       
       </div>
    </c:if>
    
   
	<legend class="section">Code Key Search</legend>
	
	   <div class="clearfix">
	      <div class="control-group inline-control">
	          <label class="control-label" for="remarks"><spring:message code="fbs.common.codemaintenance.ui.label.search.remarks" /></label>
			  <div class="controls">
				  <form:input id="remarks" path="remarks" class="input-large"/>
			  </div>
	      </div>
	      
	      <div class="control-group inline-control">
			  <label class="control-label" for="remarksCode"><spring:message code="fbs.common.codemaintenance.ui.label.search.code.key" /></label>
			  <div class="controls">
				  <form:input id="remarksCode" path="remarksCode" class="input-large" />
			  </div>
		  </div>
		  
		  <div class="control-group inline-control">
			  <label class="control-label" for="effectiveDate"><spring:message code="fbs.common.codemaintenance.ui.label.search.effective.date" /></label>
			  <div class="controls">
				 <netui:dateinput id="effectiveDateFrom" path="effectiveDateFrom" cssClass="input-medium" maxlength="10" placeholder="dd/MM/yyyy" />
				 <button id="effectiveDateFromBtn" type="button" class="btn">
					 <i class=" icon-calendar"></i>
				 </button>
				 <span class="text-inline">To</</span>
				 <netui:dateinput id="effectiveDateTo" path="effectiveDateTo" cssClass="input-medium" maxlength="10" placeholder="dd/MM/yyyy" />
				 <button id="effectiveDateToBtn" type="button" class="btn">
					 <i class=" icon-calendar"></i>
				 </button>
			  </div>
		  </div>
		  <div class="control-group inline-control">
			  <label class="control-label" for="expiryDate"><spring:message code="fbs.common.codemaintenance.ui.label.search.expiry.date" /></label>
			  <div class="controls">
				  <netui:dateinput id="expiryDateFrom" path="expiryDateFrom" cssClass="input-medium" maxlength="10" placeholder="dd/MM/yyyy" />
				  <button id="expiryDateFromBtn" type="button" class="btn">
					  <i class=" icon-calendar"></i>
				  </button>
				  <span class="text-inline">To</</span>
				  <netui:dateinput id="expiryDateTo" path="expiryDateTo" cssClass="input-medium" maxlength="10" placeholder="dd/MM/yyyy" />
				  <button id="expiryDateToBtn" type="button" class="btn">
					  <i class=" icon-calendar"></i>
				  </button>
			  </div>
		  </div>
		  
		  <div class="control-group">
			  <div class="controls checkbox">
				<form:checkbox path="searchInactiveMasterCodes" />
				<span class="text-inline"><spring:message code="fbs.common.codemaintenance.ui.search.inactive.records" /></span>
			  </div>
		  </div>
		  
	   </div>
	   
	   <div style="margin-left:25px">	
		 <button id="btnSearch" name="btnSearch" class="bt bt1" type="submit">Search</button>
		 <button id="btnReset" name="btnReset" class="bt bt1" type="reset">Reset</button>
		 <button id="btnAddCodeType" name="btnAddCodeType" class="bt bt1" onClick="javascript:location.href='showAddCodeKey.action'" type="button">Add Code Key</button>
		 <button id="btnAddCodeValue" name="btnAddCodeValue" class="bt bt1" onClick="javascript:location.href='showAddCodeValue.action'" type="button">Add Code Value</button>
		 <!-- <button id="btnFileUpload" name="btnFileUpload" class="bt bt1" onClick="javascript:location.href='showMasterCodeFileUpload.action'" type="button">Upload file</button> -->
	   </div>
	</form:form>

	<netui:grid queryFormName="command" action="searchCategoryType.action">
		<netui:gridPaging tableId="tblSearchCategoryType" />
		<table id="tblSearchCategoryType" class="table ftable table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><spring:message code="fbs.common.ui.label.pagination.sn" /></th>					
					<th class="nowrap"><netui:gridSorting displayName="fbs.common.codemaintenance.ui.label.code.key" name="code" /></th>
					<th class="nowrap"><netui:gridSorting displayName="fbs.common.codemaintenance.ui.label.remarks" name="name" /></th>
					<th class="nowrap"><netui:gridSorting displayName="fbs.common.codemaintenance.ui.label.effective.date" name="effectiveDate" /></th>
					<th class="nowrap">
					    <c:choose>
							<c:when test="${command.searchInactiveMasterCodes}">
								<spring:message code="fbs.common.codemaintenance.ui.label.category.inactive.size" />
							</c:when>
							<c:otherwise>
								<spring:message code="fbs.common.codemaintenance.ui.label.category.active.size" />
							</c:otherwise>
						</c:choose>
					</th>
					<th class="nowrap export-ignore"><spring:message code="fbs.common.ui.label.action" /></th>
				</tr>
			</thead>
			<tbody>
				<netui:gridRows id="masterTyperow"><!-- single record -->
					<tr>
						<td><netui:gridCurrentRowNum /></td>						
						<td class="nowrap"><netui:gridRowElement name="code" /></td>
						<td><netui:gridRowElement name="name" /></td>
						<td><netui:gridRowElement name="effectiveDate" format="dd MMM yyyy"/></td>
						<td>
							<c:choose>
								<c:when test="${command.searchInactiveMasterCodes}">
									<netui:gridRowElement name="inactiveMasterCodesSize" />
								</c:when>
								<c:otherwise>
									<netui:gridRowElement name="activeMasterCodesSize" />
								</c:otherwise>
							</c:choose>
						</td>
						<td class="nowrap export-ignore">
						    <netui:updateTag name="id" keyName="id" uri="listCodeValuesDetails.action?searchInactiveMasterCodes=${command.searchInactiveMasterCodes}" />
							<c:if test="${masterTyperow.deletable}">								
								<button type="button" class="btn btn-danger" title="Delete" name="deleteCodeKey" code-key-id="${masterTyperow.id}">
									<i class="icon-remove icon-white"></i>
								</button>
							</c:if>
						</td>
					</tr>
				</netui:gridRows>
			</tbody>
		</table>
	</netui:grid>

</fieldset>

<div id="deleteCodeKeyDialog" style="width: 630px; height: 180px;" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="Delete Code Key" aria-hidden="true">
	<div class="modal-header">
		<a class="close" data-dismiss="modal">×</a>
		<h3 style="width:330px;font-family:Arial;font-size:16px;font-weight:bold;font-style:normal;text-decoration:none;color:#EB9B07;">Delete Code Key</h3>
	</div>
	<div class="modal-body" style="max-height:800px;width:600px;height:60px;">	
		Are you sure you want to delete this code key?
	</div>
	<div class="modal-footer" style="width:600px;height:30px;">
		<a class="bt bt-pane b1" id="deleteCodeKeyDialogDeleteBtn">Delete</a>
		<a class="bt" id="deleteCodeKeyDialogCloseBtn">Close</a>
	</div>
</div> 



<script type="text/javascript">
	$('#effectiveDateFrom').myDatePicker('#effectiveDateFromBtn');
	$('#effectiveDateTo').myDatePicker('#effectiveDateToBtn');	
	$('#expiryDateFrom').myDatePicker('#expiryDateFromBtn');
	$('#expiryDateTo').myDatePicker('#expiryDateToBtn');
</script>







