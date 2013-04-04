
<%@ page import="java.sql.*" %>
<%
//get the user input from the login page
String userName = (request.getParameter("USERID")).trim();
String passwd = (request.getParameter("PASSWD")).trim();
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

//select the user table from the underlying db and validate the user name and password
Statement stmt = null;
ResultSet rset = null;
String sql = "select PASSWORD from users where USER_NAME = '"+userName+"'";
//out.println(sql);
try{
	stmt = conn.createStatement();
	rset = stmt.executeQuery(sql);
}
catch(Exception ex){
	out.println("<hr>" + ex.getMessage() + "<hr>");
}
String truepwd = "";
String userClass = "";
while(rset != null && rset.next())
	truepwd = (rset.getString(1)).trim();
//If the login is successful, then we store the username as a session variable
if(passwd.equals(truepwd)){
	session.setAttribute("userName", userName);
	userClass = "select CLASS from users where USER_NAME = '"+userName+"'";
	try{
		stmt = conn.createStatement();
		rset = stmt.executeQuery(userClass);
	}
	catch(Exception ex){
	out.println("<hr>" + ex.getMessage() + "<hr>");
	}
	while(rset != null && rset.next())
	userClass = (rset.getString(1)).trim();
	session.setAttribute("userClass", userClass);
	response.sendRedirect("../proj1/home.html");    
}
else {
    try{
    	stmt.close();
		conn.close();
        javax.swing.JOptionPane.showMessageDialog(null, "Invalid login credentials");                     
		//Redirect code from http://stackoverflow.com/questions/2443247/redirecting-users-in-jsp-from-within-a-includes-java-syntax-error
		response.sendRedirect("../proj1/login.html");
	}
    catch(Exception ex){
		out.println("<hr>" + ex.getMessage() + "<hr>");
	}
}
%>



