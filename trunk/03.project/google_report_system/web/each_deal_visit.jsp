<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Welcome to Google Analytics Test System</title>
<link rel="stylesheet" type="text/css" href="static/css/main.css">
<style type="text/css">
 ul li {
	width:24%;
 }

</style>
</head>

<body>
<form action="view_each_deal_page.action" method="post">
<table>
<tr>
	<td><s:textfield name="filePath" label="filePath" cssStyle="width:400px;"/></td>
</tr>
<tr>
	<td colspan="2" align="right"><input type="submit" value="submit"/></td>
</tr>
<table>
</form>
</body>
</html>