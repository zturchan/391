package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<TITLE>RIS - Search</TITLE>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\t\n");
      out.write("</HEAD>\n");
      out.write("\n");
      out.write("<BODY onload=\"var e=document.getElementsByTagName('input');var i=0;while(i<e.length){if(e[i++].type=='radio'){e[i].checked=false;}}\">\n");
      out.write("<div id=\"content\">\n");
      out.write("\t\t\n");
      out.write("<H1><CENTER>Search</CENTER></H1>\n");
      out.write("\n");
      out.write("<FORM NAME=\"keyword\" ACTION=\"dbaccess\" METHOD=\"get\" >\n");
      out.write("\n");
      out.write("<TABLE>\n");
      out.write("<TR VALIGN=TOP ALIGN=LEFT>\n");
      out.write("<TD><B><I>Sort By:</I></B></TD>\n");
      out.write("<TD>\n");
      out.write("<input type=\"radio\" name=\"sortby\" value=\"Rank\" checked>Rank\n");
      out.write("<input type=\"radio\" name=\"sortby\" value=\"Date\">Date\n");
      out.write("</TD></TR>\n");
      out.write("<TR VALIGN=TOP ALIGN=LEFT>\n");
      out.write("<TD><B><I>Keywords:</I></B></TD>\n");
      out.write("<TD><INPUT TYPE=\"text\" NAME=\"keywords\" VALUE=\"\"><BR></TD>\n");
      out.write("</TR>\n");
      out.write("<TD><B><I>Start Date: (yyyy-mm-dd)</I></B></TD>\n");
      out.write("<TD><INPUT TYPE=\"text\" NAME=\"startdate\" VALUE=\"\"><BR></TD>\n");
      out.write("</TR>\n");
      out.write("<TD><B><I>End Date: (yyyy-mm-dd)</I></B></TD>\n");
      out.write("<TD><INPUT TYPE=\"text\" NAME=\"enddate\" VALUE=\"\"><BR></TD>\n");
      out.write("</TR>\n");
      out.write("</TABLE>\n");
      out.write("\n");
      out.write("<INPUT TYPE=\"submit\" VALUE=\"Search\">\n");
      out.write("</FORM>\n");
      out.write("</div>\n");
      out.write("<div id=\"footer\">\n");
      out.write("<a href=\"../proj1/logout.jsp\">Logout</a>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
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
