<HTML>
<HEAD>
<link rel="stylesheet" type="text/css" href="style.css" />	
<TITLE>RIS</TITLE>
</HEAD>

<BODY>
<div id="content">
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%
	String username = request.getParameter("user");
	String first = request.getParameter("first");
	String last = request.getParameter("last");
	String phone = request.getParameter("phone");
	String address = request.getParameter("address");
	String email = request.getParameter("email");
	String docs = request.getParameter("docs");
	if (docs == null) docs = "";
	String classid = request.getParameter("classid");
	

%>
<H1><CENTER>Modifying <%= username %>'s Information</CENTER></H1>



<FORM NAME="ModForm" ACTION="modify.jsp?user=<%= username %>" METHOD="post" >

<TABLE>
<TR VALIGN=TOP ALIGN=LEFT>
<!-- Things the admin cannot change: username, password, class -->
<TR VALIGN=TOP ALIGN=LEFT>
<TD><B><I>First Name:</I></B></TD>
<TD><INPUT TYPE="text" NAME="FIRST" VALUE='<%= first %>'><BR></TD>
</TR>
<TR VALIGN=TOP ALIGN=LEFT>
<TD><B><I>Last Name:</I></B></TD>
<TD><INPUT TYPE="text" NAME="LAST" VALUE='<%= last %>'></TD>
</TR>
<TR VALIGN=TOP ALIGN=LEFT>
<TD><B><I>Address:</I></B></TD>
<TD><INPUT TYPE="text" NAME="ADDRESS" VALUE='<%= address %>'></TD>
</TR>
<TR VALIGN=TOP ALIGN=LEFT>
<TD><B><I>Phone Number  (up to 10 characters):</I></B></TD>
<TD><INPUT TYPE="text" NAME="PHONE" VALUE='<%= phone %>'></TD>
</TR>
<TR VALIGN=TOP ALIGN=LEFT>
<TD><B><I>Email:</I></B></TD>
<TD><INPUT TYPE="text" NAME="EMAIL" VALUE='<%= email %>'></TD>
</TR>
<% if(classid.equals("p")){ %>
<TR VALIGN=TOP ALIGN=LEFT>
<TD ID="DOCLABEL"><B><I>Family Doctor (Separate multiples by commas, old ones will be deleted):</I></B></TD>
<TD><INPUT TYPE="text" NAME="DOCTORNAME" VALUE='<%= docs %>'></TD>
</TR>
<% } %>


</TR>
</TABLE>

<INPUT TYPE="submit" VALUE="Update Info">
</FORM>
</div>
<div id="footer">
<a href="../proj1/logout.jsp">Logout</a>
</div>
</BODY>
</HTML>
