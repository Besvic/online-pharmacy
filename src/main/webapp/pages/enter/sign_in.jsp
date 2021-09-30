<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="title.sign_in"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sign_in.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/registration.css" rel="stylesheet" <%--type="text/css"--%>>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
<%--            <img src="${pageContext.request.contextPath}/css/image/sign_in.jpg" alt="fon" style="width:100%;">--%>
            <div class="tab" role="tabpanel">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#Section1" aria-controls="home" role="tab" data-toggle="tab"><fmt:message key="title.sign_in"/></a></li>
                    <li role="presentation"><a href="#Section2" aria-controls="profile" role="tab" data-toggle="tab"><fmt:message key="title.registration"/> </a></li>
                </ul>
                <!-- Tab panes -->
                <%--Section sign in--%>
                <div class="tab-content tabs">
                    <div role="tabpanel" class="tab-pane fade in active" id="Section1">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/controller">
                            <div class="form-group">
                                <label for="exampleInputEmail1"> <fmt:message key="label.text.Enter_email"/> </label>
                                <input type="email" name="email" placeholder="<fmt:message key="placeholder.E-mail"/>" required class="form-control" id="exampleInputUserName" >
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1"><fmt:message key="label.text.Enter_password"/> </label>
                                <input type="password" name="password" placeholder="<fmt:message key="placeholder.password"/>" required class="form-control" id="exampleInputPassword" >
                            </div>

                            <div class="form-group">
                                <button type="submit" name="command" value="confirm_sign_in" class="btn btn-default"> <fmt:message key="button.name.sign_in"/> </button>
                            </div>
                        </form>
                    </div>
                        <%--registration section--%>
                    <div role="tabpanel" class="tab-pane fade" id="Section2">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="exampleInputEmail1"> <fmt:message key="label.text.Enter_name"/> </label>
                                <input type="text" name="name" placeholder="<fmt:message key="placeholder.name"/> " required class="form-control" id="exampleInputName1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1"><fmt:message key="label.text.Enter_email"/></label>
                                <input type="email"  name="email" placeholder="<fmt:message key="placeholder.E-mail"/> " required class="form-control" id="exampleInputEmail1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1"><fmt:message key="label.text.Enter_password"/> </label>
                                <input type="password" name="password" placeholder="<fmt:message key="placeholder.password"/> " required class="form-control" id="exampleInputPassword1">
                            </div>
                            <%--checkBock--%>
                            <div class="form-group">
                                <div class="main-checkbox">
                                    <input id="is_admin" value="none" name="is_admin" type="checkbox" >
                                    <label for="is_admin"><fmt:message key="label.text.admin_authorisation"/> </label>
                                </div>
                                <%--<span class="text">Admin</span>--%>
                            </div>
                            <div class="form-group">
                                <button type="submit" name="command" value="confirm_registration" class="btn btn-default"><fmt:message key="button.name.registration"/> </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div><!-- /.col-md-offset-3 col-md-6 -->
    </div><!-- /.row -->
</div><!-- /.container -->



</body>
</html>




