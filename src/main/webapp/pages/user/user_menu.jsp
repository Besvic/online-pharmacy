<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 20.09.2021
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<html>
<head>
    <title><fmt:message key="title.menu"/> </title>

    <link href="${pageContext.request.contextPath}/css/user/menu.css" rel="stylesheet" type="text/css"/>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/admin/navbar.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../user_navbar.jsp"/>
<div class="container">
    <main class="main_wrapper">
        <p class="main_wrapper_text">
            <fmt:message key="label.menu.main"/>
        </p>

        <p class="main_wrapper_text">
            <fmt:message key="label.menu.main"/>
        </p>
    </main>
</div>
<div class="container">
    <footer class="footer_wrapper">
        <p class="footer_wrapper_text">
            <fmt:message key="label.menu.contact"/> <br>
            <fmt:message key="label.menu.street"/> <br>
            <fmt:message key="label.menu.phoneNumber"/> <br>
            <fmt:message key="label.menu.hours.work"/>
        </p>
    </footer>
</div>
</body>
</html>
