<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<fieldset>
   <legend class="section">Add Code Value</legend>
   
   <form:form method="POST" commandName="command" action="addCodeValue.action" class="clearfix form-horizontal">
     <form:errors cssClass="alert alert-error" element="div"/>
      <div class="clearfix">
         <c:choose>
            <c:when test="${empty param.codeKey}"> <!-- codeKey is query string name -->
                <div class="control-group required">
                   <label class="control-label" for="categoryType"><spring:message code="fbs.common.codemaintenance.ui.label.code.key"/></label>
                   <div class="controls">
                     <form:select path="categoryType.value" id="categoryType" class="input-large value-label-pair">
                        <form:options items="${codeKeys}"/>
                     </form:select>
                     <form:errors path="categoryType" cssClass="mandatory" element="div"/>
                   </div>
                </div>
            </c:when>
            <c:otherwise>
               <div class="control-group">
                   <label class="control-label" for=""><spring:message code="fbs.common.codemaintenance.ui.label.code.key"/></label>
                   <div class="controls">
                      <span id="spanCK" class="input-xlarge uneditable-input">${param.codeKey}</span>
                   </div>
               </div>
               <form:hidden path="categoryType.value" value="${param.codeKey}"/>				   
               <form:hidden path="categoryType.label" value="${param.remarks}"/>
            </c:otherwise>
         </c:choose>
         
         <div class="control-group required">
             <label class="control-label" for="description"><spring:message code="fbs.common.codemaintenance.ui.label.code.value.description"/></label>
             <div class="controls">
                <form:input path="description" id="description" class="input-xlarge" maxlength="255"/>
                <form:errors path="description" cssClass="mandatory" element="div"/>
             </div>
         </div>
         
         <div class="control-group required">
            <label class="control-label" for="codeValue"><spring:message code="fbs.common.codemaintenance.ui.label.code.value"/></label>
            <div class="controls">
               <form:input path="codeValue" id="codeValue" class="input-xlarge" maxlength="300"/>
               <form:errors path="codeValue" cssClass="mandatory" element="div"/>
            </div>
         </div>
         
         <div class="control-group">
            <label class="control-label">Always Available</label>
            <div class="controls">
              <form:checkbox id="alwaysAvailable" path="alwaysAvailable"/>
            </div>
         </div>
         
         <div class="control-group">
            <label class="control-label" for="effectiveDate"><spring:message code="fbs.common.codemaintenance.ui.label.effective.date"/></label>
            <div class="controls">
               <netui:dateinput id="effectiveDate" path="effectiveDate" cssClass="input-medium" maxlength="10" placeholder="dd/MM/yyyy" disabled="true"/>
               <button id="effectiveDateBtn" type="button" class="btn" disabled="disabled"><i class="icon-calendar"></i></button>
               <form:errors path="effectiveDate" cssClass="mandatory" element="div"/>
            </div>
         </div>
         
         <div class="control-group">
            <label class="control-label" for="expiryDate"><spring:message code="fbs.common.codemaintenance.ui.label.expiry.date"/></label>
            <div class="controls">        
               <netui:dateinput id="expiryDate" path="expiryDate" cssClass="input-medium" maxlength="10" placeholder="dd/MM/yyyy" disabled="true"/>
               <button id="expiryDateBtn" type="button" class="btn" disabled="disabled"><i class="icon-calendar"></i></button>
               <form:errors path="expiryDate" cssClass="mandatory" element="div"/>
            </div>
         </div>
         
         <div class="control-group">
            <label class="control-label" for="remarks"><spring:message code="fbs.common.codemaintenance.ui.label.remarks2"/></label>
            <div class="controls">
               <form:input path="remarks" id="remarks" class="input-xlarge" maxlength="255" />
            </div>
         </div>
         
      </div>
      
      <div class="control-group">
        <div class="controls">
           <button id="btnSave" name="btnSave" class="bt bt1" type="submit" ><spring:message code="fbs.common.ui.button.save"/></button>
		   <button id="btnReset" name="btnReset" class="bt bt1" type="reset"><spring:message code="fbs.common.ui.button.reset"/></button>
		   <button id="btnBack" name="btnBack" class="bt bt-back" onClick="javascript:location.href='showSearchCategoryType.action'" type="button" ><spring:message code="fbs.common.ui.button.back"/></button>	
        </div>	     		
      </div>
   </form:form>
</fieldset>
<script type="text/javascript">
    $('#effectiveDate').myDatePicker('#effectiveDateBtn');
    $('#expiryDate').myDatePicker('#expiryDateBtn');  
</script>













