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
<script scr="/resources/js/jquery-3.1.0.min.js"></script>
<html lang="en">
<head>
    <title>DEBUG</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/jquery-3.1.0.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <style>
        .col_01 img {width:100%;}
        .div1 {display:inline-block}
    </style>
</head>
<body>
<div class="div1">
<form action="/admin/allPanels" method="get">
    <input type="text" id="id" placeholder="ID" required name="byid">
    <button type="submit">SORT</button>
</form>
    </div>
    <div class="div1">
<form action="/admin/allPanels" method="get">
    <input type="text" id="login" placeholder="LOGIN" required name="byname">
    <button type="submit">SORT</button>
</form>
</div>
<hr>
<h1>USERS</h1>
<div class="col_01">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Password(MD5)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="acc" items="${accounts}">
            <tr>
                <td>${acc.getId()}</td>
                <td>${acc.getLogin()}</td>
                <td>${acc.getPass()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<hr>
<h1>GOODS</h1>
<div class="col_01">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>CATEGORY</th>
            <th>NAME</th>
            <th>QUANTITY</th>
            <th>RETAIL PRICE</th>
            <th>WHOLESALE PRICE</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="good" items="${goods}">
            <tr>
                <td>${good.getId()}</td>
                <td>${good.getCategory()}</td>
                <td>${good.getName()}</td>
                <td>${good.getQuantity()}</td>
                <td>${good.getRetailPrice()}</td>
                <td>${good.getWholesalePrice()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<hr>
</body>
</html>

