import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 *  This servlet sends one picture to the client who requested the servlet.
 *
 *  The request must come with a query string as follows:
 *    oneimage?f12:        sends the thumbnail with image_id = 12
 *    oneimage?r12:        sends regular size photo with image_id = 12
 *    oneimage?t12:       sends full size photo with image_id = 12
 *
 *  adapted from Li-Yan Yuan's sample code
 *
 */
public class oneimage extends HttpServlet {

    /**
     *    This method first gets the query strings indicating image_id and SIZE,
     *    and then executes the query 
     *          select SIZE from pacs_images where image_id = image_id   
     *    Finally, it sends the picture to the client
     */

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException {
	
	//  construct the query  from the client's QueryString
	String picid  = request.getQueryString();
	String query;

	if ( picid.startsWith("t") )  
	    query = 
	     "select thumbnail from pacs_images where image_id=" + picid.substring(1);
	else if ( picid.startsWith("r") )  
	    query = 
	      "select regular_size from pacs_images where image_id=" + picid.substring(1);	
	else
	    query = "select full_size from pacs_images where image_id=" + picid.substring(1);

	ServletOutputStream out = response.getOutputStream();


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



	try {
	
	    Statement stmt = conn.createStatement();
	    ResultSet rset = stmt.executeQuery(query);

	    if ( rset.next() ) {
		response.setContentType("image/gif");
		InputStream input = rset.getBinaryStream(1);	    
		int imageByte;
		while((imageByte = input.read()) != -1) {
		    out.write(imageByte);
		}
		input.close();
	    } 
	    else 
		out.println("no picture available");
	} catch( Exception ex ) {
	    out.println(ex.getMessage() );
	}
	// to close the connection
	finally {
	    try {
		conn.close();
	    } catch ( SQLException ex) {
		out.println( ex.getMessage() );
	    }
	}
    }

    /*
     *   Connect to the specified database
     */
    private Connection getConnected() throws Exception {

	String username = "user_name";
	String password = "*****";
        /* one may replace the following for the specified database */
	String dbstring = "jdbc.logicsql@luscar.cs.ualberta.ca:2000:database";
	String driverName = "com.shifang.logicsql.jdbc.driver.LogicSqlDriver";

	/*
	 *  to connect to the database
	 */
	Class drvClass = Class.forName(driverName); 
	DriverManager.registerDriver((Driver) drvClass.newInstance());
	return( DriverManager.getConnection(dbstring,username,password) );
    }
}

