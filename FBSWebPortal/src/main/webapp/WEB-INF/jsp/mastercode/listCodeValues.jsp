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
	$("[name='deleteCodeValue']").click(function(){
		$('#deleteCodeValueDialog').attr('code-id',$(this).attr('code-id'));
		
		$('#deleteCodeValueDialog').modal({
	        keyboard: false ,
	    	backdrop: true,	    	
	    }) ;
	});
	
	$("#deleteCodeValueDialogCloseBtn").click(function(){
		$('#deleteCodeValueDialog').modal('hide');
	});
	
	$("#deleteCodeValueDialogDeleteBtn").click(function(){
		$("#codeValueForm").attr('action','deleteCodeValue.action?deleteCodeId='+$('#deleteCodeValueDialog').attr('code-id')+'&id='+$(this).attr('code-key-id'));
		$("#codeValueForm").submit();
	});
});


</script>

<fieldset>
    <c:if test="${command.crudResponse.moreQueryResults.successMsg}">
       <div class="alert alert-success" id="deleteSuccessMsg">
         <button data-dismiss="alert" class="close" type="button">×</button>
         You have successfully deleted code value <strong>${command.crudResponse.moreQueryResults.deletedCodeValue}</strong>       
       </div>
    </c:if>
	<legend class="section">Code Key Details</legend>
	<form:form class="clearfix form-horizontal" id="codeValueForm">
		<div class="clearfix">
		
			<div class="control-group">
	            <label class="control-label bold"><spring:message code="fbs.common.codemaintenance.ui.label.code.key"/></label>
	            <div class="controls">
	               <label>${command.codeKey}</label>               
	            </div>
	         </div>
	         
	         <div class="control-group">
	            <label class="control-label bold"><spring:message code="fbs.common.codemaintenance.ui.label.remarks"/></label>
	            <div class="controls">
	               <label>${command.remarks}</label>               
	            </div>
	         </div>
	         
	         <div style="margin-left:25px">
	            <c:if test="${!command.searchInactiveMasterCodes}">
			      <button id="btnAddCodeValue" name="btnAddCodeValue" class="bt" onClick="javascript:location.href='showAddCodeValue.action?codeKey=${command.codeKey}'" type="button" ><spring:message code="fbs.common.codemaintenance.ui.button.add.code.value"/></button>
			    </c:if>
			    <button id="btnBack" name="btnBack" class="bt" onClick="javascript:location.href='searchCategoryType.action'" type="button" ><spring:message code="fbs.common.ui.button.back"/></button>
			    <c:if test="${!command.searchInactiveMasterCodes}">
				  <button id="btnUpdateSequence" name="btnUpdateSequence" class="bt" onClick="javascript:location.href='showCodeValueSequence.action?id=${command.id}'" type="button" ><spring:message code="fbs.common.codemaintenance.ui.button.update.sequence"/></button>
				  <button id="btnUpdateCodeKey" name="btnUpdateCodeKey" class="bt" onClick="javascript:location.href='showUpdateCodeKey.action?id=${command.id}'" type="button" ><spring:message code="fbs.common.codemaintenance.ui.label.update.code.key"/></button>
				</c:if>
	         </div>
	         
	         <br>
	         
	         <netui:grid queryFormName="command" action="listCodeValuesDetails.action">
				<netui:gridPaging tableId="tblListCodeValuesDetails" />
				<table id="tblListCodeValuesDetails" class="table ftable table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th><spring:message code="fbs.common.ui.label.pagination.sn"/></th>
							<th class="nowrap"><netui:gridSorting displayName="fbs.common.codemaintenance.ui.label.code.value.description" name="description"/></th>
							<th class="nowrap"><netui:gridSorting displayName="fbs.common.codemaintenance.ui.label.code.value" name="codeValue"/></th>
							<th class="nowrap"><netui:gridSorting displayName="fbs.common.codemaintenance.ui.label.effective.date" name="effectiveDate"/></th>
                            <th class="nowrap"><netui:gridSorting displayName="fbs.common.codemaintenance.ui.label.expiry.date" name="expiryDate"/></th>
                            <th class="nowrap"><netui:gridSorting displayName="fbs.common.codemaintenance.ui.label.active.status" name="activeStatus"/></th>                           
							<th class="nowrap export-ignore" ><spring:message code="fbs.common.ui.label.action"/></th>							
						</tr>
					</thead>
					<tbody>
						<netui:gridRows id="codeValueRow">
						<tr>
							<td><netui:gridCurrentRowNum /></td>							
							<td><netui:gridRowElement name="description" htmlEscape="false"/></td>
							<td class="nowrap"><netui:gridRowElement  name="codeValue"/></td>
							<td><netui:gridRowElement  name="effectiveDate" format="dd MMM yyyy"/></td>
							<td><netui:gridRowElement  name="expiryDate" format="dd MMM yyyy"/></td> 
							<td><netui:gridRowElement  name="activeStatus"/></td>							
							<td class="nowrap export-ignore">		
							<c:if test="${codeValueRow.deletable}">						
								<netui:updateTag  name="id" keyName="id" uri="showUpdateCodeValue.action?searchInactiveMasterCodes=${command.searchInactiveMasterCodes}" />
								<%-- <netui:deleteTag  name="id" keyName="id" uri="showDeleteCodeValue.action?codeTypeId=${command.id}" /> --%>
								<button type="button" class="btn btn-danger" title="Delete" name="deleteCodeValue" code-id="${codeValueRow.id}" >
									<i class="icon-remove icon-white"></i>
								</button>		
							</c:if>						
							</td>							
						</tr>
						</netui:gridRows>
					</tbody>
				</table>
			</netui:grid> 
         
                  
		
		</div>
	</form:form>
	
	
</fieldset>

<div id="deleteCodeValueDialog" style="width: 630px; height: 180px;" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="Delete Code Value" aria-hidden="true">
	<div class="modal-header">
		<a class="close" data-dismiss="modal">×</a>
		<h3 style="width:330px;font-family:Arial;font-size:16px;font-weight:bold;font-style:normal;text-decoration:none;color:#EB9B07;">Delete Code Value</h3>
	</div>
	<div class="modal-body" style="max-height:800px;width:600px;height:60px;">	
		Are you sure you want to delete this code value?
	</div>
	<div class="modal-footer" style="width:600px;height:30px;">
		<a class="bt bt-pane b1" id="deleteCodeValueDialogDeleteBtn" code-key-id="${command.id}">Delete</a>
		<a class="bt" id="deleteCodeValueDialogCloseBtn">Close</a>
	</div>
</div> 