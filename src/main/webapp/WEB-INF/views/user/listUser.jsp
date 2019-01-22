<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-01-15
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>用户列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <script type="text/javascript">
        function deleteuser(id) {
            var b = window.confirm("您确定要删除吗？")
            if(b){
                window.location.href="${pageContext.request.contextPath }/user/delete?id=" + id;
            }
        }
        function deletealluser() {
            var b = window.confirm("您确定要删除所有用户吗？")
            if(b){
                window.location.href="${pageContext.request.contextPath }/user/deleteAll";
            }
        }
    </script>
</head>
<body style="text-align: center">
<div align="right">${user.nickname }，欢迎您!当前${count }人在线！&nbsp;&nbsp;&nbsp;&nbsp;<a href="${path }/user/logout">退出</a></div>
<br/><br/>
<c:if test="${!empty(userModels)}">
<table width="70%" border="1" cellspacing="4" cellpadding="4" align="center" style="border-collapse: collapse">
    <tr>
        <th>编号</th>
        <th>账号</th>
        <th>姓名</th>
        <th>邮箱</th>
        <th>生日</th>
        <th>创建日期</th>
        <th>操作</th>
    </tr>
<c:forEach var="user" items="${userModels }" varStatus="status">
    <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.nickname}</td>
        <td>${user.email}</td>
        <td>${user.birthday}</td>
        <td>${user.createdtime}</td>
        <td>
            <a href="${path }/user/edit?id=${user.id}">编辑</a>&nbsp&nbsp|&nbsp&nbsp
            <a href="javascript:void(0)" onclick="deleteuser('${user.id}') ">删除</a>
        </td>
    </tr>
</c:forEach>
    <tr>
        <td colspan="7">
            <a href="javascript:void(0)" onclick="deletealluser() ">删除所有用户</a>
        </td>
    </tr>
</table>
</c:if>
<c:if test="${empty(userModels)}">
    没有任何用户存在！
</c:if>
</div>
</body>
</html>
