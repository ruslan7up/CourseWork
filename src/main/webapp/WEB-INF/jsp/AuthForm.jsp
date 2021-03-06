<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12.09.2016
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="/resources/images/icon2.ico" type="image/ico">

    <title>Авторизация</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/signin.css" rel="stylesheet">

</head>

<body>
<!-- Начало блока авторизации -->
<div class="container">

    <form class="form-signin" action="/users/authPage" method="post">
        <h2 class="form-signin-heading">Пожалуйста авторизуйтесь</h2>
        <label for="user"  class="sr-only">Логин</label>
        <input type="text" id="user" name="user" class="form-control" placeholder="Логин" required autofocus>
        <label for="pass" class="sr-only">Пароль</label>
        <input type="password" id="pass" name="pass" class="form-control" placeholder="Пароль" required>
        <label for="button" style="color: red">${authresult}</label>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="button">Войти</button>
    </form>
</div>
<!-- Конец блока авторизации -->

</body>
</html>