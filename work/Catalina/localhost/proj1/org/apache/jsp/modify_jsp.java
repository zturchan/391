package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.*;

public final class modify_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");

String username = "";
String first = "";
String last = "";
String add = "";
String email = "";
String phone = "";
String doctors = "";
String newPass = "";

try{username = (request.getParameter("user")).trim();} catch (Exception ex){}
try{first = (request.getParameter("FIRST")).trim();} catch (Exception ex){}
try{last = (request.getParameter("LAST")).trim();} catch (Exception ex){}
try{add = (request.getParameter("ADDRESS")).trim();} catch (Exception ex){}
try{email = (request.getParameter("EMAIL")).trim();} catch (Exception ex){}
try{phone = (request.getParameter("PHONE")).trim();} catch (Exception ex){}
try{doctors = (request.getParameter("DOCTORNAME")).trim();} catch (Exception ex){}
try{newPass = (request.getParameter("PASSWORD")).trim();} catch (Exception ex){}

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
//select the user table from the underlying db and validate the user name and authority
Statement stmt = null;
ResultSet rset = null;
String sql = "select USER_NAME from users where USER_NAME = '"+sessionUserName+"' and class = 'a'";
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
	javax.swing.JOptionPane.showMessageDialog(null, "You are not currently authenticated as an administrator.  Please authenticate first.");
	response.sendRedirect("../proj1/login.html");
}
//So if we get here, we're authenticated.
try {
	
	sql = "select first_name,last_name,address,email,phone from persons where USER_NAME = '"+username+"'";
	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	rset = stmt.executeQuery(sql);
	rset.absolute(1);
	rset.updateString(1,first);
	rset.updateString(2,last);
	rset.updateString(3,add);
	rset.updateString(4,email);
	rset.updateString(5,phone);
	rset.updateRow();
	
	//Only do this if a new password was entered
	if (!(newPass == null | newPass.equals(""))){
		sql = "select password from users where USER_NAME = '"+username+"'";
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rset = stmt.executeQuery(sql);
		rset.updateString(1,newPass);
		rset.updateRow();
		
	}
}
catch(SQLException ex) {
	System.err.println("SQLException: " +
	ex.getMessage());
}
try {
	//Now want to check if the user is a patient
	sql = "select USER_NAME from users where USER_NAME = '"+username+"' and class = 'p'";
	stmt = conn.createStatement();
	rset = stmt.executeQuery(sql);

	if(rset.next()){
		PreparedStatement ps;
		//If we're a patient then we should update family doctor info	
		ps = conn.prepareStatement("delete from family_doctor where patient_name = ?");
		ps.setString(1,username);
		int del = ps.executeUpdate();
		//del now holds number of old docs we removed
		String[] docs = doctors.split(",");
		for (int i = 0; i < docs.length; i++){
			ps = conn.prepareStatement("insert into family_doctor(doctor_name,patient_name) values(?,?)");
			ps.setString(1,docs[i]);
			ps.setString(2,username);
			ps.executeUpdate();
		}
		ps.close();
	}	
} 
catch(SQLException ex) {

}
	javax.swing.JOptionPane.showMessageDialog(null, "User info successfully updated in database.");
	stmt.close();
    conn.close();
    response.sendRedirect("../proj1/home.html");


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
