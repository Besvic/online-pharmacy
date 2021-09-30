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
    <title><fmt:message key="title.pharmacy_list"/> </title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/admin/navbar.css" rel="stylesheet" type="text/css"/>

    <link href="${pageContext.request.contextPath}/css/admin/table.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../admin_navbar.jsp"/>
<div class="table_custom">
    <table align="center">
        <thead>
        <tr class="first_row">
            <th><fmt:message key="label.pharmacy.city"/> </th>
            <th><fmt:message key="label.pharmacy.street"/> </th>
            <th><fmt:message key="label.pharmacy.number"/> </th>
            <th><fmt:message key="label.pharmacy.status"/> </th>

            <form action="${pageContext.request.contextPath}/controller" method="post">
                <th><button type="submit" class="button" name="command" value="go_to_add_pharmacy"><fmt:message key="button.add"/> </button></th>
            </form>
        </tr>
        </thead>


        <c:forEach var="list" items="${pharmacy_list}">
            <tbody>
            <tr class="row_custom">
                <td>${list.city}</td>
                <td>${list.street}</td>
                <td>${list.number}</td>
                <td>${list.status}</td>
            </tr>

            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <c:if test="${list.status.value == 'actual'}">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <button type="submit" class="button delete" name="command" value="delete_pharmacy"><fmt:message key="button.delete"/> </button>
                            <input type="hidden" name="pharmacy_id" value="${list.id}">
                        </form>
                    </c:if>

                    <c:if test="${list.status.value == 'delete'}">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <button type="submit" class="button" name="command" value="restore_pharmacy"><fmt:message key="button.restore"/> </button>
                            <input type="hidden" name="user_id" value="${list.id}">
                        </form>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
</body>
</html>
