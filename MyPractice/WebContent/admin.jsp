<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理界面</title>
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

<script>
        function deleteUser(id) {
            if (confirm("确定要删除吗？")) {
                location.href = "${pageContext.request.contextPath}/DeleteUserByIdServlet?id=" + id;
            }
        }
 
        window.onload = function () {
            document.getElementById("delSelected").onclick = function () {
                if (confirm("确认要删除吗？")) {
                    var flag = false;
                    var uids = document.getElementsByName("uid");
                    //判断是否有选中条目
                    for (var i = 0; i < uids.length; i++) {
                        if (uids[i].checked) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        document.getElementById("delForm").submit();
                    }
                }
 
            };
            //获取第一个cb
            document.getElementById("firstCb").onclick = function () {
                //获取下边所有的cb
                var cbs = document.getElementsByName("uid")
                //遍历
                for (var i = 0; i < cbs.length; i++) {
                    //设置cbs[i]的状态与第一个cb状态一致
                    cbs[i].checked = this.checked;
                }
 
            }
        }
    </script>

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
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float:right;margin: 5px">
    	<a class="btn btn-primary" href="${pageContext.request.contextPath}/FindAllUserServlet">刷新</a>
        <a class="btn btn-primary" href="adminAdd.jsp">添加用户</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中用户</a>
    </div>
    <form id="delForm" action="${pageContext.request.contextPath}/DeleteUserSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>学工号</th>
                <th>角色</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${list}" var="user" varStatus="s">
                <tr>
                    <td>
                    	<c:if test = "${user.userStatus ne '0'}">
                    		<input type="checkbox" name="uid" value="${user.userId}">
                    	</c:if>
                    </td>
                    <td>${s.count}</td>
                    <td>${user.userName}</td>
                    <td>${user.userNum}</td>
                    <td>
                    	<c:choose>			
							<c:when test = "${user.userStatus eq '0'}"><p class="text-warning">管理员</p></c:when>
							<c:when test = "${user.userStatus eq '1'}"><p class="text-success">核酸采集大白</p></c:when>
							<c:when test = "${user.userStatus eq '2'}"><p class="text-info">检测结果上传</p></c:when>
							<c:when test = "${user.userStatus eq '3'}"><p class="text-danger">被检测者学生</p></c:when>
						</c:choose>
					</td>
                    <td><!--  <a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/UpdateUserByIdServlet?id=${user.userId}">修改</a>&nbsp;-->
                        <c:if test = "${user.userStatus ne '0'}">
                        	<a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.userId});">删除</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6" align="center"></td>
            </tr>
        </table>
     </form>
     </div> 
</body>
</html>