<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="parts/head.jsp" %>

<body>
<div class="container">
<%@ include file="parts/top.jsp" %>
<%@ include file="parts/admin_left_menu.jsp" %><div id="right">
  <div id="right_top">
    <h1>管理班级页面</h1>
  </div>
  <div id="right_add">
    <h3>列表</h3>
  </div>
  <div id="right_content">
    <div id="form">
      <ul>
      <c:choose>
      <c:when test="${sessionScope.classifications eq null }">
      	<li>无数据</li>
      </c:when>
      <c:otherwise>
      	<c:forEach var="classification" items="${sessionScope.classifications }">
    		<c:if test="${sessionScope.id.classification != classification}">
    			<li><span><c:out value="${classification }"/></span><a href="GetUsersServlet?classification=${classification}"><button type="button">查看</button></a><a href="DeleteClassificationServlet?classification=${classification }"><button type="button">删除</button></a></li>
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
