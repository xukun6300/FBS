<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<style>
table td{
padding-left : 20px;
}

table tr{
margin:20px;
padding : 20px
}

</style>

<script type="text/javascript">
$(document).ready(function(){
	$("#assignTo").click(function(){
		$("#accountsLeft").find("option:selected").each(function(){
			var newOption = $("<option/>").text($(this).text()).val($(this).val());
			$("#accountsRight").append(newOption);
		});
		$("#accountsLeft").find("option:selected").remove();
		
		setSelectedAccountsVal();
	});
	
	$("#assignBack").click(function(){
		$("#accountsRight").find("option:selected").each(function(){
			var newOption = $("<option/>").text($(this).text()).val($(this).val());
			$("#accountsLeft").append(newOption);
		});
		$("#accountsRight").find("option:selected").remove();
		setSelectedAccountsVal();
	});
	
	function setSelectedAccountsVal(){
		var selectedAccounts = [];
		$("#accountsRight option").each(function(index){
			selectedAccounts[index] = $(this).val();
		})
		//console.log(selectedAccounts);
		$("#selectedAccounts").val(selectedAccounts);
	}
})


</script>

<fieldset>
   <legend class="section">User Details</legend>
   
   <form:form method="POST" id="userDetailsForm" commandName="command" action="updateUser.action" class="clearfix form-horizontal">
       <form:errors path="*" cssClass="error" element="div"/>
       
       <div class="clearfix">
           <fieldset>
               <table style="margin-left:20px; width:100%">
                  <tr>
                    <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.name"/></td>
                    <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.salutation"/></td>
                    <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.program"/></td>
                    <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.role"/></td>
                  </tr>
                  <tr>
                    <td>${command.name}</td> 
                    <td>${command.salutation}</td> 
                    <td>${command.programme}</td>
                    <td>${command.userRole}</td>
                  </tr>
                  <tr style="line-height:25px">
                   	<td>&nbsp</td>
                   	<td></td>
                   	<td>&nbsp</td>
                   	<td></td>
	              </tr>
                  <tr>
                    <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.email"/></td>
                    <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.account.status"/></td>
                    <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.dob"/></td>
                    <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.gender"/></td>
                  </tr>
                  <tr>
                    <td>${command.email}</td> 
                    <td>${command.accountStatus}</td> 
                    <td>${command.dateOfBirth}</td>
                    <td>${command.gender}</td>
                  </tr>
                  
                  <tr style="line-height:25px">
                   	<td>&nbsp</td>
                   	<td></td>
                   	<td>&nbsp</td>
                   	<td></td>
	              </tr>
	              
	              <tr>
	                <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.office.tel"/></td>
                    <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.mobile.num"/></td>
                    <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.last.success.login"/></td>
                    <td width="25%" style="font-weight:bold"><spring:message code="fbs.common.ana.ui.label.user.last.failed.login"/></td>
                  </tr>
                  <tr>
                    <td>${command.officeTel}</td>
                    <td>${command.mobileNum}</td> 
                    <td>${command.lastSuccessLoginDate}</td> 
                    <td>${command.lastFailedLoginDate}</td>   
                  </tr>
               </table>
             
           <br><hr/>
           
           <table style="margin-left:20px;">
           <tr>
           <td style="font-weight:bold;padding-bottom:15px">All Accounts</td>
           <td></td>
           <td style="font-weight:bold;padding-bottom:15px">Accounts Assigned To User</td>
           </tr>
           <tr>
           <td>
             <form:select path=""  multiple="true" style="height:100px" id="accountsLeft">
              <form:option value="1" label="1"/>
              <form:option value="2" label="2"/>
              <form:option value="3" label="3"/>
              <form:option value="4" label="4"/>
              <form:option value="5" label="5"/>
             </form:select>
           </td>
           <td>
			<div class="btn-group-vertical" role="group" aria-label="Vertical button group">
				<button id="assignTo" type="button" class="btn btn-success" style="width:120px">Assign To >></button>
				<button id="assignBack" type="button" class="btn btn-danger"style="width:120px"><< Assign Back</button>
			</div> 
			
           </td>
           <td>
          
            <form:select path=""  multiple="true" style="height:100px" id="accountsRight">
              <form:option value="11" label="11"/>
              <form:option value="12" label="12"/>
              <form:option value="13" label="13"/>
              <form:option value="14" label="14"/>
              <form:option value="15" label="15"/>
           </form:select>
           </td>
           </tr>
           </table>
           <form:hidden path="selectedAccounts" id="selectedAccounts"/>
           </fieldset>
       
       </div>
   </form:form>
</fieldset>
