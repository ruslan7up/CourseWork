<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 15.09.2016
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Панель администратора</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <style type="text/css">
        .bs-example{
            margin: 20px;
        }
        CSS

        .parent
        {
            border: 1px solid black;
            height: 200px;
        }
        .child
        {
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="parent">
    <div class="child">
        <form action="/users/adminPanel">
            <select name="sort">
                <option name="sortbyID">Сортировать по ID</option>
                <option name="sortbyLogin">Сортировать по Логину</option>
            </select>
            <button type="submit">SORT</button>
        </form>
    </div>

    <div class="child">
        <form action="/users/adminPanel">
            <input type="text" id="id" placeholder="SEARCH BY ID" name="byid" required>
            <button type="submit">SEARCH</button></form>
    </div>
    <div class="child">
        <form action="/users/adminPanel">
            <input type="text" id="name" placeholder="SEARCH BY NAME" name="byname" required>
            <button type="submit">SEARCH</button>
        </form>
    </div>
    <div class="child">
        <button name="get">POST</button>
    </div>
    <div class="child">
        <button name="get" onclick="">GET</button>
    </div>
    <div class="child">
        <input id="resultat" name="test">
    </div>
    <div id="result">

    </div>
<hr>
</div>
<div class="bs-example">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Логин</th>
            <th>Пароль (MD5)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td>${account.getId()}</td>
                <td>${account.getLogin()}</td>
                <td>${account.getPass()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<hr>
</body>
</html>