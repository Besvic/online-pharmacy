<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 20.09.2021
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>



<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>--%>


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
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Препараты</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="submit" name="command" value="go_to_product_list" class="dropdown-item">Список препаратов</button>
                            <button type="submit" name="command" value="go_to_add_product" class="dropdown-item">Добавить препарат</button>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Производители</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="submit" name="command" value="go_to_manufacture_list" class="dropdown-item">Список производителей</button>
                            <button type="submit" name="command" value="go_to_add_manufacture" class="dropdown-item">Добавить производителя</button>
                        </div>
                    </li>

                    <%--
                                                <li class="nav-item">
                                                    <a class="nav-link" href="#">Препараты</a>
                                                </li>--%>

                    <li class="nav-item" >
                        <button type="submit" name="command" value="go_to_user_list" class="nav-link">Пользователи</button>
                    </li>

                    <li class="nav-item" >
                        <button type="submit" name="command" value="go_to_admin_profile" class="nav-link">Профиль</button>
                    </li>



                    <%--<li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Добавить произваодеителя</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="submit" name="command" value="go_to_product_list" class="dropdown-item" href="#">Список препаратов</button>
                            <a class="dropdown-item" href="#">Добавить препарат</a>
                            <a class="dropdown-item" href="#">Добавить производителя</a>
                        </div>
                    </li>--%>

                </ul>

            </form>

        </div>
    </nav>
</div>