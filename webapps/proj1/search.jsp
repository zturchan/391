<HTML>
<HEAD>
<TITLE>RIS - Search</TITLE>
<link rel="stylesheet" type="text/css" href="style.css" />	
</HEAD>

<BODY onload="var e=document.getElementsByTagName('input');var i=0;while(i<e.length){if(e[i++].type=='radio'){e[i].checked=false;}}">
<div id="content">
		
<H1><CENTER>Search</CENTER></H1>

<FORM NAME="keyword" ACTION="dbaccess" METHOD="get" >

<TABLE>
<TR VALIGN=TOP ALIGN=LEFT>
<TD><B><I>Sort By:</I></B></TD>
<TD>
<input type="radio" name="sortby" value="Rank" checked>Rank
<input type="radio" name="sortby" value="Date">Date
</TD></TR>
<TR VALIGN=TOP ALIGN=LEFT>
<TD><B><I>Keywords:</I></B></TD>
<TD><INPUT TYPE="text" NAME="keywords" VALUE=""><BR></TD>
</TR>
<TD><B><I>Start Date: (yyyy-mm-dd)</I></B></TD>
<TD><INPUT TYPE="text" NAME="startdate" VALUE=""><BR></TD>
</TR>
<TD><B><I>Keywords: (yyyy-mm-dd)</I></B></TD>
<TD><INPUT TYPE="text" NAME="enddate" VALUE=""><BR></TD>
</TR>
</TABLE>

<INPUT TYPE="submit" VALUE="Search">
</FORM>
</div>
<div id="footer">
<a href="../proj1/logout.jsp">Logout</a>
</div>


</BODY>
</HTML>
