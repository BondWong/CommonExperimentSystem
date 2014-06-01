<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link type="text/css" rel="stylesheet" href="css/iframeE.css" />
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
</head>

<body>
<div class="container">
  <form enctype="multipart/form-data" method="post" id="reportUpload">
  	<input type="file" name="report"/>
  </form>
  <button id="upExp">上传</button>
  <input type="hidden" id="reurl"/>
  <script>
				$('#upExp').on('click',function(){
    				var file = new FormData(document.getElementById("reportUpload"));
					$.ajax({ 
		          		type : "post",
		          		url : "FileServlet?id=${param.id}&experimentId=${param.experimentId}", 
		          		data : file, 
		          		async : false,
		          		cache: false,
		    	        contentType: false,
		    	        processData: false,
		    	        success:function(data){
		    	        	window.location.href="report_sent.jsp";
		    	        }
		          	});
				});
    </script>
</div>

</body>
</html>
