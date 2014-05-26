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
    <h1>管理实验页面</h1>
  </div>
  <div id="right_content">
    添加实验点击:
    <select disabled="disabled">
  	<option>C++实验课</option>
	</select>
    <button type="button" class="addExp">添加实验</button>
  </div>
  <div id="right_add">
    <h3>课程实验列表</h3>
  </div>
  <div id="right_content">
    <div id="form">
      <ul>
      <c:choose>
      <c:when test="${sessionScope.experiments eq null }">
      <li><span>没有实验</span></li>
      </c:when>
      <c:otherwise>
      
      	<c:forEach var="experiment" items="${ sessionScope.experiments }">
      		<c:if test="${experiment.courseId eq sessionScope.experimentCourseId }">
      			<li><span>实验一：<c:out value = "${experiment.name }"/></span><button type="button">开放实验</button><button type="button" id="experiment${experiment.id }">编辑</button><a href="DeleteExperimentServlet?experimentId=${experiment.id}&courseId=${experiment.courseId}"><button type="button">删除</button></a></li>
      		</c:if>
      	</c:forEach>
    	</c:otherwise>
    </c:choose>
    </ul>
  </div>
  </div>
  </div>

<%@ include file="parts/footer.jsp" %>
<script>
  	 $('.addExp').on('click',function(){
         $.layer({
    	type: 2,
    	title: '添加实验',
    	area: ['600px', '450px'],
		offset: [($(window).height() - 450)/2 + 'px', ''],
    	shade: [0],
        iframe: {src:"addExp.html"},
		success: function(){
        layer.shift('top'); 
    }
});
     });
  	<c:forEach var="experiment" items="${ sessionScope.experiments }">
  	<c:if test="${experiment.courseId eq sessionScope.experimentCourseId }">
	 $('#experiment${experiment.id }').on('click',function(){
         $.layer({
    	type: 2,
    	title: '编辑实验',
    	area: ['600px', '450px'],
		offset: [($(window).height() - 450)/2 + 'px', ''],
    	shade: [0],
        iframe: {src:"editExp.jsp?experimentId=${experiment.id}"},
		success: function(){
        layer.shift('top'); 
    }
});
     });
	 </c:if>
	 </c:forEach>
  </script>
</div>
</body>
</html>
