<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<fieldset>
	<legend class="section">Account Management</legend>
	<h2 class="expand"></h2>
	<form:form method="POST" commandName="command" action="searchAccount.action" class="clearfix">
		<form:errors path="*" cssClass="alert alert-error" element="div" />
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
								    <%-- <form:options items="${financialYears}"/> --%>
								    <form:option value="2016" label="Please Select"/>
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
                  </tr>
               </thead>
               <tbody>
                  <netui:gridRows>
                     <tr>
                        <td><netui:gridCurrentRowNum/></td>
                        <td><netui:gridRowElement name="accountCode"/></td>
                        <td><netui:gridRowElement name="accountDesc"/></td>                       
                        <td><netui:gridRowElement name="requisitionForm"/></td> 
                        <td><netui:gridRowElement name="spendPeriod"/></td>
                        <td><netui:gridRowElement name="financialYear"/></td>                      
                     </tr>
                  </netui:gridRows>
               </tbody>
            </table>
         </netui:grid>
</fieldset>
