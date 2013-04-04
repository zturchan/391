package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class upload_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<head>\n");
      out.write("<title>Uploading Module</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\n");
      out.write("<script>\n");
      out.write(" function validateNewRecord()\n");
      out.write("{\n");
      out.write("\n");
      out.write("if (document.forms[\"newRecord\"][\"patientName\"].value==null || document.forms[\"newRecord\"][\"patientName\"].value==\"\")\n");
      out.write("  {\n");
      out.write("  alert(\"Patient name must be filled out\");\n");
      out.write("  return false;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("if (document.forms[\"newRecord\"][\"doctorName\"].value==null || document.forms[\"newRecord\"][\"doctorName\"].value==\"\")\n");
      out.write("  {\n");
      out.write("  alert(\"Doctor name must be filled out\");\n");
      out.write("  return false;\n");
      out.write("  }\t\n");
      out.write("\n");
      out.write("if (document.forms[\"newRecord\"][\"prescribingDate\"].value==null || document.forms[\"newRecord\"][\"prescribingDate\"].value==\"\")\n");
      out.write("  {\n");
      out.write("  alert(\"Prescribing date must be filled out\");\n");
      out.write("  return false;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("if (document.forms[\"newRecord\"][\"testType\"].value==null || document.forms[\"newRecord\"][\"testType\"].value==\"\")\n");
      out.write("  {\n");
      out.write("  alert(\"Test Type must be filled out\");\n");
      out.write("  return false;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("\n");
      out.write("if (document.forms[\"newRecord\"][\"testDate\"].value==null || document.forms[\"newRecord\"][\"testDate\"].value==\"\")\n");
      out.write("  {\n");
      out.write("  alert(\"Test Date must be filled out\");\n");
      out.write("  return false;\n");
      out.write("  }\n");
      out.write("if (document.forms[\"newRecord\"][\"diagnosis\"].value==null || document.forms[\"newRecord\"][\"diagnosis\"].value==\"\")\n");
      out.write("  {\n");
      out.write("  alert(\"Diagnosis must be filled out\");\n");
      out.write("  return false;\n");
      out.write("  }\n");
      out.write("\t\n");
      out.write("if (document.forms[\"newRecord\"][\"description\"].value==null || document.forms[\"newRecord\"][\"description\"].value==\"\")\n");
      out.write("  {\n");
      out.write("  alert(\"Description must be filled out\");\n");
      out.write("  return false;\n");
      out.write("  }\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write(" function validateImage()\n");
      out.write("{\n");
      out.write("\n");
      out.write("if (document.forms[\"uploadImage\"][\"imagePath\"].value==null || document.forms[\"uploadImage\"][\"imagePath\"].value==\"\")\n");
      out.write("  {\n");
      out.write("  alert(\"Image file path must be filled out\");\n");
      out.write("  return false;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("\n");

// If a radiologist is logged in, displays the forms. If not, displays an error message
if (session.getAttribute("userClass") != null && !(((String)session.getAttribute("userClass")).equals("r"))) {
out.println("<h1>ERROR: Not logged in as a Radiologist</h1><hr>");
} else {

      out.write("\n");
      out.write("\n");
      out.write("<h2>Uploading Module</h2>\n");
      out.write("<hr />\n");
      out.write("\n");
      out.write("<h3>Add a New Radiology Record to Database</h3>\t\n");
      out.write("<form name=\"newRecord\" onsubmit=\"return validateNewRecord()\" action=\"uploadingModule.jsp\" method=\"post\" >\n");
      out.write("<table>\n");
      out.write("<tr align=\"left\">\n");
      out.write("<th>Patient name:</th>\n");
      out.write("<td><input type=\"text\" name=\"patientName\"></td>\n");
      out.write("</tr>\n");
      out.write("<tr align=\"left\">\n");
      out.write("<th>Doctor name:</th>\n");
      out.write("<td><input type=\"text\" name=\"doctorName\"></td>\n");
      out.write("</tr>\n");
      out.write("<tr align=\"left\">\n");
      out.write("<th>Test type:</th>\n");
      out.write("<td><input type=\"text\" name=\"testType\"></td>\n");
      out.write("</tr>\n");
      out.write("<tr align=\"left\">\n");
      out.write("<th>Prescribing Date:</th>\n");
      out.write("<!-- the form is prefilled to show the user the correct format to enter a date -->\n");
      out.write("<td><input type=\"text\" name=\"prescribingDate\" value=\"01-Jan-2013\"></td>\n");
      out.write("</tr>\n");
      out.write("<tr align=\"left\">\n");
      out.write("<th>Test Date:</th>\n");
      out.write("<td><input type=\"text\" name=\"testDate\" value=\"01-Jan-2013\"></td>\n");
      out.write("</tr>\n");
      out.write("<tr align=\"left\">\n");
      out.write("<th>Diagnosis:</th>\n");
      out.write("<td><input type=\"text\" name=\"diagnosis\"></td>\n");
      out.write("</tr>\n");
      out.write("<tr align=\"left\">\n");
      out.write("<th>Description:</th>\n");
      out.write("<td><textarea cols=\"40\" rows=\"4\" name=\"description\"></textarea></td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("<input type=\"submit\" name=\"submit\" VALUE=\"Add Record\">\n");
      out.write("</form>\t\n");
      out.write("\n");
      out.write("<hr>\n");
      out.write("<h3>Add A Medical Image to An Existing Record</h3>\n");
      out.write("<form name=\"uploadImage\" method=\"POST\" ENCtype=\"multipart/form-data\" action=\"UploadImage\" onsubmit=\"return validateImage()\">\n");
      out.write("<table>\n");
      out.write("<tr align=\"left\">\n");
      out.write("<th>Record ID:</th>\n");
      out.write("<td>\n");
      out.write("<select name=\"dropdownID\">\n");

//fill dropdown with all the record ids from the radiology record table
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
String sql = "select record_id from radiology_record";
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
      out.write("<tr align=\"left\">\n");
      out.write("<th>Image File:</th>\n");
      out.write("<td><input type=\"file\"  name=\"imagePath\"></td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("<input type=\"submit\" name=\"uploadImageSubmit\" VALUE=\"Upload Image\">\n");
      out.write("</form>\n");
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
