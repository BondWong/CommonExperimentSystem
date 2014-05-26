<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="menue_left">
  <div id="left_top">
    <h1>课程管理</h1>
  </div>
  <div id="line"></div>
  <div id="menue_nav">
    <ul>
      <li><a href="GetSelectedCourseServlet?id=${sessionScope.id.id }">查看课程</a></li>
      <li><a href="student_Q&A.jsp">老师答疑</a></li>
    </ul>
  </div>
  <div id="left_top">
    <h1>帐号信息管理</h1>
  </div>
  <div id="line"></div>
  <div id="menue_nav2">
    <ul>
      <li><a href="user_info.jsp">账号信息</a></li>
      <li><a href="Logout?id=${sessionScope.id.id }">登出</a></li>
    </ul>
  </div>
</div>