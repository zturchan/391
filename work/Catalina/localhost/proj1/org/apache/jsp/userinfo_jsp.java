package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class userinfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<TITLE>RIS</TITLE>\n");
      out.write("</HEAD>\n");
      out.write("\n");
      out.write("<BODY>\n");
      out.write("\n");

String newFirst = (request.getParameter("FIRST")).trim();
String newLast = (request.getParameter("LAST")).trim();
String newAdd = (request.getParameter("ADDRESS")).trim();
String newEmail = (request.getParameter("EMAIL")).trim();
String newPhone = (request.getParameter("PHONE")).trim();

//establish the connection to the underlying database
Connection conn = null;
String driverName = "oracle.jdbc.driver.OracleDriver";
String dbstring = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
try{
	//load and register the driver
	Class drvClass = Class.forName(driverName);
	DriverManager.registerDriver((Driver) drvClass.newInstance());
}
catch(Exception ex){
	out.println("<hr>" + ex.getMessage() + "<hr>");
}
try{
	//establish the connection
	conn = DriverManager.getConnection(dbstring,"zturchan","Pikachu1");
    conn.setAutoCommit(false);
}
catch(Exception ex){
	out.println("<hr>" + ex.getMessage() + "<hr>");
}
String sessionUserName = (String) session.getAttribute("userName");
//select the user table from the underlying db and validate the user name and password
Statement stmt = null;
ResultSet rset = null;
String sql = "select USER_NAME from users where USER_NAME = '"+sessionUserName+"'";
try{
	stmt = conn.createStatement();
	rset = stmt.executeQuery(sql);
}
catch(Exception ex){
	out.println("<hr>" + ex.getMessage() + "<hr>");
}
if(!rset.next()){
	//If we're not a curently authenticated user, redirect to login
	//This should also implement timeout functionality as added bonus
	javax.swing.JOptionPane.showMessageDialog(null, "You cannot perform that action.  Please authenticate first.");
	response.sendRedirect("../proj1/login.html");
}

//out.println("Should be a real session user");
//So if we get here, we're authenticated.
try {
	sql = "select FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, PHONE from persons where USER_NAME = '"+sessionUserName+"'";
	//out.println(sql);
	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	rset = stmt.executeQuery(sql);
	rset.absolute(1);
	rset.updateString(1,newFirst);
	rset.updateString(2,newLast);
	rset.updateString(3,newAdd);	
	rset.updateString(4,newEmail);
	rset.updateString(5,newPhone);		 
	rset.updateRow();
	ResultSet newRset = stmt.executeQuery(sql);
	stmt.close();
    conn.close();
    javax.swing.JOptionPane.showMessageDialog(null, "Personal Information Changed:\n"+newFirst+ " " + newLast +"\n"+newAdd+"\n" + newPhone +"\n"+newEmail);
    response.sendRedirect("../proj1/home.html");
}
catch(SQLException ex) {
	System.err.println("SQLException: " +
	ex.getMessage());
}

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</BODY>\n");
      out.write("</HTML>\n");
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
}
