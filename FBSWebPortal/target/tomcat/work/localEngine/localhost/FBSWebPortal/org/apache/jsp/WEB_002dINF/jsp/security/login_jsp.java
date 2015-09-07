package org.apache.jsp.WEB_002dINF.jsp.security;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/WEB-INF/tags/netui.tld");
    _jspx_dependants.add("/WEB-INF/tags/captcha.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
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
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<script src=\"//code.jquery.com/jquery-1.11.0.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/common.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/pbkdf2.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/ajax.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/login.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/jsbn.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/prng4.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/rng.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/rsa.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css\">\r\n");
      out.write("<!-- <link rel=\"stylesheet\" href=\"http://bootsnipp.com/dist/bootsnipp.min.css?ver=70eabcd8097cd299e1ba8efe436992b7\"> -->\r\n");
      out.write("<style>\r\n");
      out.write("/*    --------------------------------------------------\r\n");
      out.write("\t:: Login Section\r\n");
      out.write("\t-------------------------------------------------- */\r\n");
      out.write("#login {\r\n");
      out.write("    padding-top: 50px\r\n");
      out.write("}\r\n");
      out.write("#login .form-wrap {\r\n");
      out.write("    width: 30%;\r\n");
      out.write("    margin: 150px auto 0 auto;\r\n");
      out.write("}\r\n");
      out.write("#login h1 {\r\n");
      out.write("    color: #1fa67b;\r\n");
      out.write("    font-size: 18px;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("    font-weight: bold;\r\n");
      out.write("    padding-bottom: 20px;\r\n");
      out.write("}\r\n");
      out.write("#login .form-group {\r\n");
      out.write("    margin-bottom: 25px;\r\n");
      out.write("}\r\n");
      out.write("#login .checkbox {\r\n");
      out.write("    margin-bottom: 20px;\r\n");
      out.write("    position: relative;\r\n");
      out.write("    -webkit-user-select: none;\r\n");
      out.write("    -moz-user-select: none;\r\n");
      out.write("    -ms-user-select: none;\r\n");
      out.write("    -o-user-select: none;\r\n");
      out.write("    user-select: none;\r\n");
      out.write("}\r\n");
      out.write("#login .checkbox.show:before {\r\n");
      out.write("    content: '\\e013';\r\n");
      out.write("    color: #1fa67b;\r\n");
      out.write("    font-size: 17px;\r\n");
      out.write("    margin: 1px 0 0 3px;\r\n");
      out.write("    position: absolute;\r\n");
      out.write("    pointer-events: none;\r\n");
      out.write("    font-family: 'Glyphicons Halflings';\r\n");
      out.write("}\r\n");
      out.write("#login .checkbox .character-checkbox {\r\n");
      out.write("    width: 25px;\r\n");
      out.write("    height: 25px;\r\n");
      out.write("    cursor: pointer;\r\n");
      out.write("    border-radius: 3px;\r\n");
      out.write("    border: 1px solid #ccc;\r\n");
      out.write("    vertical-align: middle;\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("}\r\n");
      out.write("#login .checkbox .label {\r\n");
      out.write("    color: #6d6d6d;\r\n");
      out.write("    font-size: 13px;\r\n");
      out.write("    font-weight: normal;\r\n");
      out.write("}\r\n");
      out.write("#login .btn.btn-custom {\r\n");
      out.write("    font-size: 14px;\r\n");
      out.write("\tmargin-bottom: 20px;\r\n");
      out.write("}\r\n");
      out.write("#login .forget {\r\n");
      out.write("    font-size: 13px;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/*    --------------------------------------------------\r\n");
      out.write("\t:: Inputs & Buttons\r\n");
      out.write("\t-------------------------------------------------- */\r\n");
      out.write(".form-control {\r\n");
      out.write("    color: #212121;\r\n");
      out.write("}\r\n");
      out.write(".btn-custom {\r\n");
      out.write("    color: #fff;\r\n");
      out.write("\tbackground-color: #1fa67b;\r\n");
      out.write("}\r\n");
      out.write(".btn-custom:hover,\r\n");
      out.write(".btn-custom:focus {\r\n");
      out.write("    color: #fff;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/*    --------------------------------------------------\r\n");
      out.write("    :: Footer\r\n");
      out.write("\t-------------------------------------------------- */\r\n");
      out.write("#footer {\r\n");
      out.write("    color: #6d6d6d;\r\n");
      out.write("    font-size: 12px;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("}\r\n");
      out.write("#footer p {\r\n");
      out.write("    margin-bottom: 0;\r\n");
      out.write("}\r\n");
      out.write("#footer a {\r\n");
      out.write("    color: inherit;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("\r\n");


	if(request.getUserPrincipal() !=null)
	{
		
      out.write("\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\twindow.top.location.href = '");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("';\r\n");
      out.write("\t\t</script> \r\n");
	
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<form name=\"submitForm\" method=\"post\" action=\"authenticate.action\">\r\n");
      out.write("   <input name=\"u\" type=\"hidden\"/>\r\n");
      out.write("   <input name=\"f\" type=\"hidden\"/>\r\n");
      out.write("<!--    <input name=\"at\" type=\"hidden\"/>\r\n");
      out.write("   <input name=\"bt\" type=\"hidden\"/>\r\n");
      out.write("   <input name=\"en\" type=\"hidden\"/>\r\n");
      out.write("   <input name=\"pv\" type=\"hidden\"/> -->\r\n");
      out.write("</form> \r\n");
      out.write("\r\n");
      out.write("\t   \r\n");
      out.write("<section id=\"login\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("    \t<div class=\"row\">\r\n");
      out.write("    \t    <div class=\"col-xs-12\">\r\n");
      out.write("        \t    <div class=\"form-wrap\">\r\n");
      out.write("                <h1>Log in FBS with your email account</h1>\r\n");
      out.write("                    <form name=\"login\" id=\"login\" method=\"post\" action=\"authenticate.action\" autocomplete=\"off\">\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"userId\" class=\"sr-only\">Email</label>\r\n");
      out.write("                            <input type=\"email\" name=\"userId\" id=\"userId\" class=\"form-control\" placeholder=\"somebody@example.com\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"password\" class=\"sr-only\">Password</label>\r\n");
      out.write("                            <input type=\"password\" name=\"password\" id=\"password\" class=\"form-control\" placeholder=\"Password\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"checkbox\">\r\n");
      out.write("                            <span class=\"character-checkbox\" onclick=\"showPassword()\"></span>\r\n");
      out.write("                            <span class=\"label\">Show password</span>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <input class=\"btn btn-custom btn-lg btn-block\" id=\"login2\" name=\"login2\" type=\"button\" value=\"Log in\"  onclick=\"fidLogin(true)\"> \r\n");
      out.write("                        <!-- <input type=\"submit\" id=\"btn-login\" class=\"btn btn-custom btn-lg btn-block\" value=\"Log in\"> -->\r\n");
      out.write("                        <img alt=\"spinner\" id=\"spinner\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/spinner.gif\" style=\"display: none;\" />\r\n");
      out.write("                    </form>\r\n");
      out.write("                    <a href=\"javascript:;\" class=\"forget\" data-toggle=\"modal\" data-target=\".forget-modal\">Forgot your password?</a>\r\n");
      out.write("                    <hr>\r\n");
      out.write("        \t    </div>\r\n");
      out.write("    \t\t</div> \r\n");
      out.write("    \t</div> \r\n");
      out.write("    </div> \r\n");
      out.write("</section>\r\n");
      out.write("\r\n");
      out.write("<!-- <div class=\"modal fade forget-modal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myForgetModalLabel\" aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog modal-sm\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\">\r\n");
      out.write("\t\t\t\t\t<span aria-hidden=\"true\">×</span>\r\n");
      out.write("\t\t\t\t\t<span class=\"sr-only\">Close</span>\r\n");
      out.write("\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t<h4 class=\"modal-title\">Recovery password</h4>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t<p>Type your email account</p>\r\n");
      out.write("\t\t\t\t<input type=\"email\" name=\"recovery-email\" id=\"recovery-email\" class=\"form-control\" autocomplete=\"off\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Cancel</button>\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-custom\">Recovery</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div> \r\n");
      out.write("</div>  -->\r\n");
      out.write("\r\n");
      out.write("<footer id=\"footer\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <div class=\"col-xs-12\">\r\n");
      out.write("                <p>Page © - 2015</p>\r\n");
      out.write("                <p>Powered by <strong><a href=\"https://sg.linkedin.com/pub/frank-xu-kun/63/62a/314\" target=\"_blank\">Frank</a></strong></p>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</footer>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
    // /WEB-INF/jsp/security/login.jsp(10,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("contextPath");
    // /WEB-INF/jsp/security/login.jsp(10,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent(null);
    // /WEB-INF/jsp/security/login.jsp(126,31) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("/authentication/showSessionExpired.action");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }
}
