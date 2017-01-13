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

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("<script src=\"//code.jquery.com/jquery-1.11.0.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/common.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/pbkdf2.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/ajax.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/login.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/jsbn.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/prng4.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/rng.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/static/internal/js/ana/rsa.js\"></script>\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css\">\n");
      out.write("<!-- <link rel=\"stylesheet\" href=\"http://bootsnipp.com/dist/bootsnipp.min.css?ver=70eabcd8097cd299e1ba8efe436992b7\"> -->\n");
      out.write("<style>\n");
      out.write("#login {\n");
      out.write("    padding-top: 50px\n");
      out.write("}\n");
      out.write("#login .form-wrap {\n");
      out.write("    width: 30%;\n");
      out.write("    margin: 150px auto 0 auto;\n");
      out.write("}\n");
      out.write("#login h1 {\n");
      out.write("    color: #1fa67b;\n");
      out.write("    font-size: 20px;\n");
      out.write("    text-align: center;\n");
      out.write("    font-weight: bold;\n");
      out.write("    padding-bottom: 20px;\n");
      out.write("}\n");
      out.write("#login .form-group {\n");
      out.write("    margin-bottom: 25px;\n");
      out.write("}\n");
      out.write("#login .checkbox {\n");
      out.write("    margin-bottom: 20px;\n");
      out.write("    position: relative;\n");
      out.write("    -webkit-user-select: none;\n");
      out.write("    -moz-user-select: none;\n");
      out.write("    -ms-user-select: none;\n");
      out.write("    -o-user-select: none;\n");
      out.write("    user-select: none;\n");
      out.write("}\n");
      out.write("#login .checkbox.show:before {\n");
      out.write("    content: '\\e013';\n");
      out.write("    color: #1fa67b;\n");
      out.write("    font-size: 17px;\n");
      out.write("    margin: 1px 0 0 3px;\n");
      out.write("    position: absolute;\n");
      out.write("    pointer-events: none;\n");
      out.write("    font-family: 'Glyphicons Halflings';\n");
      out.write("}\n");
      out.write("#login .checkbox .character-checkbox {\n");
      out.write("    width: 25px;\n");
      out.write("    height: 25px;\n");
      out.write("    cursor: pointer;\n");
      out.write("    border-radius: 3px;\n");
      out.write("    border: 1px solid #ccc;\n");
      out.write("    vertical-align: middle;\n");
      out.write("    display: inline-block;\n");
      out.write("}\n");
      out.write("#login .checkbox .label {\n");
      out.write("    color: #6d6d6d;\n");
      out.write("    font-size: 13px;\n");
      out.write("    font-weight: normal;\n");
      out.write("}\n");
      out.write("#login .btn.btn-custom {\n");
      out.write("    font-size: 14px;\n");
      out.write("\tmargin-bottom: 20px;\n");
      out.write("}\n");
      out.write("#login .forget {\n");
      out.write("    font-size: 13px;\n");
      out.write("\ttext-align: center;\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".form-control {\n");
      out.write("    color: #212121;\n");
      out.write("}\n");
      out.write(".btn-custom {\n");
      out.write("    color: #fff;\n");
      out.write("\tbackground-color: #1fa67b;\n");
      out.write("}\n");
      out.write(".btn-custom:hover,\n");
      out.write(".btn-custom:focus {\n");
      out.write("    color: #fff;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#footer {\n");
      out.write("    color: #6d6d6d;\n");
      out.write("    font-size: 12px;\n");
      out.write("    text-align: center;\n");
      out.write("}\n");
      out.write("#footer p {\n");
      out.write("    margin-bottom: 0;\n");
      out.write("}\n");
      out.write("#footer a {\n");
      out.write("    color: inherit;\n");
      out.write("}\n");
      out.write("\n");
      out.write("</style>\n");
      out.write("\n");
      out.write("<form name=\"submitForm\" method=\"post\" action=\"authenticate.action\">\n");
      out.write("   <input name=\"u\" type=\"hidden\"/>\n");
      out.write("   <input name=\"f\" type=\"hidden\"/>\n");
      out.write("</form> \n");
      out.write("\n");
      out.write("\t   \n");
      out.write("<section id=\"login\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("    \t<div class=\"row\">\n");
      out.write("    \t    <div class=\"col-xs-12\">\n");
      out.write("        \t    <div class=\"form-wrap\">\n");
      out.write("                <h1>Log in FBS with your email account</h1>\n");
      out.write("                    <form name=\"login\" id=\"login\" method=\"post\" action=\"authenticate.action\" autocomplete=\"off\">\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"userId\" class=\"sr-only\">Email</label>\n");
      out.write("                            <input type=\"email\" name=\"userId\" id=\"userId\" class=\"form-control\" placeholder=\"somebody@example.com\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"password\" class=\"sr-only\">Password</label>\n");
      out.write("                            <input type=\"password\" name=\"password\" id=\"password\" class=\"form-control\" placeholder=\"Password\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"checkbox\">\n");
      out.write("                            <span class=\"character-checkbox\" onclick=\"showPassword()\"></span>\n");
      out.write("                            <span class=\"label\">Show password</span>\n");
      out.write("                        </div>\n");
      out.write("                        <input class=\"btn btn-custom btn-lg btn-block\" id=\"login2\" name=\"login2\" type=\"button\" value=\"Log in\"  onclick=\"fidLogin(true)\"> \n");
      out.write("                        <!-- <input type=\"submit\" id=\"btn-login\" class=\"btn btn-custom btn-lg btn-block\" value=\"Log in\"> -->\n");
      out.write("                        <img alt=\"spinner\" id=\"spinner\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/spinner.gif\" style=\"display: none;\" />\n");
      out.write("                    </form>\n");
      out.write("                    <a href=\"javascript:;\" class=\"forget\" data-toggle=\"modal\" data-target=\".forget-modal\">Forgot your password?</a>\n");
      out.write("                    <hr>\n");
      out.write("        \t    </div>\n");
      out.write("    \t\t</div> \n");
      out.write("    \t</div> \n");
      out.write("    </div> \n");
      out.write("</section>\n");
      out.write("\n");
      out.write("<!-- <div class=\"modal fade forget-modal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myForgetModalLabel\" aria-hidden=\"true\">\n");
      out.write("\t<div class=\"modal-dialog modal-sm\">\n");
      out.write("\t\t<div class=\"modal-content\">\n");
      out.write("\t\t\t<div class=\"modal-header\">\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\">\n");
      out.write("\t\t\t\t\t<span aria-hidden=\"true\">×</span>\n");
      out.write("\t\t\t\t\t<span class=\"sr-only\">Close</span>\n");
      out.write("\t\t\t\t</button>\n");
      out.write("\t\t\t\t<h4 class=\"modal-title\">Recovery password</h4>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"modal-body\">\n");
      out.write("\t\t\t\t<p>Type your email account</p>\n");
      out.write("\t\t\t\t<input type=\"email\" name=\"recovery-email\" id=\"recovery-email\" class=\"form-control\" autocomplete=\"off\">\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"modal-footer\">\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Cancel</button>\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-custom\">Recovery</button>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div> \n");
      out.write("</div>  -->\n");
      out.write("\n");
      out.write("<footer id=\"footer\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-xs-12\">\n");
      out.write("                <p>Page © - 2015</p>\n");
      out.write("                <p>Powered by <strong><a href=\"https://sg.linkedin.com/pub/frank-xu-kun/63/62a/314\" target=\"_blank\">Frank</a></strong></p>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</footer>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
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
}
