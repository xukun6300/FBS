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
             
           <br>
           <div style="margin-left:40px">
               <label class="bold" style="font-size:14px">Accounts</label>
	           <c:forEach items="${command.selectedAccountList}" var="selectedAccount">
	               ${selectedAccount}<br><br>
	           </c:forEach>
           
           </div>
           
           
           <form:hidden path="selectedAccounts" id="selectedAccounts"/>
           </fieldset>
       
       </div>
   </form:form>
</fieldset>
