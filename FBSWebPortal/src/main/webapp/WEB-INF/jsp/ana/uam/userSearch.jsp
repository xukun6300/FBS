<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script>
   var jsBaseURL = '${pageContext.request.contextPath}';
</script>


   <fieldset>
      <legend class="section">User Account Management</legend>
      <h2 class="expand"></h2>
         <form:form method="POST" commandName="command" action="searchUser.action" class="clearfix">
           <form:errors path="*" cssClass="alert alert-error" element="div"/>
           <div class="clearfix">
               <table class="ftable">
                  <tr>
                    <!-- User name field -->
					<td style="width: 200px;">
						<div class="pull-left inline-control">
							<label class="control-label" for="name"><spring:message code="fbs.common.ana.ui.label.user.search.namecontains.name" /></label>
						</div>
					</td>
					<td style="width: 50px;">
						<div class="pull-left inline-control">
							<label class="control-label" for="name"><spring:message code="fbs.common.ana.ui.label.user.search.namecontains.contains" /></label>
						</div>
					</td>
					<td style="width:250px;">
						<div class="controls">
							<form:input id="name" path="name" class="input-large" maxlength="100"/>					
						</div>
			        </td>
			        <!-- User name field -->
			        
			        <!-- Email address field -->
			        <td style="width: 200px;">
						<div class="pull-left inline-control">
							<label class="control-label" for="email"><spring:message code="fbs.common.ana.ui.label.user.search.email.address" /></label>
						</div>
					</td>
					<td style="width: 50px;">
						
					</td>
					<td style="width:250px;">
						<div class="controls">
							<form:input id="email" path="email" class="input-large" maxlength="100"/>					
						</div>
			        </td>
					<!-- Email address field -->
				  </tr>
				  
				  <tr>
                    <!-- Account status field -->
					<td style="width: 200px;">
						<div class="pull-left inline-control">
							<label class="control-label" for="accountStatus"><spring:message code="fbs.common.ana.ui.label.user.search.status" /></label>
						</div>
					</td>
					<td style="width: 50px;">
						
					</td>
					<td style="width:250px;">
						<div class="controls">
							<form:select path="accountStatus" id="accountStatus" class="input-large">
							    <%-- <form:options items="${ }"/> --%>
							</form:select>		
						</div>
			        </td>
			        <!-- Account status field -->
			        
			        <!-- User role field -->
			        <td style="width: 200px;">
						<div class="pull-left inline-control">
							<label class="control-label" for="userRole"><spring:message code="fbs.common.ana.ui.label.user.search.role" /></label>
						</div>
					</td>
					<td style="width: 50px;">
						
					</td>
					<td style="width:250px;">
						<div class="controls">
					     	<%-- <form:input id="userRole" path="userRole" class="input-large" maxlength="100"/> --%>			
							<form:select path="userRole" id="userRole" class="input-large">
							   <%-- <form:options items="${ }"/> --%>
							</form:select>	 			
						</div>
			        </td>
					<!-- User role field -->
				  </tr>
				  
				  <tr>
				     <td colspan="6">
				        <button id="btnSearch" name="btnSearch" class="bt bt-pane b1" type="submit">Search</button>
				     </td>
				  </tr>
               </table>          
           </div>
         </form:form>	
         
         <netui:grid queryFormName="command" action="searchUser.action">
            <netui:gridPaging tableId="usersTable"/>
            <table id="usersTable" class="table ftable table-bordered table-hover table-condensed">
               <thead>
                  <tr>
                     <th><spring:message code="fbs.common.ui.label.pagination.sn"/></th>
                     <th class="nowrap"><netui:gridSorting displayName="fbs.common.ana.ui.label.user.search.namecontains.name" name="name"/></th>
                     <th class="nowrap"><netui:gridSorting displayName="fbs.common.ana.ui.label.user.salutation" name="salutation"/></th>
                     <%-- <th class="nowrap"><netui:gridSorting displayName="fbs.common.ana.ui.label.user.program" name="programme"/></th> --%>
                     <th class="nowrap"><netui:gridSorting displayName="fbs.common.ana.ui.label.user.email" name="email"/></th>
                     <th class="nowrap"><netui:gridSorting displayName="fbs.common.ana.ui.label.user.account.status" name="accountStatus"/></th>
                     <th class="nowrap"><netui:gridSorting displayName="fbs.common.ana.ui.label.user.last.success.login" name="lastSuccessLoginDate"/></th>
                     <th class="nowrap"><netui:gridSorting displayName="fbs.common.ana.ui.label.user.last.failed.login" name="lastFailedLoginDate"/></th>
                  </tr>
               </thead>
               <tbody>
                  <netui:gridRows>
                     <tr>
                        <td><netui:gridCurrentRowNum/></td>
                        <td><netui:gridRowElement name="name"/></td>
                        <td><netui:gridRowElement name="salutation"/></td>
                        <%-- <td><netui:gridRowElement name="programme"/></td> --%>
                        <td><netui:gridRowElement name="email"/></td>
                        <td><netui:gridRowElement name="accountStatus"/></td>
                        <td><netui:gridRowElement name="lastSuccessLoginDate" format="dd MMM yyyy"/></td>
                        <td><netui:gridRowElement name="lastFailedLoginDate" format="dd MMM yyyy"/></td>
                     </tr>
                  </netui:gridRows>
               </tbody>
            </table>
         </netui:grid>
   </fieldset>		




