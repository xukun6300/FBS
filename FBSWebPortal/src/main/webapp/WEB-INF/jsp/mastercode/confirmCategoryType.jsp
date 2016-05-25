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
				<p>Code Key has been saved successfully!</p>
			</div>

			<div class="well clearfix form-horizontal">
				<legend>Code Key Details</legend>
				<div class="control-group">
					<label class="control-label" style="width:50px;font-weight:bold">Name:</label> 
					<label class="control-label" style="width:100px;text-align:left;padding-left:20px">${command.remarks}</label>
				</div>
				
				<div class="control-group">
					<label class="control-label" style="width:50px;font-weight:bold">Code:</label> 
					<label class="control-label" style="width:100px;text-align:left;padding-left:20px">${command.codeKey}</label>
				</div>
				
				<div class="control-group">
					<label class="control-label" style="width:50px;font-weight:bold">Effective Date:</label> 
					<label class="control-label" style="width:100px;text-align:left;padding-left:20px"><joda:format value="${command.effectiveDate}" pattern="dd MMM yyyy"/>  </label>
				</div>
			</div>
            <button id="btnAdd" name="btnAdd" class="bt bt1" onClick="javascript:location.href='showAddCodeKey.action'" type="button">Add Another Code Key</button> 
            <button id="btnAddValues" name="btnAddValues" class="bt bt1" onClick="javascript:location.href='showAddCodeValue.action?codeKey=${command.codeKey}'" type="button">Add Code Values</button>
            <button id="btnBack" name="btnBack" class="bt bt-back" onClick="javascript:history.back()" type="button">Back</button>
		</div>
	</div>
</div>