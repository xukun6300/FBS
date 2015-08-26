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
      out.write("\r\n");
      out.write("\r\n");
      out.write("  <div id=\"popupBottom\" class=\"popover bottom in\" aria-hidden=\"false\" style=\"top: 100px; left:10px;max-width:345px;max-height:250px; display: block; z-index: 1050; \">\r\n");
      out.write("        <div class=\"arrow\"></div>     \r\n");
      out.write("        <a class=\"close\" data-dismiss=\"modal\" style=\"margin-right:5px\">x</a>\r\n");
      out.write("        <div class=\"popover-content\" id=\"popover-content\"  style=\"width:340px;\" >\r\n");
      out.write("           <div class=\"log_frnt\"  style=\"width:340px;\" > </div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("        \t  \t<form method=\"POST\" name=\"login\" id=\"login\" action=\"authenticate.action\"  style=\"width:340px;\" autocomplete=\"off\">\r\n");
      out.write("                   <input class=\"input-medium\"  name=\"userId\" id=\"userId\" type=\"text\"  placeholder=\"Your Email\" size=\"200\" maxlength=\"50\" >\r\n");
      out.write("                   <input class=\"input-medium\"  name=\"password\" id=\"password\" type=\"password\"  placeholder=\"Your Password\" onkeypress=\"handleKeyPress(event,0)\"  size=\"200\" maxlength=\"24\" >\r\n");
      out.write("                    <div id=\"statusbar\" style=\"display:none;width:320px;\" class=\"alert-error errormsg\" ></div>\r\n");
      out.write("                    <div id=\"divCapsLock0\" style=\"display:none;border-radius:3px;padding-left: 2px;width: 215px;\" class=\"alert-error errormsg\">\r\n");
      out.write("\t\t\t\t\t\t<span>Caps Lock ON!</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("                   <span>By clicking on the login button, you agree to the Terms of Use.</span>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("                   <input class=\"btn_blk\" id=\"login2\" name=\"login2\" type=\"button\" value=\"Login\"  onclick=\"fidLogin(true)\"> <br>\r\n");
      out.write("                   <a class=\"links\"  href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/passwordmanagement/showResetUserAccountPassword.action\"> Forgot Password </a>  <a class=\"links\"  href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/useraccountmanagement/registerPublicUser.action\"> Request User Account </a>\r\n");
      out.write("                   <input name=\"mod\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${modulus}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t\t\t   <input name=\"exp\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${exponent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t\t\t   <img alt=\"spinner\" id=\"spinner\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/spinner.gif\" style=\"display: none;\" />   \r\n");
      out.write("\t\t\t   </form>\r\n");
      out.write("\t\t\t  \r\n");
      out.write("        </div>\r\n");
      out.write("    </div>  \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
    _jspx_th_c_005fset_005f0.setVar("contectPath");
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
