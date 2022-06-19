<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检测结果</title>
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
	<div class="container">
    <center><h3>检测结果显示页面</h3></center>
    
    	
    	<div class="form-group" style="text-align: center">
			<c:choose>			
				<c:when test = "${status eq '0'}"><h4><p class="text-warning">等待</p></h4></c:when>
				<c:when test = "${status eq '1'}"><h4><p class="text-success">阴性</p></h4></c:when>
				<c:when test = "${status eq '2'}"><h4><p class="text-danger">阳性</p></h4></c:when>
			</c:choose>
		</div>
    	

    <form action="${pageContext.request.contextPath}/" method="post">
    	<div class="form-group">
            <label for="age">学号：</label>
            <input type="text" class="form-control" id="userNum" name="userNum" value="${userNum}" readonly="true">
        </div>
    
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="userName" name="userName" value="${userName}" readonly="true">
        </div>
 	
 		 <div class="form-group">
            <label for="name">采样时间：</label>
            <input type="text" class="form-control" id="userName" name="getTime" value="${getTime}" readonly="true">
        </div>
 		
 		 <div class="form-group">
            <label for="name">结果提交时间：</label>
            <input type="text" class="form-control" id="userName" name="resTime" value="${resTime}" readonly="true">
        </div>
 	

       
 		
        <div class="form-group" style="text-align: center">
            <a class="btn btn-default" href="student.jsp">返回</a>

            <a class="btn btn-default" href="student.jsp">关闭</a>
        </div>
    </form>
</div>

</body>
</html>