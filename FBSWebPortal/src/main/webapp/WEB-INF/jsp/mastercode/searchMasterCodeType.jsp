<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<fieldset>
	<legend class="section">Code Key Search</legend>
	<form:form method="POST" commandName="command" action="searchCategoryType.action" class="clearfix form-horizontal">
	   <div class="clearfix">
	   
	      <div class="control-group">
	          <label class="control-label" for="remarks"><spring:message code="frontier.common.codemaintenance.ui.label.remarks" /></label>
			  <div class="controls">
				  <form:input id="remarks" path="remarks" class="input-large"/>
			  </div>
	      </div>
	      
	      <div class="control-group">
			  <label class="control-label span1" for="remarks"><spring:message code="frontier.common.codemaintenance.ui.label.code.key" /></label>
			  <div class="controls">
				  <form:input id="remarksCode" path="remarksCode" class="input-large" />
			  </div>
		  </div>
		  
		  <div class="control-group ">
			  <label class="control-label" for="effectiveDate"><spring:message code="frontier.common.codemaintenance.ui.label.effective.date" /></label>
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
		  <div class="control-group">
			  <label class="control-label" for="expiryDate"><spring:message code="frontier.common.codemaintenance.ui.label.expiry.date" /></label>
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
				<span class="text-inline"><spring:message code="frontier.common.codemaintenance.ui.search.inactive.records" /></span>
			  </div>
		  </div>
		  
	   </div>
	   
	   <div class="form-actions">
		 <button id="btnSearch" name="btnSearch" class="bt bt1" type="submit">Search</button>
		 <button id="btnReset" name="btnReset" class="bt bt1" type="reset">Reset</button>
		 <button id="btnAddCodeType" name="btnAddCodeType" class="bt bt1" onClick="javascript:location.href='showAddCodeKey.action'" type="button">Add Code Key</button>
		 <button id="btnAddCodeValue" name="btnAddCodeValue" class="bt bt1" onClick="javascript:location.href='showAddCodeValue.action'" type="button">Add Code Value</button>
		 <button id="btnFileUpload" name="btnFileUpload" class="bt bt1" onClick="javascript:location.href='showMasterCodeFileUpload.action'" type="button">Upload file</button>
	   </div>
	</form:form>

	<netui:grid queryFormName="command" action="searchCategoryType.action">
		<netui:gridPaging tableId="tblSearchCategoryType" />
		<table id="tblSearchCategoryType" class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><spring:message code="frontier.common.ui.label.pagination.sn" /></th>
					<th class="nowrap"><netui:gridSorting displayName="frontier.common.ui.label.agency" name="agency.name" /></th>
					<th class="nowrap"><netui:gridSorting displayName="frontier.common.codemaintenance.ui.label.code.key" name="code" /></th>
					<th class="nowrap"><netui:gridSorting displayName="frontier.common.codemaintenance.ui.label.remarks" name="name" /></th>
					<th class="nowrap"><netui:gridSorting displayName="frontier.common.codemaintenance.ui.label.effective.date" name="effectiveDate" /></th>
					<th class="nowrap">
					    <c:choose>
							<c:when test="${command.searchInactiveMasterCodes}">
								<spring:message code="frontier.common.codemaintenance.ui.label.category.inactive.size" />
							</c:when>
							<c:otherwise>
								<spring:message code="frontier.common.codemaintenance.ui.label.category.active.size" />
							</c:otherwise>
						</c:choose>
					</th>
					<th class="nowrap export-ignore"><spring:message code="frontier.common.ui.label.action" /></th>
				</tr>
			</thead>
			<tbody>
				<netui:gridRows id="masterTyperow">
					<tr>
						<td><netui:gridCurrentRowNum /></td>
						<td class="nowrap"><netui:gridRowElement name="agency.name" /></td>
						<td class="nowrap"><netui:gridRowElement name="code" /></td>
						<td><netui:gridRowElement name="name" /></td>
						<td><netui:gridRowElement name="effectiveDate" /></td>
						<td><netui:gridRowElement name="activeMasterCodesSize" /></td>
						<td class="nowrap export-ignore"><netui:updateTag name="id" keyName="id" uri="listCodeValuesDetails.action?searchInactiveMasterCodes=${command.searchInactiveMasterCodes}" />
							<c:if test="${masterTyperow.deletable}">
								<netui:deleteTag name="id" keyName="id" uri="showDeleteCodeKey.action?" />
							</c:if>
						</td>
					</tr>
				</netui:gridRows>
			</tbody>
		</table>
	</netui:grid>

</fieldset>









