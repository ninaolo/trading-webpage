/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.32
 * Generated at: 2016-02-27 16:15:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;

public final class trading_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write("<html>\n");
      out.write("<head><title>Exempel 3</title></head>\n");
      out.write("<body>\n");
      out.write("<center>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      bean.Forum forum = null;
      synchronized (application) {
        forum = (bean.Forum) _jspx_page_context.getAttribute("forum", javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        if (forum == null){
          forum = new bean.Forum();
          _jspx_page_context.setAttribute("forum", forum, javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        }
      }
      out.write('\n');
      bean.User user = null;
      synchronized (session) {
        user = (bean.User) _jspx_page_context.getAttribute("user", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (user == null){
          user = new bean.User();
          _jspx_page_context.setAttribute("user", user, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }
      out.write('\n');
      bean.Post post = null;
      post = (bean.Post) _jspx_page_context.getAttribute("post", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (post == null){
        post = new bean.Post();
        _jspx_page_context.setAttribute("post", post, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("forum"), request);
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("user"), request);
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("post"), request);
      out.write('\n');
      out.write('\n');
 if(session.isNew()){ 
      out.write("\n");
      out.write("<h1>Ny session!</h1>\n");
      out.write("<form>\n");
      out.write("Nickname<input type=\"text\" name=\"nickname\"><br>\n");
      out.write("Email<input type=\"text\" name=\"email\">\n");
      out.write("<input type=\"submit\"></form>\n");
      out.write("\n");

}
if(request.getParameter("email")!=null){

      out.write("\n");
      out.write("<h1>Ny anvÃ¤ndare</h1>\n");
      out.write("<form>\n");
      out.write("Text: <input type=\"text\" name=\"text\">\n");
      out.write("<input type=\"submit\"></form>\n");
      out.write("\n");
      out.write("\n");

}
if(request.getParameter("text")!=null){

      out.write("\n");
      out.write("<h1>Nytt inlÃ¤gg</h1>\n");

post.setNickname(user.getNickname());
forum.addPost(post);
ArrayList posts = forum.getPosts();
for(int i = 0; i < posts.size(); i++){
post = (bean.Post)posts.get(i);

      out.write("\n");
      out.write("<b>");
      out.print(post.getText());
      out.write("</b><br>\n");
      out.write("<i>");
      out.print(post.getNickname());
      out.write("</i><br>\n");

}

      out.write("\n");
      out.write("<form>\n");
      out.write("Text: <input type=\"text\" name=\"text\">\n");
      out.write("<input type=\"submit\"></form>\n");

}

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</center>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
