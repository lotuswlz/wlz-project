<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Page</title>
</head>
<body>
Error<br/>
<c:choose>
    	<c:when test="${sessionScope.errormessage!=null}">
				 <c:out value="${sessionScope.errormessage}" escapeXml="false"/>
			 <c:remove var="errormessage" scope="session" />
    	</c:when>
    	<c:otherwise><%out.println("Unknow Exception");%></c:otherwise>
    </c:choose> 
</body>
</html>