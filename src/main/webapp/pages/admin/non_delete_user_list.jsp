<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 25.09.2021
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<html>
<head>
    <title>user_list</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/admin/navbar.css" rel="stylesheet" type="text/css"/>

    <link href="${pageContext.request.contextPath}/css/admin/table.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<jsp:include page="../admin_navbar.jsp"/>
<div class="table_custom">
    <table align="center">
        <thead>
        <tr class="first_row">
            <th><fmt:message key="label.user.photo"/> </th>
            <th><fmt:message key="label.user.name"/> </th>
            <th><fmt:message key="label.user.login"/> </th>
            <th><fmt:message key="label.user.cash"/> </th>
            <th><fmt:message key="label.user.status"/> </th>
        </tr>
        </thead>


        <c:forEach var="user_list" items="${user_list}">
            <tbody>
                <tr class="row_custom">
                    <td rowspan="2"><img height="120px" width="160" src="${pageContext.request.contextPath}${user_list.photo}" alt="don't upload"/></td>
                    <td>${user_list.name}</td>
                    <td>${user_list.login}</td>
                    <td>${user_list.cash}</td>
                    <td>${user_list.userStatus}</td>
                </tr>

                <tr>
                    <td></td>
                    <td></td>
                    <td>
                        <form action="controller" method="post">
                            <button type="submit" class="button delete" name="command" value="delete_user">delete</button>
                            <input type="hidden" name="user_id" value="${user_list.id}">
                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <button type="submit" class="button" name="command" value="go_check_order">check order</button>
                            <input type="hidden" name="user_id" value="${user_list.id}">
                        </form>
                    </td>
                </tr>
            </tbody>
        </c:forEach>



    </table>
</div>
</body>
</html>
