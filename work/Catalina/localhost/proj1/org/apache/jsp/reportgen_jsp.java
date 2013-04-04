package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.*;

public final class reportgen_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Report Module</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\n");
      out.write("</head>\n");
      out.write("\n");

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

String startDate = request.getParameter("startDate");
String endDate = request.getParameter("endDate");
if (startDate == "") {startDate = "01-Jan-1900";}
if (endDate == "") {endDate = "31-Dec-2100";}

String diagnosis = request.getParameter("diagnosis");

PreparedStatement stmt = null;
ResultSet rset = null;
String sql = "select p.user_name, p.first_name, p.last_name, p.address, p.phone,  to_char(r.test_date, 'DD-MON-YYYY') as test_date, r.record_id from persons p, radiology_record r where r.diagnosis = ? and p.user_name = r.patient_name and r.test_date BETWEEN ? and ? ORDER BY r.test_date asc";
    
 
stmt = conn.prepareStatement(sql);
stmt.setString(1, diagnosis);
stmt.setString(2, startDate);
stmt.setString(3, endDate);
rset = stmt.executeQuery();

out.println("<h2>Diagnosis: " + diagnosis + "</h2>");
out.println("<h2>Start Date: " + startDate + "</h2>");
out.println("<h2>End Date: " + endDate + "</h2>");


out.println("<table border='1'>");
out.println("<tr><th>User Name</th><th>First Name</th><th>Last Name</th><th>Address</th><th>Phone</th><th>First Test Date</th></tr>");
// remove duplicate values
ArrayList<String> names=new ArrayList<String>();
while (rset != null && rset.next()){
String name = rset.getString(1).trim();
if(!names.contains(name)){
     names.add(name);

out.println("<tr><td>"+name+"</td>");
out.println("<td>"+rset.getString(2).trim()+"</td>");
out.println("<td>"+rset.getString(3).trim()+"</td>");
out.println("<td>"+rset.getString(4).trim()+"</td>");
out.println("<td>"+rset.getString(5).trim()+"</td>");
out.println("<td>"+rset.getString(6).trim()+"</td></tr>");
}
}
out.println("</table>");
out.println("<br /><a href='../proj1/report.jsp'>Back</a><br />");
out.println("<a href='../proj1/home.html'>Home</a><br />");
stmt.close();
conn.close();

      out.write("\n");
      out.write("\n");
      out.write("<div id=\"footer\">\n");
      out.write("<a href=\"../proj1/logout.jsp\">Logout</a>\n");
      out.write("</div>\t\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
