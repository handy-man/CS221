/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.35
 * Generated at: 2013-01-30 16:02:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>MonsterMash - Game</title>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("        <meta name=\"description\" content=\"Monster Mash online Game\" />\r\n");
      out.write("        <meta name=\"keywords\" content=\"Game, Monster, Mash, on-line, gaming, fights\" />\r\n");
      out.write("        <meta name=\"author\" content=\"Group_07\" />\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\" media=\"screen\" />\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\t\r\n");
      out.write("        <div id=\"login-page-banner\">\r\n");
      out.write("        </div>\r\n");
      out.write("        <div id=\"login-container\">\r\n");
      out.write("            <form action=\"Login_servlet\" method=\"post\" >\r\n");
      out.write("                <h1 class=\"login-page-header\">Log in:</h1>\r\n");
      out.write("                <hr />\r\n");
      out.write("                <input class=\"textfield\" value=\"Email\" type=\"text\" name=\"email\" maxlength=\"40\" onclick=\"this.value=''\"/>\r\n");
      out.write("                <input class=\"textfield\" value=\"Password\" type=\"text\" name=\"password\" maxlength=\"40\" onclick=\"this.value=''\"/>\r\n");
      out.write("                <button class=\"button\">Login</button>\r\n");
      out.write("                <p class=\"error\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</p>\r\n");
      out.write("            </form>\r\n");
      out.write("        </div>     \r\n");
      out.write("        <div id=\"register-container\">\r\n");
      out.write("            <form action=\"registration.jsp\" method=\"post\" >\r\n");
      out.write("                <h1 class=\"login-page-header\">Register:</h1>\r\n");
      out.write("                <hr /><br /><br />\r\n");
      out.write("                <button class=\"button\">Register</button>\r\n");
      out.write("            </form>\r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\t\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
