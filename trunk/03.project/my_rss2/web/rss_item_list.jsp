<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Rss Item List - <s:property value="typeName"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link rel="stylesheet" type="text/css" href="static/css/main.css">
	<style type="text/css">
	 ul li {
		width:100%;
	 }
	</style>
  </head>
  
  <body>
    <h1><s:property value="typeName"/></h1>
    <ul style="list-style:none">
	<s:iterator value="list" status="st">
		<li <s:if test="#st.odd">class="odd"</s:if><s:else>class="even"</s:else>><s:property value="title" escape="false"/></li>
	</s:iterator>
</ul>
  </body>
</html>
