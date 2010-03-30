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
	 div.xx {
	 	widht:100%;
	 	z-index:999;
	 	line-height:33px;
	 	
	 }
	 div.content {
	 	font-size:13px;
	 }
	 .s_title {
	 	font-size:14px;
	 	font-weight:bold;
	 	color:#000066;
	 }
	 i {
	 	font-size:13px;
	 }
	</style>
  </head>
  
  <body>
    <h1><s:property value="typeName"/></h1>
	<s:iterator value="list" status="st">
	<div class="xx">
		<SPAN class="s_title"><s:property value="title" escape="false"/></SPAN>&nbsp;&nbsp;<I><s:property value="publishDate"/></I>
		<br />
		<div class="content" style="width:100%"><s:property value="description" escape="false"/></div>
	</div>
	<hr/>
	</s:iterator>
  </body>
</html>
