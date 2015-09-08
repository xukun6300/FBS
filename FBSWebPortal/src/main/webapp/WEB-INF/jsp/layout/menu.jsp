<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="/tags/security" %>
<%-- <%@ taglib prefix="netui" uri="/tags/netui" %>
<%@ taglib prefix="menu" uri="/tags/menu" %>
<%@ taglib prefix="sec" uri="/tags/securityACL" %>
<%@ taglib prefix="security" uri="/tags/security" %> --%>



<div class="navbar">
  <div class="navbar-inner">
	<div class="container_new">
	  <!-- <a class="brand" href="#">Biz</a> -->
	  <div class="btn-group pull-right">
		<a class="btn btn-info dropdown-toggle" data-toggle="dropdown" href="#">
		  <i class="icon-user icon-white"></i><security:username/>
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
		      <li role="presentation"><a href="#">Help</a></li>
		      <li role="presentation" class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="true">
		          Menu1 <span class="caret"></span>
		        </a>
		        <ul class="dropdown-menu" role="menu">
		          <li><a href="#">Action</a></li>
		          <li><a href="#">Another action</a></li>
		          <li><a href="#">Something else here</a></li>
		        <!--   <li class="divider"></li> -->
		          <li><a href="#">Separated link</a></li>
		        </ul>
		      </li>
		      
		      <li role="presentation" class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="true">
		          Menu2 <span class="caret"></span>
		        </a>
		        <ul class="dropdown-menu" role="menu">
		          <li><a href="#">Action</a></li>
		          <li><a href="#">Another action</a></li>
		          <li><a href="#">Something else here</a></li>
		        <!--   <li class="divider"></li> -->
		          <li><a href="#">Separated link</a></li>
		        </ul>
		      </li>  
		         
		       <li role="presentation" class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="true">
		          Menu3 <span class="caret"></span>
		        </a>
		        <ul class="dropdown-menu" role="menu">
		          <li><a href="#">Action</a></li>
		          <li><a href="#">Another action</a></li>
		          <li><a href="#">Something else here</a></li>
		        <!--   <li class="divider"></li> -->
		          <li><a href="#">Separated link</a></li>
		        </ul>
		      </li>  
		      
		      <li role="presentation" class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="true">
		          Menu4 <span class="caret"></span>
		        </a>
		        <ul class="dropdown-menu" role="menu">
		          <li><a href="#">Action</a></li>
		          <li><a href="#">Another action</a></li>
		          <li><a href="#">Something else here</a></li>
		        <!--   <li class="divider"></li> -->
		          <li><a href="#">Separated link</a></li>
		        </ul>
		      </li>    
		    </ul>
			
	<!-- menu end -->
	</div>
  </div>
</div>