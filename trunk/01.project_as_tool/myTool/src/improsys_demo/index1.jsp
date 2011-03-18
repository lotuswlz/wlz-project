<%@ page language="java" contentType="text/html" %>

<%
    String parser, userId, password, user, mtype;
    parser = userId = password = user = mtype = "";
    boolean error = false;

    userId = request.getParameter("user");
    mtype = request.getParameter("webmail");
    
    if(userId != null) {
        try {
			if(mtype.equals("webmail")) 
			{
				user = userId.substring(0, userId.indexOf("@"));
				parser = userId.substring(userId.indexOf("@")+1);
				parser = parser.substring(0, parser.indexOf(".")).toUpperCase();
			}
			else if(mtype.equals("linkedin")) 
			{
				user = userId;
				parser = "LINKEDIN";
			
			}
            password = request.getParameter("password");
			
        } catch (Exception e) {
            parser = user = password = "";
            error = true;
        }
    }
    if(user == null) user = "";	
    if(userId == null) userId = "";
    if(password == null) password = "";	
    // if(parser == null) parser = "AOL"; //default
%>

<html>
<head>
<title>AOL/Gmail/Hotmail/Yahoo Address Book Importer</title>

<script language="JavaScript" type="text/JavaScript">
<!--

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_validateForm() { //v4.0
  var i,p,q,nm,test,num,min,max,errors='',args=MM_validateForm.arguments;
  for (i=0; i<(args.length-2); i+=3) { test=args[i+2]; val=MM_findObj(args[i]);
    if (val) { nm=val.id; if ((val=val.value)!="") {
      if (test.indexOf('isEmail')!=-1) { p=val.indexOf('@');
        if (p<1 || p==(val.length-1)) errors+='- '+nm+' must contain an e-mail address.\n';
      } else if (test!='R') { num = parseFloat(val);
        if (isNaN(val)) errors+='- '+nm+' must contain a number.\n';
        if (test.indexOf('inRange') != -1) { p=test.indexOf(':');
          min=test.substring(8,p); max=test.substring(p+1);
          if (num<min || max<num) errors+='- '+nm+' must contain a number between '+min+' and '+max+'.\n';
    } } } else if (test.charAt(0) == 'R') errors += '- '+nm+' is required.\n'; }
  } if (errors) alert('The following error(s) occurred:\n'+errors);
  document.MM_returnValue = (errors == '');
}

//-->

<!-- Begin

function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
//-->
</script>

<style type="text/css">
<!--
.tdbcolor {
	background-color: #cccccc;
}
.bordcol {
	border:1px solid black;
}
.style3 {font-family: Arial, Helvetica, sans-serif}
-->
</style>
</head>

<body>
<div id="pagelink" align="right"><a href="index.jsp" style=""><font size="2" face="Verdana, Arial, Helvetica, sans-serif">Back to dropdown List</font></a></div>
<div id="loginfrm" style="visibility:visible">

<div>
<form name=form1 method=post action=index1.jsp>
<br>

<table width="46%" border="0" align="center" cellpadding="1" cellspacing="0" bgcolor="#F2F1F0">
    <tr>
		<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="0">
          		<tr align="center" bgcolor="#99FFFF"> 
            		<td colspan="3">
            			<font color="#006699" size="2" face="Arial, Helvetica, sans-serif">
            				<strong>Grab Address Book</strong>
            			</font>
            		</td>
          		</tr>
          		<tr> 
            		<td width="20%" align="right">
            			<font size="2" face="Verdana, Arial, Helvetica, sans-serif">User id:</font>            		</td>
            		<td width="55%">
            			<input name="user" type="text" id="user" value="<%=userId%>" style="width:210px;">
           		  </td>
					<td width="25%">
						<input type="radio" name="webmail" value="webmail" checked="checked" align="right">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">Webmail</font>		   
				  </td>
          		</tr>
          		<tr>
            		<td align="right">
            			<font size="2" face="Verdana, Arial, Helvetica, sans-serif">Password:</font>
            		</td>
           		  <td><input name="password" type="password" id="Password" style="width:210px;"></td>
				  <td width="32%">  <input type="radio" name="webmail" value="paracalls" align="right" >
              			<font size="2" face="Verdana, Arial, Helvetica, sans-serif">Paracalls</font>
				  </td>
          		  </tr>
          		<tr align="center" bgcolor="#99FFFF"> 
            		<td colspan="3">
            			<input name="Submit" type="submit" onClick="MM_validateForm('user','','R','password','','R');return document.MM_returnValue" value="Get address book"> 
              			<input type="reset" name="Submit2" value="Reset">
              		</td>
          		</tr>
        	</table>
        </td>
    </tr>
</table>

