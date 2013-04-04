<html>
<%@ page import="java.sql.*" %>
<head>
<title>Report Module</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>


<%
if (session.getAttribute("userClass") != null && !(((String)session.getAttribute("userClass")).equals("a"))) {
out.println("<h1>ERROR: Not logged in as a Administrator</h1><hr>");
} else {
%>
<h2>Uploading Module</h2>
<hr />
<form name="reportGen" method="POST"  action="reportgen.jsp">
<table>
<tr align="left">
<th>Diagnosis</th>
<td>
<select name="diagnosis">
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
%>
</select>
</td>
</tr>
<tr>
<th>
Start Date:
</th>
<td><input type="text" name="startDate" value="01-Jan-2013"></td>
</tr>
<tr>
<th>
End Date:
</th>
<td><input type="text" name="endDate" value="01-Jan-2013"></td>
</tr>
</table>
<input type="submit" name="submit" VALUE="Generate Report">
</form>

<% } %>
<div id="footer">
<a href="../proj1/logout.jsp">Logout</a>
</div>	
</body>
</html>
