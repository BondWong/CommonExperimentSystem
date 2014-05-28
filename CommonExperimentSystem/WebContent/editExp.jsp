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
	<c:forEach var="experiment" items="${ sessionScope.experiments }">
		<c:if test="${experiment.id eq param.experimentId }">
		<form action="#" method="post">
		<h1>实验信息</h1>
		<input type="hidden" name="experimentId" value="experiment.id"/>
        <p>
            <span class="title"><label for="name">实验标题</label></span>
            <input type="text" name="name" value="${experiment.name }" size="51" id="experiment_name"/>
        </p>
        <p>
            <span class="title"><label for="type">类型:</label></span>
            <input type="text" name="type" value="${experiment.type }" size="51" id="experiment_type"/>
        </p>
        <p>
            <span class="title"><label for="duration">截至时间:</label></span>
            <input type="text" name="duration" value="${ experiment.duration}" size="51" id="experiment_duration"/>
        </p>
        <h1>实验详细内容</h1>
        <p>
            <span class="title"><label for="purpose">实验目的:</label></span>
            <textarea name="purpose"  cols="45" rows="6" id="experiment_purpose">${ experiment.purpose}</textarea>
        </p>
        <p>
            <span class="title"><label for="demand">实验要求:</label></span>
            <textarea name="demand" cols="45" rows="6" id="experiment_demand">${ experiment.demand}</textarea>
        </p>
        <p>
            <span class="title"><label for="description">实验描述:</label></span>
            <textarea name="description" cols="45" rows="6" id="experiment_description">${ experiment.description}</textarea>
        </p>
        </form>
        </c:if>
	</c:forEach>
</div>
</body>
</html>