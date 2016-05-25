<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>



<fieldset>
	<legend class="section">Update Code Value Sequence</legend>
	<form:form class="clearfix form-horizontal" method="POST" commandName="command" action="updateCodeValueSequence.action">
		<div class="clearfix">
		
		<div class="control-group">
            <label class="control-label bold"><spring:message code="fbs.common.codemaintenance.ui.label.code.key"/></label>
            <div class="controls">
               <label>${command.codeKey}</label>               
            </div>
         </div>
         
         <div class="control-group">
            <label class="control-label bold"><spring:message code="fbs.common.codemaintenance.ui.label.remarks"/></label>
            <div class="controls">
               <label>${command.remarks}</label>               
            </div>
         </div>
		
		<div class="control-group">
           <div class="controls">
            <button id="btnSave" name="btnSave" class="bt" disabled="disabled" type="submit" ><spring:message code="fbs.common.ui.button.save"/></button>
			<button id="btnBack" name="btnBack" class="bt" onClick="javascript:history.back()" type="button" ><spring:message code="fbs.common.ui.button.back"/></button>		  
		   </div>
		</div>
		
		<netui:grid queryFormName="command" action="">
			<table class="table ftable table-bordered table-hover table-condensed table-sortable">
				<thead>
					<tr>
						<th><spring:message code="fbs.common.ui.label.pagination.sn"/></th>
						<th class="nowrap"><spring:message code="fbs.common.codemaintenance.ui.label.code.value.description" /></th>
						<th class="nowrap"><spring:message code="fbs.common.codemaintenance.ui.label.code.value" /></th>
						<th class="nowrap"><spring:message code="fbs.common.codemaintenance.ui.label.effective.date" /></th>
                        <th class="nowrap"><spring:message code="fbs.common.codemaintenance.ui.label.expiry.date" /></th>
					</tr>
				</thead>
				<tbody>
					<netui:gridRows>
						<tr record-id='<netui:gridRowElement name="id"/>'>
							<td><netui:gridCurrentRowNum /></td>							
							<td><netui:gridRowElement name="description"/></td>
							<td class="nowrap"><netui:gridRowElement  name="codeValue"/></td>
							<td><netui:gridRowElement  name="effectiveDate"/></td>
							<td><netui:gridRowElement  name="expiryDate"/></td>
						</tr>
					</netui:gridRows>
				</tbody>
			</table>
		</netui:grid>
		<p style="color: red;"><spring:message code="fbs.common.codemaintenance.ui.label.updatesequence.note" /><p>
		
		<form:hidden path="codeTypeId"/>
		<form:hidden path="sequenceJson"/>
		</div>
	</form:form>
</fieldset>
<script type="text/javascript">
	$('.table-sortable tbody').sortable({
		start		: function(event, ui) {
			$(ui.item).attr('previous-index', ui.item.index());
		},
		update		:	function(event, ui) {
			var items = [];
			var rows = $(this).find('tr');
			for (var i=0; i<rows.length; i++) {
				var item = {
					"recordId" :	$(rows[i]).attr('record-id'),
					"sequenceNo"	:	i + 1
				};
				items[i]=item;
				$(rows[i]).find('td:first').text(i+1);
			}
			var json = JSON.stringify(items);
			console.log(json);
			$('#sequenceJson').val(json);
			$('#btnSave').removeAttr("disabled");
		}
	});
</script>