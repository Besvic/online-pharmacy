<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 25.09.2021
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content"/>
<div class="logo">

    <img class="graphic_logo" src="${pageContext.request.contextPath}/css/image/logo.png" alt="Logo" height="90px">
    <nav class="navbar navbar-expand-lg navbar-light" >

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown" >
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <ul class="navbar-nav">

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Product</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="submit" name="command" value="go_to_product_list_by_user" class="dropdown-item"><fmt:message key="label.navbar.product_list"/> </button>
                            <button type="submit" name="command" value="go_to_order_list_by_user" class="dropdown-item"><fmt:message key="label.navbar.check_order"/> </button>

                        </div>
                    </li>

                    <li class="nav-item" >
                        <button type="submit" name="command" value="go_to_user_profile" class="nav-link" id="navbarDropdownMenuLink2"><fmt:message key="label.navbar.profile"/> </button>
                    </li>

                    <li>
                        <button type="submit" name="command" value="go_to_add_cash" class="nav-link" id="navbarDropdownMenuLink5"><fmt:message key="label.navbar.add_cash"/> </button>
                    </li>


                   <%-- <li class="nav-item" >
                        <button type="submit" name="command" value="go_to_user_menu" class="nav-link" id="navbarDropdownMenuLink3">Menu</button>
                    </li>

                    <li class="nav-item" >
                        <button type="submit" name="command" value="go_sign_in" class="nav-link" id="navbarDropdownMenuLink4">Exit</button>
                    </li>--%>



                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="label.navbar.exit"/> </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="submit" name="command" value="go_to_user_menu" class="dropdown-item"><fmt:message key="label.navbar.menu"/> </button>
                            <button type="submit" name="command" value="go_sign_in" class="dropdown-item"><fmt:message key="label.navbar.sign_out"/> </button>
                        </div>
                    </li>

                    <li>
                        <button type="submit" name="command" value="change_locale" class="nav-link" id="navbarDropdownMenuLink7"><fmt:message key="label.navbar.change.locale"/> </button>
                    </li>

                    <li>
                        <label  class="nav-link" id="navbarDropdownMenuLink6"><fmt:message key="label.text.cash"/>: ${sessionScope.user.cash}BYR</label>
                    </li>


                </ul>
            </form>
        </div>
    </nav>
</div>
