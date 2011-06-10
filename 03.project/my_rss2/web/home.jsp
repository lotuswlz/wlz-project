<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Welcome to My Rss</title>
<link rel="stylesheet" type="text/css" href="static/css/main.css">
<style>
td {
	line-height:30px;
    padding:5px;
}
.odd {
	background-color:#dddddd;
}
.even {
	background-color:#ffffff;
}
</style>
</head>

<body>
<h1>Go to Rss Index, <a href="/my_rss/rss_index.action" target="_self">click here</a></h1>
<hr/>
<form action="/my_rss/index.action" method="post">
<label>Path: </label><s:textfield name="path"/><br/>
<label>Last Modified Date: </label><s:textfield name="timeStr"/><br/>
<label>Extension: </label><s:textfield name="ext"/><br/>
<label>Base Path(xml): </label><s:textfield name="basePath"/><br/>
<input type="submit" value="submit"/><br/>
</form>
<hr/>
<table>
	<s:iterator value="list" status="st" id="id">
		<tr>
		<td style="width: 50px;" <s:if test="#st.index%2==0">class="odd"</s:if><s:else>class="even"</s:else>><s:property value='#st.index'/></td>
		<td style="width: 600px;"<s:if test="#st.index%2==0">class="odd"</s:if><s:else>class="even"</s:else>><s:property value='#id.path'/></td>
		<td style="width: 300px;" <s:if test="#st.index%2==0">class="odd"</s:if><s:else>class="even"</s:else>><s:if test="!#id.directory"><s:property value='#id.name'/></s:if><s:else>&nbsp;</s:else></td>
		<td style="width: 250px;"<s:if test="#st.index%2==0">class="odd"</s:if><s:else>class="even"</s:else>><s:property value='#id.fileModifidTimeStr'/></td>
		</tr>
	</s:iterator>	
</ul>
</body>
</html>