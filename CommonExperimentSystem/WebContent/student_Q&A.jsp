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
    <h1>答疑页面</h1>
  </div>
  <div id="right_content">
    <h3>问题列表</h3>
    <div id="form">
      <ul>
    	<li><span>老师考试怎么考啊..</span><button type="button" class="answerQ">回答</button></li>
        <li><span>第二章第二题怎么做..</span><button type="button" class="answerQ">回答</button></li>
      </ul>
    </div>
  </div>
  
</div>
 <%@ include file="parts/footer.jsp" %> 
 <script>
  	 $('.answerQ').on('click',function(){
         $.layer({
    	type: 2,
    	title: '回答问题',
    	area: ['600px', '350px'],
		offset: [($(window).height() - 450)/2 + 'px', ''],
    	shade: [0],
        iframe: {src:"addAnswer.html"},
		success: function(){
        layer.shift('top'); 
    }
});
     });
  </script>
</div>
</body>
</html>
