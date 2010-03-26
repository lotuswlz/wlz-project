<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Welcome to My Rss</title>
<style>
body {
	margin: 3 3 3 3 ;
}
 h1 {
 	background-color:#D9C075;
	font-size:18px;
	font-weight:bold;
	color: white;
	line-height:36px;
	padding-left:5px;
 }
 hr {
	color:#C0C0C0;
	border-width:1px;
 }
 ul {
	margin-left:0px;
	margin-top:0px;
	float:left;
	padding:0px;
	width:100%;
 }
 ul li {
	font-size:16px;
	padding-left: 5px;
	line-height:30px;
	float: left;
	width:24%;
 }
 
 li.odd {
	background-color: #F2F2F2;
 }

 li.even {
	background-color: white;
 }

 a {
	text-decoration:none;
	color:black;
 }
 a:hover {
	text-decoration:underline;
	color:black;
 }
 a:visited {
	color:black;
 }
</style>

</head>

<body>

<h1>NEWS</h1>
<ul style="list-style:none">
	<s:iterator value="list" status="st">
		<li <s:if test="#st.index/4%2==0">class="odd"</s:if><s:else>class="even"</s:else>><a href="<s:property value='link'/>" target="_blank"><s:property value='typeName'/></a></li>
	</s:iterator>	
</ul>

</body>
</html>