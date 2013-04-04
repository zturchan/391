<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
<title>Report Module</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>

<%
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

String startDate = request.getParameter("startDate");
String endDate = request.getParameter("endDate");
if (startDate == "") {startDate = "01-Jan-1900";}
if (endDate == "") {endDate = "31-Dec-2100";}

String diagnosis = request.getParameter("diagnosis");

PreparedStatement stmt = null;
ResultSet rset = null;
String sql = "select p.user_name, p.first_name, p.last_name, p.address, p.phone,  to_char(r.test_date, 'DD-MON-YYYY') as test_date, r.record_id from persons p, radiology_record r where r.diagnosis = ? and p.user_name = r.patient_name and r.test_date BETWEEN ? and ? ORDER BY r.test_date asc";
    
 
stmt = conn.prepareStatement(sql);
stmt.setString(1, diagnosis);
stmt.setString(2, startDate);
stmt.setString(3, endDate);
rset = stmt.executeQuery();

out.println("<h2>Diagnosis: " + diagnosis + "</h2>");
out.println("<h2>Start Date: " + startDate + "</h2>");
out.println("<h2>End Date: " + endDate + "</h2>");


out.println("<table border='1'>");
out.println("<tr><th>User Name</th><th>First Name</th><th>Last Name</th><th>Address</th><th>Phone</th><th>First Test Date</th></tr>");
// remove duplicate values
ArrayList<String> names=new ArrayList<String>();
while (rset != null && rset.next()){
String name = rset.getString(1).trim();
if(!names.contains(name)){
     names.add(name);

out.println("<tr><td>"+name+"</td>");
out.println("<td>"+rset.getString(2).trim()+"</td>");
out.println("<td>"+rset.getString(3).trim()+"</td>");
out.println("<td>"+rset.getString(4).trim()+"</td>");
out.println("<td>"+rset.getString(5).trim()+"</td>");
out.println("<td>"+rset.getString(6).trim()+"</td></tr>");
}
}
out.println("</table>");
out.println("<br /><a href='../proj1/report.jsp'>Back</a><br />");
out.println("<a href='../proj1/home.html'>Home</a><br />");
stmt.close();
conn.close();
%>

<div id="footer">
<a href="../proj1/logout.jsp">Logout</a>
</div>	
</body>
</html>
