<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 30.09.2021
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<html>
<head>
    <title><fmt:message key="title.add"/> </title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/admin/navbar.css" rel="stylesheet" type="text/css"/>

    <link href="${pageContext.request.contextPath}/css/admin/create_info.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../admin_navbar.jsp"/>
<div class="table_custom">
    <table align="center">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <tr class="row_custom">
            <td><label class="label-info"><fmt:message key="label.pharmacy.city"/>:</label> </td>
            <td><input class="input" type="text" maxlength="20" pattern="[a-zA-Zа-я-А-Я]{1,20}" name="pharmacy_city" placeholder="<fmt:message key="placeholder.city"/> " required/></td>
            </tr>
            <tr class="row_custom">
                <td><label><fmt:message key="label.pharmacy.street"/>:</label> </td>
                <td><input class="input" type="text" maxlength="20" pattern="[a-zA-Zа-я-А-Я]{1,20}" name="pharmacy_street" placeholder="<fmt:message key="placeholder.street"/> " required/></td>
            </tr>
            <tr class="row_custom">
                <td><label><fmt:message key="label.pharmacy.number"/>:</label> </td>
                <td align="left"><input class="input" type="number" min="1" max="999" name="pharmacy_number" placeholder="<fmt:message key="placeholder.number"/> " required/></td>
            </tr>

            <tr>
                <td colspan="2"><button class="button" type="submit" name="command" value="create_pharmacy"><fmt:message key="button.submit"/> </button></td>
            </tr>
        </form>
    </table>
</div>
</body>
</html>
