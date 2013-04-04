<HTML>
<HEAD>
<TITLE>RIS</TITLE>
</HEAD>
<BODY>
<%@ page import="java.sql.*" %>
<%
String newPass = (request.getParameter("NEWPASSWD")).trim();

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
String sql = "select USER_NAME from users where USER_NAME = '"+sessionUserName+"'";
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
	javax.swing.JOptionPane.showMessageDialog(null, "You cannot perform that action.  Please authenticate first.");
	response.sendRedirect("../proj1/login.html");
}
//So if we get here, we're authenticated.
try {
	sql = "select PASSWORD from users where USER_NAME = '"+sessionUserName+"'";
	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	rset = stmt.executeQuery(sql);
	rset.absolute(1);
	rset.updateString(1,newPass);
	rset.updateRow();
	ResultSet newRset = stmt.executeQuery(sql);
	while(newRset.next()){
		javax.swing.JOptionPane.showMessageDialog(null, "Password Successfully Changed.");
	}
	stmt.close();
    conn.close();
    response.sendRedirect("../proj1/home.html");
}
catch(SQLException ex) {
	System.err.println("SQLException: " +
	ex.getMessage());
}
%>
</BODY>
</HTML>

