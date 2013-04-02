<%@ page import="java.sql.*" %>
<head>
<title>Report Module</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>


<%
if (session.getAttribute("userClass") != null){
String userClass = (String)session.getAttribute("userClass");
if (!userClass.equals("r")) {
out.println("<h1>ERROR: Not logged in as a Administrator</h1><hr>");
}}
%>




<select>
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
String sql = "select diagnosis from radiology_record";
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



<div id="footer">
<a href="../proj1/logout.jsp">Logout</a>
</div>	
</body>
</html>
