<HTML>
<HEAD>
<TITLE>OLAP</TITLE>
<link rel="stylesheet" type="text/css" href="style.css" />	
</HEAD>

<BODY onload="var e=document.getElementsByTagName('input');var i=0;while(i<e.length){if(e[i++].type=='radio'){e[i].checked=false;}}">
<div id="content">
		
<H1><CENTER>OLAP</CENTER></H1>

<FORM NAME="analysis" ACTION="olap" METHOD="get" >

<TABLE>

<TR VALIGN=TOP ALIGN=LEFT>
<TD><B><I>Use Patient Name?</I></B>
<input type="radio" name="use_name" value="Yes" checked>Yes
<input type="radio" name="use_name" value="No">No
</TR>
<TR VALIGN=TOP ALIGN=LEFT>
<TD><B><I>Use Test Type?</I></B>
<input type="radio" name="use_type" value="Yes" checked>Yes
<input type="radio" name="use_type" value="No">No
</TR>
<TD><select name="granularity">
  <option value="year">Year</option>
  <option value="month">Month</option>
  <option value="week">Week</option>
  <option value="no">Do Not Use</option>
</select> 
<BR></TD>
</TR>
</TABLE>

<INPUT TYPE="submit" VALUE="OLAP">
</FORM>
</div>
<div id="footer">
<a href="../proj1/logout.jsp">Logout</a>
</div>


</BODY>
</HTML>
