<HTML>
<HEAD>
<TITLE>RIS</TITLE>
</HEAD>
<BODY>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%
//Get parameters from form found in addnew.html
String userclass = request.getParameter("CLASS").trim();
String username = (request.getParameter("USERNAME")).trim();
String password = (request.getParameter("PASSWORD")).trim();
String first = (request.getParameter("FIRST")).trim();
String last = (request.getParameter("LAST")).trim();
String add = (request.getParameter("ADDRESS")).trim();
String email = (request.getParameter("EMAIL")).trim();
String phone = (request.getParameter("PHONE")).trim();
String doctors = (request.getParameter("DOCTORNAME")).trim();
String classid ="";
if(userclass.equals("doctor")) classid = "d";
else if (userclass.equals("admin")) classid = "a";
else if (userclass.equals("radiologist")) classid = "r";
else if (userclass.equals("patient")) classid = "p";

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
//This is the username of the user who is currently authenticated
String sessionUserName = (String) session.getAttribute("userName");
//select the user table from the underlying db and validate the user name and password
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

//So if we get here, we're authenticated.
try {
	PreparedStatement ps = conn.prepareStatement("insert into users(user_name,password,class,date_registered) values (?,?,?,?)");
	java.util.Date currentDatetime = new java.util.Date(System.currentTimeMillis());  
	ps.setString(1,username);
	ps.setString(2,password);
	ps.setString(3,classid);
	ps.setDate(4, new java.sql.Date(currentDatetime.getTime()));
	ps.executeUpdate();
	
	ps = conn.prepareStatement("insert into persons(user_name,first_name,last_name,address,email,phone) values(?,?,?,?,?,?)");
	ps.setString(1,username);
	ps.setString(2,first);
	ps.setString(3,last);
	ps.setString(4,add);
	ps.setString(5,email);
	ps.setString(6,phone);
	ps.executeUpdate();
	
	//If we're adding a patient, then we add any doctors they want as well
	if(classid.trim().equals("p")){
		String[] docs = doctors.split(",");
		for (int i = 0; i < docs.length; i++){
			ps = conn.prepareStatement("insert into family_doctor(doctor_name,patient_name) values(?,?)");
			//out.println(docs[i]);
			ps.setString(1,docs[i]);
			ps.setString(2,username);
			ps.executeUpdate();
		}		
	}
	javax.swing.JOptionPane.showMessageDialog(null, "User "+username+" successfully added to the database.");
	ps.close();
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

