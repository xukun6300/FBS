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
	    <div class="clearfix">
	    <form:errors path="acctStructureJson" cssClass="mandatory" element="div" style="margin-left:25px; margin-bottom:20px"/>
	    <br>
	    <form:input path="acctStructureJson" type="hidden"/>
	    
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
            <label class="control-label" for="needRequisitionForm"><spring:message code="fbs.common.account.ui.label.account.need.requisition.form"/></label>
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
	    <table id="accountTb" class="table ftable table-bordered table-hover table-condensed table-sortable">
             <thead>
                <tr>
                   <th><spring:message code="fbs.common.account.ui.label.account.column.name"/></th>
                   <th><spring:message code="fbs.common.account.ui.label.account.column.size"/></th>                  
                   <th><spring:message code="fbs.common.account.ui.label.account.column.type"/></th>
                   <th><spring:message code="fbs.common.account.ui.label.account.column.nullable"/></th>
                   <th><spring:message code="fbs.common.ui.label.delete"/></th>
                </tr>
             </thead>             
             <tbody>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.item.desc"/></td>
	              <td>300</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.text"/></td>	    
	              <td>
	                 <div class="controls control-radio-checkbox control-in-table"> 
                      <input type="radio" value="Y" name="nullable-row1" id="nullable-row1-yes" checked="checked"/><label for="nullable-row1-yes">Yes</label>
                      <input type="radio" value="N" name="nullable-row1" id="nullable-row1-no"/><label for="nullable-row1-no">No</label>
                     </div>
	              </td>          
	              <td>
	              <button class="btn-icon btn-danger deleteRow" disabled><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.workings"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.text"/></td>
	              <td>
	                 <div class="controls control-radio-checkbox control-in-table"> 
                      <input type="radio" value="Y" name="nullable-row2" id="nullable-row2-yes" checked="checked"/><label for="nullable-row2-yes">Yes</label>
                      <input type="radio" value="N" name="nullable-row2" id="nullable-row2-no"/><label for="nullable-row2-no">No</label>
                      </div>
	              </td>	              
	              <td>
	              <button class="btn-icon btn-danger deleteRow" disabled><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.amount"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.numeric"/></td>	    
	              <td>
	                 <div class="controls control-radio-checkbox control-in-table"> 
                      <input type="radio" value="Y" name="nullable-row3" id="nullable-row3-yes" checked="checked"/><label for="nullable-row3-yes">Yes</label>
                      <input type="radio" value="N" name="nullable-row3" id="nullable-row3-no"/><label for="nullable-row3-no">No</label>
                      </div>
	              </td>         
	              <td>
	              <button class="btn-icon btn-danger deleteRow" disabled><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.q1"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.numeric"/></td>     
	              <td>
	              <div class="controls control-radio-checkbox control-in-table"> 
                      <input type="radio" value="Y" name="nullable-row4" id="nullable-row4-yes" checked="checked"/><label for="nullable-row4-yes">Yes</label>
                      <input type="radio" value="N" name="nullable-row4" id="nullable-row4-no"/><label for="nullable-row4-no">No</label>
                      </div>
	              </td>         
	              <td>
	              <button class="btn-icon btn-danger deleteRow" disabled><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.q2"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.numeric"/></td>	      
	              <td>
	              <div class="controls control-radio-checkbox control-in-table"> 
                      <input type="radio" value="Y" name="nullable-row5" id="nullable-row5-yes" checked="checked"/><label for="nullable-row5-yes">Yes</label>
                      <input type="radio" value="N" name="nullable-row5" id="nullable-row5-no"/><label for="nullable-row5-no">No</label>
                      </div>
	              </td>        
	              <td>
	              <button class="btn-icon btn-danger deleteRow" disabled><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.q3"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.numeric"/></td>	  
	              <td>
	              <div class="controls control-radio-checkbox control-in-table"> 
                      <input type="radio" value="Y" name="nullable-row6" id="nullable-row6-yes" checked="checked"/><label for="nullable-row6-yes">Yes</label>
                      <input type="radio" value="N" name="nullable-row6" id="nullable-row6-no"/><label for="nullable-row6-no">No</label>
                      </div>
	              </td>            
	              <td>
	              <button class="btn-icon btn-danger deleteRow" disabled><i class="icon-remove icon-white"></i></button>
	              </td>
	            </tr>
	            <tr>
	              <td><spring:message code="fbs.common.account.ui.label.account.q4"/></td>
	              <td>100</td>
	              <td><spring:message code="fbs.common.account.ui.label.account.column.type.numeric"/></td>	 
	              <td>
	              <div class="controls control-radio-checkbox control-in-table"> 
                      <input type="radio" value="Y" name="nullable-row7" id="nullable-row7-yes" checked="checked"/><label for="nullable-row7-yes">Yes</label>
                      <input type="radio" value="N" name="nullable-row7" id="nullable-row7-no"/><label for="nullable-row7-no">No</label>
                      </div>
	              </td>             
	              <td>
	              <button class="btn-icon btn-danger deleteRow" disabled><i class="icon-remove icon-white"></i></button>
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
