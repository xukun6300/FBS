package org.apache.jsp.WEB_002dINF.jsp.layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/tags/security.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fusername_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fspring_005furl_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fusername_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fspring_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fsecurity_005fusername_005fnobody.release();
    _005fjspx_005ftagPool_005fspring_005furl_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"navbar\">\r\n");
      out.write("  <div class=\"navbar-inner\">\r\n");
      out.write("\t<div class=\"container_new\">\r\n");
      out.write("\t  <!-- <a class=\"brand\" href=\"#\">Biz</a> -->\r\n");
      out.write("\t  <div class=\"btn-group pull-right\">\r\n");
      out.write("\t\t<a class=\"btn btn-info dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">\r\n");
      out.write("\t\t  <i class=\"icon-user icon-white\"></i>");
      if (_jspx_meth_security_005fusername_005f0(_jspx_page_context))
        return;
      out.write("   <!-- Display User Name -->\r\n");
      out.write("\t\t  <span class=\"caret\"></span>\r\n");
      out.write("\t\t</a>\r\n");
      out.write("\t\t<ul class=\"dropdown-menu\">\r\n");
      out.write("          <li><a href=\"#\">Manage Profile</a></li> \r\n");
      out.write("\t\t  <li class=\"divider\"></li>\t\t  \r\n");
      out.write("\t\t  <li><a href=\"");
      if (_jspx_meth_spring_005furl_005f0(_jspx_page_context))
        return;
      out.write("\">Sign Out</a></li>\r\n");
      out.write("\t\t </ul>\r\n");
      out.write("\t  </div>\r\n");
      out.write("\t  <!-- menu start -->\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<ul class=\"nav nav-pills\">\r\n");
      out.write("\t\t      <li role=\"presentation\"><a href=\"#\">Home</a></li>\r\n");
      out.write("\t\t      <li role=\"presentation\" class=\"dropdown\">\r\n");
      out.write("\t\t        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\" role=\"button\" aria-expanded=\"true\">\r\n");
      out.write("\t\t          User Management <span class=\"caret\"></span>\r\n");
      out.write("\t\t        </a>\r\n");
      out.write("\t\t        <ul class=\"dropdown-menu\" role=\"menu\">\r\n");
      out.write("\t\t          <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/useraccountmanagement/showRegisterUser.action\">Register New User</a></li>\r\n");
      out.write("\t\t          <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/useraccountmanagement/searchUser.action\">Search User</a></li>\t\t          \r\n");
      out.write("\t\t        </ul>\r\n");
      out.write("\t\t      </li>\r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t      <li role=\"presentation\" class=\"dropdown\">\r\n");
      out.write("\t\t        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\" role=\"button\" aria-expanded=\"true\">\r\n");
      out.write("\t\t          Account Management <span class=\"caret\"></span>\r\n");
      out.write("\t\t        </a>\r\n");
      out.write("\t\t        <ul class=\"dropdown-menu\" role=\"menu\">\r\n");
      out.write("\t\t          <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/account/showAddAccount.action\">Create New Account</a></li>\r\n");
      out.write("\t\t          <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/account/searchAccount.action\">Search Account</a></li>\t\t          \r\n");
      out.write("\t\t        </ul>\r\n");
      out.write("\t\t      </li>  \r\n");
      out.write("\t\t         \r\n");
      out.write("\t\t       <li role=\"presentation\" class=\"dropdown\">\r\n");
      out.write("\t\t        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\" role=\"button\" aria-expanded=\"true\">\r\n");
      out.write("\t\t          System Configuration <span class=\"caret\"></span>\r\n");
      out.write("\t\t        </a>\r\n");
      out.write("\t\t        <ul class=\"dropdown-menu\" role=\"menu\">\r\n");
      out.write("\t\t          <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/mastercode/showAddCodeKey.action\">Add Code Key</a></li>\t\t          \r\n");
      out.write("\t\t        </ul>\r\n");
      out.write("\t\t      </li>  \r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t      <li role=\"presentation\" class=\"dropdown\">\r\n");
      out.write("\t\t        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\" role=\"button\" aria-expanded=\"true\">\r\n");
      out.write("\t\t          Menu4 <span class=\"caret\"></span>\r\n");
      out.write("\t\t        </a>\r\n");
      out.write("\t\t        <ul class=\"dropdown-menu\" role=\"menu\">\r\n");
      out.write("\t\t          <li><a href=\"#\">Action</a></li>\r\n");
      out.write("\t\t          <li><a href=\"#\">Another action</a></li>\r\n");
      out.write("\t\t          <li><a href=\"#\">Something else here</a></li>\r\n");
      out.write("\t\t        <!--   <li class=\"divider\"></li> -->\r\n");
      out.write("\t\t          <li><a href=\"#\">Separated link</a></li>\r\n");
      out.write("\t\t        </ul>\r\n");
      out.write("\t\t      </li>    \r\n");
      out.write("\t\t    </ul>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t<!-- menu end -->\r\n");
      out.write("\t</div>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /WEB-INF/jsp/layout/menu.jsp(8,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("contextPath");
    // /WEB-INF/jsp/layout/menu.jsp(8,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_security_005fusername_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:username
    sg.com.fbs.core.techinfra.web.tag.security.acl.UserInfoTag _jspx_th_security_005fusername_005f0 = (sg.com.fbs.core.techinfra.web.tag.security.acl.UserInfoTag) _005fjspx_005ftagPool_005fsecurity_005fusername_005fnobody.get(sg.com.fbs.core.techinfra.web.tag.security.acl.UserInfoTag.class);
    _jspx_th_security_005fusername_005f0.setPageContext(_jspx_page_context);
    _jspx_th_security_005fusername_005f0.setParent(null);
    int _jspx_eval_security_005fusername_005f0 = _jspx_th_security_005fusername_005f0.doStartTag();
    if (_jspx_th_security_005fusername_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fusername_005fnobody.reuse(_jspx_th_security_005fusername_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fusername_005fnobody.reuse(_jspx_th_security_005fusername_005f0);
    return false;
  }

  private boolean _jspx_meth_spring_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  spring:url
    org.springframework.web.servlet.tags.UrlTag _jspx_th_spring_005furl_005f0 = (org.springframework.web.servlet.tags.UrlTag) _005fjspx_005ftagPool_005fspring_005furl_0026_005fvalue_005fnobody.get(org.springframework.web.servlet.tags.UrlTag.class);
    _jspx_th_spring_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_spring_005furl_005f0.setParent(null);
    // /WEB-INF/jsp/layout/menu.jsp(22,17) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005furl_005f0.setValue("/authentication/logout.action");
    int[] _jspx_push_body_count_spring_005furl_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_spring_005furl_005f0 = _jspx_th_spring_005furl_005f0.doStartTag();
      if (_jspx_th_spring_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_005furl_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_005furl_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_005furl_005f0.doFinally();
      _005fjspx_005ftagPool_005fspring_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_spring_005furl_005f0);
    }
    return false;
  }
}
