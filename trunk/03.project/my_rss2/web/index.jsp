<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Welcome to My Rss</title>
<link rel="stylesheet" type="text/css" href="static/css/main.css">
<style type="text/css">
 ul li {
	width:24%;
 }
</style>
</head>

<body>

<s:iterator value="rootList" status="str" id="id">
<h1><s:property value="catName"/></h1>
<ul style="list-style:none">
	<s:iterator value="#id.childTypeList" status="st">
		<li <s:if test="#st.index/4%2==0">class="odd"</s:if><s:else>class="even"</s:else>><a href="rss_detail.action?catId=<s:property value='catId'/>" target="_blank"><s:property value='typeName'/></a></li>
	</s:iterator>	
</ul>
<p>&nbsp;</p>
</s:iterator>
</body>
</html>