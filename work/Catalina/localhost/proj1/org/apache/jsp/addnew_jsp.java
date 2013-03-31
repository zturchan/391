package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.*;

public final class addnew_jsp extends org.apache.jasper.runtime.HttpJspBase
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


String userclass = request.getParameter("CLASS").trim();
String username = (request.getParameter("USERNAME")).trim();
String password = (request.getParameter("PASSWORD")).trim();
String first = (request.getParameter("FIRST")).trim();
String last = (request.getParameter("LAST")).trim();
String add = (request.getParameter("ADDRESS")).trim();
String email = (request.getParameter("EMAIL")).trim();
String phone = (request.getParameter("PHONE")).trim();
String doctors = (request.getParameter("DOCTORNAME")).trim();
String classid ="";
if(userclass.equals("doctor")) classid = "d";
else if (userclass.equals("admin")) classid = "a";
else if (userclass.equals("radiologist")) classid = "r";
else if (userclass.equals("patient")) classid = "p";

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

//out.println("Should be a real session user");
//So if we get here, we're authenticated.
try {
	
//	sql = "insert into users values ('"+username+"','"+password+"','"+classid+"',CURRENT_DATE);";
//	sql = "select user_name, password, class, date_registered from users";
	PreparedStatement ps = conn.prepareStatement("insert into users(user_name,password,class,date_registered) values (?,?,?,?)");
	java.util.Date currentDatetime = new java.util.Date(System.currentTimeMillis());  
	ps.setString(1,username);
	ps.setString(2,password);
	ps.setString(3,classid);
	ps.setDate(4, new java.sql.Date(currentDatetime.getTime()));
	ps.executeUpdate();
	
	ps = conn.prepareStatement("insert into persons(user_name,first_name,last_name,address,email,phone) values(?,?,?,?,?,?)");
	ps.setString(1,username);
	ps.setString(2,first);
	ps.setString(3,last);
	ps.setString(4,add);
	ps.setString(5,email);
	ps.setString(6,phone);
	ps.executeUpdate();
	

	if(classid.trim().equals("p")){
		String[] docs = doctors.split(",");
		for (int i = 0; i < docs.length; i++){
			ps = conn.prepareStatement("insert into family_doctor(doctor_name,patient_name) values(?,?)");
			//out.println(docs[i]);
			ps.setString(1,docs[i]);
			ps.setString(2,username);
			ps.executeUpdate();
		}
		
	}
	javax.swing.JOptionPane.showMessageDialog(null, "User "+username+" successfully added to the database.");
	ps.close();
	stmt.close();
    conn.close();
    //response.sendRedirect("../proj1/home.html");
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
