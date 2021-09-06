<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 05.09.2021
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>--%>



<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Вход в личный кабинет</title>
    <link href="../../css/sign_in.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<header>
    <h1 id="Pharmacy" align="center"> Pharmacy </h1>
    <hr>
</header>
<section>
    <form>
        <div class="sign_in">
            <p align="center"><strong>Введите данные:</strong></p>
            <label class="label" for="input_login"><b>E-mail:</b></label>
            <br>
            <input class="input_text" id= "input_login" type="login" name="login" placeholder="E-mail" required>
            <br>
            <br>
            <label class="label" for="input_pass"><b>Пароль:</b></label>
            <br>
            <input class="input_text" id="input_pass" type="password" name="pass" placeholder="Password" required>
            <br>
            <a href="/controller">
                <input class="confirm-button" id="confirm_sign_in" type="button" name="confirm" value="Войти">
            </a>
            <input class="confirm-button" id="confirm_registr" type="button" name="registr" value="Регистрация">

        </div>
    </form>
</section>

</body>
</html>