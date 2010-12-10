<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Welcome to My Rss</title>
<link rel="stylesheet" type="text/css" href="static/css/main.css">
<style type="text/css">

</style>
</head>

<body>

<h1><s:property value="timeRange"/></h1>
<table>
	<tr>
		<td>Abstract Visitor: </td>
		<td><s:property value="form.totalVisitors"/></td>
	</tr>
	<tr>
		<td>Return Visit Rate</td>
		<td><s:property value="form.totalReturnVisitsRate"/></td>
	</tr>
	<tr>
		<td>Top 10 Sources Bounce Rate</td>
		<td><s:property value="form.topTenBounceRate"/></td>
	</tr>
	<tr>
		<td>Top 10 Sources Return Visit Percentage</td>
		<td><s:property value="form.topTenReturnVisitRate"/></td>
	</tr>
	<tr>
		<td>Avg. time on GB page</td>
		<td><s:property value="form.avgGBTimeOnPage"/></td>
	</tr>
	<tr>
		<td>Avg. time on GBC page</td>
		<td><s:property value="form.avgGBCTimeOnPage"/></td>
	</tr>
	<tr>
		<td>Avg. time for new visits on landing page</td>
		<td><s:property value="form.newVisitTimeOnPage"/></td>
	</tr>
	<tr>
		<td>GB visit</td>
		<td><s:property value="form.totalGBUniqVisits"/></td>
	</tr>
	<tr>
		<td>GBC visit</td>
		<td><s:property value="form.totalGBCUniqVisits"/></td>
	</tr>
	<tr>
		<td>Uniq. visit to GB</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>Uniq. visit to GBC</td>
		<td>&nbsp;</td>
	</tr>
</table>

</body>
</html>