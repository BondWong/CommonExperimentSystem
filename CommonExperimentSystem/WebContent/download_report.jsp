<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<c:choose>
			<c:when test="${sessionScope.reports eq null }">
				<li>没有实验报告</li>
			</c:when>
			<c:otherwise>
				<c:forEach var="report" items="${sessionScope.reports }">
					<li><c:out value="${report}"></c:out><a href="FileServlet?uri=${report}"><button type="button">下载</button></a></li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</ul>
</body>
</html>