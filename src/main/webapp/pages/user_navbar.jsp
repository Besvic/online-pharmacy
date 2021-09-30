<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 25.09.2021
  Time: 17:49
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
            <form>
                <ul class="navbar-nav">

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Product</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="submit" name="command" value="go_to_product_list_by_user" class="dropdown-item">Product list</button>
                            <button type="submit" name="command" value="go_to_order_list_by_user" class="dropdown-item">Purchase history</button>

                        </div>
                    </li>

                    <%--<li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Пользователи</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="submit" name="command" value="go_to_user_list" class="dropdown-item">user list</button>
                            <button type="submit" name="command" value="go_to_order_list" class="dropdown-item">check order</button>
                        </div>
                    </li>--%>

                    <li class="nav-item" >
                        <button type="submit" name="command" value="go_to_user_profile" class="nav-link" id="navbarDropdownMenuLink2">Profile</button>
                    </li>

                    <li class="nav-item" >
                        <button type="submit" name="command" value="go_to_user_menu" class="nav-link" id="navbarDropdownMenuLink3">Menu</button>
                    </li>

                    <li class="nav-item" >
                        <button type="submit" name="command" value="go_sign_in" class="nav-link" id="navbarDropdownMenuLink4">Exit</button>
                    </li>
                </ul>
            </form>
        </div>
    </nav>
</div>
