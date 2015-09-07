<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="netui" uri="/tags/netui"%>
<%@ taglib prefix="captcha" uri="/WEB-INF/tags/captcha.tld"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/common.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/pbkdf2.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/ajax.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/login.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/jsbn.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/prng4.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/rng.js"></script>
<script type="text/javascript" src="${contextPath}/static/internal/js/ana/rsa.js"></script>

<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="http://bootsnipp.com/dist/bootsnipp.min.css?ver=70eabcd8097cd299e1ba8efe436992b7"> -->
<style>
/*    --------------------------------------------------
	:: Login Section
	-------------------------------------------------- */
#login {
    padding-top: 50px
}
#login .form-wrap {
    width: 30%;
    margin: 0 auto;
}
#login h1 {
    color: #1fa67b;
    font-size: 18px;
    text-align: center;
    font-weight: bold;
    padding-bottom: 20px;
}
#login .form-group {
    margin-bottom: 25px;
}
#login .checkbox {
    margin-bottom: 20px;
    position: relative;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    -o-user-select: none;
    user-select: none;
}
#login .checkbox.show:before {
    content: '\e013';
    color: #1fa67b;
    font-size: 17px;
    margin: 1px 0 0 3px;
    position: absolute;
    pointer-events: none;
    font-family: 'Glyphicons Halflings';
}
#login .checkbox .character-checkbox {
    width: 25px;
    height: 25px;
    cursor: pointer;
    border-radius: 3px;
    border: 1px solid #ccc;
    vertical-align: middle;
    display: inline-block;
}
#login .checkbox .label {
    color: #6d6d6d;
    font-size: 13px;
    font-weight: normal;
}
#login .btn.btn-custom {
    font-size: 14px;
	margin-bottom: 20px;
}
#login .forget {
    font-size: 13px;
	text-align: center;
	display: block;
}

/*    --------------------------------------------------
	:: Inputs & Buttons
	-------------------------------------------------- */
.form-control {
    color: #212121;
}
.btn-custom {
    color: #fff;
	background-color: #1fa67b;
}
.btn-custom:hover,
.btn-custom:focus {
    color: #fff;
}

/*    --------------------------------------------------
    :: Footer
	-------------------------------------------------- */
#footer {
    color: #6d6d6d;
    font-size: 12px;
    text-align: center;
}
#footer p {
    margin-bottom: 0;
}
#footer a {
    color: inherit;
}

</style>


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

<%-- <div id="popupBottom" class="popover bottom in" aria-hidden="false" style="top: 100px; left:10px;max-width:345px;max-height:250px; display: block; z-index: 1050; ">
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
  </div>   --%>


<form name="submitForm" method="post" action="authenticate.action">
   <input name="u" type="hidden"/>
   <input name="f" type="hidden"/>
<!--    <input name="at" type="hidden"/>
   <input name="bt" type="hidden"/>
   <input name="en" type="hidden"/>
   <input name="pv" type="hidden"/> -->
</form> 
<%-- 
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
	   </form> --%>
	   
<section id="login">
    <div class="container">
    	<div class="row">
    	    <div class="col-xs-12">
        	    <div class="form-wrap">
                <h1>Log in FBS with your email account</h1>
                    <form name="login" id="login" method="post" action="authenticate.action" autocomplete="off">
                        <div class="form-group">
                            <label for="userId" class="sr-only">Email</label>
                            <input type="email" name="userId" id="userId" class="form-control" placeholder="somebody@example.com">
                        </div>
                        <div class="form-group">
                            <label for="password" class="sr-only">Password</label>
                            <input type="password" name="password" id="password" class="form-control" placeholder="Password">
                        </div>
                        <div class="checkbox">
                            <span class="character-checkbox" onclick="showPassword()"></span>
                            <span class="label">Show password</span>
                        </div>
                        <input class="btn btn-custom btn-lg btn-block" id="login2" name="login2" type="button" value="Log in"  onclick="fidLogin(true)"> 
                        <!-- <input type="submit" id="btn-login" class="btn btn-custom btn-lg btn-block" value="Log in"> -->
                        <img alt="spinner" id="spinner" src="${contextPath}/images/spinner.gif" style="display: none;" />
                    </form>
                    <a href="javascript:;" class="forget" data-toggle="modal" data-target=".forget-modal">Forgot your password?</a>
                    <hr>
        	    </div>
    		</div> 
    	</div> 
    </div> 
</section>

<!-- <div class="modal fade forget-modal" tabindex="-1" role="dialog" aria-labelledby="myForgetModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
					<span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Recovery password</h4>
			</div>
			<div class="modal-body">
				<p>Type your email account</p>
				<input type="email" name="recovery-email" id="recovery-email" class="form-control" autocomplete="off">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-custom">Recovery</button>
			</div>
		</div>
	</div> 
</div>  -->

<footer id="footer">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <p>Page © - 2015</p>
                <p>Powered by <strong><a href="https://sg.linkedin.com/pub/frank-xu-kun/63/62a/314" target="_blank">Frank</a></strong></p>
            </div>
        </div>
    </div>
</footer>





