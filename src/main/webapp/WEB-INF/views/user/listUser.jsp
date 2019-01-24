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
    <%@include file="../_header.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <script type="text/javascript">
        function deleteuser(id) {
            var b = window.confirm("您确定要删除吗？")
            if(b){
                window.location.href="${pageContext.request.contextPath }/user/delete/" + id;
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
<div align="right">${sessionScope.form.nickname }，欢迎您!当前${count }人在线！&nbsp;&nbsp;&nbsp;&nbsp;<a href="${path }/user/logout">退出</a></div>
<br/><br/>

<c:if test="${!empty(users)}">
    <form action="<c:url value="${path}/list/${page.start}/${count}"/>" method="post">
        <p>记录总数:${page.total}，
            每页记录数:<input name="count" type="text" value="${page.count}"/>(<input type="submit" value="修改"/>)，
            总页数:${page.totalPage}，当前页:${page.start}</p>
    </form>
    <br/><br/>
    <a href="javascript:void(0)" onclick="deletealluser() ">删除所有</a>
<table width="80%" border="1" cellspacing="4" cellpadding="4" align="center" style="border-collapse: collapse">
    <tr>
        <th>编号</th>
        <th>账号</th>
        <th>姓名</th>
        <th>邮箱</th>
        <th>生日</th>
        <th>创建日期</th>
        <th>操作</th>
    </tr>
<c:forEach var="user" items="${users }" varStatus="status">
    <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.nickname}</td>
        <td>${user.email}</td>
        <td>${user.birthday}</td>
        <td>${user.createdtime}</td>
        <td>
            &nbsp&nbsp<a href="${path }/user/edit/${user.id}">编辑</a>&nbsp&nbsp|&nbsp&nbsp<a href="javascript:void(0)" onclick="deleteuser('${user.id}') ">删除</a>&nbsp&nbsp
        </td>
    </tr>
</c:forEach>
    <tr>
        <td colspan="7">
            <nav>
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="<c:url value="${path}/list/1/${page.count}"/>">首页</a></li>
                    <li class="page-item"><a class="page-link" href="<c:url value="${path}/list/${page.start-1>1?page.start-1:1}/${page.count}"/>">«</a>
                    </li>

                    <c:forEach begin="1" end="${page.totalPage}" varStatus="loop">
                        <c:set var="active" value="${loop.index==page.start?'active':''}"/>
                        <li class="page-item ${active}">
                            <a class="page-link" href="<c:url value="${path}/list/${loop.index}/${page.count}"/>">${loop.index}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link" href="<c:url value="${path}/list/${page.start+1<page.totalPage?page.start+1:page.totalPage}/${page.count}"/>">»</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="<c:url value="${path}/list/${page.totalPage}/${page.count}"/>">尾页</a>
                    </li>
                </ul>
            </nav>
        </td>
    </tr>
</table>
</c:if>
<c:if test="${empty(users)}">
    没有任何用户存在！
</c:if>
</div>
<%@include file="../_footer.jsp" %>
</body>
</html>
