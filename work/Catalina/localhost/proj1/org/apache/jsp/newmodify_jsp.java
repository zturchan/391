package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.*;

public final class newmodify_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write("<HTML>\n");
      out.write("<HEAD>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\t\n");
      out.write("<TITLE>RIS</TITLE>\n");
      out.write("</HEAD>\n");
      out.write("<BODY>\n");
      out.write("<div id=\"content\">\n");
      out.write("\n");
      out.write("\n");

	String username = request.getParameter("user");
	String first = request.getParameter("first");
	String last = request.getParameter("last");
	String phone = request.getParameter("phone");
	String address = request.getParameter("address");
	String email = request.getParameter("email");
	String docs = request.getParameter("docs");
	if (docs == null) docs = "";
	String classid = request.getParameter("classid");

      out.write("\n");
      out.write("<H1><CENTER>Modifying ");
      out.print( username );
      out.write("'s Information</CENTER></H1>\n");
      out.write("<FORM NAME=\"ModForm\" ACTION=\"modify.jsp?user=");
      out.print( username );
      out.write("\" METHOD=\"post\" >\n");
      out.write("<TABLE>\n");
      out.write("<TR VALIGN=TOP ALIGN=LEFT>\n");
      out.write("<!-- Things the admin cannot change: username, class -->\n");
      out.write("<TR VALIGN=TOP ALIGN=LEFT>\n");
      out.write("<TD><B><I>New Password:</I></B></TD>\n");
      out.write("<TD><INPUT TYPE=\"password\" NAME=\"PASSWORD\" VALUE=\"\"><BR></TD>\n");
      out.write("</TR>\n");
      out.write("<TR VALIGN=TOP ALIGN=LEFT>\n");
      out.write("<TD><B><I>First Name:</I></B></TD>\n");
      out.write("<TD><INPUT TYPE=\"text\" NAME=\"FIRST\" VALUE='");
      out.print( first );
      out.write("'><BR></TD>\n");
      out.write("</TR>\n");
      out.write("<TR VALIGN=TOP ALIGN=LEFT>\n");
      out.write("<TD><B><I>Last Name:</I></B></TD>\n");
      out.write("<TD><INPUT TYPE=\"text\" NAME=\"LAST\" VALUE='");
      out.print( last );
      out.write("'></TD>\n");
      out.write("</TR>\n");
      out.write("<TR VALIGN=TOP ALIGN=LEFT>\n");
      out.write("<TD><B><I>Address:</I></B></TD>\n");
      out.write("<TD><INPUT TYPE=\"text\" NAME=\"ADDRESS\" VALUE='");
      out.print( address );
      out.write("'></TD>\n");
      out.write("</TR>\n");
      out.write("<TR VALIGN=TOP ALIGN=LEFT>\n");
      out.write("<TD><B><I>Phone Number  (up to 10 characters):</I></B></TD>\n");
      out.write("<TD><INPUT TYPE=\"text\" NAME=\"PHONE\" VALUE='");
      out.print( phone );
      out.write("'></TD>\n");
      out.write("</TR>\n");
      out.write("<TR VALIGN=TOP ALIGN=LEFT>\n");
      out.write("<TD><B><I>Email:</I></B></TD>\n");
      out.write("<TD><INPUT TYPE=\"text\" NAME=\"EMAIL\" VALUE='");
      out.print( email );
      out.write("'></TD>\n");
      out.write("</TR>\n");
 if(classid.equals("p")){ 
      out.write("\n");
      out.write("<TR VALIGN=TOP ALIGN=LEFT>\n");
      out.write("<TD ID=\"DOCLABEL\"><B><I>Family Doctor (Separate multiples by commas, old ones will be deleted):</I></B></TD>\n");
      out.write("<TD><INPUT TYPE=\"text\" NAME=\"DOCTORNAME\" VALUE='");
      out.print( docs );
      out.write("'></TD>\n");
      out.write("</TR>\n");
 } 
      out.write("\n");
      out.write("</TR>\n");
      out.write("</TABLE>\n");
      out.write("<INPUT TYPE=\"submit\" VALUE=\"Update Info\">\n");
      out.write("</FORM>\n");
      out.write("</div>\n");
      out.write("<div id=\"footer\">\n");
      out.write("<a href=\"../proj1/logout.jsp\">Logout</a>\n");
      out.write("</div>\n");
      out.write("</BODY>\n");
      out.write("</HTML>\n");
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
}
