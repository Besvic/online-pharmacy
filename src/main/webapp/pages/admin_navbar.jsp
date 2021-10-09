<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 20.09.2021
  Time: 16:37
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

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Препараты</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="submit" name="command" value="go_to_product_list" class="dropdown-item">Список препаратов</button>
                            <button type="submit" name="command" value="go_to_add_product" class="dropdown-item">Добавить препарат</button>
                            <button type="submit" name="command" value="go_to_delete_product_list" class="dropdown-item">История удалений</button>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Pharmacy</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="submit" name="command" value="go_to_pharmacy_list" class="dropdown-item">pharmacy list</button>
                            <button type="submit" name="command" value="go_to_add_pharmacy" class="dropdown-item">add pharmacy</button>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Пользователи</a>
                        <div  id="dropdown-item" class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="submit" name="command" value="go_to_user_list" class="dropdown-item">user list</button>
                            <button type="submit" name="command" value="go_to_delete_user_list" class="dropdown-item">del user list</button>
                            <button type="submit" name="command" value="go_to_order_list" class="dropdown-item">check order</button>
                        </div>
                    </li>

                    <li class="nav-item" >
                        <button type="submit" name="command" value="go_to_admin_profile" class="nav-link" id="navbarDropdownMenuLink4">Profile</button>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Come back</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="submit" name="command" value="go_to_admin_menu" class="dropdown-item">Menu</button>
                            <button type="submit" name="command" value="go_sign_in" class="dropdown-item">sign in</button>
                        </div>
                    </li>

                    <li>
                        <button type="submit" name="command" value="change_locale" class="nav-link" id="navbarDropdownMenuLink7">RU/EN</button>
                    </li>
                </ul>

            </form>

        </div>
    </nav>
</div>