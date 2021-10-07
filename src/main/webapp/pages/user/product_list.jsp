<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 25.09.2021
  Time: 18:25
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

    <link href="${pageContext.request.contextPath}/css/admin/table.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../user_navbar.jsp"/>

<div class="table_custom">
    <table align="center">
        <thead>
        <tr class="first_row">
            <th><fmt:message key="label.product.name"/> </th>
            <th><fmt:message key="label.product.quantity"/> </th>
            <th><fmt:message key="label.product.dosage"/> <span id="measure_id"> (<fmt:message key="label.product.measure"/>) </span> </th>
            <th><fmt:message key="label.product.price"/> </th>
            <th><fmt:message key="label.product.manufacture_country"/> </th>
            <th><fmt:message key="label.product.date_of_delivery"/> </th>
        </tr>
        </thead>

        <c:forEach var="product_list" items="${product_list}">

            <tbody>
            <tr class="row_custom">
                <td>${product_list.name}</td>
                <td>${product_list.quantity}</td>
                <td>${product_list.dosage}(${product_list.measure})</td>
                <td>${product_list.price}</td>
                <td>${product_list.manufactureCountry}</td>
                <td>${product_list.dateOfDelivery}</td>
            </tr>

           <tr>
               <td></td>
               <td></td>
               <td></td>
               <td></td>
               <td></td>
               <td>
                   <form action="${pageContext.request.contextPath}/controller" method="post">
                       <input class="input" width="80px" type="number" name="quantity" min="1" placeholder="<fmt:message key="label.product.quantity"/>" required>
                       <button class="button" type="submit" name="command" value="add_product_in_order"><fmt:message key="button.add_in_order"/> </button>
                       <input type="hidden" name="product_id" value="${product_list.id}">
                   </form>
               </td>
           </tr>
           </tbody>
        </c:forEach>
    </table>
</div>
</body>
</html>