</form>
</div>
</div>
<br>
<%
if(	user != null && user.length() > 0 
	&& password != null && password.length() > 0
	&& parser != null && parser.length() > 0
)
{

	try
	{
		
		
		// userId = user;
		
		if(!parser.equals("HOTMAIL") && !parser.equals("MSN") && !parser.equals("ROCKETMAIL") && !parser.equals("YMAIL") && !parser.equals("LIVE")) 
		{
			userId = user; // userId = user + parser.toLowerCase() + ".com";
		}

		improsys.contactimport.common.FourInOne imp = new improsys.contactimport.common.FourInOne(userId, password);;
		
		imp.setType(parser);
		
		imp.doLogin();
		if(!imp.isLoggedIn())
			throw new Exception("Can not be logged in as " + user + " to " + parser.toLowerCase()+".com with the given password.");
		
		improsys.contactimport.common.Contact al[] = imp.getContacts();
		if (al==null)
			throw new Exception("Unable to parse contacts for " + user + " at " + parser.toLowerCase()+".com");

%>
<form name="form2" method="post" action="" >
<table width="68%" border="0" align="center" cellpadding="1" cellspacing="0" bgcolor="#F2F1F0">
	<tr> 
		<td width="100%">
		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="0">
        <tr align="center" bgcolor="#99FFFF"> 
          <td colspan="3"> <font color="#006699" size="2" face="Arial, Helvetica, sans-serif"> 
            <strong><%=parser%> Address Book for <%=userId%><br>
            </strong> <font face="Verdana, Arial, Helvetica, sans-serif"> 
            <script language="javascript">
function submitter2(which){

if (document.images) {
for (i=0;i<which.length;i++) {
var tempobj=which.elements[i];
if (tempobj.name.substring(0,8)=="chk_emai") {

if(which.checkboxed.checked==false)
tempobj.checked=false;
else
tempobj.checked=true;
                                         
                                              }
}
}

}
function validation(which)
{

selected=0;
if (document.images) {
for (i=0;i<which.length;i++) {
var tempobj=which.elements[i];
if (tempobj.name.substring(0,8)=="chk_emai") {

if(tempobj.checked==true)
 
  return true;

                                         
                                              }
							}
					}
if(selected==0)
  {
  alert('Select atleast one email address to send invitation.');
  return false;
  }					
}		
		</script>
            Total Address found <%=al.length%> </font> </font> </td>
        </tr>
        <tr align="center" bgcolor="#99CCFF">
          <td width="6%" align="left" style="PADDING-LEFT: 12px"><input name="checkboxed" type="checkbox" id="checkboxed" value="checkbox" checked onClick="submitter2(document.form2)"></td>
          <td width="43%" align="left" style="PADDING-LEFT: 12px"> <font color="#000066" size="2" face="Arial, Helvetica, sans-serif"> 
            <strong>Display Name</strong> </font> </td>
          <td width="50%" align="left" bgcolor="#99CCFF" style="PADDING-LEFT: 12px"> 
            <font color="#000066" size="2" face="Arial, Helvetica, sans-serif"> 
            <strong>Email ID</strong> </font> </td>
        </tr>
        <%

		//show others
		for(int i=0; i < al.length; i++)
		{
			%>
        <tr>
          <td align="left" style="PADDING-LEFT: 12px">
<%
if(al[i].getEmailAddress().equals(""))
{
%>
<input name="chk_email[]" type="checkbox" id="chk_email[]" disabled="true">
<%
}
else
{
%>
            <input name="chk_email[]" type="checkbox" id="members2" value="<%out.println(al[i].getEmailAddress());%>" checked>
<%
}
%>
          </td>
          <td align="left" style="PADDING-LEFT: 12px"> <font size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
            <%if(al[i].getName().equals(""))
							out.println("<i>NA</i>");
						else
							out.println(al[i].getName());
						%>
            </font> </td>
          <td style="PADDING-LEFT: 12px"> <font size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
            <%if(al[i].getEmailAddress().equals(""))
							out.println("<i>NA</i>");
						else
							out.println(al[i].getEmailAddress());
						%>
            </font> </td>
        </tr>
        <%

		}//for 1
		
		if(al.length == 0)
		{
		%>
        <tr align="center"> 
          <td colspan="3" style="PADDING-LEFT: 12px"> <font size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
            <em>No Address found.</em> </font> </td>
        </tr>
        <%
		}
		%>
        <tr align="center" bgcolor="#99FFFF"> 
          <td colspan="3">&nbsp;</td>
        </tr>
      </table>
		</tr>
	</td>	
</table>
</form>

<%
	}//try
	catch(Exception e)
	{
		out.println("<p><div align=center><strong>"+e.getMessage()+"</strong></div></p>");
	}
	
}//master if
else if(error){
    out.println("<p><div align=center><strong>Please enter valid e-mail address</strong></div></p>");
}
%>


</body>
</html>
