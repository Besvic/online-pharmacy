<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 15.09.2021
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<html>
<head>
    <title>404 Error</title>
</head>
<body>
<p>
    Error-404
</p>
<p > session: ${sessionScope.error} </p>
<p > request: ${error} </p>
</body>
</html>
