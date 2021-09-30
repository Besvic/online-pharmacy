<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29.09.2021
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<html>
<head>
    <title><fmt:message key="title.order_list"/> </title>

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
            <th><fmt:message key="label.user.name"/> </th>
            <th><fmt:message key="label.user.cash"/> </th>
            <th><fmt:message key="label.order.price"/> </th>
            <th><fmt:message key="label.order.quantity"/> </th>
            <td><fmt:message key="label.order.date"/> </td>
        </tr>
        </thead>

        <c:forEach var="order_list" items="${order_list_c}">
            <tbody>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <tr class="row_custom">
                    <td>${order_list.user.name}</td>
                    <td>${order_list.user.cash}</td>
                    <td>${order_list.product.price}</td>
                    <td>${order_list.quantity}</td>
                    <td>${order_list.date}</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <button class="button" type="submit" name="command" value="go_to_order_list_by_user_id"><fmt:message key="button.info"/> </button>
                        <input type="hidden" name="user_id" value="${order_list.user.id}"/>
                        <input type="hidden" name="date" value="${order_list.date}"/>
                    </td>

                </tr>
            </form>
            </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>
