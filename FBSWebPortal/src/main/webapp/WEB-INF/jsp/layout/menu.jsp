<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="/tags/security" %>


<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<div class="navbar">
  <div class="navbar-inner">
	<div class="container_new">
	  <!-- <a class="brand" href="#">Biz</a> -->
	  <div class="btn-group pull-right">
		<a class="btn btn-info dropdown-toggle" data-toggle="dropdown" href="#" style="margin-top:-4px;">
		  <i class="icon-user icon-white"></i><security:username/>   <!-- Display User Name -->
		  <span class="caret"></span>
		</a>
		<ul class="dropdown-menu">
          <li><a href="#">Manage Profile</a></li> 
		  <li class="divider"></li>		  
		  <li><a href="<spring:url value="/authentication/logout.action"/>">Sign Out</a></li>
		 </ul>
	  </div>
	  <!-- menu start -->
			
			<ul class="nav nav-pills">
		      <li role="presentation"><a href="#">Home</a></li>
		      <li role="presentation" class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="true">
		          User Management <span class="caret"></span>
		        </a>
		        <ul class="dropdown-menu" role="menu">
		          <li><a href="${contextPath}/useraccountmanagement/showRegisterUser.action">Register New User</a></li>
		          <li><a href="${contextPath}/useraccountmanagement/searchUser.action">Search User</a></li>		          
		        </ul>
		      </li>
		      
		      <li role="presentation" class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="true">
		          Account Management <span class="caret"></span>
		        </a>
		        <ul class="dropdown-menu" role="menu">
		          <li><a href="${contextPath}/account/showAddAccount.action">Create New Account</a></li>
		          <li><a href="${contextPath}/account/searchAccount.action">Search Account</a></li>		          
		        </ul>
		      </li>  
		         
		       <li role="presentation" class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="true">
		          System Configuration <span class="caret"></span>
		        </a>
		        <ul class="dropdown-menu" role="menu">
		          <li><a href="${contextPath}/mastercode/searchCategoryType.action">Manage Code Key</a></li>		          
		        </ul>
		      </li>  
		      
		      <li role="presentation" class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="true">
		          Budget Configuration <span class="caret"></span>
		        </a>
		        <ul class="dropdown-menu" role="menu">
		          <li><a href="${contextPath}/budgetconfig/showConfigNewBudgeting.action">Config New Budget</a></li>
		          <li><a href="${contextPath}/budgetconfig/showConfigWorkingBudget.action">Config Working Budget</a></li>		  
		        </ul>
		      </li>    
		      
		      <li role="presentation" class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="true">
		          Budgeting <span class="caret"></span>
		        </a>
		        <ul class="dropdown-menu" role="menu">
		          <li><a href="${contextPath}/budgeting/viewPlanBudget.action">Plan Budget</a></li>	  
		        </ul>
		      </li>
		    </ul>
			
	<!-- menu end -->
	</div>
  </div>
</div>