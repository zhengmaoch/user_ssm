<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-01-07
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
  <head>
    <title>首页</title>
  </head>
  <body style="text-align: center">

  <h2>用户管理网站</h2>
  <br/><br/>
  <c:if test="${!empty(user)}">
    欢迎您：${user.nickname} <a href="${path }/user/logout">注销</a>
  </c:if>
<div style="text-align: right">
  <c:if test="${empty(user)}">
    <a href="${path }/user/register">注册</a>
    <a href="${path }/user/login">登录</a>
  </c:if>
</div>
  <br/>
  <hr>
  </body>
</html>
