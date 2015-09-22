<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="block-center span12">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h4>Success!</h4>
				<p>Code Value has been saved successfully!</p>
			</div>

			<div class="well clearfix form-horizontal">
				<legend>Code Value Details</legend>
				
				<div class="control-group">
					<label class="control-label" style="width:100px;font-weight: bold"><spring:message code="fbs.common.codemaintenance.ui.label.code.key"/></label>
					<label class="control-label" style="width:auto;text-align:left;padding-left:20px">${command.categoryType.label}</label>
				</div>
				
				<div class="control-group">
					<label class="control-label" style="width:100px;font-weight: bold"><spring:message code="fbs.common.codemaintenance.ui.label.code.value"/></label>
					<label class="control-label" style="width:auto;text-align:left;padding-left:20px">${command.codeValue}</label>
				</div>
				
				<div class="control-group">
					<label class="control-label" style="width:100px;font-weight: bold"><spring:message code="fbs.common.codemaintenance.ui.label.code.value.description"/></label>
					<label class="control-label" style="width:auto;text-align:left;padding-left:20px">${command.description}</label>
				</div>
				
				<div class="control-group">
					<label class="control-label" style="width:100px;font-weight: bold"><spring:message code="fbs.common.codemaintenance.ui.label.effective.date"/></label>
					<label class="control-label" style="width:auto;text-align:left;padding-left:20px">${command.effectiveDate}</label>
				</div>
				
				<div class="control-group">
					<label class="control-label" style="width:100px;font-weight: bold"><spring:message code="fbs.common.codemaintenance.ui.label.expiry.date"/></label>
					<label class="control-label" style="width:auto;text-align:left;padding-left:20px">${command.expiryDate}</label>
				</div>
				
				<div class="control-group">
					<label class="control-label" style="width:100px;font-weight: bold"><spring:message code="fbs.common.codemaintenance.ui.label.remarks2"/></label>
					<label class="control-label" style="width:auto;text-align:left;padding-left:20px">${command.remarks}</label>
				</div>
				
				
				
			</div>
			<button id="btnAdd" name="btnAdd" class="bt bt1" onClick="javascript:location.href='showAddCodeValue.action?codeKey=${command.categoryType.value}'" type="button" >Add Code Value</button> 
		    <button id="btnBack" name="btnBack" class="bt bt-back" onClick="javascript:location.href='showSearchCategoryType.action'" type="button" >Back</button>
		</div>
	</div>
</div>