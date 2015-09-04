<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="netui" uri="/tags/netui"%>
<%@ taglib prefix="captcha" uri="/WEB-INF/tags/captcha.tld"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/common.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/pbkdf2.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/ajax.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/login.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/jsbn.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/prng4.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/rng.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/rsa.js"></script>

<%

	if(request.getUserPrincipal() !=null)
	{
		%>
		<script type="text/javascript">
			window.top.location.href = '<c:url value="/authentication/showSessionExpired.action"/>';
		</script> 
<%	
	}
%>

<div id="popupBottom" class="popover bottom in" aria-hidden="false" style="top: 100px; left:10px;max-width:345px;max-height:250px; display: block; z-index: 1050; ">
      <div class="arrow"></div>     
      <a class="close" data-dismiss="modal" style="margin-right:5px">x</a>
      <div class="popover-content" id="popover-content"  style="width:340px;" >
         <div class="log_frnt"  style="width:340px;" > </div>
		
      	<form method="POST" name="login" id="login" action="authenticate.action"  style="width:340px;" autocomplete="off">
             <input class="input-medium"  name="userId" id="userId" type="text"  placeholder="Your Email" size="200" maxlength="50" >
             <input class="input-medium"  name="password" id="password" type="password"  placeholder="Your Password"   size="200" maxlength="24" >
             <div id="statusbar" style="display:none;width:320px;" class="alert-error errormsg" ></div>
             <div id="divCapsLock0" style="display:none;border-radius:3px;padding-left: 2px;width: 215px;" class="alert-error errormsg">
             <span>Caps Lock ON!</span>
		     </div>
			<br>
                 <span>By clicking on the login button, you agree to the Terms of Use.</span>
			<br>
                 <input class="btn_blk" id="login2" name="login2" type="button" value="Login"  onclick="fidLogin(true)"> <br>
                 <a class="links"  href="${contextPath}/passwordmanagement/showResetUserAccountPassword.action"> Forgot Password </a>  <a class="links"  href="${contextPath}/useraccountmanagement/registerPublicUser.action"> Request User Account </a>
                 <input name="mod" type="hidden" value="${modulus}" />
		   <input name="exp" type="hidden" value="${exponent}" />
		   <img alt="spinner" id="spinner" src="${contextPath}/images/spinner.gif" style="display: none;" />   
	   </form>
	  
      </div>
  </div>  


<form name="submitForm" method="post" action="authenticate.action">
   <input name="u" type="hidden"/>
   <input name="f" type="hidden"/>
<!--    <input name="at" type="hidden"/>
   <input name="bt" type="hidden"/>
   <input name="en" type="hidden"/>
   <input name="pv" type="hidden"/> -->
</form> 






