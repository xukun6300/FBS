<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="netui" uri="/tags/netui"%>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="block-center span12">
		   <h1>Confirmation Page</h1>
		   <hr/>
		   
		   <div class="alert alert-success">
		      <button type="button" class="close" data-dismiss="alert"></button>
		      <h4><spring:message code="fbs.common.registeruser.ui.label.success"/></h4>  
		   </div>
		   
		   <button id="btnBack" name="btnBack" class="bt" type="button" onclick="javascript:location.href='<c:url value="${backBtnAction}"/>'">Go Back To Home</button>
		
		</div>
	</div>
</div>