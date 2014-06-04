<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="parts/head.jsp" %>

<body>
<div class="container">
<%@ include file="parts/top.jsp" %>

<c:choose>
<c:when test="${sessionScope.id.userType eq 'STUDENT' }">
<%@ include file="parts/student_left_menu.jsp" %>
</c:when>
<c:when test="${sessionScope.id.userType eq 'PROFESSOR' }">
<%@ include file="parts/professor_left_menu.jsp" %>
</c:when>
<c:when test="${sessionScope.id.userType eq 'ADMIN' }">
<%@ include file="parts/admin_left_menu.jsp" %>
</c:when>
</c:choose>
<div id="right">
  <div id="right_top">
   
  	 <h1>个人信息页面</h1>
  </div>
  <div id="right_content">
    <h3>个人信息</h3>
    <div id="form">
    <form action="UpdateUserInfoServlet" method="post" onsubmit="return userInfoValidate()">
      	<ul>
      		<li>&nbsp;&nbsp;&nbsp;&nbsp;I&nbsp;&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;&nbsp;：<input name="id" value="${sessionScope.id.id }" readOnly/></li>
    		<li>&nbsp;&nbsp;&nbsp;姓&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;：<input type="text" name="name" size="18" id="name" value="${sessionScope.id.name }"/></li>
        	<li>&nbsp;&nbsp;&nbsp;科&nbsp;&nbsp;室&nbsp;&nbsp;&nbsp;：<input name="classification" size="18" value="${sessionScope.id.classification }" readOnly/></li>
        	<li>&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;码&nbsp;&nbsp;&nbsp;：<input type="password" name="newPassword" id="Name" size="18" maxlength="30" /></li>
        	<li>当前密码：<input type="password" name="oldPassword" id="oldPassword" size="18" maxlength="30" /></li>
      	</ul>
        <input type="submit" value="保存" />
     </form>
    </div>
  </div>
</div>
<%@ include file="parts/footer.jsp" %>
</div>
</body>
</html>
