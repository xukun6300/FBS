<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<fieldset>
	<legend class="section">Update Code Key</legend>
	<form:form method="POST" commandName="command" action="updateCodeKey.action" class="clearfix form-horizontal">
		<form:errors cssClass="alert alert-error" element="div" />
		<div class="clearfix">
		
		<div class="control-group required">
            <label class="control-label" for="remarks"><spring:message code="fbs.common.codemaintenance.ui.label.remarks"/></label>
            <div class="controls">
               <form:input id="remarks" path="remarks" class="input-xlarge" maxlength="100"/>
               <form:errors path="remarks" cssClass="mandatory" element="div"/> 
            </div>
         </div>
         
         <div class="control-group required">
            <label class="control-label" for="codeKey"><spring:message code="fbs.common.codemaintenance.ui.label.code.key"/></label>
            <div class="controls">
	            <form:input id="codeKey" path="codeKey" class="input-xlarge" maxlength="200" code-gen="true" code-gen-id="remarks" code-gen-tb="MASTERCODE_TYPE"/>
	            <form:errors path="codeKey" cssClass="mandatory" element="div"/>
            </div>
         </div>
         
         <div class="control-group required">
            <label class="control-label" for="sortOrder"><spring:message code="fbs.common.codemaintenance.ui.label.sortorder"/></label>
            <div class="controls">
               <label class="radio inline">
                  <form:radiobutton id="sortOrder" path="sortOrder" value="1"/>Description
               </label>
               <label class="radio inline">
                  <form:radiobutton id="sortOrder" path="sortOrder" value="2"/>Sequence
               </label>
               <form:errors path="sortOrder" cssClass="mandatory" element="div"/>
            </div>
         </div>
         
         <div class="control-group required">
            <label class="control-label" for="effectiveDate"><spring:message code="fbs.common.codemaintenance.ui.label.effective.date"/></label>
			<div class="controls">
			   <netui:dateinput id="effectiveDate" path="effectiveDate" cssClass="input-medium" maxlength="10" placeholder="dd/MM/yyyy"/>
			   <button id="effectiveDateBtn" type="button" class="btn"><i class="icon-calendar"></i></button>
			   <form:errors path="effectiveDate" cssClass="mandatory" element="div"/>
			</div>
		</div>   
         
        <div class="control-group">
           <div class="controls">
            <button id="btnSave" name="btnSave" type="submit" class="bt bt1"><spring:message code="fbs.common.ui.button.save"/></button>
            <button id="btnReset" name="btnReset" type="reset" class="bt bt1"><spring:message code="fbs.common.ui.button.reset"/></button>
            <button id="btnBack" name="btnBack" type="button" class="bt bt-back" onClick="javascript:history.back()"><spring:message code="fbs.common.ui.button.back"/></button>
           </div>
        </div>
		
		
		</div>
	</form:form>
</fieldset>
<script type="text/javascript">
$("#effectiveDate").myDatePicker("#effectiveDateBtn");
</script>