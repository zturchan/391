package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class uploadingModule_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');


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



	
	String patientName = request.getParameter("patientName");
	String doctorName = request.getParameter("doctorName");
	String radiologistName = (String)session.getAttribute("userName");
	String testType = request.getParameter("testType");
	String prescribingDate = request.getParameter("prescribingDate");
	String testDate = request.getParameter("testDate");
	String diagnosis = request.getParameter("diagnosis");
	String description = request.getParameter("description");



//Insert record
PreparedStatement stmt = null;
     ResultSet rset = null;
String sql = "insert into radiology_record values(test_seq.nextval, '"+patientName+"',  '"+doctorName+"', '"+radiologistName+"', '"+testType+"', '"+prescribingDate+"', '"+testDate+"', '"+diagnosis+"', '"+description+"' )";
try{
stmt = conn.prepareStatement(sql);
stmt.executeUpdate();

conn.commit();


}
     catch(Exception ex){
         out.println("<hr>" + ex.getMessage() + "<hr>");
}

String sql2 = "select max(record_id) from radiology_record";
int i = 0;
Statement stmt2;
try{
stmt2 = conn.createStatement();
rset = stmt2.executeQuery(sql2);

while(rset != null && rset.next())
i = rset.getInt(1);

javax.swing.JOptionPane.showMessageDialog(null, "The Radiology record was added with an id of "+i);                     
response.sendRedirect("../proj1/upload.jsp");
}
     catch(Exception ex){
         out.println("<hr>" + ex.getMessage() + "<hr>");
}



try{
	conn.close();
}
        catch(Exception ex){
        out.println("<hr>" + ex.getMessage() + "<hr>");
}



      out.write('\n');
      out.write('\n');
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
