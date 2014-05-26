<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="parts/head.jsp" %>

<body>
<div class="container">
<%@ include file="parts/top.jsp" %>
<%@ include file="parts/professor_left_menu.jsp" %>
<div id="right">
  <div id="right_top">
    <h1>申请实验课程</h1>
  </div>
  <div id="right_content">
    课程申请点击:<button type="button" class="addCourse">申请实验课程</button>
  </div>
  <div id="right_add">
    <h3>已添加实验课程：</h3>
  </div>
  <div id="right_content">
    <div id="form">
      <ul>
      <c:choose>
      	<c:when test="${sessionScope.createdCourses eq null }">
      		<li><span>没有课程</span></li>
      	</c:when>
      	<c:otherwise>
      		<c:forEach var="course" items="${sessionScope.createdCourses }" >
      			<li><span>${ course.name}</span><button type="button" id="course${course.id }">编辑信息</button><a href="GetExperimentsServlet?id=${sessionScope.id.id }&courseId=${course.id}"><button type="button" class="student_management">管理实验</button></a><a href="DeleteCourseServlet?id=${sessionScope.id.id }&courseId=${course.id }"><button type="button">删除</button></a></li>
      		</c:forEach>
      	</c:otherwise>
      </c:choose>
    </ul>
  </div>
    
  </div>

  
</div>

<%@ include file="parts/footer.jsp" %>

</div>
  <script>
  	 $('.addCourse').on('click',function(){
         $.layer({
    	type: 2,
		btns:2,
		btn: ['确定', '取消'],
    	title: '添加课程',
    	area: ['600px', '250px'],
		offset: [($(window).height() - 450)/2 + 'px', ''],
    	shade: [0],
        iframe: {src:"addCourse.html"},
		success: function(){
        layer.shift('top'); 
    }
});
     });
  	<c:forEach var="course" items="${sessionScope.createdCourses }" >
	  $('#course${course.id}').on('click',function(){
         $.layer({
    	type: 2,
		btns:2,
		btn: ['确定', '取消'],
    	title: '编辑课程',
    	area: ['600px', '250px'],
		offset: [($(window).height() - 450)/2 + 'px', ''],
    	shade: [0],
        iframe: {src:"editCourse.jsp?courseId=${course.id}"},
		success: function(){
        layer.shift('top'); 
    }
});
     });
	  </c:forEach>
  </script>
</body>
</html>
