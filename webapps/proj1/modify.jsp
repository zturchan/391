<HTML>
<HEAD>
<TITLE>RIS</TITLE>
</HEAD>

<BODY>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%

String username = (request.getParameter("USERNAME")).trim();
String first = (request.getParameter("FIRST")).trim();
String last = (request.getParameter("LAST")).trim();
String add = (request.getParameter("ADDRESS")).trim();
String email = (request.getParameter("EMAIL")).trim();
String phone = (request.getParameter("PHONE")).trim();
String doctors = (request.getParameter("DOCTORNAME")).trim();

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
//select the user table from the underlying db and validate the user name and authority
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
	
/*
sql = "select PASSWORD from users where USER_NAME = '"+sessionUserName+"'";
	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	rset = stmt.executeQuery(sql);
	rset.absolute(1);
	rset.updateString(1,newPass);
	rset.updateRow();
	ResultSet newRset = stmt.executeQuery(sql);
	while(newRset.next()){
		javax.swing.JOptionPane.showMessageDialog(null, "Password Successfully Changed.");
		*/
	sql = "select first_name,last_name,address,email,phone from persons where USER_NAME = '"+username+"'";
	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	rset = stmt.executeQuery(sql);
	rset.absolute(1);
	rset.updateString(1,first);
	rset.updateString(2,last);
	rset.updateString(3,add);
	rset.updateString(4,email);
	rset.updateString(5,phone);
	rset.updateRow();
	
	//idea - maybe empty field = no update?
	//Now want to check if the user is a patient
	sql = "select USER_NAME from users where USER_NAME = '"+username+"' and class = 'p'";
	stmt = conn.createStatement();
	rset = stmt.executeQuery(sql);

	if(rset.next()){
		PreparedStatement ps;
		//If we're a patient then we should update family doctor info	
		ps = conn.prepareStatement("delete from family_doctor where patient_name = ?");
		ps.setString(1,username);
		int del = ps.executeUpdate();
		//del now holds number of old docs we removed
		String[] docs = doctors.split(",");
		for (int i = 0; i < docs.length; i++){
			ps = conn.prepareStatement("insert into family_doctor(doctor_name,patient_name) values(?,?)");
			ps.setString(1,docs[i]);
			ps.setString(2,username);
			ps.executeUpdate();
		}
		ps.close();
	}	
	
	javax.swing.JOptionPane.showMessageDialog(null, "User info successfully updated in database.");

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

