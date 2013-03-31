<head>
<title>Uploading Module</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>

<%
String userClass = (String)session.getAttribute("userClass");
if (!userClass.equals("r")) {
out.println("<h1>ERROR: Not logged in as a Radiologist</h1><hr>");
}
%>


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
<td><input type="text" name="prescribingDate"></td>
</tr>
<tr align="left">
<th>Test Date:</th>
<td><input type="text" name="testDate"></td>
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
<td><input type="text" name="recid"></td>
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
