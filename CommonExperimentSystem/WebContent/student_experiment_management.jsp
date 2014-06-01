<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="parts/head.jsp" %>

<body>
<div class="container">
<%@ include file="parts/top.jsp" %>

<%@ include file="parts/student_left_menu.jsp" %>

<div id="right">
  <div id="right_top">
    <h1>查看实验页面</h1>
  </div>
  <div id="right_content">
    所在课程:
  	<button type="button">${sessionScope.courseName}</button>
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
      
      	<c:forEach var="experiment" items="${ sessionScope.experiments }" varStatus="status">
      		<c:if test="${experiment.courseId eq sessionScope.experimentCourseId }">
      			<li><span>实验${status.count}：<c:out value = "${experiment.name }"/></span>
      			<button type="button" id="experiment${experiment.id }">查看</button>
      			<script>
      			$('#experiment${experiment.id}').on('click',function(){
         			$.layer({
    					type: 2,
    					btns:1,
    					btn: ['返回'],
    					title: '查看实验',
    					area: ['650px', '600px'],
						offset: [($(window).height() - 600)/2 + 'px', ''],
    					shade: [0],
        				iframe: {src:"showExp.jsp?experimentId=${experiment.id}"},
						success: function(){
        					layer.shift('top'); 
    					}
					});
				});
      			</script>
      			<button type="button" class="upExp">上传实验报告</button></li>
      			<script>
				$('.upExp').on('click',function(){
        		$.layer({
    			type: 2,
    			btns:1,
    			title: '上传实验报告',
    			area: ['300px', '150px'],
				offset: [($(window).height() - 150)/2 + 'px', ''],
    			shade: [0],
        		iframe: {src:"SentReportCheckServlet?id=${sessionScope.id.id}&experimentId=${experiment.id}"},
				success: function(){
        			layer.shift('top'); 
    			}
				});
			});
			</script>
      		</c:if>
      	</c:forEach>
    	</c:otherwise>
    </c:choose>
    </ul>
  </div>
  </div>
  </div>  

<%@ include file="parts/footer.jsp" %>
</div>

</body>
</html>
