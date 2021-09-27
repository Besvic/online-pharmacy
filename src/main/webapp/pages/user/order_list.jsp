<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 27.09.2021
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<html>
<head>
    <title>order list</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/admin/navbar.css" rel="stylesheet" type="text/css"/>

    <link href="${pageContext.request.contextPath}/css/admin/table.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../user_navbar.jsp"/>

<div class="table_custom">
    <table align="center">
        <thead>
        <tr class="first_row">
            <th><fmt:message key="label.product.name"/> </th>
            <th><fmt:message key="label.order.quantity"/> </th>
            <th><fmt:message key="label.order.price"/> </th>
            <th>pick_up</th>


        </tr>
        </thead>

        <c:forEach var="order_list" items="${order_list_n_c}">
            <tbody>
            <form action="controller" method="post">
                <tr class="row_custom">
                    <td>${order_list.product.name}</td>
                    <td><input class="input" type="number" name="quantity" min="1" placeholder="${order_list.quantity}" required></td>
                    <td>${order_list.product.price}</td>
                    <td>
                        <select>
                            <c:forEach var="pharmacy_list" items="${pharmacy_list}">
                                <option >${pharmacy_list.city}, ${pharmacy_list.street}, ${pharmacy_list.number}</option>
                            </c:forEach>
                        </select>

                    </td>
                    <%--<td>${order_list.pharmacy.city}, ${order_list.pharmacy.street}, ${order_list.pharmacy.number}</td>--%>
                </tr>

                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <button class="button" type="submit" name="command" value="by_order">buy</button>
                        <input type="hidden" name="product_id" value="${order_list.product.id}">
                        <input type="hidden" name="order_id" value="${order_list.id}">
                    </td>
                </tr>
            </form>
            </tbody>
        </c:forEach>
    </table>
</div>
</body>
</html>
