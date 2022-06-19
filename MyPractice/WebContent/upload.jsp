<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传结果</title>
<!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid"> 
    		<div class="navbar-header">
        		<a class="navbar-brand" href="#">核酸检测系统</a>
    		</div>
    		<div>   
       	 	<!--向右对齐-->       
        		<form class="navbar-form navbar-right" role="search" action=ExitServlet>
            		<button type="submit" class="btn btn-default">
                		退出
            		</button>
        		</form>
        		<p class="navbar-text navbar-right">您好，${Name}</p>
    		</div>
		</div>
	</nav>
		<div class="container" style="width: 400px;">
		<h3 style="text-align: center;">检测结果上传界面</h3>
		<form action=UploadStudent method = "post">
			<div class="form-group">
	            <label for="user">试管编号：</label>
	            <input type="text" name="tubeNum" class="form-control" id="tubeNum" placeholder="请输入试管编号,中间用英文逗号隔开" required="required"/>
	        </div>
			<div class="form-group">
            <label >检测结果：</label>
	            <select name="res" class="form-control" id="res">
	                <option value="1">阴性</option>
	                <option value="2">阳性</option>
	            </select>
        	</div>
	
			<div class="form-group" style="text-align: center;">
				<input class="btn btn btn-primary" type=submit value="提交"><br>
			
			</div>
		</form>
	</div>
</body>
</html>