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
<form action="now_test.action" method="post">
<s:textfield name="username"/>
<s:password name="password"/>
<input type="submit" value="submit"/>
</form>
</body>
</html>