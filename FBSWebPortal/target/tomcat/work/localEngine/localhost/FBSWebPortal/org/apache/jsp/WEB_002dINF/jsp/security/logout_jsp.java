package org.apache.jsp.WEB_002dINF.jsp.security;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class logout_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fspring_005furl_0026_005fvalue_005fhtmlEscape_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fspring_005furl_0026_005fvalue_005fhtmlEscape_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fspring_005furl_0026_005fvalue_005fhtmlEscape_005fnobody.release();
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
      out.write("<!-- <link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css\"> -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\n");
      out.write("table.gridtable {\r\n");
      out.write("\tfont-family: arila,sans-serif;\r\n");
      out.write("\tcolor:#333333;\r\n");
      out.write("\tborder-width: 1px;\r\n");
      out.write("\tborder-color: #DEDEDE;\r\n");
      out.write("\tborder-collapse: collapse;\r\n");
      out.write("}\r\n");
      out.write("table.gridtable th {\r\n");
      out.write("\tborder-width: 1px;\r\n");
      out.write("\tpadding: 8px;\r\n");
      out.write("\tborder-style: solid;\r\n");
      out.write("\tborder-color: #DEDEDE;\r\n");
      out.write("\tbackground-color: #dedede;\r\n");
      out.write("}\r\n");
      out.write("table.gridtable td {\r\n");
      out.write("\tborder-width: 1px;\r\n");
      out.write("\tpadding: 8px;\r\n");
      out.write("\tborder-style: solid;\r\n");
      out.write("\tborder-color: #DEDEDE;\r\n");
      out.write("\tbackground-color: #ffffff;\r\n");
      out.write("}\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div align=\"center\">\r\n");
      out.write("\t<table cellpadding=\"0\" cellsapcing=\"0\" width=\"100%\" border=0>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<div class=\"success\">Logged out successfully.</div>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<a href=\"");
      if (_jspx_meth_spring_005furl_005f0(_jspx_page_context))
        return;
      out.write("\"><h3>Go To Login</h3></a>\r\n");
      out.write("</div>\r\n");
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

  private boolean _jspx_meth_spring_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  spring:url
    org.springframework.web.servlet.tags.UrlTag _jspx_th_spring_005furl_005f0 = (org.springframework.web.servlet.tags.UrlTag) _005fjspx_005ftagPool_005fspring_005furl_0026_005fvalue_005fhtmlEscape_005fnobody.get(org.springframework.web.servlet.tags.UrlTag.class);
    _jspx_th_spring_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_spring_005furl_005f0.setParent(null);
    // /WEB-INF/jsp/security/logout.jsp(48,10) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005furl_005f0.setValue("/authentication/showLogin.action");
    // /WEB-INF/jsp/security/logout.jsp(48,10) name = htmlEscape type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005furl_005f0.setHtmlEscape(true);
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
      _005fjspx_005ftagPool_005fspring_005furl_0026_005fvalue_005fhtmlEscape_005fnobody.reuse(_jspx_th_spring_005furl_005f0);
    }
    return false;
  }
}
