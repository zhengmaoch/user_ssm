<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-01-15
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户信息</title>
    <script language="javascript" type="text/javascript" src="js/WdatePicker.js"></script>
</head>
<body style="text-align: center">
<div style="text-align: center">
<form action="${pageContext.request.contextPath}/servlet/UpdateUserServlet" method="post">
    <table align="center">
        <tr style="display: none">
            <td>编号:</td>
            <td>
                <input type="text" name="id" value="${user.id }">
            </td>
        </tr>
            <tr>
                <td>账号:</td>
                <td>
                    <input type="text" name="username" value="${user.username }">
                </td>
            </tr>
            <tr>
                <td>密码:</td>
                <td>
                    <input type="text" name="password" value="${user.password }">
                </td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td>
                    <input type="text" name="email" value="${user.email }">
                </td>
            </tr>
            <tr>
                <td>生日:</td>
                <td>
                    <input id="d11" type="text" name="birthday" value="${user.birthday }" onClick="WdatePicker()">
                </td>
            </tr>
            <tr>
                <td>姓名:</td>
                <td>
                    <input type="text" name="nikename" value="${user.nikename }">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="save" value="保  存">
                </td>
                <td>
                    <input type="button" name="cancel" value="取  消" onclick="window.location.href='${pageContext.request.contextPath}/servlet/ListUserServlet'">
                </td>
            </tr>
    </table>
</form>
</div>
</body>
</html>
