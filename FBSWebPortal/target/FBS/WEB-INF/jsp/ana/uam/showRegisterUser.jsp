<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="netui" uri="/tags/netui"%>
<%@ taglib prefix="captcha" uri="/WEB-INF/tags/captcha.tld" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="<spring:url value="/static/internal/js/ana/uam/publicUser.js" />"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/common.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/changepwd.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/validate.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/pbkdf2.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/jsbn.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/prng4.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/rng.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/rsa.js"></script>
<%-- <script type="text/javascript" src="${contextPath}/static/internal/js/jQuery.Plugins.js"></script>  --%> <!-- for date picker, no need, it includes in include-scripts.jsp in main.jsp -->

<div class="container-fluid">
  <div class="row-fluid">
    <div class="block-center span12">
       <form:form method="POST" id="registerForm" name="registerForm" commandName="command" 
            action="saveNewUser.action" class="form-horizontal" autocomplete="off">
            
          <h1>Registration For New User</h1>
          <form:hidden path="salt"/>
          <form:hidden path="modulus" value="${modulus}"/>
          <form:hidden path="exponent" value="${exponent}"/> 
           <!-- Person Details -->
          <div class="clearfix">
            <strong><spring:message code="fbs.common.registeruser.ui.label.personaldetails"></spring:message></strong>
            <br/>
  
            <div class="control-group required">
              <label class="control-label"> <spring:message code="fbs.common.ana.registeruser.ui.label.emailfbsid"></spring:message> </label>
              
              <div class="controls">
                <form:input id="email" path="email" class="input-xlarge" maxlength="50"></form:input>
                <button id="btnCheckEmailId" name="btnCheckEmailId" class="bt-mid bt-append" type="button">
                   <spring:message code="fbs.common.ana.registeruser.ui.label.checkidavailablility"></spring:message>
                </button>
                <form:errors path="email" cssClass="mandatory" element="div"></form:errors>
                <div id="message" class="mandatory"></div>
              </div>
            </div>
            
            
            <div class="control-group required">
               <label class="control-label"> <spring:message code="fbs.common.ana.registeruser.ui.label.salutation"></spring:message> </label>
               <div class="controls" id="select">
                  <form:select path="salutationTypeTId" id="salutationTypeTId" class="input-xlarge" maxlength="5" onchange="selectSalutation()">
                     <form:options items="${salutationType}"></form:options>
                  </form:select>
                  <form:errors path="salutationTypeTId" cssClass="mandatory" element="div"></form:errors>
               </div>
            </div>
            
            
            <div class="control-group required">
               <label class="control-label"> <spring:message code="fbs.common.ana.registeruser.ui.label.namefbsid"></spring:message> </label>
               <div class="controls">
                  <form:input id="name" path="name" class="input-xlarge" maxlength="50"  onPaste="return false"></form:input>
                  <form:errors path="name" cssClass="mandatory" element="div"></form:errors>
               </div>
            </div>
            
           <div class="control-group">
              <label class="control-label" for="genderTypeTDesc"><strong><spring:message code="fbs.common.registeruser.ui.label.gender"></spring:message></strong> </label>       
              <div class="controls control-radio-checkbox">
                 <form:radiobuttons path="genderTypeTDesc" items="${genderType}" onclick="chooseGender()"></form:radiobuttons> 
                 <form:errors path="genderTypeTDesc" cssClass="mandatory" element="div"></form:errors>      
              </div>       
           </div>
          
           <div class="control-group required">
              <label class="control-label" for="dob"><spring:message code="fbs.common.registeruser.ui.label.dob"></spring:message></label>
              <div class="controls">
                 <form:input id="dob" path="dob" class="input-large" maxlength="50" readonly="true" style="background:white;"></form:input>
                 <button id="dob_btn" type="button" class="btn">
                    <i class=" icon-calendar"></i>
                 </button>
                 <form:errors path="dob" cssClass="mandatory" element="div"></form:errors>              
              </div>
           </div>
  
         </div>
         
         <!-- Contact Details -->
         <div class="clearfix">
           <strong><spring:message code="fbs.common.registeruser.ui.label.contactdetails"></spring:message></strong>
           <br/>
           
           <div class="control-group required">
              <label class="control-label" for="primaryContactTypeT"><spring:message code="fbs.common.ana.registeruser.ui.label.primarycontact"></spring:message></label>
              <div class="controls control-radio-checkbox">
                 <form:radiobuttons path="primaryContactTypeT" items="${primaryContactType}" onclick="choosePrimaryContact()"></form:radiobuttons> 
                 <form:errors path="primaryContactTypeT" cssClass="mandatory" element="div"></form:errors>
              </div>
           </div>
           
           <div class="control-group inline-control phoneNumber" id="officeTelNo">
              <label class="control-label" for="officeTelNo"><spring:message code="fbs.common.ana.registeruser.ui.label.officeTelNo"></spring:message></label>
              <div class="controls">
				 <div class="control-group">
				    <form:input id="officeTelNo" path="officeTelNo" class="input-large" maxlength="16"></form:input>
				    <form:errors path="officeTelNo" cssClass="mandatory" element="div"></form:errors>
				 </div>
			  </div>
           </div>
           
           <div class="control-group inline-control phoneNumber" id="mobileNo">
              <label class="control-label" for="mobileNo"><spring:message code="fbs.common.ana.registeruser.ui.label.mobileno"></spring:message></label>
              <div class="controls">
				 <div class="control-group">
				   <form:input path="mobileNo" id="mobileNo" class="input-large" maxlength="16"/>
				   <form:errors path="mobileNo" cssClass="mandatory" element="div"></form:errors>
				 </div>
			  </div>
           </div>
          
         </div>
          
         <!-- Security Questions --> 
         <div class="clearfix"> 
            <strong><spring:message code="fbs.common.registeruser.ui.label.securityquestions"></spring:message></strong>
            <br>
            
            <div class="control-group required">
               <label class="control-label" for="securityQuestion1"><spring:message code="fbs.common.registeruser.ui.label.securityquestions.question1" /></label>
               <div class="controls" id="select2">
                  <form:select path="securityQuestion1" id="securityQuestion1" class="input-xlarge">
                    <form:options items="${securityQuestion}"></form:options> 
                  </form:select>
                  <form:input id="ans1" path="ans1" class="input-large" onchange="formatAns(this.form, this, 'encryptedAnswer1')"></form:input>
                  <form:hidden path="encryptedAnswer1"/>
                  <div class="controls" style="margin-left:1px;">
                    <form:errors path="securityQuestion1" cssClass="mandatory" element="div"/>
                    <form:errors path="ans1" cssClass="mandatory" element="div" style="margin-left:40%;"/>
                  </div>                
               </div>
            </div>
            
            <div class="control-group required">
               <label class="control-label" for="securityQuestion2"><spring:message code="fbs.common.registeruser.ui.label.securityquestions.question2" /></label>
               <div class="controls" id="select2">
                  <form:select path="securityQuestion2" id="securityQuestion2" class="input-xlarge">
                    <form:options items="${securityQuestion}"></form:options> 
                  </form:select>
                  <form:input id="ans2" path="ans2" class="input-large" onchange="formatAns(this.form, this, 'encryptedAnswer2')"></form:input>
                  <form:hidden path="encryptedAnswer2"/>
                  <div class="controls" style="margin-left:1px;">
                    <form:errors path="securityQuestion2" cssClass="mandatory" element="div"/>
                    <form:errors path="ans2" cssClass="mandatory" element="div" style="margin-left:40%;"/>
                  </div>
               </div>
            </div>
            
            <div class="control-group required">
               <label class="control-label"><spring:message code="fbs.common.registeruser.ui.label.password"></spring:message></label>
               <div class="controls passwordtooltip" id="select2" style="width:25%">
                  <form:password id="password" path="password" class="input-large" maxlength="100" onkeyup="checkPasswordStrength(this.value)" ></form:password>
                  <span style="color:#4392BD;cursor:pointer;" >
                    <input type="hidden" value="<spring:message code="fbs.common.user.changepassword.hint" />"/>
                  </span>
                  <div id="passwordDescription" class="ui-state-hover" style="width: 220px; border: 0px;"></div>
                  <div id="password_strength_border">
					 <div id="passwordStrength" class="text-info"></div>
				  </div>
				  <form:errors path="password" cssClass="mandatory" element="div" style="margin-left:1px;"/>
               </div>          
            </div>
            
            <c:set var="captchaFlag">
              <spring:message code="fbs.publicuser.captch.turn.on"/>
            </c:set>
            
            <div class="control-group required">
               <label class="control-label"><spring:message	code="fbs.common.registeruser.ui.label.confirmpassword" /></label>
               <div class="controls">
                 <form:password path="confirmPassword" id="confirmPassword" class="input-large" maxlength="100"></form:password>
                 <form:errors path="confirmPassword" cssClass="mandatory" element="div" style="margin-left:1px;"></form:errors>
               </div>               
            </div>
            
            <!-- captcha will implement later -->
           <%--  <c:if test="${captchaFlag eq 'Y'}"> --%>
               <div class="control-group required">
				<label class="control-label"> <spring:message code="fbs.common.registeruser.ui.label.captcha" /></label>
				<div class="controls">
				  <c:set var="captchaUrl" value="${contextPath}/icaptcha/showCaptcha.action"></c:set>
				  <table>
				    <tr>
				      <td class="formField" colspan="2">
				          <captcha:captcha captchaImageUrl="${captchaUrl}" captchaRefreshImageUrl="${contextPath}/images/refresh.gif" />
				      </td>
				    </tr>
				  </table>
				</div>
			   </div>
           <%--  </c:if> --%>

         </div>  
  
         <div>
           <button id="saveBtnnew" name="saveBtnnew" class="bt b1" type="button" onclick="submitForm();"><spring:message code="fbs.common.ui.button.submit" /></button>
           <button id="cancel" name="cancel" class="bt b1" type="button" onclick="javascript:location.href='${contextPath}/authentication/showLogin.action'"><spring:message code="fbs.common.ui.button.cancel" /></button>
           
         </div>
          
       </form:form>   
    </div>
  </div>
</div>

<div id="modalApplicationError" class="modal hide fade alert-error">
	<div class="modal-body">
		<span id="errorMsgApplication">Error Message</span>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn" data-dismiss="modal">Close</button>
	</div>
</div>











