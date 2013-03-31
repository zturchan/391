<%@ page import="java.sql.*" %>
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



	
	String patientName = request.getParameter("patientName");
	String doctorName = request.getParameter("doctorName");
	String radiologistName = (String)session.getAttribute("userName");
	String testType = request.getParameter("testType");
	String prescribingDate = request.getParameter("prescribingDate");
	String testDate = request.getParameter("testDate");
	String diagnosis = request.getParameter("diagnosis");
	String description = request.getParameter("description");



//Insert record
PreparedStatement stmt = null;
     ResultSet rset = null;
String sql = "insert into radiology_record values(test_seq.nextval, '"+patientName+"',  '"+doctorName+"', '"+radiologistName+"', '"+testType+"', '"+prescribingDate+"', '"+testDate+"', '"+diagnosis+"', '"+description+"' )";
try{
stmt = conn.prepareStatement(sql);
stmt.executeUpdate();

conn.commit();


}
     catch(Exception ex){
         out.println("<hr>" + ex.getMessage() + "<hr>");
}

String sql2 = "select max(record_id) from radiology_record";
int i = 0;
Statement stmt2;
try{
stmt2 = conn.createStatement();
rset = stmt2.executeQuery(sql2);

while(rset != null && rset.next())
i = rset.getInt(1);

javax.swing.JOptionPane.showMessageDialog(null, "The radiology record was added with an id of "+i);                     
response.sendRedirect("../proj1/upload.jsp");
}
     catch(Exception ex){
         out.println("<hr>" + ex.getMessage() + "<hr>");
}



try{
	conn.close();
}
        catch(Exception ex){
        out.println("<hr>" + ex.getMessage() + "<hr>");
}


%>

