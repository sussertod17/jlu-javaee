<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录</title>
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
        		<p class="navbar-text navbar-right">您好，请登录!</p>
    		</div>
		</div>
	</nav>
	<div class="container" style="width: 400px;">
		<h3 style="text-align: center;">登录</h3>
		<form action=LoginServlet method = "post">
			<div class="form-group">
	            <label for="user">学工号：</label>
	            <input type="text" name="userNum" class="form-control" id="userNum" placeholder="请输入学工号" required="required"/>
	        </div>
			<div class="form-group">
	            <label for="password">密码：</label>
	            <input type="password" name="userPswd" class="form-control" id="userPswd" placeholder="请输入密码" required="required"/>
	        </div>
	
			<div class="form-group" style="text-align: center;">
				<input class="btn btn btn-primary" type=submit value="登录"><br>
				<a href="register.jsp">注册</a>
			</div>
		</form>
	</div>
</body>
</html>
