<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/iframeE.css" />
</head>
<body>
<div class="container">
	<c:forEach var="course" items="${sessionScope.createdCourses }" >
		<c:if test="${course.id eq param.courseId }">
		<form action="#" method="post">
		<h1>课程信息</h1>
		<input type="hidden" name="courseId" value="course.id"/>
        <p>
            <span class="title"><label for="name">名称</label></span>
            <input type="text" name="name" value="${ course.name}" size="51" id="course_name"/>
        </p>
        <p>
            <span class="title"><label for="duration">开课时间:</label></span>
            <input type="text" name="duration"  value="${course.duration }" size="51" id="course_duration"/>
        </p>
        <p>
            <span class="title"><label for="classTime">上课时间:</label></span>
            <input type="text" name="classTime"  value="${course.classTime }" size="51" id="course_classTime"/>
        </p>
        </form>
        </c:if>
	</c:forEach>
</div>
</body>
</html>