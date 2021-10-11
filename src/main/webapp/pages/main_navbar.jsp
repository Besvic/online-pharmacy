<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 10.10.2021
  Time: 1:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="logo">

    <img class="graphic_logo" src="${pageContext.request.contextPath}/css/image/logo.png" alt="Logo" height="90px">
    <nav class="navbar navbar-expand-lg navbar-light" >

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown" >
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <ul class="navbar-nav">

                    <li>
                        <button type="submit" name="command" value="go_sign_in" class="nav-link" id="navbarDropdownMenuLink5">sign in</button>
                    </li>

                    <li>
                        <button type="submit" name="command" value="change_locale" class="nav-link" id="navbarDropdownMenuLink7">RU/EN</button>
                    </li>

                </ul>

            </form>

        </div>
    </nav>
</div>