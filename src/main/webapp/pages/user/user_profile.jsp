<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<html>
<head>
    <title>Profile user</title>
</head>
<body>
login = ${sessionScope.user.login}
name = ${sessionScope.user.name}
password = ${sessionScope.user.password}
id = ${sessionScope.user.id}
</body>
</html>