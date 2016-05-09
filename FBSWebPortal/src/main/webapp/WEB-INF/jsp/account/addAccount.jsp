<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="<spring:url value="/static/internal/js/account/account.js" />"></script>
<fieldset>
	<legend class="section">Configure New Account Template</legend>
    <form:form method="POST" id="accountForm" commandName="command" action="addNewAccount.action" class="clearfix form-horizontal">
	    <form:errors cssClass="alert alert-error" element="div"/>
	    <div class="clearfix"><br>
	    <div class="control-group required">
            <label class="control-label" for="accountCode"><spring:message code="fbs.common.account.ui.label.account.code"/></label>
            <div class="controls">
               <form:input id="accountCode" path="accountCode" class="input-xlarge" maxlength="100"/>
               <form:errors path="accountCode" cssClass="mandatory" element="div"/> 
            </div>
         </div>
         
	     <div class="control-group required">
            <label class="control-label" for="accountDesc"><spring:message code="fbs.common.account.ui.label.account.desc"/></label>
            <div class="controls">
               <form:input id="accountDesc" path="accountDesc" class="input-xlarge" maxlength="100"/>
               <form:errors path="accountDesc" cssClass="mandatory" element="div"/> 
            </div>
         </div>
	    
	    <div class="control-group required">
            <label class="control-label" for="accountDesc"><spring:message code="fbs.common.account.ui.label.account.need.requisition.form"/></label>
            <div class="controls control-radio-checkbox">        
               <form:radiobutton id="needRequisitionFormYes" path="needRequisitionForm" value="true"/><label for="needRequisitionFormYes">Yes</label>
               <form:radiobutton id="needRequisitionFormNo" path="needRequisitionForm" value="false"/><label for="needRequisitionFormNo">No</label> 
               <form:errors path="needRequisitionForm" cssClass="mandatory" element="div"></form:errors>  
            </div>
         </div>

         <div class="control-group required">
            <label class="control-label" for="acctSpendingPeriod"><spring:message code="fbs.common.account.ui.label.account.spend.period"/></label>
            <div class="controls">
               <form:input id="acctSpendingPeriod" path="acctSpendingPeriod" class="input-xlarge" maxlength="100"/>
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
                   <th><spring:message code="fbs.common.account.ui.label.account.column.order"/></th>
                   <th></th>
                </tr>
             </thead>             
             <tbody>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.item.desc"/></td>
	              <td>300</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.text"/></td>
	              <td>
		              <button type="button" class="btn btn-default btn-sm arrow-up">
			            <span class="icon-arrow-up"></span>
			          </button>	    
			          <button type="button" class="btn btn-default btn-sm arrow-down">
			            <span class="icon-arrow-down"></span>
			          </button>	           
	              </td>
	              <td>
	              <button class="btn btn-danger deleteRow"><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.workings"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.text"/></td>
	              <td>
	                 <button type="button" class="btn btn-default btn-sm arrow-up">
			            <span class="icon-arrow-up"></span>
			          </button>	    
			          <button type="button" class="btn btn-default btn-sm arrow-down">
			            <span class="icon-arrow-down"></span>
			          </button>
	              </td>
	              <td>
	              <button class="btn btn-danger deleteRow"><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.amount"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.numeric"/></td>
	              <td>
	                 <button type="button" class="btn btn-default btn-sm arrow-up">
			            <span class="icon-arrow-up"></span>
			          </button>	    
			          <button type="button" class="btn btn-default btn-sm arrow-down">
			            <span class="icon-arrow-down"></span>
			          </button>
	              </td>
	              <td>
	              <button class="btn btn-danger deleteRow"><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.q1"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.numeric"/></td>
	              <td>
	                 <button type="button" class="btn btn-default btn-sm arrow-up">
			            <span class="icon-arrow-up"></span>
			          </button>	    
			          <button type="button" class="btn btn-default btn-sm arrow-down">
			            <span class="icon-arrow-down"></span>
			          </button>
	              </td>
	              <td>
	              <button class="btn btn-danger deleteRow"><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.q2"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.numeric"/></td>
	              <td>
	                 <button type="button" class="btn btn-default btn-sm arrow-up">
			            <span class="icon-arrow-up"></span>
			          </button>	    
			          <button type="button" class="btn btn-default btn-sm arrow-down">
			            <span class="icon-arrow-down"></span>
			          </button>
	              </td>
	              <td>
	              <button class="btn btn-danger deleteRow"><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.q3"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.numeric"/></td>
	              <td>
	                 <button type="button" class="btn btn-default btn-sm arrow-up">
			            <span class="icon-arrow-up"></span>
			          </button>	    
			          <button type="button" class="btn btn-default btn-sm arrow-down">
			            <span class="icon-arrow-down"></span>
			          </button>
	              </td>
	              <td>
	              <button class="btn btn-danger deleteRow"><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.q4"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.numeric"/></td>
	              <td>
	                 <button type="button" class="btn btn-default btn-sm arrow-up">
			            <span class="icon-arrow-up"></span>
			          </button>	    
			          <button type="button" class="btn btn-default btn-sm arrow-down">
			            <span class="icon-arrow-down"></span>
			          </button>
	              </td>
	              <td>
	              <button class="btn btn-danger deleteRow"><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>

             </tbody>
        </table>
        
		<div style="margin:2%">
			<button type="button" class="bt" id="addNewRow"><i class="icon-plus icon-white"></i> Add a New Row</button>
			<button type="button" class="bt" id="saveAccount">Save</button>			
		</div>


		</div>
    </form:form>

</fieldset>