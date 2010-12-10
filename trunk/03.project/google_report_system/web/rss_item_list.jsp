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
    
    <title>Rss Item List - <s:property value="rss.typeName"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link rel="stylesheet" type="text/css" href="static/css/main.css">
	<style type="text/css">
	 ul li.c1 {
		width:50px;
		text-align:right;
		padding-right:10px;
	 }
	 ul li.c2 {
		width:75%;
	 }
	 ul li.c3 {
		width:120px;
	 }
	 ul li.c4 {
		width:100px;
	 }
	</style>
  </head>
  
  <body>
    <h1><s:property value="rss.typeName"/></h1>
    <span style="font-size: 12px;">Use Time: <s:property value="rss.useTime" /> ms</span>
    <br />
    <ul style="list-style:none">
	<s:iterator value="list" status="st">
		<li class="c1 <s:if test="#st.odd">odd</s:if><s:else>even</s:else>"><s:property value="#st.index + 1" /></li>
		<li class="c2 <s:if test="#st.odd">odd</s:if><s:else>even</s:else>"><s:property value="title" escape="false"/></li>
		<li class="c3 <s:if test="#st.odd">odd</s:if><s:else>even</s:else>"><s:property value="publishDate"/></li>
		<li class="c4 <s:if test="#st.odd">odd</s:if><s:else>even</s:else>"><s:if test="href == null">&nbsp;</s:if><s:else><a href="<s:property value='href'/>" target="_blank">see detail</a></s:else></li>
	</s:iterator>
</ul>
  </body>
</html>
