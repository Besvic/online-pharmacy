<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 22.09.2021
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<html>
<head>
    <title><fmt:message key="title.add_product"/> </title>

    <link href="${pageContext.request.contextPath}/css/admin/navbar.css" rel="stylesheet" type="text/css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <link href="${pageContext.request.contextPath}/css/admin/add_product.css" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>
<body>

<jsp:include page="../admin_navbar.jsp"/>
<div class="box">
    <table class="table_data">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <tr class="first_row">
                <td class="img_column" rowspan="3"> </td>
                <td><label class="label_text"><fmt:message key="label.product.name"/>: </label></td>
                <td><input class="input" type="text" name="product_name" placeholder="<fmt:message key="label.product.name"/>" required></td>
                <td><label class="label_text"><fmt:message key="label.product.manufacture_country"/>: </label></td>
                <td><input class="input" type="text" name="manufacture_country" placeholder="<fmt:message key="label.product.manufacture_country"/>" required></td>
            </tr>

            <tr class="second_row">
                <td><label class="label_text"><fmt:message key="label.product.dosage"/> </label></td>
                <td><input class="input" type="number" step="0.001" min="0.001"  name="dosage" placeholder="0.020" required></td>
                <td><label class="label_text"> <fmt:message key="label.product.measure"/> </label></td>
                <td><input class="input" type="text" maxlength="10" name="measure" placeholder="g" required></td>

            </tr>
            <tr class="treeth_row">
                <td><label class="label_text"> <fmt:message key="label.product.quantity"/> </label></td>
                <td><input class="input" type="number" min="0" name="quantity" placeholder="<fmt:message key="label.product.quantity"/>" required></td>
                <td><label class="label_text"><fmt:message key="label.product.price"/> (BYR) </label></td>
                <td><input class="input" type="number" step="0.01" min="0" name="price" placeholder="1.11" required></td>

            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><button class="submit_button" type="submit" name = "command" value="add_product"><fmt:message key="button.name.save"/> </button></td>
            </tr>
        </form>
    </table>

    <label class="report_label">${report} </label>
</div>
</body>
