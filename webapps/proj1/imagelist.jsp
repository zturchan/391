<%
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import oracle.jdbc.driver.*;
import java.text.*;
import java.net.*;

/**
 *  A simple example to demonstrate how to use servlet to 
 *  query and display a list of pictures
 *
 *  adapted from Li-Yan Yuan's example code
 *
 */
public class PictureBrowse extends HttpServlet implements SingleThreadModel {
    
    /**
     *  Generate and then send an HTML file that displays all full sizes of the photos.
     *
     *  The images be generated using another 
     *  servlet, called oneimage, with the image_id as its query string
     *
     */
    public void doGet(HttpServletRequest request,
		      HttpServletResponse res)
	throws ServletException, IOException {

	//  send out the HTML file
	res.setContentType("text/html");
	PrintWriter out = res.getWriter ();

	out.println("<html>");
	out.println("<head>");
	out.println("<title> Photo List </title>");
	out.println("</head>");
	out.println("<body bgcolor=\"#000000\" text=\"#cccccc\" >");
	out.println("<center>");
	out.println("<h3>The List of Images </h3>");
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
	/*
	 *   to execute the given query
	 */
	try {
	    String query = "select image_id from pictures";
		
		
	    Statement stmt = conn.createStatement();
	    ResultSet rset = stmt.executeQuery(query);
	    String p_id = "";

	    while (rset.next() ) {
		p_id = (rset.getObject(1)).toString();


	       out.println("<img src=\"WEB-INF/classes/oneimage?f"+p_id +
	                   "\">");
	    }
	    stmt.close();
	    conn.close();
	} catch ( Exception ex ){ out.println( ex.toString() );}
    
	out.println("</body>");
	out.println("</html>");
    }
    

}
%>




