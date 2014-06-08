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
    <button type="button"><c:out value="${sessionScope.courseName }"/></button>
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
      
      	<c:forEach var="experiment" items="${ sessionScope.experiments }" varStatus="count">
      		<c:if test="${experiment.courseId eq sessionScope.experimentCourseId }">
      			<li><span>实验<c:out value="${count.count }"></c:out>：<c:out value = "${experiment.name }"/></span>
      			<button type="button" id="experiment${experiment.id }report">查看实验报告</button>
      			<button type="button" id="experiment${experiment.id }">编辑</button>
      			<a href="DeleteExperimentServlet?experimentId=${experiment.id}&courseId=${experiment.courseId}"><button type="button">删除</button></a></li>
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
    	btns:2,
    	btn: ['确定', '取消'],
    	yes: function(index){
			var name = layer.getChildFrame("#experiment_name", index).val();
			var type = layer.getChildFrame("#experiment_type", index).val();
			var duration = layer.getChildFrame("#experiment_duration", index).val();
			var purpose = layer.getChildFrame("#experiment_purpose", index).val();
			var demand = layer.getChildFrame("#experiment_demand", index).val();
			var description = layer.getChildFrame("#experiment_description", index).val();
			var courseName = "${sessionScope.courseName}";
			var data = {"id":"${sessionScope.id.id}","courseId":"${sessionScope.experimentCourseId}","name":name,"type":type,"duration":duration,"purpose":purpose,"demand":demand,"description":description,"courseName":courseName};
			if(experimentValidate(name,type,duration,purpose,demand,description) == 0){
				return false;
			}
			$.ajax({ 
		          type : "post", 
		          url : "CreateExperimentServlet", 
		          data : data, 
		          async : false,
		          success:function(data){
					location.reload();
		          }
		          });
			layer.close(index);
			},
    	area: ['600px', '600px'],
		offset: [($(window).height() - 600)/2 + 'px', ''],
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
    	btns:2,
    	btn: ['确定', '取消'],
    	title: '编辑实验',
    	yes: function(index){
			var name = layer.getChildFrame("#experiment_name", index).val();
			var type = layer.getChildFrame("#experiment_type", index).val();
			var duration = layer.getChildFrame("#experiment_duration", index).val();
			var purpose = layer.getChildFrame("#experiment_purpose", index).val();
			var demand = layer.getChildFrame("#experiment_demand", index).val();
			var description = layer.getChildFrame("#experiment_description", index).val();
			var data = {"courseId":"${sessionScope.experimentCourseId}","experimentId":"${experiment.id}","name":name,"type":type,"duration":duration,"purpose":purpose,"demand":demand,"description":description};
			if(experimentValidate(name,type,duration,purpose,demand,description) == 0){
				return false;
			}
			$.ajax({ 
		          type : "post", 
		          url : "UpdateExperimentServlet", 
		          data : data, 
		          async : false,
		          success:function(data){
					location.reload();
		          }
		          });
			layer.close(index);
			},
    	area: ['600px', '600px'],
		offset: [($(window).height() - 600)/2 + 'px', ''],
    	shade: [0],
        iframe: {src:"editExp.jsp?experimentId=${experiment.id}"},
		success: function(){
        	layer.shift('top'); 
    }
});
     });
	 $('#experiment${experiment.id}report').on('click',function(){
		 $.layer({
		    	type: 2,
		    	title: '查看实验报告',
		    	btns:1,
		    	btn: ['返回'],
		       	area: ['600px', '350px'],
				offset: [($(window).height() - 350)/2 + 'px', ''],
		    	shade: [0],
		        iframe: {src:"GetReportLinksServlet?experimentId=${experiment.id}"},
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
