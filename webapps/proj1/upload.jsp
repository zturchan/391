<%@ page import="java.sql.*" %>
<head>
<title>Uploading Module</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>

<%
if (session.getAttribute("userClass") != null){
String userClass = (String)session.getAttribute("userClass");
if (!userClass.equals("r")) {
out.println("<h1>ERROR: Not logged in as a Radiologist</h1><hr>");
}}
%>


<h2>Uploading Module</h2>
<hr />

<h3>Add a New Radiology Record to Database</h3>	
<form name="newRecord" action="uploadingModule.jsp" method="post">
<table>
<tr align="left">
<th>Patient name:</th>
<td><input type="text" name="patientName"></td>
</tr>
<tr align="left">
<th>Doctor name:</th>
<td><input type="text" name="doctorName"></td>
</tr>
<tr align="left">
<th>Test type:</th>
<td><input type="text" name="testType"></td>
</tr>
<tr align="left">
<th>Prescribing Date:</th>
<td><input type="text" name="prescribingDate" value="01-Jan-2013"></td>
</tr>
<tr align="left">
<th>Test Date:</th>
<td><input type="text" name="testDate" value="01-Jan-2013"></td>
</tr>
<tr align="left">
<th>Diagnosis:</th>
<td><input type="text" name="diagnosis"></td>
</tr>
<tr align="left">
<th>Description:</th>
<td><textarea cols="40" rows="4" name="description"></textarea></td>
</tr>
</table>
<input type="submit" name="submit" VALUE="Add Record">
</form>	

<hr>
<h3>Add A Medical Image to An Existing Record</h3>
<form name="uploadImage" method="POST" ENCtype="multipart/form-data" action="UploadImage">
<table>
<tr align="left">
<th>Record ID:</th>
<td>
<select name="dropdownID">
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
String sql = "select record_id from radiology_record";
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
<tr align="left">
<th>Image File:</th>
<td><input type="file"  name="imagePath"></td>
</tr>
</table>

<input type="submit" name="uploadImageSubmit" VALUE="Upload Image">
</form>
<div id="footer">
<a href="../proj1/logout.jsp">Logout</a>
</div>	
</body>
</html>
