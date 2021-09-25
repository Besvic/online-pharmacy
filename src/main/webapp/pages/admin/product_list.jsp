<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 23.09.2021
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<html>
<head>
    <title><fmt:message key="title.product_list"/> </title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/admin/navbar.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../admin_navbar.jsp"/>

    <div class="table">
        <table>
            <thead class="first_row">
            <tr>
                <th><fmt:message key="label.product.name"/> </th>
                <th><fmt:message key="label.product.dosage"/> <span id="measure_id"> (<fmt:message key="label.product.measure"/>) </span> </th>
                <th><fmt:message key="label.product.quantity"/> </th>
                <th><fmt:message key="label.product.price"/> </th>
                <th><fmt:message key="label.product.manufacture_country"/> </th>
                <th><fmt:message key="label.product.date_of_delivery"/> </th>
                <th colspan="3"><fmt:message key="label.product.change_function"/> </th>

                <th></th>
            </tr>
            </thead>


            <c:forEach var="product_list" items="${product_list}">
                <tr>
                    <td>${product_list.name}</td>
                    <td>${product_list.dosage}(${product_list.measure})</td>
                    <td>${product_list.quantity}</td>
                    <td>${product_list.price}</td>
                    <td>${product_list.manufactureCountry}</td>
                    <td>${product_list.dateOfDelivery}</td>
                    <td>
                        <form action="controller" method="post">
                            <button type="submit" name="command" value="delete_product">delete</button>
                            <input type="hidden" name="product_id" value="${product_list.id}">
                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <button type="submit" name="command" value="go_change_product">change</button>
                            <input type="hidden" name="product_id" value="${product_list.id}">
                           <%-- <input type="hidden" name="product_name" value="${product_list.name}">
                            <input type="hidden" name="dosage" value="${product_list.dosage}">
                            <input type="hidden" name="measure" value="${product_list.measure}">
                            <input type="hidden" name="quantity" value="${product_list.quantity}">
                            <input type="hidden" name="price" value="${product_list.price}">
                            <input type="hidden" name="manufacture_country" value="${product_list.manufactureCountry}">
                            <input type="hidden" name="date" value="${product_list.dateOfDelivery}">--%>


                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="number" name="quantity" placeholder="add quantity" required>
                            <button type="submit" name="command" value="add_product_quantity">add quantity</button>
                            <input type="hidden" name="product_id" value="${product_list.id}">
                        </form>
                    </td>
                </tr>
            </c:forEach>



        </table>
    </div>

</body>
</html>
