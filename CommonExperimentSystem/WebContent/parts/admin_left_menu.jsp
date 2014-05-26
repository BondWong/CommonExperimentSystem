<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="menue_left">
  <div id="left_top">
    <h1>系统管理</h1>
  </div>
  <div id="line"></div>
  <div id="menue_nav">
    <ul>
      <li><a href="GetClassification">管理用户</a></li>
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