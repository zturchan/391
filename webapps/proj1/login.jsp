<%@ page import="java.sql.*" %>
<%
//get the user input from the login page
String userName = (request.getParameter("USERID")).trim();
String passwd = (request.getParameter("PASSWD")).trim();
//out.println("<p>Your input User Name is "+userName+"</p>");
//out.println("<p>Your input password is "+passwd+"</p>");
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
while(rset != null && rset.next())
	truepwd = (rset.getString(1)).trim();
//display the result
if(passwd.equals(truepwd)){
	session.setAttribute("userName", userName);
	response.sendRedirect("../proj1/home.html");    
}
else {
	//out.println("<p><b>Either your userName or Your password is inValid!</b></p>");
    try{
		conn.close();
        //Almost certain this is not how we're supposed to do it & it's slow, but no idea how to do it right.
        javax.swing.JOptionPane.showMessageDialog(null, "Invalid login credentials");                     
		//Redirect code from http://stackoverflow.com/questions/2443247/redirecting-users-in-jsp-from-within-a-includes-java-syntax-error
		response.sendRedirect("../proj1/login.html");
	}
    catch(Exception ex){
		out.println("<hr>" + ex.getMessage() + "<hr>");
	}
}
%>



