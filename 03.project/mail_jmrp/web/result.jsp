<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Welcome to Mail Jmrp Utility</title>
<link rel="stylesheet" type="text/css" href="static/css/main.css">
<style type="text/css">
 ul li {
	width:100%;
 }
 
 label {
	font-size: 14px;
	font-weight: bold;
}
</style>
</head>

<body>

<h1>Welcome to Mail Jmrp Utility</h1>
<hr />
<form action="obtain_mails.action" method="post">
<LABEL>Email: </LABEL><s:textfield name="email"/>
<label>Password: </label><s:password name="password"/>
<input type="submit" value="Start to get mails..." />
</form>
<hr />
size: <s:property value="emailList == null"/>
<ul>
<s:iterator value="emailList" id="id">
	<li><s:property value="#id"/></li>
</s:iterator>
</ul>
</body>
</html>