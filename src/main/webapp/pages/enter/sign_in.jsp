




<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>

<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title> <fmt:message key="title.sign_in"/> </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sign_in.css" rel="stylesheet">

</head>
<body>
<header>
    <h1 id="Pharmacy" align="center"> <fmt:message key="text.pharmacy"/> </h1>
    <hr>
</header>
<section>
    <form>
        <div class="sign_in">
            <p align="center">
                <strong><fmt:message key="text.enter_data"/>:</strong>
            </p>
            <label class="label" for="input_login">
                <strong><fmt:message key="label.placeholder.E-mail"/>:</strong>
            </label>
            <br>
            <input class="input_text" id= "input_login" type="login" name="login" placeholder="<fmt:message key="label.placeholder.E-mail"/> " required>
            <br>
            <label class="label" for="input_pass">
                <strong><fmt:message key="label.placeholder.password"/>:</strong>
            </label>
            <br>
            <input class="input_text" id="input_pass" type="password" name="pass" placeholder="<fmt:message key="label.placeholder.password"/>" required>
            <br>
            <a href="${pageContext.request.contextPath}/registration.jsp">
                <input class="confirm-button" id="confirm_sign_in" type="button" name="confirm" value="<fmt:message key="button.name.sign_in"/>">
            </a>

            <form action="${pageContext.request.contextPath}\registration.jsp">
                <input class="confirm-button" id="confirm_registr" type="button" name="registr" value="<fmt:message key="button.name.registration"/> ">
            </form>

        </div>
    </form>
</section>

<button type="button" onclick="location.href='registration.jsp'"> to page1</button>


</body>
</html>