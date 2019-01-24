<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-01-13
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>用户登录</title>
    <script type="text/javascript">
        function validate_form() {
            var name = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            if(name == null || name == ""){
                alert("用户名不能为空！")
                return false;
            }
            if(password == null || password == ""){
                alert("密码不能为空！")
                return false;
            }
            return true;
        }
    </script>
</head>
<body style="text-align: center">
<div style="text-align: center">
<form action="${path}/user/login" method="post">
    <table align="center">
        <tr>
            <td>用户名:</td>
            <td>
                <input id="username" type="text" name="username"/>
            </td>
        </tr>
        <tr>
            <td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
            <td>
                <input id="password" type="text" name="password"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" name="register" value="注  册" onclick="window.location.href='${path}/user/register';"/>
                <input type="submit" name="login" value="登  录"/>
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
