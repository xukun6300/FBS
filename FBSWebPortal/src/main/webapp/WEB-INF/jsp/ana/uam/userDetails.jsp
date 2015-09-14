<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<style>
table td{
padding-left : 20px
}
</style>

<fieldset>
   <legend class="section">User Details</legend>
   <h2 class="expand"></h2>
   
   <form:form method="POST" id="userDetailsForm" commandName="command" action="updateUser.action" class="clearfix form-horizontal">
       <form:errors path="*" cssClass="error" element="div"/>
       
       <div class="clearfix">
           <fieldset>
               <table style="margin-left:20px;">
                  <tr>
                    <td width="25%" style="font-weight:bold">Name</td>
                    <td width="25%" style="font-weight:bold">Email</td>
                    <td width="25%" style="font-weight:bold">Birthday</td>
                    <!-- <td width="25%" style="font-weight:bold">Role</td>  -->
                  </tr>
                   
                  <tr>
                    <td>Frank</td> 
                    <td>frankxukun@gmail.com</td> 
                    <td>1988-09-29</td>
                    <!-- <td>Admin</td> -->
                  </tr>
               
               </table>
           
           </fieldset>
       
       </div>
   </form:form>
</fieldset>
