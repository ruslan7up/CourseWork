<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 01.10.2016
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script scr="/resources/js/jquery-3.1.0.min.js"></script>
    <title>Склад</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/jquery-3.1.0.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
</head>
<script>
    function getAllGoods() {
        $.ajax(
                {
                    type:'get',
                    url:'http://localhost:8080/table/goods',
                    success: function (data) {
                        $('#goodstable').html(data);
                    }
                }
        )
    }
</script>
<body>
<div style="text-align: center;">
<button onclick="getAllGoods()" >LOAD ALL GOODS</button>
<form action="/goods/goodsPanel" method="get" style="display: inline-block;">
<select name="selectsort">
    <option disabled>Сортировать по....</option>
    <option value="byid">По ID</option>
    <option value="byiddesc">По ID (В обратном)</option>
    <option value="byname">По Имени</option>
    <option value="bynamedesc">По Имени (В обратном)</option>
    <option value="byquantity">По Количеству</option>
    <option value="byquantitydesc">По Количеству(В обратном)</option>
</select>
    <button type="submit" >Сортировать</button>
</form>
    <form action="/goods/goodsPanel" method="get" style="display: inline-block;">
        <input type="number" required placeholder="ID">
        <button type="submit">Искать</button>
    </form>
    <form action="/goods/goodsPanel" method="get" style="display: inline-block;">
        <input type="text" required placeholder="NAME">
        <button type="submit">Искать</button>
    </form>
</div>
<div class="table-responsive">
<table class="table table-hover" style="width: 70%; margin: auto; border-radius: 15px; border-style:solid;">
    <thead>
    <tr>
        <th>#</th>
        <th>Категория</th>
        <th>Название товара</th>
        <th>Количество товара</th>
        <th>Розничная цена</th>
        <th>Оптовая цена</th>
    </tr>
    </thead>
    <tbody id="goodstable">
    <tr>
        <th>1</th>
        <td>TESTCATEGORY</td>
        <td>TESTNAME</td>
        <td>TESTQUANTITY</td>
        <td>TESTRETAILPRICE</td>
        <td>TESTWHOLESALEPRICE</td>
    </tr>
    </tbody>
</table>
</div>
</body>
</html>


