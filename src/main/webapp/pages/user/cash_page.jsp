<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06.10.2021
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<html>
<head>
    <title><fmt:message key="title.cash_page"/> </title>

    <link href="${pageContext.request.contextPath}/css/user/cash.css" rel="stylesheet" type="text/css"/>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/admin/navbar.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<jsp:include page="../user_navbar.jsp"/>
<div class="box">
    <table class="table_data">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <tr>
                <td><Label class="label-text"><fmt:message key="label.cart_code"/> :</Label></td>
                <td colspan="2"><input type="text" pattern="[1-9]{16,19}" name="card_number" minlength="16" maxlength="19" autocomplete="off" placeholder="#### #### #### ####" required></td>
            </tr>

            <tr>
                <td><Label class="label-text"><fmt:message key="label.card.name"/>: </Label></td>
                <td COLSPAN="2"> <input type="text" name="card_name" pattern="[A-ZА-Я\s]+" placeholder="<fmt:message key="placeholder.code.name"/>"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <select name="card_month" required>
                        <option disabled selected hidden><fmt:message key="label.month"/></option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option>6</option>
                        <option>7</option>
                        <option>8</option>
                        <option>9</option>
                        <option>10</option>
                        <option>11</option>
                        <option>12</option>
                    </select>
                </td>
                <td>
                    <select name="card_year" required>
                        <option disabled selected hidden><fmt:message key="label.Year"/></option>
                        <option>2021</option>
                        <option>2022</option>
                        <option>2023</option>
                        <option>2024</option>
                        <option>2025</option>
                        <option>2026</option>
                        <option>2027</option>
                        <option>2028</option>
                        <option>2029</option>
                        <option>2030</option>
                        <option>2031</option>
                        <option>2032</option>
                    </select>
                </td>
                <td>
                    <input type="text" name="card_cvv" pattern="[0-9]{3}" maxlength="3" class="CVV_code" autocomplete="off" placeholder="<fmt:message key="label.CVV"/>" required>
                </td>
            </tr>

            <tr>
                <td><Label class="label-text"><fmt:message key="label.input_cash"/> : </Label></td>
                <td COLSPAN="2"><input type="number" name="money" step="0.01" placeholder="<fmt:message key="placeholder.currency"/> "></td>
            </tr>

            <tr>
               <td></td>
                <td colspan="2">
                    <button class="save_button" type="submit" name="command" value="add_money_to_cash"><fmt:message key="button.submit"/></button>
                </td>
                <td></td>
            </tr>
        </form>
    </table>
</div>

</body>
</body>
</html>
