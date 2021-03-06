package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class report_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("<title>Report Generation Module</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("\n");

// if an administrator is logged in, show him the form
// if not, just show an error message
if (session.getAttribute("userClass") != null && !(((String)session.getAttribute("userClass")).equals("a"))) {
out.println("<h1>ERROR: Not logged in as a Administrator</h1><hr>");
} else {

      out.write("\n");
      out.write("<h2>Report Generation Module</h2>\n");
      out.write("<hr />\n");
      out.write("<form name=\"reportGen\" method=\"POST\"  action=\"reportgen.jsp\">\n");
      out.write("<table>\n");
      out.write("<tr align=\"left\">\n");
      out.write("<th>Diagnosis</th>\n");
      out.write("<td>\n");
      out.write("<select name=\"diagnosis\">\n");

// display all distinct diagnoses in the radiology_record table to
// prevent searching for a nonexistent diagnosis
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

Statement stmt = null;
ResultSet rset = null;
String sql = "select distinct diagnosis from radiology_record";
try{
stmt = conn.createStatement();
rset = stmt.executeQuery(sql);

conn.commit();


}
     catch(Exception ex){
         out.println("<hr>" + ex.getMessage() + "<hr>");
}

while (rset.next() ) {
	out.println("<option>"+rset.getString(1)+"</option>");
}

 stmt.close();
conn.close();

      out.write("\n");
      out.write("</select>\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<th>\n");
      out.write("Start Date:\n");
      out.write("</th>\n");
      out.write("<!-- a default value is used to show the user the proper format to input a date -->\n");
      out.write("<td><input type=\"text\" name=\"startDate\" value=\"01-Jan-2013\"></td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<th>\n");
      out.write("End Date:\n");
      out.write("</th>\n");
      out.write("<td><input type=\"text\" name=\"endDate\" value=\"01-Jan-2013\"></td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("<input type=\"submit\" name=\"submit\" VALUE=\"Generate Report\">\n");
      out.write("</form>\n");
      out.write("\n");
 } 
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
