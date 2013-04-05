<%@ page import="java.sql.*" %>
<head>
<title>Uploading Module</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script>
 function validateNewRecord()
{

if (document.forms["newRecord"]["patientName"].value==null || document.forms["newRecord"]["patientName"].value=="")
  {
  alert("Patient name must be filled out");
  return false;
  }

if (document.forms["newRecord"]["doctorName"].value==null || document.forms["newRecord"]["doctorName"].value=="")
  {
  alert("Doctor name must be filled out");
  return false;
  }	

if (document.forms["newRecord"]["prescribingDate"].value==null || document.forms["newRecord"]["prescribingDate"].value=="")
  {
  alert("Prescribing date must be filled out");
  return false;
  }

if (document.forms["newRecord"]["testType"].value==null || document.forms["newRecord"]["testType"].value=="")
  {
  alert("Test Type must be filled out");
  return false;
  }


if (document.forms["newRecord"]["testDate"].value==null || document.forms["newRecord"]["testDate"].value=="")
  {
  alert("Test Date must be filled out");
  return false;
  }
if (document.forms["newRecord"]["diagnosis"].value==null || document.forms["newRecord"]["diagnosis"].value=="")
  {
  alert("Diagnosis must be filled out");
  return false;
  }
	
if (document.forms["newRecord"]["description"].value==null || document.forms["newRecord"]["description"].value=="")
  {
  alert("Description must be filled out");
  return false;
  }
}


 function validateImage()
{

if (document.forms["uploadImage"]["imagePath"].value==null || document.forms["uploadImage"]["imagePath"].value=="")
  {
  alert("Image file path must be filled out");
  return false;
  }

	
}

</script>
</head>


<%
// If a radiologist is logged in, displays the forms. If not, displays an error message
if (session.getAttribute("userClass") == null || (session.getAttribute("userClass") != null && !(((String)session.getAttribute("userClass")).equals("r")))) {
out.println("<h1>ERROR: Not logged in as a Radiologist</h1><hr>");
} else {
%>

<h2>Uploading Module</h2>
<hr />

<h3>Add a New Radiology Record to Database</h3>	
<form name="newRecord" onsubmit="return validateNewRecord()" action="uploadingModule.jsp" method="post" >
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
<!-- the form is prefilled to show the user the correct format to enter a date -->
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
<form name="uploadImage" method="POST" ENCtype="multipart/form-data" action="UploadImage" onsubmit="return validateImage()">
<table>
<tr align="left">
<th>Record ID:</th>
<td>
<select name="dropdownID">
<%
//fill dropdown with all the record ids from the radiology record table
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
<% } %>
<div id="footer">
<a href="../proj1/logout.jsp">Logout</a>
</div>	
</body>
</html>
