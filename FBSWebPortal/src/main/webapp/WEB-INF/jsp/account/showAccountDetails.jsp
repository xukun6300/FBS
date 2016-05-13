<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="<spring:url value="/static/internal/js/account/account.js" />"></script>
<style type="text/css">
/* .edit-mode{
   display:none !important;
} */

label.non-edit-mode {
   margin-left:20px;
}

td label.non-edit-mode {
   margin-left:0px;
}
</style>
<fieldset>
	<legend class="section">Account Details</legend>
	<form:form method="POST" commandName="command" action="updateAccount.action" class="clearfix form-horizontal">
		<div class="clearfix">
		<form:errors path="acctStructureJson" cssClass="mandatory" element="div" style="margin-left:25px; margin-bottom:20px"/>
	    <br>
	    <form:input path="acctStructureJson" type="hidden"/>
		
		<div class="control-group">
            <label class="control-label bold" for="accountCode"><spring:message code="fbs.common.account.ui.label.account.code"/></label>
            <div class="controls">
               <label class="non-edit-mode">${command.accountCode}</label>               
               <form:input id="accountCode" path="accountCode" class="input-xlarge edit-mode" maxlength="100" disabled="true"/>
               <%-- <form:errors path="accountCode" cssClass="mandatory" element="div"/> --%> 
            </div>
         </div>
         
	     <div class="control-group">
            <label class="control-label bold" for="accountDesc"><spring:message code="fbs.common.account.ui.label.account.desc"/></label>
            <div class="controls">
               <label class="non-edit-mode">${command.accountDesc}</label>       
               <form:input id="accountDesc" path="accountDesc" class="input-xlarge edit-mode" maxlength="100"/>
               <form:errors path="accountDesc" cssClass="mandatory" element="div"/> 
            </div>
         </div>
	    
	    <div class="control-group">
            <label class="control-label bold" for="needRequisitionForm"><spring:message code="fbs.common.account.ui.label.account.need.requisition.form"/></label>
            <div class="controls control-radio-checkbox">        
               
               <c:choose>
                  <c:when test="${command.needRequisitionForm}">
                     <label class="non-edit-mode">Yes</label> 
                  </c:when>
                  <c:otherwise>
                     <label class="non-edit-mode">No</label> 
                  </c:otherwise>
               </c:choose>
               <form:radiobutton id="needRequisitionFormYes" path="needRequisitionForm" class="edit-mode" value="true"/><label class="edit-mode" for="needRequisitionFormYes">Yes</label>
               <form:radiobutton id="needRequisitionFormNo" path="needRequisitionForm" class="edit-mode" value="false"/><label class="edit-mode" for="needRequisitionFormNo">No</label> 
               <form:errors path="needRequisitionForm" cssClass="mandatory" element="div"></form:errors>  
            </div>
         </div>

         <div class="control-group">
            <label class="control-label bold" for="acctSpendingPeriod"><spring:message code="fbs.common.account.ui.label.account.spend.period"/></label>
            <div class="controls">
               <label class="non-edit-mode">${command.acctSpendingPeriod}</label>
               <form:input id="acctSpendingPeriod" path="acctSpendingPeriod" class="input-xlarge edit-mode" maxlength="100" display="none"/>
               <form:errors path="acctSpendingPeriod" cssClass="mandatory" element="div"/> 
            </div>
         </div>
	    
	    <br>
		
		 <table id="accountTb" class="table ftable table-bordered table-hover table-condensed">
             <thead>
                <tr>
                   <th><spring:message code="fbs.common.account.ui.label.account.column.name"/></th>
                   <th><spring:message code="fbs.common.account.ui.label.account.column.size"/></th>                  
                   <th><spring:message code="fbs.common.account.ui.label.account.column.type"/></th>
                   <th class="edit-mode"><spring:message code="fbs.common.account.ui.label.account.column.order"/></th>
                   <th class="edit-mode"></th>
                </tr>
             </thead>             
             <tbody>
                 <c:forEach items="${command.acctStructures}" var="acctStructure">
                    <c:choose>
                       <c:when test="${acctStructure.defaultColumn=='Y'}">
                           <tr>
		                      <td>
		                          ${acctStructure.columnName}
		                      </td>
		                      <td>
		                          ${acctStructure.columnSize}
		                      </td>
		                      <td>		                        
		                        <c:choose>
		                            <c:when test="${acctStructure.columnType=='T'}">
		                               Text
		                            </c:when>
		                            <c:when test="${acctStructure.columnType=='N'}">
		                               Numeric
		                            </c:when>
		                            <c:otherwise>
		                               Date
		                            </c:otherwise>
	                             </c:choose>	                   
		                      </td>
		                      <td class="edit-mode">
					              <button type="button" class="btn btn-default btn-sm arrow-up">
						            <span class="icon-arrow-up"></span>
						          </button>	    
						          <button type="button" class="btn btn-default btn-sm arrow-down">
						            <span class="icon-arrow-down"></span>
						          </button>	           
				              </td>
				              <td class="edit-mode">
				                   <button class="btn btn-danger deleteRow" disabled><i class="icon-remove icon-white"></i></button>
				              </td>
		                    </tr>
                       
                       </c:when>
                       <c:otherwise>
                           <tr>
		                      <td>
		                          <label class="non-edit-mode">${acctStructure.columnName}</label>
		                          <input type="text" class="input edit-mode" maxlength="100" value="${acctStructure.columnName}"/>
		                      </td>
		                      <td>
		                          <label class="non-edit-mode">${acctStructure.columnSize}</label>
		                          <input type="text" class="input edit-mode" maxlength="100" value="${acctStructure.columnSize}"/>
		                      </td>
		                      <td>
		                          <label class="non-edit-mode">
			                         <c:choose>
			                            <c:when test="${acctStructure.columnType=='T'}">
			                               Text
			                            </c:when>
			                            <c:when test="${acctStructure.columnType=='N'}">
			                               Numeric
			                            </c:when>
			                            <c:otherwise>
			                               Date
			                            </c:otherwise>
		                             </c:choose>
		                         </label>
		                         
		                         <select class="input-small edit-mode">
			                         <option value="T" <c:if test="${acctStructure.columnType=='T'}">selected="selected"</c:if>>Text</option>
			                         <option value="N" <c:if test="${acctStructure.columnType=='N'}">selected="selected"</c:if>>Numeric</option>
			                         <option value="D" <c:if test="${acctStructure.columnType=='D'}">selected="selected"</c:if>>Date</option>
		                         </select>
		                         
		                          
		                      </td>
		                      <td class="edit-mode">
					              <button type="button" class="btn btn-default btn-sm arrow-up">
						            <span class="icon-arrow-up"></span>
						          </button>	    
						          <button type="button" class="btn btn-default btn-sm arrow-down">
						            <span class="icon-arrow-down"></span>
						          </button>	           
				              </td>
				              <td class="edit-mode">
				                   <button class="btn btn-danger deleteRow"><i class="icon-remove icon-white"></i></button>
				              </td>
		                    </tr>
                          
                       </c:otherwise>
                    </c:choose>
                 
                    
                 </c:forEach>
             </tbody>
          </table>
		
			<div style="margin:2%">
				<button type="button" class="bt non-edit-mode" id="editBtn">Edit</button>
				<button type="button" class="bt edit-mode" id="addNewRow"><i class="icon-plus icon-white"></i> Add a New Row</button>
				<button type="submit" class="bt edit-mode" id="saveBtn">Save</button>	
				<button type="button" class="bt edit-mode" id="cancelBtn">Cancel</button>		
			</div>
		</div>
	</form:form>
</fieldset>

