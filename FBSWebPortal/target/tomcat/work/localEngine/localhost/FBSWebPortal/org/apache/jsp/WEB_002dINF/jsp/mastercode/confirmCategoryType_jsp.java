package org.apache.jsp.WEB_002dINF.jsp.mastercode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class confirmCategoryType_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/tags/netui.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fjoda_005fformat_0026_005fvalue_005fpattern_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fjoda_005fformat_0026_005fvalue_005fpattern_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fjoda_005fformat_0026_005fvalue_005fpattern_005fnobody.release();
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
      out.write("\r\n");
      out.write("<div class=\"container-fluid\">\r\n");
      out.write("\t<div class=\"row-fluid\">\r\n");
      out.write("\t\t<div class=\"block-center span12\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"alert alert-success\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>\r\n");
      out.write("\t\t\t\t<h4>Success!</h4>\r\n");
      out.write("\t\t\t\t<p>Code Key has been saved successfully!</p>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"well clearfix form-horizontal\">\r\n");
      out.write("\t\t\t\t<legend>Code Key Details</legend>\r\n");
      out.write("\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\" style=\"width:50px;font-weight:bold\">Name:</label> \r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\" style=\"width:100px;text-align:left;padding-left:20px\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${command.remarks}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</label>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\" style=\"width:50px;font-weight:bold\">Code:</label> \r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\" style=\"width:100px;text-align:left;padding-left:20px\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${command.codeKey}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</label>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\" style=\"width:50px;font-weight:bold\">Effective Date:</label> \r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\" style=\"width:100px;text-align:left;padding-left:20px\">");
      if (_jspx_meth_joda_005fformat_005f0(_jspx_page_context))
        return;
      out.write("  </label>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("            <button id=\"btnAdd\" name=\"btnAdd\" class=\"bt bt1\" onClick=\"javascript:location.href='showAddCodeKey.action'\" type=\"button\">Add Another Code Key</button> \r\n");
      out.write("            <button id=\"btnAddValues\" name=\"btnAddValues\" class=\"bt bt1\" onClick=\"javascript:location.href='showAddCodeValue.action?codeKey=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${command.codeKey}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("'\" type=\"button\">Add Code Values</button>\r\n");
      out.write("            <button id=\"btnBack\" name=\"btnBack\" class=\"bt bt-back\" onClick=\"javascript:history.back()\" type=\"button\">Back</button>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
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

  private boolean _jspx_meth_joda_005fformat_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  joda:format
    org.joda.time.contrib.jsptag.FormatTag _jspx_th_joda_005fformat_005f0 = (org.joda.time.contrib.jsptag.FormatTag) _005fjspx_005ftagPool_005fjoda_005fformat_0026_005fvalue_005fpattern_005fnobody.get(org.joda.time.contrib.jsptag.FormatTag.class);
    _jspx_th_joda_005fformat_005f0.setPageContext(_jspx_page_context);
    _jspx_th_joda_005fformat_005f0.setParent(null);
    // /WEB-INF/jsp/mastercode/confirmCategoryType.jsp(33,88) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_joda_005fformat_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${command.effectiveDate}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/mastercode/confirmCategoryType.jsp(33,88) name = pattern type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_joda_005fformat_005f0.setPattern("dd MMM yyyy");
    int _jspx_eval_joda_005fformat_005f0 = _jspx_th_joda_005fformat_005f0.doStartTag();
    if (_jspx_th_joda_005fformat_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjoda_005fformat_0026_005fvalue_005fpattern_005fnobody.reuse(_jspx_th_joda_005fformat_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fjoda_005fformat_0026_005fvalue_005fpattern_005fnobody.reuse(_jspx_th_joda_005fformat_005f0);
    return false;
  }
}
