<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>



<fieldset>
	<legend class="section">Update Code Value</legend>
	<form:form class="clearfix form-horizontal" id="codeValueForm" method="POST" commandName="command" action="updateCodeValue.action">
		<div class="clearfix">		
			<div class="control-group">
	            <label class="control-label bold"><spring:message code="fbs.common.codemaintenance.ui.label.code.key"/></label>
	            <div class="controls">
	               <label>${command.masterCodeType.codeKey}</label>               
	            </div>
	         </div>
	         
	         <div class="control-group">
	            <label class="control-label bold"><spring:message code="fbs.common.codemaintenance.ui.label.code.value"/></label>
	            <div class="controls">
	               <label>${command.codeValue}</label>               
	            </div>
	         </div>
	         
	         <div class="control-group required">
				<label class="control-label bold" for="description"><spring:message code="fbs.common.codemaintenance.ui.label.code.value.description"/></label>
				<div class="controls">
					<form:input id="description" path="description" class="input-xlarge" maxlength="255" />
					<form:errors path="description" cssClass="mandatory" element="div" />							
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label bold">Always Available</label>
				<div class="controls">			        	
			        <form:checkbox id="alwaysAvailable" path="alwaysAvailable"/>
                </div>
			</div>
			
			<div class="control-group">
				<label class="control-label bold" for="effectiveDate"><spring:message code="fbs.common.codemaintenance.ui.label.effective.date"/></label>
				<div class="controls">
					<netui:dateinput id="effectiveDate" path="effectiveDate" cssClass="input-small" maxlength="10" placeholder="dd/MM/yyyy" /><button id="effectiveDateBtn" type="button" class="btn" ><i class=" icon-calendar"></i></button>						
					<form:errors path="effectiveDate" cssClass="mandatory" element="div" />				
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label bold" for="expiryDate"><spring:message code="fbs.common.codemaintenance.ui.label.expiry.date"/></label>
				<div class="controls">			        
				    <netui:dateinput id="expiryDate" path="expiryDate" cssClass="input-small" maxlength="10" placeholder="dd/MM/yyyy" /><button id="expiryDateBtn" type="button" class="btn" ><i class=" icon-calendar"></i></button>					
					<form:errors path="expiryDate" cssClass="mandatory" element="div" />			
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label bold" for="remarks"><spring:message code="fbs.common.codemaintenance.ui.label.remarks2"/></label>
				<div class="controls">
					<form:input id="remarks" path="remarks" class="input-xlarge" maxlength="255" />
				</div>
			</div>	
			
			 <div class="control-group">
               <div class="controls">
			    <button id="btnSave" name="btnSave" class="bt" type="submit" ><spring:message code="fbs.common.ui.button.save"/></button>
				<button id="btnReset" name="btnReset" class="bt" type="reset"><spring:message code="fbs.common.ui.button.reset"/></button>
				<button id="btnBack" name="btnBack" class="bt" onClick="javascript:history.back()" type="button" ><spring:message code="fbs.common.ui.button.back"/></button>
			   </div>
			 </div>
			 <form:hidden path="id"/>
			 <form:hidden path="masterCodeType.codeKey" />
	         <form:hidden path="codeValue" />
             <form:hidden path="sequenceNo" />
			 <form:hidden path="categoryType.value" value="${command.masterCodeType.codeKey}"/>
	    </div>
	</form:form>
</fieldset>

<script type="text/javascript">
    $('#effectiveDate').myDatePicker('#effectiveDateBtn');
    $('#expiryDate').myDatePicker('#expiryDateBtn');

    $(document).ready(function(){
    	
    	$("#alwaysAvailable").click(function(){
    		adjustRadioState($(this));
    	});
    	
    	adjustRadioState($("#alwaysAvailable"));
    });
    
    function adjustRadioState(source){
    	if(source.attr('checked')){
    		$('#effectiveDate').val('');
    		$('#expiryDate').val('');
    		$('#effectiveDate').attr("disabled", "disabled");
    		$('#expiryDate').attr("disabled", "disabled");
    		$('#effectiveDateBtn').attr("disabled", "disabled");
    		$('#expiryDateBtn').attr("disabled", "disabled");
    	}else{
    		$('#effectiveDate').removeAttr('disabled');
    		$('#expiryDate').removeAttr('disabled');
    		$('#effectiveDateBtn').removeAttr('disabled');
    		$('#expiryDateBtn').removeAttr('disabled');
    	}
    }
    
</script>









