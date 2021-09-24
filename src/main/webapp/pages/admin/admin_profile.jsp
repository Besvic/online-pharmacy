
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 20.09.2021
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<html>
<head>
    <title><fmt:message key="title.profile"/> </title>

    <link href="${pageContext.request.contextPath}/css/admin/admin_profile.css" rel="stylesheet" type="text/css"/>

    <link href="${pageContext.request.contextPath}/css/admin/navbar.css" rel="stylesheet" type="text/css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

</head>
<body>
<jsp:include page="../admin_navbar.jsp"/>
<div class="box">
    <form action="controller">


        <table class="table_data">
            <tr>
                <td class="img_column" rowspan="3">
                    <img class="img" src=" ${pageContext.request.contextPath}${sessionScope.user.photo}" alt="photo didn't upload" >
                </td>
                    <td>
                        <Label class="label-text">
                            <fmt:message key="label.text.full_name"/>:
                        </Label>
                    </td>
                    <td>
                        <input type="text" name="new_name" value="${sessionScope.user.name}" class="input" placeholder="<fmt:message key="placeholder.name"/> " required>
                    </td>
                    <td>
                        <Label class="label-text">
                            <fmt:message key="label.text.cash"/>:
                        </Label>
                    </td>
                    <td>
                        <Label class="label-text">
                            ${sessionScope.user.cash}
                        </Label>
                    </td>
            </tr>
                    <tr>
                        <td>
                            <Label class="label-text">
                                <fmt:message key="label.text.email"/> :
                            </Label>
                        </td>
                        <td>
                            <label class="label-text"> ${sessionScope.user.login}</label>
                        </td>
                        <td>
                            <Label class="label-text">
                                <fmt:message key="label.text.position"/> :
                            </Label>
                        </td>
                        <td>
                            <Label class="label-text">
                                ${sessionScope.user.position}
                            </Label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <Label class="label-text" >
                                <fmt:message key="label.text.password"/> :
                            </Label>
                        </td>
                        <td>
                            <input type="password" name="new_password" class="input" placeholder="<fmt:message key="placeholder.password"/> " required>
                        </td>
                        <td>

                        </td>
                        <td>

                        </td>

                    </tr>

                <tr>
                    <td>

                    </td>
                    <td >
                        <Label class="label-text" class="column_first">
                            <fmt:message key="label.text.repeat_password"/> :
                        </Label>
                    </td>
                    <td class="column_second">
                        <input type="password" name="new_repeat_password" class="input" placeholder="<fmt:message key="placeholder.repeat_password"/> " required>
                    </td>
                    <td colspan="2">
                        <button class="save_button" type="submit" name="command" value="update_data_admin"><fmt:message key="button.name.save"/> </button>

                    </td>
                </tr>
        </table>


</form>


    <form method="post" action="uploadServlet" enctype="multipart/form-data">
        <label class="upload_button">

            <div class="example-1">
                <div class="form-group">
                    <label class="label">
                        <i class="material-icons">attach_file</i>
                        <span class="title">Добавить файл</span>
                        <input class="input_file" type="file" accept=".jpg, .jpeg, .png" name="command" value="upload_image" />
                    </label>
                </div>
            </div>
        </label>
        <%--Choose a file: <input type="file" name="command" value="upload_image" />
        <input type="submit" name="command" value="upload_image" />--%>
    </form>
</div>

<label class="report_label">${report} </label>
</body>
</html>
