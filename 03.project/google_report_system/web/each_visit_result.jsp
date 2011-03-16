<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Welcome to My Rss</title>
<link rel="stylesheet" type="text/css" href="static/css/main.css">
<style type="text/css">
 .ab {
	text-decoration:none;
	color:black;
	border-collapse:collapse;
	border-width:1px;
	border-style:solid;
	border-color:#888888;
	background-color:#EEEEEE;
 }
 .ab:hover {
	text-decoration:none;
	color:black;
	background-color:#DEDEDE;
 }
 .ab:visited {
	color:black;
 }
</style>
</head>

<body>

<h1><s:property value="timeRangeStr"/></h1>
<table>
	<s:iterator value="campaignList" status="st" id="id">
		<tr>
			<td><s:property value="campaignId" />:</td><td><s:property value="pageUniqueViews" /></td>
		</tr>
	</s:iterator>
</table>

</body>
</html>